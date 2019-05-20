
/**
 * Write a description of class RandomCPU here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RandomCPU extends CPU
{
    
    public void setMove()
    {
        double chance = Math.random();
        if (chance < 0.333 && isMoveAllowed(new Move(2)))
        {
            currentMove = new Move(2);
        }
        else
        {
            chance = Math.random();
            if(chance < 0.5 && isMoveAllowed(new Move(3)))
                currentMove = new Move(3);
            else
                currentMove = new Move(1);
        }
    }
}
