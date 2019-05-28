
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    
    public LoginScreen()
    {
        numWaves = 0;
    }
    
    public void start(Stage myStage) throws Exception
    {
        GridPane grid = new GridPane();
        grid.setHgap(100);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(100);
        grid.setGridLinesVisible(true);
        Text credits = new Text("TEAM MERCY SCORE");
        credits.setFontSize(getFontSize()+10);
        grid.add(credits, 0,0);
        //grid.add(new Text("TEAM MERCY SCORE"), 0, 1);
        Scene myScene = new Scene(grid, 500, 500);
        myStage.setScene(myScene);
        myStage.show();
    }
    
    public int getInput()
    {
        return numWaves;
    }
    

}
