import javafx.animation.KeyFrame;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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

public class Game extends Application {
    int x = 0;
    int total = 1000;
    boolean shoot, block, reload, shoot2, block2, reload2;
    Move myMove;
    boolean hasMoved = false;
    Backend myBackend = new Backend(5);
    int currentRound = 1;
    


    Node hero;
    boolean press = false;
    @Override
    public void start(Stage stage) {

        //create canvas and sets the background

        Pane canvas = new Pane();
        BackgroundImage myBI= new BackgroundImage(new Image("https://cdn.dribbble.com/users/705826/screenshots/2680904/_______.jpg",800,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        canvas.setBackground(new Background(myBI));
        Scene scene = new Scene(canvas, 800, 600);

        Image hero = new Image("http://icons.iconarchive.com/icons/raindropmemory/legendora/64/Hero-icon.png");


        //player stats
        Text t = new Text(10, 50, "Your Lives: " + myBackend.getPlayer().getLives());
        //Text t = new Text(10, 50, "Your Lives: ");
        t.setFont(new Font(20));

        Text x = new Text(10, 80, "Your Ammo: "+myBackend.getPlayer().getBullets());
        x.setFont(new Font(20));

        Text y = new Text(10, 110, "Your Blocks: " + myBackend.getPlayer().getBlockCounter());
        y.setFont(new Font(20));
        
        //enemy stats
        Text et = new Text(650, 50, "Enemy Lives: " + myBackend.getCPU().getLives());
        et.setFont(new Font(20));

        Text ex = new Text(650, 80, "Enemy Ammo: " + myBackend.getCPU().getBullets());
        ex.setFont(new Font(20));

        Text ey = new Text(650, 110, "Enemy Blocks: "+myBackend.getCPU().getBlockCounter());
        ey.setFont(new Font(20));
        
        Text roundDisplay = new Text(390, 50, "Round: " + currentRound);
        roundDisplay.setFont(new Font(20));

        ImageView player = new ImageView();
        player.setImage(hero);
        player.relocate(0, ((canvas.getHeight())/2) - 40);

        ImageView enemy = new ImageView();
        enemy.setImage(hero);
        enemy.relocate(730, ((canvas.getHeight())/2) - 40);

        canvas.getChildren().add(player);
        canvas.getChildren().add(enemy);
        canvas.getChildren().add(t);
        
        canvas.getChildren().add(x);
        canvas.getChildren().add(y);
        canvas.getChildren().add(et);
        canvas.getChildren().add(ex);
        canvas.getChildren().add(ey);
        
        canvas.getChildren().add(roundDisplay);
        
        //input controller
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    switch (event.getCode()) {
                        case RIGHT: myMove = new Move(3); hasMoved = true; break;
                        case LEFT: myMove = new Move(2); hasMoved = true; break;
                        case UP: myMove = new Move(1); hasMoved = true; break;
                        
                    }
                }
            });

        AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (hasMoved && myBackend.validMove(myMove))
                    {
                        myBackend.setMoves(myMove);
                        myBackend.update();
                        
                        et.setText("Enemy Lives: " + myBackend.getCPU().getLives());
                        ex.setText("Enemy Ammo: " + myBackend.getCPU().getBullets());
                        ey.setText("Enemy Blocks: "+myBackend.getCPU().getBlockCounter());
                        t.setText("Your Lives: " + myBackend.getPlayer().getLives());
                        x.setText("Your Ammo: "+myBackend.getPlayer().getBullets());
                        y.setText("Your Blocks: " + myBackend.getPlayer().getBlockCounter());
                        
                        hasMoved = false;
                    }   
                    
                   

                }
            };
        timer.start();

        stage.setTitle("Bullet Test");
        stage.setScene(scene);
        stage.show();
        
          
    }

    public static void playerShootAnimation(Pane canvas)

    {
        Circle ball = new Circle(5, Color.BLACK);
        ball.relocate(50, (canvas.getHeight())/2);

        canvas.getChildren().add(ball);
        Bounds bounds = canvas.getBoundsInLocal();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), 
                    new KeyValue(ball.layoutXProperty(), (bounds.getMaxX()-30)-ball.getRadius())));
        timeline.play();
        timeline.setOnFinished(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent arg0) {
                    canvas.getChildren().remove(ball);
                }
            });

    }

    public static void enemyShootAnimation(Pane canvas)
    {
        Circle ball = new Circle(5, Color.BLACK);
        ball.relocate(750, (canvas.getHeight())/2);

        canvas.getChildren().add(ball);
        Bounds bounds = canvas.getBoundsInLocal();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), 
                    new KeyValue(ball.layoutXProperty(), 20)));

        timeline.play();
                timeline.setOnFinished(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent arg0) {
                    canvas.getChildren().remove(ball);
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
    

    
}

