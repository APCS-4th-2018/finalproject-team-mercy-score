import javafx.animation.KeyFrame;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.text.*;
import java.util.concurrent.TimeUnit;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;


public class Game extends Application {
    int x = 0;
    int total = 1000;
    boolean shoot, block, reload, shoot2, block2, reload2;
    Move myMove;
    boolean hasMoved = false;
    boolean enemyMoved = false;
    Backend myBackend = new Backend(5);
    TextField txtEntry;
    GridPane stg;
    Stage ststage;

 


    boolean press = false;
    @Override
    public void start(Stage myStage) {
        
        //create canvas and sets the background

        Pane canvas = new Pane();
        BackgroundImage myBI= new BackgroundImage(new Image("assets/background.jpg",800,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);//background image for the game

        canvas.setBackground(new Background(myBI));
        Scene scene = new Scene(canvas, 800, 600);

        Image hero = new Image("assets/hero.png");//player and the enemy
        Image nonhero = new Image("assets/enemy.png");
       
        //player stats
        Text t = new Text(10, 50, "Your Lives: " + myBackend.getPlayer().getLives());
        //Text t = new Text(10, 50, "Your Lives: ");
        t.setFont(new Font(20));

        Text x = new Text(10, 80, "Your Ammo: "+myBackend.getPlayer().getBullets());
        x.setFont(new Font(20));

        Text y = new Text(10, 110, "Your Blocks: " + myBackend.getPlayer().getBlockCounter());
        y.setFont(new Font(20));
        

        //enemy stats
        Text et = new Text(640, 50, "Enemy Lives: " + myBackend.getCPU().getLives());
        et.setFont(new Font(20));

        Text ex = new Text(640, 80, "Enemy Ammo: " + myBackend.getCPU().getBullets());
        ex.setFont(new Font(20));

        Text ey = new Text(640, 110, "Enemy Blocks: "+myBackend.getCPU().getBlockCounter());
        ey.setFont(new Font(20));
        
        Text ep= new Text(670, 400, myBackend.getCPU().getName());
        ep.setFont(new Font(15));
        
        Text roundDisplay = new Text(390, 50, "Round: " + myBackend.getCurrentRound());
        roundDisplay.setFont(new Font(20));

        ImageView player = new ImageView();
        player.setImage(hero);
        player.relocate(0, 400);

        ImageView enemy = new ImageView();
        enemy.setImage(nonhero);
        enemy.relocate(730, 435);

        canvas.getChildren().add(player);
        canvas.getChildren().add(enemy);
        canvas.getChildren().add(t);
        
        canvas.getChildren().add(x);
        canvas.getChildren().add(y);
        canvas.getChildren().add(et);
        canvas.getChildren().add(ex);
        canvas.getChildren().add(ey);
        canvas.getChildren().add(ep);
        canvas.getChildren().add(roundDisplay);
        
        //sorry for the terrible switch case format in advance
        //input controller
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    switch (event.getCode()) {
                        case LEFT: myMove = new Move(3); hasMoved = true;//the move is a block
                        if ( myBackend.getPlayer().isMoveAllowed(new Move(3)) )
                        {
                            
                            myBackend.setMoves(myMove);
                            myBackend.update();
                            playerBlockAnimation(canvas, player); 
                        }break;
                        
                        case RIGHT: myMove = new Move(2); hasMoved = true; //the move is shoot
                        if ( myBackend.getPlayer().isMoveAllowed(new Move(2)) )
                        {
                            myBackend.setMoves(myMove);
                           myBackend.update();
                            playerShootAnimation(canvas, player); 
                        }break;
                        
                        case UP: myMove = new Move(1); hasMoved = true; //the move is block
                        if ( myBackend.getPlayer().isMoveAllowed(new Move(1)) )
                        {
                            myBackend.setMoves(myMove);
                            myBackend.update();
                            playerReloadAnimation(canvas, player); 
                        } break;
                    }
                    
                    //same thing as above except with the CPU
                    if (myBackend.getCPU().getMove().getType() == 3)
                    {
                        enemyMoved = true;
                        enemyBlockAnimation(canvas, enemy);
                    }
                    
                    if (myBackend.getCPU().getMove().getType() == 2)
                    {
                        enemyMoved = true;
                        enemyShootAnimation(canvas, enemy);
                    }
                    
                    
                    if (myBackend.getCPU().getMove().getType() == 1)
                    {
                        enemyMoved = true;
                        enemyReloadAnimation(canvas, enemy);
                    }
                    
                }
            });

        AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {

                        //myBackend.setMoves(myMove);
                        //myBackend.update();
                        
                        et.setText("Enemy Lives: " + myBackend.getCPU().getLives());
                        ex.setText("Enemy Ammo: " + myBackend.getCPU().getBullets());
                        ey.setText("Enemy Blocks: "+myBackend.getCPU().getBlockCounter());
                        t.setText("Your Lives: " + myBackend.getPlayer().getLives());
                        x.setText("Your Ammo: "+myBackend.getPlayer().getBullets());
                        y.setText("Your Blocks: " + myBackend.getPlayer().getBlockCounter());
                        roundDisplay.setText("Round: " + myBackend.getCurrentRound());
                        
                        if (myBackend.getPlayer().isDead())
                        {
                            canvas.getChildren().clear();
                            Text ded = new Text(320, 300, "You Died");
                            ded.setFont(new Font(40));
                            ded.setFill(Color.RED);
                            canvas.getChildren().add(ded);
                            
                        }
                        
                        if (myBackend.getCPU().isDead() && !myBackend.isMoreWaves())
                        {
                            canvas.getChildren().clear();
                            Text ded = new Text(320, 300, "You Won");
                            ded.setFont(new Font(40));
                            canvas.getChildren().add(ded);   
                        }
                        
                        hasMoved = false;
  
                    
                        if (enemyMoved == true)
                        {
                            enemyMoved = false;
                        }

                }
            };
        timer.start();

        myStage.setTitle("Bullet Test");
        myStage.setScene(scene);
        myStage.show();
        
          
    }

    public static void playerShootAnimation(Pane canvas, ImageView target)

    {
        target.setImage(new Image("assets/heroShoot.png"));
        Circle ball = new Circle(5, Color.BLACK);
        ball.relocate(50, 440);

        canvas.getChildren().add(ball);
        Bounds bounds = canvas.getBoundsInLocal();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), 
                    new KeyValue(ball.layoutXProperty(), (bounds.getMaxX()-30)-ball.getRadius())));
        timeline.play();
        timeline.setOnFinished(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent arg0) {
                    canvas.getChildren().remove(ball);
                    target.setImage(new Image("assets/hero.png"));
                }
            });

    }

    public static void enemyShootAnimation(Pane canvas, ImageView target)
    {
        target.setImage(new Image("assets/enemyShoot.png"));
        Circle ball = new Circle(5, Color.BLACK);
        ball.relocate(750, 460);
        
        canvas.getChildren().add(ball);
        Bounds bounds = canvas.getBoundsInLocal();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), 
                    new KeyValue(ball.layoutXProperty(), 20)));

        timeline.play();
                timeline.setOnFinished(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent arg0) {
                    canvas.getChildren().remove(ball);
                    target.setImage(new Image("assets/enemy.png"));
                }
            });
        
    }
    
    public static void playerBlockAnimation(Pane canvas, ImageView target)
    {
        target.setImage(new Image("assets/heroBlock.png"));
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1)));

        timeline.play();
                timeline.setOnFinished(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent arg0) {
                    target.setImage(new Image("assets/hero.png"));
                }
            });
    }
    
    public static void enemyBlockAnimation(Pane canvas, ImageView target)
    {
        target.setImage(new Image("assets/enemyBlock.png"));
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1)));

        timeline.play();
                timeline.setOnFinished(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent arg0) {
                    target.setImage(new Image("assets/enemy.png"));
                }
            });
    }
    
    public static void playerReloadAnimation(Pane canvas, ImageView target)
    {
        target.setImage(new Image("assets/heroReload.png"));
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1)));

        timeline.play();
                timeline.setOnFinished(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent arg0) {
                    target.setImage(new Image("assets/hero.png"));
                }
            });
    }
        
    public static void enemyReloadAnimation(Pane canvas, ImageView target)
    {
        target.setImage(new Image("assets/enemyReload.png"));
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1)));

        timeline.play();
                timeline.setOnFinished(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent arg0) {
                    target.setImage(new Image("assets/enemy.png"));
                }
            });
    }


    // public static void displayEnemyDamage(ImageView target) {
        // Image og = new Image("http://icons.iconarchive.com/icons/raindropmemory/legendora/64/Hero-icon.png");
        // Image hurt = new Image("https://i.imgur.com/znzKcj0.png");
        // target.setImage(hurt);  

        // target.setImage(og);
    // }

    public static void main(String[] args) {
        launch();
    }
    
    private void goButtonClick(ActionEvent event)
    {

        // Returns the value in the textfield
        ((Text)stg.getChildren().get(5)).setText("Waves bruh");
        int ans = Integer.parseInt(txtEntry.getText());
        Text weDone = new Text(ans+" waves set");
        weDone.setFill(Color.WHITE);
        ststage.show();
        //Pause for a second
        
        try 
        {
            Thread.sleep(600);
        }
        catch(InterruptedException e) {}
        
        //Close the grid
        ststage.close();
    }
    

    
}

