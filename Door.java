/**********************************************************************************************/
/** Class description: Door                                                                  **/
/** Author: Kenneth Monteiro                                                                 **/
/** Class version: Version 11                                                                 **/
/**********************************************************************************************/

/** Import game engine library class's */
import city.soi.platform.*;


import org.jbox2d.common.Vec2;

public class Door extends Body implements CollisionListener {
    
 
        /** The game to which this level belongs. */
        protected Game game;

        
        /**
         * Initialise a new game level.
         * @param game the game to which this level belongs
         */
        public Door(Game game) {
            super(game.getWorld(), PolygonShape.makeBox(15, 20), Body.Type.STATIC);
            this.game = game;
         
            setImage(new BodyImage("images/wood3.png", new Vec2(0,2f), 0.3f));
            
            getWorld().addCollisionListener(this);
        }
        
        /** Checks if it collided with player object and checks if lives is equal to 0 */
        public void collide(CollisionEvent next){
            
            if(next.getOtherBody() == game.getPlayer()){
                        
                game.nextLevel();
                       
            }
                    
        }

}
