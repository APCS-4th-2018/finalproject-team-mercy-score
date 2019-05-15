
/**
 * General class for player objects of the game, whether they are CPU or player.
 *
 * @author Team Mercy Score
 * @version 5-13-2019
 */
public class Double07
{
    // instance variables - replace the example below with your own
    private int numBullets;
    private int numLives;
    private Move currentMove;

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
        
        if(x.getType() == 2 && numBullets <= 0)
            ret = false;
        //add in more stuff if we want to have a set amount of blocks
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
}
