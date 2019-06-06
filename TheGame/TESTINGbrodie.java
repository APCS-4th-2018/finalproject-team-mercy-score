
/**
 * Write a description of class TESTINGbrodie here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Timer;
import java.util.TimerTask;

public class TESTINGbrodie
{
    int x = 0;
    int total = 1000;
    
    public static void main(String[] args) {
        Timer timer;
        
        timer = new Timer();
        
        int delay = 1000, period = 100;
        
        for(int cv = 5; cv >= 1; cv--)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
            }
            
            System.out.println(cv);
        }
        
        // timer.scheduleAtFixedRate(new TimerTask() {
            // public void run() {
                // getX();
                // check();
            // }
        // }, delay, period);
    }
        
        
        
}
