/**********************************************************************************************/
/** Class description: Bullet																 **/
/** Author: Kenneth Monteiro                                                                 **/
/** Class version: Version 11                                                                **/
/**********************************************************************************************/

/** Import game engine library class's */
import org.jbox2d.common.Vec2;

import city.soi.platform.Body;
import city.soi.platform.BodyImage;
import city.soi.platform.CircleShape;
import city.soi.platform.CollisionEvent;
import city.soi.platform.CollisionListener;


public class StarBullet extends Body implements CollisionListener {

    private Game game;
    private float playerPosition;
  
    

    
    /** Initialise a new star, @param g The game, */
    public StarBullet(Game game)
    {
        super(game.getWorld(), new CircleShape(13));
       
        this.game = game;
               
        //set image for each star object
        setImage(new BodyImage("images/star.png", new Vec2(0,0), 0.8f));
        setGravityStrength(0);
        getWorld().addCollisionListener(this);
       

        
    }
    
    /** checks for collision with other bodies **/
    public void collide(CollisionEvent collide) {
          	
    	 if(collide.getOtherBody() == collide.getOtherBody()){
    
    		 destroy();
            			
                    			
        }
    	 
         
     }
	   
    
    /****************************************************************/
    /** 				All Methods in Game class 					*/
    /****************************************************************/
    
  /** destroys bullet after a certain distance **/
    public void destroyBullet(){
    	playerPosition = game.getPlayer().getPosition().x;
        
        if(getPosition().x > playerPosition + 20 || getPosition().x < playerPosition + 20 ){
        
          	 destroy();
          }
        	
    }
        
 
}
