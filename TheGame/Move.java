
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
     * @param  y  another Move to compare to
     * @return    if the Move is equal return true, else return false
     */
    public boolean equals(Move y)
    {
        boolean ans = false;
        if (getType() == y.getType())
            ans = true;
        return ans;
    }
    
    /**
     * Compares the moves to each other based on whether the incoming Move 
     * will beat the current Move
     * 
     * Precondition - the incoming Move type is not equal to the current Move
     * 
     * @param  y  another Move to compare to
     * @return    if the incoming Move beats the current Move, the program
     *            will return a negative value, else it will return positive
     *            if the current Move beats the incoming Move, otherwise
     *            the program will return 0
     */
    public int compareTo(Move y)
    {
        int ret = 0;
        
        //if the current move is doing nothing or reloading, and the incoming
        //move is shooting at you
        if((myType == 1 || myType == 4) && y.getType() == 2)
            ret = -1;
        else
            if((y.getType() == 1 || y.getType() == 4 && myType == 2))
                ret = 1;
        
        return ret;
    }
}
