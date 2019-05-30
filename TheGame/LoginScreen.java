
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.text.Text;

/**
 * Start screen for the game
 *
 * @author Team Mercy Score
 * @version 5-28-2019
 */
public class LoginScreen extends Application
{
    private int numWaves;
    private TextField txtEntry;
    private GridPane g;
    
    public LoginScreen()
    {
        numWaves = 0;
    }
    
    public void start(Stage myStage) throws Exception
    {
        GridPane grid = new GridPane();
        //grid.setHgap(300);
        grid.setAlignment(Pos.CENTER);
        //grid.setVgap(300);
        grid.setGridLinesVisible(true);
        g = grid;
        
        //Show credits
        Text credits = new Text("TEAM MERCY SCORE");
        credits.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(credits, 0, 0);
        
        
        //Prompt for number of waves
        Text numWavesPrompt = new Text("Enter number of waves");
        numWavesPrompt.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(numWavesPrompt, 0, 1);
        
        //Get user input
        TextField userTextField = new TextField();
        grid.add(userTextField, 0, 2);
        txtEntry = userTextField;
        
        //GO Button
        Button goBtn = new Button("GO!");
        grid.add(goBtn, 0,3);
        goBtn.setOnAction(this::buttonClick);
        
        Text weDone1 = new Text("");
        g.add(weDone1,0, 4);
        
        //Show it
        Scene myScene = new Scene(grid, 500, 500);
        myStage.setScene(myScene);
        myStage.show();
        
    }
    
    
     /**
     * This will be executed when the button is clicked
     * It increments the count by 1
     */
    private void buttonClick(ActionEvent event)
    {

        // Returns the value in the textfield
        g.getChildren().remove(5);
        int ans = Integer.parseInt(txtEntry.getText());
        Text weDone = new Text(ans+" waves set");
        g.add(weDone,0, 4);
    }
    

}
