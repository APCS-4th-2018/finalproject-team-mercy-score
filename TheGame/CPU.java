
/**
 * Computer Double07 player
 *
 * @author Team Mercy Score
 * @version 5/15/2019
 */
public class CPU extends Double07
{
    /**
     * Sets the current move to whatever based on logic
     */
    public void setMove(Double07 opp)
    {
        //This random double helps us make a decision using chance
        double myRandomVar = Math.random();
        //probability that we are gonna shoot
        double triggerHappy =   1-opp.getBlockCounter()/(MAXBLOCKS + 1.00 + getBullets())  ;
        //Probability of blocking
        double willIBlock;
        Move temp, ans;
        
        //If the random process selects for a shoot and it is allowed, do it
        temp  = new Move(2);
        if (  myRandomVar < triggerHappy && isMoveAllowed(temp)  )
            ans = temp;
        //Otherwise, choose between blocking and reloading
        else
        {
            //Reload the random variable to avoid possible problems
            myRandomVar = Math.random();
            //Use a formula to calculate the probability of blocking
            //The probability of blocking is related to CPU number of block counters
                //and the opponents number of bullets
            willIBlock = 1.000 - (3.00/(3.00+ ( getBlockCounter() )*( Math.log(opp.getBullets()) )  ));
            //If the random variable falls in the right range for a block
            if(myRandomVar < willIBlock)
            {
                //If the block is allowed
                if(isMoveAllowed(new Move(3)))
                    ans = new Move(3);
                //If the block isn't allowed, we gotta do a reload
                else
                    ans = new Move(1);
            }
            else
            //The last choice is doing a reload
                ans = new Move(1);
        }
        //Load the found move in currentMove
        currentMove = ans;
        dealWithCounters(currentMove.getType());
    }
}
