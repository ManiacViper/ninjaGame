/**********************************************************************************************/
/** Class description: Spikes																 **/
/** Author: Kenneth Monteiro                                                                 **/
/** Class version: Version 11                                                                 **/
/**********************************************************************************************/

/** Import game engine library class's */


import city.soi.platform.Body;
import city.soi.platform.BodyImage;
import city.soi.platform.CollisionEvent;
import city.soi.platform.CollisionListener;
import city.soi.platform.PolygonShape;

/**
 * Dangerous object in game takes away a life from the player
 * when player collides until it goes down to 0 at which point
 * the game is over
 */
public class Spikes extends Body implements CollisionListener
{   
    /** The game in which the player is playing */
    private Game game;
    
    /** Initialise a new orange, @param g The game */
    public Spikes(Game game)
    {
        super(game.getWorld(), PolygonShape.makeBox(40, 15), Body.Type.STATIC);
        this.game = game;
    
        
        //set image of spikes object
        setImage(new BodyImage("images/spikes2.png"));
        //add collision listener to the world
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