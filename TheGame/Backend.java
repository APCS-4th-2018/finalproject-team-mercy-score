
/**
 * Write a description of class theGame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class Backend
{
    private Player user;
    private CPU fighter;
    private ArrayList<CPU> cpus;
    
    public Backend(int numWaves)
    {
        double difficulty;
        cpus = new ArrayList<CPU>();
        
        
        for(int count = 0; count < numWaves; count++)
        {
            difficulty = (count)*10.0/numWaves + 1.0;
            
            cpus.add(new CPU(difficulty));
        }
        
        user = new Player();
        fighter = cpus.remove(0);

    }
    
    /**
     *  @return  Returns -1 if no player is dead
     *      0 if player 1 is dead and player 2 is alive
     *      1 if player 1 is alive and player 2 is dead
     *      2 if both players dead
     */
    private boolean deadPlayer()
    {
        boolean ans = false;
        if (user.isDead())
            ans = true;
        return ans;
    }
    public void nextWave()
    {
        
        if(fighter.isDead())
        {
            fighter = cpus.remove(0);
            //Increment lives by 2
            fighter.setLives(fighter.getLives() + 2);
            //Increment block counters
            fighter.setBlockCounter(fighter.getBlockCounter()+1);
        }

        
    }
    
    public boolean isMoreWaves()
    {
        boolean ans = true;
        if (cpus.size() == 0)
            ans = false;
        return ans;
    }
    
    public void setMoves(Move x)
    {
        user.setMove(x);
        fighter.setMove(user);
    }
    public void update()
    {
        Double07.compareMoves(user, fighter);
        nextWave();
    }

    public Player getPlayer()
    {
        return user;
    }    

    public CPU getCPU()
    {
        return fighter;
    }
    
    public boolean validMove(Move x)
    {
        boolean ans = false;
        if (user.isMoveAllowed(x))
            ans = true;
        return ans;
    }
}
