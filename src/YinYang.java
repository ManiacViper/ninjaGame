/**********************************************************************************************/
/** Class description: Stars (The yin-yangs in the game)									 **/
/** Author: Kenneth Monteiro                                                                 **/
/** Class version: Version 11                                                                **/
/**********************************************************************************************/

/** Import game engine library class's */
import org.jbox2d.common.Vec2;
import city.soi.platform.*;

/**
 * Pick-ups in a game. When the player collides with a star, the
 * player's stars gets destroyed is increased and the star count is increased
 * for the player
 */
public class YinYang extends Body implements CollisionListener
{   
    /** The game in which the player is playing. */
    private Game game;
    
    /** Initialise a new star, @param g The game, */
    public YinYang(Game game)
    {
        super(game.getWorld(), new CircleShape(20), Body.Type.STATIC);
        this.game = game;
    
        //set image for each star object
        setImage(new BodyImage("images/yin_yang2.png", new Vec2(0,0), 0.16f ));
       //add collision listener to the world object
        getWorld().addCollisionListener(this);
    }
    
    /** The collision method that checks if the other object is a player object */
    public void collide(CollisionEvent collide) {
        if(collide.getOtherBody() == game.getPlayer()){
            
            game.getPlayer().incrementStarsCount();
            game.getPlayer().playerScoreCalc();
            destroy();
            
        }
    }


}
