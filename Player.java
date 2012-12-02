/**********************************************************************************************/
/** Class description: Player class which inherits actor class and implements step listener  **/
/** Author: Kenneth Monteiro                                                                 **/
/** Class version: Version 11                                                                **/
/**********************************************************************************************/

/** Import game engine library class's */
import city.soi.platform.*;

import org.jbox2d.common.Vec2;

/** This is a specialised actor class that can be controlled, it counts the lives 
 *  of the player object remaining and counts the amount of stars collected,
 */
public class Player extends Actor implements StepListener
{
   
   
    //no of stars variable declared
    private int stars;
    //no of lives variable declared
    private int lives;
    //stores the score earned by player
    private int score;
    //player facing
    private float tRight;
    private float tLeft;
    //the game class field declared
    private Game game;
     
    /** Initialise a new player @param game  The game in which the player will be playing **/
    public Player(Game game)
    {
      
        super(game.getWorld(), PolygonShape.makeBox(10f, 20f));
        this.game = game;
            
        //initialise stars to 0 and lives to 5 and score to 0
        stars = 0;
        lives = 5;
        score = 0;
        //add step listener to the world object
        getWorld().addStepListener(this);
      
    }
    
  
   
    /** For the prestep of the player the Step listener does nothing */
    public void preStep(StepEvent e) {}
    
     
    /** checks y positions of player and checks for no of lives through the step listener's post step */
    public void postStep(StepEvent fallsOf){
    	
       	 
     
           playerPosLivesMod();
                    
        
    }
    
    /****************************************************************/
    /** 				All Methods in Player class					*/
    /****************************************************************/
    
    /** Player walks left **/
    public void walkLeft(){
    	game.setPlayerFacing(1);
        walkLeft(60);
    	
    }
    
    /** Player walks right **/
    public void walkRight(){
    	game.setPlayerFacing(2);
        walkRight(60);
    	
    }
    
    /** Player jumps up **/
    public void jump(){
    	
    	if (!isJumping()) {
              jump(175);
           }
    	
    }
    
    /** calculates score for player ***/
    public void playerScoreCalc(){
    	
    	//add high score calc for player
    	score = score + (getStars() * 250);
    	
    	
    }
    /** returns the value in score ***/
    public int getScore(){
    	
    	return score;
    	
    }
    /** checks if player has fallen and decreases life if the player has ***/
    public void playerPosLivesMod(){
    	
       if(game.getPlayer().getPosition().y < -300){
            
            game.getPlayer().decrementLivesCount();
            
            if(game.getPlayer().getLives() != 0){
            	 playerLevelPosition();
            }
        
            else {
        	
        	game.gameOver(); 
        	System.out.println("Game Over");
        	
            }
            
        }
    	
    	
    }
    
    /** checks player respawn for each level according to level no **/
    public void playerLevelPosition(){
  	  if(game.getCurrentLevel()==1){
          setPosition(new Vec2(-10, -250));
        } 
  	  else if(game.getCurrentLevel()==2){
            setPosition(new Vec2(-10, -250));
        } 
  	  else if(game.getCurrentLevel()==3){
            setPosition(new Vec2(250, 250));
        } 
  	
  }
  
    /** teleports player **/
    public void teleport(){
    	if(game.getCurrentLevel() == 3){
    	if(getStars() != 0){
    		decrementStarsCount();
    	if(game.getPlayerFacing() == 2){
    	tRight = getPosition().x + 60;
     	setPosition(new Vec2(tRight, getPosition().y));
    	} else if(game.getPlayerFacing() == 1) {
        tLeft = getPosition().x - 60;
        setPosition(new Vec2(tLeft, getPosition().y));
      	}
    	
    	}
    	
    	}
    	    	
    }
    
     
    /** Increase the lives count of player ****/
    public void incrementLivesCount()
    {
        lives++;
    }
    
   
    /** Decrease the lives count. of player */
    public void decrementLivesCount()
    {
        lives--;
    }
    
  
    /** Returns the number of lives the player currently has ****/
    public int getLives()
    {
        return lives;
    }
    
    public void setLives(int lives)
    {
        this.lives = lives;
    }
        

    /** Increase the stars count. */
    public void incrementStarsCount()
    {
        stars++;
    }
    

    /** Decrease the yin-yangs count. */
    public void decrementStarsCount()
    {
        stars--;
    }
    
    
    /** The number of oranges the player currently has. */
    public int getStars()
    {
        return stars;
    }
    
    /** The number of oranges the player currently has. */
    public void setStars(int stars)
    {
        this.stars = stars;
    }
   
}
