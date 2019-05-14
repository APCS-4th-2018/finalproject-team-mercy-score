
/**
 * Object for a Move that the player makes
 *
 * @author Team Mercy Score
 * @version 5-14-2019
 */
public class Move
{
    // 1 is for reload
    // 2 is to shoot
    // 3 is to block
    //4 is for nothing
    private int myType;

    /**
     * Constructor for objects of class Move
     */
    public Move(int type)
    {
        // initialise instance variables
        myType = type;
    }

    /**
     * Accessor for the type of the move
     * 
     * @return int type  - type of move this is
     */
    public int getType()
    {
        return myType;
    }
    
    /**
     * Tells whether one move is equal to another
     *
     * @param  y  another move to compare to
     * @return    if the move is equal return true, else return false
     */
    public boolean equals(Move y)
    {
        boolean ans = false;
        if (getType() == y.getType())
            ans = true;
        return ans;
    }
    
    /**
     * Compa
     */
}
