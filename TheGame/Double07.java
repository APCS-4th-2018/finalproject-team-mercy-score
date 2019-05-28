
/**
 * General class for player objects of the game, whether they are CPU or player.
 *
 * @author Team Mercy Score
 * @version 5-13-2019
 */
public class Double07
{
    // instance variables
    private int numBullets;
    private int numLives;
    protected Move currentMove;
    private int blockCounter;
    protected final int MAXBLOCKS = 10;

    /**
     * Constructor for objects of class Double07
     * Set number of lives 
     */
    public Double07()
    {
        numBullets = 0;
        //U get a random number of lives from 2 to 4
        numLives = (int) (Math.random() * 3 + 2);
        currentMove = new Move(4);
        //U get a random block counter: set it equal to 10
        blockCounter = MAXBLOCKS;
    }

    /**
     * Accessor for number of lives
     *
     * @return    number of lives
     */
    public int getLives()
    {   
        return numLives;
    }
    
    /**
     * Modifier for number of lives
     * 
     * @param new number of lives
     */
    public void setLives(int theNumLives)
    {
        numLives = theNumLives;
    }
    
    /**
     * Accessor for number of bullets
     * @return  number of bullets
     */
    public int getBullets()
    {
        return numBullets;
    }
    
    /**
     * Modifier for number of bullets
     * @param      new number of bullets
     */
    public void setBullets(int newNumBullets)
    {
        //U can't set the numBullets to a negative number
        if (newNumBullets>= 0)
            numBullets = newNumBullets;
    }
    
    /**
     * Tells whether the player is dead
     * @return      true if player is dead, false otherwise
     */
    public boolean isDead()
    {
        boolean ans = false;
        if (getLives() <= 0)
            ans = true;
        return ans;
    }
    
    /**
     * Tells the program whether or not the move being made is valid
     * 
     * @param   x   Move being passed into the program
     * @return      Returns a boolean valued based on if the move is legal
     *              i.e. if the player tries to shoot when he has no bullets
     */
    public boolean isMoveAllowed(Move x)
    {
        boolean ret = true;
        
        //IIf you don't have enough bullets to shoot
        if(x.getType() == 2 && numBullets <= 0)
            ret = false;
        //If you don't have enough block counters to blocc
        if (x.getType() == 3 && getBlockCounter() < 2)
            ret = false;
        return ret;
    }
    
    /**
     * Gives the program the current Move that object is using
     * 
     * @return      Returns the Move object that the Player or CPU is using
     */
    public Move getMove()
    {
        return currentMove;
    }
    
    /**
     * Accessor for the current block counter
     * @return      returns current block counter
     */
    public int getBlockCounter()
    { 
        return blockCounter;
    }
    
    /**
     * Setter for the current block counter
     * @param int newBlockCounter  -  value to set the block counter to
     */
    public void setBlockCounter(int newBlockCounter)
    {
        //You can't set the block counter to past the capacity of bloccs
        if (newBlockCounter < MAXBLOCKS)
            blockCounter = newBlockCounter;

    }
    
    /*
     * Helper method that increments all the counters when a move is set
     * @param int type - the type of the move
     */
    protected void dealWithCounters(int type)
    {
        switch(type)
        {
            //Reloading
            case 1:
                setBlockCounter(getBlockCounter()+1);
                setBullets(getBullets()+1);
                break;
            //Shooting
            case 2:
                setBlockCounter(getBlockCounter()+1);
                setBullets(getBullets()-1);
                break;
            //Bloccing
            case 3:
                setBlockCounter(getBlockCounter()-2);
                break;
            //Doing nothing
            case 4:
                setBlockCounter(getBlockCounter()+1);
        }
    }
}
