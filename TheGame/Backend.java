/**
 * Write a description of class theGame here.
 *
 * @author Team Mercy Score
 * @version DDAY
 */
import java.util.*;
public class Backend
{
    private Player user;
    private CPU fighter;
    private ArrayList<CPU> cpus;
    private ArrayList<String> names;
    private int currentRound;
    /**
     * Initializes all of the variables, the waves, the amount of CPU's, their names, and the difficulties
     * 
     * @param int numWaves - number of waves for the game
     */
    public Backend(int numWaves)
    {
        double difficulty;
        int x;
        cpus = new ArrayList<CPU>();
        names = new ArrayList<String>();
        //Add some names
        names.add("Billy the Kidd");//all of the names to be used for the CPUs
        names.add("Homeless Joe");
        names.add("L0g1ck4l Lantsborg");
        names.add("Auspicious Andy");
        names.add("Vagabond Vinod");
        names.add("Ashy Abhinav");
        names.add("Gassy Gilbert");
        names.add("Janky James");
        names.add("Dr. Oz");
        names.add("Lazy Larry");
        names.add("Powerful Alex Jones");
        names.add("Sickly Steve");
        names.add("Dangerous Deandre");
        names.add("Crazy Carl");
        
        currentRound = 1;//starting round
        
        for(int count = 0; count < numWaves; count++)//giving the difficulty to the CPUs& the names
        {
            difficulty = (count)*10.0/numWaves + 1.0;
            
            cpus.add(new CPU(difficulty));
            x = (int)(Math.random()*names.size());
            cpus.get(count).changeName(names.get(x));
        }
        
        user = new Player();
        fighter = cpus.remove(0);
        //Select a random name
        //x = (int)(Math.random()*names.size());
        //fighter.changeName(names.get(x));

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
    
    /**
     * Starts the next wave/round by making a new CPU and incrementing all of the necessary counters
     */
    public void nextWave()
    {
        if(fighter.isDead())
        {
            fighter = cpus.remove(0);
            //int x = (int)(Math.random()*names.size());
            //fighter.changeName(names.get(x));
            //Increment lives by 2
            user.setLives(user.getLives() + 2);
            //Increment block counters
            user.setBlockCounter(user.getBlockCounter()+1);
            currentRound++;
        }
    }
    
    /**
     * This method checks to see if there are any more possible waves/rounds
     * 
     * @return boolean - Returns true if there are more waves left, and false if there aren't anymore
     */
    public boolean isMoreWaves()
    {
        boolean ans = true;
        if (cpus.size() == 0)
            ans = false;
        return ans;
    }
    
    /**
     * Sets the moves for both the CPU and the player
     * 
     * @param Move x - Move to be set for the player
     */
    public void setMoves(Move x)
    {
        user.setMove(x);
        fighter.setMove(user);
    }
    
    /**
     * Updates all of the information for the player and the fighter's counters
     */
    public void update()
    {
        Double07.compareMoves(user, fighter);
        nextWave();
    }
    
    /**
     * Returns the player
     * 
     * @return Player - Returns the player that the game has
     */
    public Player getPlayer()
    {
        return user;
    }    
    
    /**
     * Accessor for cpu
     * 
     * @return CPU - returns the current cpu in the game
     */
    public CPU getCPU()
    {
        return fighter;
    }
    
    /**
     * Checks to see if the current Move being passed is valid
     * 
     * @param Move x - Move to be checked if valid for the Player
     * @return boolean - false if the move is not allowed, true if it is
     */
    public boolean validMove(Move x)
    {
        boolean ans = false;
        if (user.isMoveAllowed(x))
            ans = true;
        return ans;
    }
    
    /**
     * Returns the current round
     * 
     * @return int - the current round
     */
    public int getCurrentRound()
    {
        return currentRound;
    }
    
}

