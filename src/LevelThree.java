/**********************************************************************************************/
/** Class description: LevelThree															**/
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

public class LevelThree extends GameLevel {
	private World world;
	private Body ground;
	//private SmallNinja smallninja;
	private Door door;
	private MovingBlades bladesM;

	
	/**
     * Initialise a new game level.
     * @param game the game to which this level belongs
    */
    public LevelThree(Game game) {
    	
    	super(game);
    	

    	   
       }
    
    /****************************************************************/
    /** 				All Methods in LevelThree class				*/
    /****************************************************************/
      
 /** populates level 3 with objects **/
public void populate(){
	world = game.getWorld();
	
    //background image
    Body background = new Body(world, PolygonShape.makeBox(800, 600), Body.Type.STATIC);
    background.setPosition(new Vec2(0,0));
    background.setGhostly(true);
   background.setImage(new BodyImage("images/autumn.jpg"));
    background.setRenderLayer(-5);
		
	
    ground = new Body(world, PolygonShape.makeBox(150, 10), Body.Type.STATIC); 
	ground.setPosition(new Vec2(250, 250));

	ground.setImage(new BodyImage("images/plat1.png", new Vec2(0,25), 1f ));
    
    //static platform
    
    Body staticPlatform10 = new Body(world, PolygonShape.makeBox(100, 5), Body.Type.STATIC);
    staticPlatform10.setPosition(new Vec2(-200, 170));
    staticPlatform10.setImage(new BodyImage("images/plat1.png", new Vec2(0,13), 0.65f ));
 
    Body staticPlatform8 = new Body(world, PolygonShape.makeBox(190, 5), Body.Type.STATIC);
    staticPlatform8.setPosition(new Vec2(150, 130));
    staticPlatform8.setImage(new BodyImage("images/plat1.png", new Vec2(0,22), 1.3f ));
    Body staticPlatform7 = new Body(world, PolygonShape.makeBox(70, 5), Body.Type.STATIC);
    staticPlatform7.setPosition(new Vec2(150, 30));
    staticPlatform7.setImage(new BodyImage("images/plat1.png", new Vec2(0,13), 0.5f ));
    Body staticPlatform6 = new Body(world, PolygonShape.makeBox(30, 5), Body.Type.STATIC);
    staticPlatform6.setPosition(new Vec2(-90, 30));
    staticPlatform6.setImage(new BodyImage("images/plat1.png", new Vec2(0,10), 0.2f ));
     
    Body staticPlatform5 = new Body(world, PolygonShape.makeBox(120, 5), Body.Type.STATIC);
    staticPlatform5.setPosition(new Vec2(250, -70));
    staticPlatform5.setImage(new BodyImage("images/plat1.png", new Vec2(0,16), 0.8f ));    
    Body staticPlatform4 = new Body(world, PolygonShape.makeBox(20, 5), Body.Type.STATIC);
    staticPlatform4.setPosition(new Vec2(-200, -70));
    staticPlatform4.setImage(new BodyImage("images/plat1.png", new Vec2(0,9), 0.18f ));
    Body staticPlatform3 = new Body(world, PolygonShape.makeBox(120, 5), Body.Type.STATIC);
    staticPlatform3.setPosition(new Vec2(20, -120));
    staticPlatform3.setImage(new BodyImage("images/plat1.png", new Vec2(0,16), 0.8f ));

    Body staticPlatform1 = new Body(world, PolygonShape.makeBox(70, 5), Body.Type.STATIC);
    staticPlatform1.setPosition(new Vec2(240, -200));
    staticPlatform1.setImage(new BodyImage("images/plat1.png", new Vec2(0,10), 0.4f ));
   
    
    Body victoryPlatform = new Body(world, PolygonShape.makeBox(70, 5), Body.Type.STATIC);
	victoryPlatform.setPosition(new Vec2(250, -250));
	victoryPlatform.setImage(new BodyImage("images/plat3.png", new Vec2(0,-5), 0.5f ));
	   
	//blades    
	bladesM = new MovingBlades(game);
	bladesM.setPosition(new Vec2(0, 10));
	
	
    //moving platforms
	Body movingPlatform4 = new SlidingPlatform(world, PolygonShape.makeBox(20, 5), new Vec2(50, 0), 1.5f);
    movingPlatform4.setPosition(new Vec2(-100, -50));
    movingPlatform4.setImage(new BodyImage("images/shortPlat2.gif", new Vec2(0,0), 1f ));
  
    Body movingPlatform2 = new SlidingPlatform(world, PolygonShape.makeBox(5, 10), new Vec2(0, 120), 2.5f);
    movingPlatform2.setPosition(new Vec2(-150, -340));
    movingPlatform2.setImage(new BodyImage("images/wood2.jpg", new Vec2(0,-20), 0.2f ));
    Body movingPlatform1 = new SlidingPlatform(world, PolygonShape.makeBox(20, 5), new Vec2(150, 0), 2.0f);
    movingPlatform1.setPosition(new Vec2(-50, -250));
    movingPlatform1.setImage(new BodyImage("images/shortPlat2.gif", new Vec2(0,0), 1f ));
   
	SmallNinja smallninja1 = new SmallNinja(game);
	smallninja1.putOn(staticPlatform10);
	smallninja1.move(new Vec2(40, 0));
	SmallNinja smallninja2 = new SmallNinja(game);
	smallninja2.putOn(staticPlatform8);
	smallninja2.move(new Vec2(40, 0));
	SmallNinja smallninja3 = new SmallNinja(game);
	smallninja3.putOn(staticPlatform5);
	smallninja3.move(new Vec2(-60, 0));
	
   
	Spikes spike1 = new Spikes(game);
    spike1.putOn(staticPlatform8);
    spike1.move(new Vec2(-150, 0));
	Spikes spike2 = new Spikes(game);
    spike2.putOn(staticPlatform7);
    spike2.move(new Vec2(-30, 0));
	Spikes spike3 = new Spikes(game);
    spike3.putOn(staticPlatform3);
    spike3.move(new Vec2(-30, 0));
	Spikes spike4 = new Spikes(game);
    spike4.putOn(staticPlatform3);
    spike4.move(new Vec2(50, 0));
	Spikes spike5 = new Spikes(game);
    spike5.putOn(staticPlatform1);
    spike5.move(new Vec2(-20, 0));
    
    
	YinYang star1 = new YinYang(game);
	star1.putOn(staticPlatform10);
	star1.move(new Vec2(-35, 0));
	YinYang star2 = new YinYang(game);
	star2.putOn(staticPlatform7);
	star2.move(new Vec2(35, 0));
	YinYang star3 = new YinYang(game);
	star3.putOn(staticPlatform5);
	star3.move(new Vec2(35, 0));
	
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
