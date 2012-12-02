/**********************************************************************************************/
/** Class description: LevelTwo																 **/
/** Author: Kenneth Monteiro                                                                 **/
/** Class version: Version 11                                                                 **/
/**********************************************************************************************/

/** Import game engine library class's */
import org.jbox2d.common.Vec2;

import city.soi.platform.*;



public class LevelTwo extends GameLevel {
	private World world;
	private Body ground;
	private Door door;
	

	
	/**
     * Initialise a new game level.
     * @param game the game to which this level belongs
    */
    public LevelTwo(Game game) {
    	
    	super(game);

       }
      
  /** populates level 2 with objects **/
public void populate(){
	world = game.getWorld();
	
    //background image
    Body background = new Body(world, PolygonShape.makeBox(800, 600), Body.Type.STATIC);
    background.setPosition(new Vec2(0,0));
    background.setGhostly(true);
    background.setImage(new BodyImage("images/jungle_bg.jpg"));
   background.setRenderLayer(-5);
	   
    
    //static platform
    
    Body victoryPlatform = new Body(world, PolygonShape.makeBox(70, 5), Body.Type.STATIC);
    victoryPlatform.setPosition(new Vec2(320, 240));
    victoryPlatform.setImage(new BodyImage("images/plat3.png", new Vec2(0,-5), 0.5f ));
   
    Body staticPlatform10 = new Body(world, PolygonShape.makeBox(70, 5), Body.Type.STATIC);
    staticPlatform10.setPosition(new Vec2(-140, 170));
    staticPlatform10.setImage(new BodyImage("images/plat1.png", new Vec2(0,12), 0.5f ));

    Body staticPlatform9 = new Body(world, PolygonShape.makeBox(5, 5), Body.Type.STATIC);
    staticPlatform9.setPosition(new Vec2(200, 140));
    staticPlatform9.setImage(new BodyImage("images/shortplat1.gif", new Vec2(0,0), 1f ));
    Body staticPlatform8 = new Body(world, PolygonShape.makeBox(10, 5), Body.Type.STATIC);
    staticPlatform8.setPosition(new Vec2(150, 100));
    staticPlatform8.setImage(new BodyImage("images/shortplat2.gif", new Vec2(0,2), 0.6f ));
    Body staticPlatform7 = new Body(world, PolygonShape.makeBox(5, 5), Body.Type.STATIC);
    staticPlatform7.setPosition(new Vec2(100, 60));
    staticPlatform7.setImage(new BodyImage("images/shortplat1.gif", new Vec2(0,0), 1f ));
    Body staticPlatform6 = new Body(world, PolygonShape.makeBox(10, 5), Body.Type.STATIC);
    staticPlatform6.setPosition(new Vec2(30, 30));
    staticPlatform6.setImage(new BodyImage("images/shortplat2.gif", new Vec2(0,2), 0.6f ));
      
    Body staticPlatform5 = new Body(world, PolygonShape.makeBox(10, 5), Body.Type.STATIC);
    staticPlatform5.setPosition(new Vec2(-400, -6));
    staticPlatform5.setImage(new BodyImage("images/plat1.png", new Vec2(0,9), 0.3f )); 
    Body staticPlatform4 = new Body(world, PolygonShape.makeBox(120, 5), Body.Type.STATIC);
    staticPlatform4.setPosition(new Vec2(-250, -70));
    staticPlatform4.setImage(new BodyImage("images/plat1.png", new Vec2(0,16), 0.8f ));    
    Body staticPlatform3 = new Body(world, PolygonShape.makeBox(80, 5), Body.Type.STATIC);
    staticPlatform3.setPosition(new Vec2(50, -60));
    staticPlatform3.setImage(new BodyImage("images/plat1.png", new Vec2(0,12), 0.55f ));
    Body staticPlatform2 = new Body(world, PolygonShape.makeBox(80, 5), Body.Type.STATIC);
    staticPlatform2.setPosition(new Vec2(0, -120));
    staticPlatform2.setImage(new BodyImage("images/plat1.png", new Vec2(0,12), 0.55f ));
    Body staticPlatform1 = new Body(world, PolygonShape.makeBox(70, 5), Body.Type.STATIC);
    staticPlatform1.setPosition(new Vec2(200, -200));
    staticPlatform1.setImage(new BodyImage("images/plat1.png", new Vec2(0,12), 0.5f ));
   
    ground = new Body(world, PolygonShape.makeBox(150, 10), Body.Type.STATIC); 
	ground.setPosition(new Vec2(0, -270));
	ground.setLineColor(java.awt.Color.WHITE);
	//ground.setAlwaysOutline(true);
	ground.setImage(new BodyImage("images/plat1.png", new Vec2(0,25), 1f ));
	
	
	  //moving platform
	
	Body movingPlatform6 = new SlidingPlatform(world, PolygonShape.makeBox(20, 5), new Vec2(180, 0), 2.8f);
    movingPlatform6.setPosition(new Vec2(-10, 220));
    movingPlatform6.setImage(new BodyImage("images/shortPlat2.gif", new Vec2(0,0), 1f ));
    Body movingPlatform5 = new SlidingPlatform(world, PolygonShape.makeBox(20, 5), new Vec2(0, 0), 1.0f);
    movingPlatform5.setPosition(new Vec2(-30, 140));
    movingPlatform5.setAngularVelocity(1);
    movingPlatform5.setImage(new BodyImage("images/shortPlat2.gif", new Vec2(0,0), 1f ));
    Body movingPlatform4 = new SlidingPlatform(world, PolygonShape.makeBox(20, 5), new Vec2(80, 0), 1.5f);
    movingPlatform4.setPosition(new Vec2(50, 140));
    movingPlatform4.setImage(new BodyImage("images/shortPlat2.gif", new Vec2(0,0), 1f ));
    Body movingPlatform3 = new SlidingPlatform(world, PolygonShape.makeBox(30, 5), new Vec2(0, 0), 1.0f);
    movingPlatform3.setPosition(new Vec2(-70, -10));
    movingPlatform3.setAngularVelocity(-2);
    movingPlatform3.setImage(new BodyImage("images/shortPlat2.gif", new Vec2(0,0), 1.45f ));
    Body movingPlatform2 = new SlidingPlatform(world, PolygonShape.makeBox(5, 20), new Vec2(0, 50), 2.5f);
    movingPlatform2.setPosition(new Vec2(-150, -170));
    movingPlatform2.setImage(new BodyImage("images/wood2.jpg", new Vec2(0,0), 0.25f ));

    
    Body movingPlatform1 = new SlidingPlatform(world, PolygonShape.makeBox(20, 5), new Vec2(100, 0), 2.0f);
    movingPlatform1.setPosition(new Vec2(-220, -220));
    movingPlatform1.setImage(new BodyImage("images/shortPlat2.gif", new Vec2(0,0), 1f ));
    
	
	

    SmallNinja SM = new SmallNinja(game);
    SM.putOn(staticPlatform4);
    SM.move(new Vec2(20,0));
    SmallNinja SM2 = new SmallNinja(game);
    SM2.putOn(staticPlatform3);
    SM2.move(new Vec2(20,0));
    SmallNinja SM3 = new SmallNinja(game);
    SM3.putOn(staticPlatform10);
    SM3.move(new Vec2(20,0));
	
	
	
	YinYang star1 = new YinYang(game);
	star1.putOn(staticPlatform10);
	star1.move(new Vec2(-35, 0));
	YinYang star2 = new YinYang(game);
	star2.putOn(staticPlatform5);
	star2.move(new Vec2(20, 0));
	YinYang star3 = new YinYang(game);
	star3.putOn(staticPlatform1);
	star3.move(new Vec2(45, 0));
	

	Spikes spike2 = new Spikes(game);
    spike2.putOn(staticPlatform4);
    spike2.move(new Vec2(-80, 0));
        
	Spikes spike1 = new Spikes(game);
    spike1.putOn(staticPlatform1);
    spike1.move(new Vec2(-30, 0));
        
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
