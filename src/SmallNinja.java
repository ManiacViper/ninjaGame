/**********************************************************************************************/
/** Class description: SmallNinja															 **/
/** Author: Kenneth Monteiro                                                                 **/
/** Class version: Version 11                                                                 **/
/**********************************************************************************************/

/** Import game engine library class's */
import org.jbox2d.common.Vec2;

import city.soi.platform.*;


public class SmallNinja extends SlidingPlatform implements CollisionListener
{
	
	/** The game in which the player is playing. */
    private Game game;
    //enemy life
    private int enemyLife;
  
    
    /** Initialise a new SmallNinja, @param g The game, */
    public SmallNinja(Game game)
    {
    	super(game.getWorld(), PolygonShape.makeBox(15, 27), new Vec2(30, 0), 1.8f);
      
        this.game = game;
        enemyLife = 3;        
       
        //set image for each star object
        setImage(new BodyImage("images/enemy.png", new Vec2(0,13f), 0.4f ));
      
    
       //add collision listener to the world object
        getWorld().addCollisionListener(this);

        
    }
       
    
    /*** The collision method that checks if the other object is a bullet object **/
    public void collide(CollisionEvent collide) {
     if(collide.getOtherBody() ==  game.getbullet()){
    	 
    		enemyLife--;
           if(enemyLife == 0){
        		
        	 game.getbullet().destroy();
                destroy();
                
             
                      		}
                     
       } 
     
     else if(collide.getOtherBody() ==  game.getPlayer()){
    	 
 		game.getPlayer().decrementLivesCount();
 	      if(game.getPlayer().getLives() != 0){
            
 	      }

 	      else {
	
 	    	  game.gameOver(); System.out.println("Game Over");
 	    	  }
     	
                  
    } 
     
    }
    
    /****************************************************************/
    /** 				All Methods in Game class 					*/
    /****************************************************************/
    
	/** get enemy life left **/
    public int getEnemyLife(){
    	
    	return enemyLife;
    	
    }
	

}
