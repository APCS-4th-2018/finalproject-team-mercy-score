
/**
 * Write a description of class TimerV here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TimerV
{
    long time1 = System.currentTimeMillis();
        long time2 = time1;
        int count = 5;
        Text timerDisplay = new Text(390, 50, "Timer");
        while (System.currentTimeMillis() - time1 <= 5000 || !hasMoved )
        {
            if (System.currentTimeMillis() - time2 >= 1000)
            {
                time2 = System.currentTimeMillis();
                count--;
                timerDisplay = new Text(390, 50, ""+count);
                timerDisplay.setFont(new Font(20));
                
                canvas.getChildren().add(timerDisplay);
            }
            //Pause to not byrn out the processor
            try
            {
                Thread.sleep(10);
            }
            catch(InterruptedException e)
            {
            }
            
            
        }
        if (hasMoved)
        {
            hasMoved = false;
        }
        else
        {
            //Set player's move to a nothing move
        }

}
