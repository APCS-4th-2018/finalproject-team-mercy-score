
/**
 * Computer Double07 player
 *
 * @author Team Mercy Score
 * @version 5/15/2019
 */
public class CPU extends Double07
{
    private double myDifficulty;
    
    public CPU(double difficulty)
    {
        //Difficulty maxes out at 10
        if (difficulty < 11)
            myDifficulty = difficulty;
        else
            myDifficulty = 10;
    }
    
    /**
     * Sets the move based on the public stats of the opponent for a CPU
     * Based on the difficulty, there's a certain probability that 
     *          it will be set to a "smart" move
     * @param Double07 opp - the opponent you're passing in
    */
    public void setMove(Double07 opp)
    {
        double prob = myDifficulty / 10.0;
        if(Math.random() < prob)
            setMoveSmart(opp);
        else
            setMoveRandom();
    }
    
    /*
     * Sets the current move to whatever based on logic
     * @param Double07 opp - the opponent
     */
    private void setMoveSmart(Double07 opp)
    {
        //This random double helps us make a decision using chance
        double myRandomVar = Math.random();
        //probability that we are gonna shoot
        double triggerHappy =   1-0.6*opp.getBlockCounter()/(MAXBLOCKS + 1.00 + getBullets())  ;
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
            willIBlock = 1.000 - (3.00/(3.00+ ( getBlockCounter() )*( Math.log(opp.getBullets()+1.0) )  ));
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
    
    /*
     * Randomly selects an allowable move
     */
    private void setMoveRandom()
    {
        double chance = Math.random();
        if (chance < 0.333 && isMoveAllowed(new Move(2)))
        {
            currentMove = new Move(2);
        }
        else
        {
            chance = Math.random();
            if(chance < 0.5 && isMoveAllowed(new Move(3)))
                currentMove = new Move(3);
            else
                currentMove = new Move(1);
        }
        dealWithCounters(currentMove.getType());
    }
    
    /**
     * Accessor for the difficulty from 0 to 10
     * @return  double myDifficulty - the decimal value of difficulty
     */
    public double getDifficulty()
    {
        return myDifficulty;
    }
}
