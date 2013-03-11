/**********************************************************************************************/
/** Class description: MovingBlades															 **/
/** Author: Kenneth Monteiro                                                                 **/
/** Class version: Version 11                                                                 **/
/**********************************************************************************************/

/** Import game engine library class's */

import org.jbox2d.common.Vec2;

import city.soi.platform.BodyImage;
import city.soi.platform.CircleShape;
import city.soi.platform.CollisionEvent;
import city.soi.platform.CollisionListener;
import city.soi.platform.SlidingPlatform;

/**
 * Dangerous object in game takes away a life from the player
 * when player collides until it goes down to 0 at which point
 * the game is over
 */
public class MovingBlades extends SlidingPlatform implements CollisionListener
{   
    /** The game in which the player is playing */
    private Game game;
    
    /** Initialise a new orange, @param g The game */
    public MovingBlades(Game game)
    {	
    
    	super(game.getWorld(), new CircleShape(20), new Vec2(20f, 0f), 1.0f);
    	this.game = game;
    	   	
        setAngularVelocity(20);
                
        //set image of spikes object
        setImage(new BodyImage("images/blades1.png", new Vec2(0,0), 0.8f ));
   
        getWorld().addCollisionListener(this);
        
    }
    
     /** Checks if it collided with player object and checks if lives is equal to 0 */
    public void collide(CollisionEvent danger){
        
        if(danger.getOtherBody() == game.getPlayer()){
            game.getPlayer().decrementLivesCount();
            if(game.getPlayer().getLives() != 0){
            	
                 game.getPlayer().setVisible(false);
                 game.getPlayer().setVisible(true);
                 game.getPlayer().playerLevelPosition();
                           
            }
            else{
                game.gameOver();
                }
        }
        
    }
    
   

    
}