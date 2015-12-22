/**********************************************************************************************/
/** Class description: LevelOne																 **/
/** Author: Kenneth Monteiro                                                                 **/
/** Class version: Version 11                                                                 **/
/**********************************************************************************************/

/** Import game engine library class's */


import org.jbox2d.common.Vec2;

import city.soi.platform.Body;
import city.soi.platform.BodyImage;
import city.soi.platform.PolygonShape;
import city.soi.platform.SlidingPlatform;
import city.soi.platform.World;

public class LevelOne extends GameLevel  {
 		
	private World world;
	private Body ground;
	private Door door;


	
	

		
	/**
     * Initialise a new game level.
     * @param game the game to which this level belongs
    */
    public LevelOne(Game game) {
    	super(game);
    	
		populate();
		putPlayerAtStart();
		isCompleted();
		
    	   
       }
    
    /****************************************************************/
    /** 				All Methods in LevelOne class 				*/
    /****************************************************************/ 
      
 /** populates level 1 with objects **/  
public void populate(){
	world = game.getWorld();
	
    //background image
    Body background = new Body(world, PolygonShape.makeBox(800, 600), Body.Type.STATIC);
    background.setPosition(new Vec2(0,0));
    background.setGhostly(true);
    background.setImage(new BodyImage("images/bamboo_bg.jpg"));
    background.setRenderLayer(-5);
	
	
	ground = new Body(world, PolygonShape.makeBox(150, 10), Body.Type.STATIC); 
	ground.setPosition(new Vec2(0, -270));
	ground.setLineColor(java.awt.Color.WHITE);
	//ground.setAlwaysOutline(true);
	ground.setImage(new BodyImage("images/plat1.png", new Vec2(0,25), 1f ));
   
 /** Making platforms that dont interact with the player/world **/
    
    //static platform
    Body victoryPlatform = new Body(world, PolygonShape.makeBox(70, 5), Body.Type.STATIC);
    victoryPlatform.setPosition(new Vec2(-320, 240));
    victoryPlatform.setImage(new BodyImage("images/plat3.png", new Vec2(0,-5), 0.5f ));
    
    Body staticPlatform8 = new Body(world, PolygonShape.makeBox(5, 5), Body.Type.STATIC);
    staticPlatform8.setPosition(new Vec2(-90, 200));
    staticPlatform8.setImage(new BodyImage("images/shortPlat1.gif", new Vec2(0,0), 1f ));
    Body staticPlatform7 = new Body(world, PolygonShape.makeBox(5, 5), Body.Type.STATIC);
    staticPlatform7.setPosition(new Vec2(-10, 200));
    staticPlatform7.setImage(new BodyImage("images/shortPlat1.gif", new Vec2(0,0), 1f ));
    Body staticPlatform6 = new Body(world, PolygonShape.makeBox(5, 5), Body.Type.STATIC);
    staticPlatform6.setPosition(new Vec2(70, 200));
    staticPlatform6.setImage(new BodyImage("images/shortPlat1.gif", new Vec2(0,0), 1f ));
    
    Body staticPlatform5 = new Body(world, PolygonShape.makeBox(50, 5), Body.Type.STATIC);
    staticPlatform5.setPosition(new Vec2(185, 140));
    staticPlatform5.setImage(new BodyImage("images/plat1.png", new Vec2(0,10), 0.38f ));
    Body staticPlatform4 = new Body(world, PolygonShape.makeBox(30, 5), Body.Type.STATIC);
    staticPlatform4.setPosition(new Vec2(-160, -52));
    staticPlatform4.setImage(new BodyImage("images/plat1.png", new Vec2(0,10), 0.2f ));
    Body staticPlatform3 = new Body(world, PolygonShape.makeBox(130, 5), Body.Type.STATIC);
    staticPlatform3.setPosition(new Vec2(50, -120));
    staticPlatform3.setImage(new BodyImage("images/plat3.png", new Vec2(0,-20), 0.9f ));
    Body staticPlatform2 = new Body(world, PolygonShape.makeBox(25, 5), Body.Type.STATIC);
    staticPlatform2.setPosition(new Vec2(-130, -210));
 
    staticPlatform2.setImage(new BodyImage("images/plat1.png", new Vec2(0,7), 0.2f ));
    Body staticPlatform1 = new Body(world, PolygonShape.makeBox(50, 5), Body.Type.STATIC);
    staticPlatform1.setPosition(new Vec2(200, -230));
    staticPlatform1.setImage(new BodyImage("images/plat1.png", new Vec2(0,10), 0.4f ));
           
  //moving platform
    Body movingPlatform6 = new SlidingPlatform(world, PolygonShape.makeBox(10, 5), new Vec2(100, 0), 0.55f);
    movingPlatform6.setPosition(new Vec2(-250, 220));
    movingPlatform6.setImage(new BodyImage("images/shortPlat2.gif", new Vec2(0,0), 0.7f ));
    Body movingPlatform5 = new SlidingPlatform(world, PolygonShape.makeBox(40, 5), new Vec2(200, 0), 1.0f);
    movingPlatform5.setPosition(new Vec2(-150, 160));
    movingPlatform5.setImage(new BodyImage("images/movPlat1.png", new Vec2(0,0), 1f ));
    
    Body movingPlatform4 = new SlidingPlatform(world, PolygonShape.makeBox(5, 10), new Vec2(70, 0), 2.10f);
    movingPlatform4.setPosition(new Vec2(50, 70));
    movingPlatform4.setImage(new BodyImage("images/shortPlat1.gif", new Vec2(0,0), 2f ));
    Body movingPlatform3 = new SlidingPlatform(world, PolygonShape.makeBox(5, 10), new Vec2(80, 0), 1.5f);
    movingPlatform3.setPosition(new Vec2(-50, 20));
    movingPlatform3.setImage(new BodyImage("images/shortPlat1.gif", new Vec2(0,0), 2f ));
    Body movingPlatform2 = new SlidingPlatform(world, PolygonShape.makeBox(5, 10), new Vec2(0, 50), 2.0f);
    movingPlatform2.setPosition(new Vec2(50, -70));
    movingPlatform2.setImage(new BodyImage("images/shortPlat1.gif", new Vec2(0,0), 2f ));
    
    Body movingPlatform1 = new SlidingPlatform(world, PolygonShape.makeBox(20, 5), new Vec2(0, 150), 5.0f);
    movingPlatform1.setPosition(new Vec2(220, -180));
    movingPlatform1.setImage(new BodyImage("images/shortPlat2.gif", new Vec2(0,0), 1f ));
   
    /******* Creating interacting objects from external class's   **/
   
    
    Spikes spike2 = new Spikes(game);
    spike2.putOn(staticPlatform3);
    spike2.move(new Vec2(0, 0));
    Spikes spike3 = new Spikes(game);
    spike3.putOn(ground);
    spike3.move(new Vec2(-100, 0));
        
    // make some star objects
    YinYang star1 = new YinYang(game);
    star1.putOn(staticPlatform1); 
    star1.move(new Vec2(-20, 0)); 
    YinYang star2 = new YinYang(game); 
    star2.putOn(staticPlatform4); 
    YinYang star3 = new YinYang(game);
    star3.putOn(staticPlatform2); 
    YinYang star4 = new YinYang(game);
    star4.putOn(staticPlatform5); 
    
    door = new Door(game);
    door.putOn(victoryPlatform);
    door.move(new Vec2(0, 0)); 
	
   
}



/**
 * Has this level been completed?
 */
public boolean isCompleted(){
	

	
	
	
return true;
	
	
}



/**
 * Set player attributes (position, velocity, etc) to the approriate start
 * values for this level. This method may be called, for example, when play
 * starts or after the player has lost a life.
 */
public void putPlayerAtStart(){
	Player player = game.getPlayer();
 	

    //put the player on the ground 
    player.putOn(ground);
    player.move(new Vec2(-10, 50));
	
	
}





  
	
	
}
