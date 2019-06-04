
/**
 * Write a description of class theGame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class theGame
{
    private Player[] users;
    private CPU[] fighters;
    private ArrayList<CPU> cpus;
    
    public theGame(int numWaves)
    {
        double difficulty;
        
        for(int count = 0; count < numWaves; count++)
        {
            difficulty = (count)*10.0/numWaves + 1.0;
            
            cpus.add(new CPU(difficulty));
            cpus.add(new CPU(difficulty));
        }
        
        users = new Player[2];
        fighters = new CPU[2];
        fighters[0] = cpus.remove(0);
        fighters[1] = cpus.remove(0);
    }
    
    public void setUserMoves(Move player1, Move player2)
    {
        fighters[0].setMove(users[0]);
        fighters[1].setMove(users[1]);
        users[0].setMove(player1);
        users[1].setMove(player2);
    }
    
    /**
     *  @return  Returns -1 if no player is dead
     *      0 if player 1 is dead and player 2 is alive
     *      1 if player 1 is alive and player 2 is dead
     *      2 if both players dead
     */
    private int deadPlayers()
    {
        int ans;
        if ( !users[0].isDead() && !users[1].isDead() )
            ans = -1;
        else
            if (users[0].isDead() && users[1].isDead())
                ans = 2;
            else
                if (users[0].isDead())
                    ans= 0;
                else
                    ans = 1;
        return ans;
    }
    public boolean nextWave()
    {
        boolean ret = false;
        
        if(fighters[0].isDead() && fighters[1].isDead())
        {
            fighters[0] = cpus.remove(0);
            fighters[1] = cpus.remove(0);
            ret = true;
        }
        
        return ret;
    }

    public void update()
    {
 
    }

    public Player[] getPlayers()
    {
        return users;
    }    

    public CPU[] getCPUs()
    {
        return fighters;
    }
}
