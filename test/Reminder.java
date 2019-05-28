
import java.util.Timer;
import java.util.TimerTask;
/**
 * Write a description of class asdfa here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Reminder {
    Timer timer;

    public Reminder(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
	}

    class RemindTask extends TimerTask 
    {
        private void setMoveHelper(Player x)
        {
            x.setMove(new Move(4));
        }
        
        public void run() {
            System.out.println("Time's up!");
            
            timer.cancel(); //Terminate the timer thread
        }
    }

    public static void main(String args[]) {
        new Reminder(5);
        System.out.println("Task scheduled.");
    }
}


public boolean isGameFinished()
//checks whether the players are the only ones left













