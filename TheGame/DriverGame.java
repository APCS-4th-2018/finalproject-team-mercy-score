

public class DriverGame
{
    public static void main(String[] args)
    {
        CPU p1 = new CPU();
        RandomCPU p2 = new RandomCPU();
        
        while(!p1.isDead() && !p2.isDead())
        {
            p1.setMove(p2);
            p2.setMove();
            
        }
        
    }
}
