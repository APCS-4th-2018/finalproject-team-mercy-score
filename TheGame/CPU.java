
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
        
        temp  = new Move(2);
        if (  myRandomVar < triggerHappy && isMoveAllowed(temp)  )
            ans = temp;
        else
        {
            myRandomVar = Math.random();
            willIBlock = 3.00/(3.00+ ( getBlockCounter() )*( Math.log(opp.getBullets()) )  );
            if(myRandomVar < willIBlock)
            {
                if(isMoveAllowed(new Move(3)))
                    ans = new Move(3);
                else
                    ans = new Move(1);
            }
            else
                ans = new Move(1);
        }
        currentMove = ans;
    }
}
