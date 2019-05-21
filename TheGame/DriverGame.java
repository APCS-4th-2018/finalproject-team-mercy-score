//
import chn.util.*;
public class DriverGame
{
    public static void main(String[] args)
    {
        CPU p1 = new CPU();
        Player p2 = new Player();
        int smartWinsCount = 0;
        int moveNum;
        ConsoleIO reader = new ConsoleIO();
        for (int cnt =1; cnt<=1000; cnt++)
        {
            p1 = new CPU();
            p2 = new Player();
            while((!p1.isDead()) && (!p2.isDead()))
            {
                System.out.println("Opponent blocks: " + p1.getBlockCounter());
                System.out.println("Ur blocks: " + p2.getBlockCounter());
                System.out.println("Opponent bullet: " + p1.getBullets());
                System.out.println("ur bullets: " + p2.getBullets());
                System.out.println("Opponent lives: " + p1.getLives());
                System.out.println("Ur lives: " + p2.getLives());
                
                System.out.println("1 to reload, 2 to shoot, 3 to block");
                moveNum = reader.readInt();
                p1.setMove(p2);
                p2.setMove(new Move(moveNum));
                System.out.println("We are " + p2.getMove() + "\nCPU is" + p1.getMove());
                
                if (p1.getMove().compareTo(p2.getMove()) < 0 )
                {
                    p1.setLives(p1.getLives()-1);
                }
                else
                {
                    if (p1.getMove().compareTo(p2.getMove()) > 0)
                        p2.setLives(p2.getLives() - 1);
                }
                
                if (p2.isDead())
                    //smartWinsCount++;
                    System.out.println("CPU won");
                if (p1.isDead())
                    System.out.println("You won");
            }
        }
        System.out.print((double)smartWinsCount / 1000);
    }
}
