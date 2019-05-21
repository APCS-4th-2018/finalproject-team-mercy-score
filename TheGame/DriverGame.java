//

public class DriverGame
{
    public static void main(String[] args)
    {
        CPU p1 = new CPU();
        RandomCPU p2 = new RandomCPU();
        int smartWinsCount = 0;
        for (int cnt =1; cnt<=1000; cnt++)
        {
            p1 = new CPU();
            p2 = new RandomCPU();
            while((!p1.isDead()) && (!p2.isDead()))
            {
                p1.setMove(p2);
                p2.setMove();
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
                    smartWinsCount++;
                    //System.out.println("SmartCPU won");
                //if (p2.isDead())
                    //System.out.println("Not smart CPU won");
            }
        }
        System.out.print((double)smartWinsCount / 1000);
    }
}
