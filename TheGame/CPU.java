
/**
 * Computer Double07 player
 *
 * @author Team Mercy Score
 * @version 
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
        double triggerHappy = 1 - opp.getBlockCounter()/(MAXBLOCKS + 5.00) ;
        //Probability of blocking
        double willIBlock;
        
        Move temp, ans;
        
        temp  = new Move(2);
        if (  myRandomVar < triggerHappy && isMoveAllowed(temp)  )
            ans = temp;
        else
        {
            myRandomVar = Math.random();
            
            if(myRandomVar < .5)
            {
                if(isMoveAllowed(new Move(3)))
                    ans = new Move(3);
                else
                    ans = new Move(4);
            }
            else
                ans = new Move(1);
        }
        currentMove = ans;
    }
}
