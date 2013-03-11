
/**********************************************************************************************/
/** Class description: Game 																	**/
/** Author: Kenneth Monteiro                                                                 **/
/** Class version: Version 11                                                                 **/
/**********************************************************************************************/

/** Import game engine library class's */

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import org.jbox2d.common.Vec2;

import city.soi.platform.Body;
import city.soi.platform.BodyImage;
import city.soi.platform.DebugSettings;
import city.soi.platform.DebugViewer;
import city.soi.platform.SoundClip;
import city.soi.platform.StepEvent;
import city.soi.platform.StepListener;
import city.soi.platform.World;
import city.soi.platform.WorldView;

/**
 * The game class creates the world(physics engine) and the world view that shows the graphical interface 
 * for the world in a Jframe, it also is a hub for all of the objects from external classes or instatiating 
 * new objects from game engine class's, another feature that it has is the event handling keyboard presses
 * and releases has an impact on the player object in the game.
 * Lastly it also includes the main method where the game classes code runs from 
 */
public class Game implements StepListener
{
    // The player (a specialised Actor)
    private Player player;
   
    //Game over flag
    private boolean isOver;
    // The World in which the game bodies move and interact
    private World world;
    // A graphical display of the world (a specialised JPanel)
    private WorldView view;
    
    // A debug display
    private DebugViewer debugViewer;
    
    //player facing direction
    private int playerFacing;
    //levels
    private HashMap<Integer, GameLevel> level;
    //key for each level
    private int currentLevel;
   
    //bullet
    private StarBullet bullet;
    
    //Player status
    private PlayerStatus jpanelstatus;
    //high score
    private HighScore savePanel;
    
	//sound playback
	private SoundClip playbackBgMusic;
	
	//frame
	final JFrame frame;
        
    /** Initialise a new Game. */
   
    public Game() {
        isOver = false;
        
        // make the world
        world = new World();
                  
        // make a player
        player = new Player(this);
        playerFacing = 2;
        //set player image
        player.setImage(new BodyImage("images/ninja2.png"));
        
        //associate an integer key to each new created object 
        currentLevel = 1;
        level = new HashMap<Integer, GameLevel>();
        level.put(1, new LevelOne(this));
        level.put(2, new LevelTwo(this));
        level.put(3, new LevelThree(this));
        
        //call method: play background music
        bgMusic();
    	     	  
        //add step listener to the world
 	   	getWorld().addStepListener(this);
          
        /*******************************************************************************************/
        /** Creating a world view which displays the graphical world(physics engine) of the game  **/
        /*******************************************************************************************/
        jpanelstatus = new PlayerStatus(this);
        savePanel = new HighScore(this);
       
        view = new WorldView(world, 800, 600);
              
        //view.setDrawStats(true); // uncomment this line to show simulation stats in game display
              
        frame = new JFrame("Game");
        frame.add(jpanelstatus, BorderLayout.SOUTH);
		frame.add(savePanel, BorderLayout.NORTH);
		savePanel.hide();
        
  
       
        /**                Add some keyboard handling               **/         
        frame.addKeyListener(new java.awt.event.KeyAdapter() {
        
            /** Handle key press events for walking and jumping. */
            public void keyPressed(java.awt.event.KeyEvent e)
            {
                if (isOver) return;
                int code = e.getKeyCode();
                // SPACE = jump
                if (code == java.awt.event.KeyEvent.VK_SPACE) {
                    // only jump if player is not already jumping
                  getPlayer().jump();
                    
                    //A for shooting bullets
                } else if (code == java.awt.event.KeyEvent.VK_A) {
                 
                	shoot();
                }
                  //S teleport's player
                  else if (code == java.awt.event.KeyEvent.VK_S) {
                      
                    	getPlayer().teleport();
                    
                // LEFT ARROW = walk left
                } else if (code == java.awt.event.KeyEvent.VK_LEFT) {
                	getPlayer().walkLeft();
                    
                // RIGHT ARROW = walk right
                 
                } else if (code == java.awt.event.KeyEvent.VK_RIGHT) {
                	getPlayer().walkRight();
                 
                    // F1 key toggles display of debug view
                } else if (code == java.awt.event.KeyEvent.VK_F1) {
                    if (debugViewer == null) debugViewer = new DebugViewer(new DebugSettings(world));
                    if (debugViewer.isRunning()) {
                        debugViewer.stopViewer();
                    } else {
                        debugViewer.startViewer();
                    }
                }
            }
           
           /**Handle key release events (stop walking). */
           public void keyReleased(java.awt.event.KeyEvent e)           {
                if (isOver) return;
                int code = e.getKeyCode();
                if (code == java.awt.event.KeyEvent.VK_LEFT) {
                    player.stopWalking();
                } else if (code == java.awt.event.KeyEvent.VK_RIGHT) {
                    player.stopWalking();
                }                
                else if (code == java.awt.event.KeyEvent.VK_A) {
                	 if(currentLevel == 2 || currentLevel == 3){
                	 bullet.destroyBullet();
                	 shoot();
                	 }
                }
                
                else if (code == java.awt.event.KeyEvent.VK_S) {
                   
            }
                	
            }
      
        });
 
        
        
         
       //                 Game Window setup                        //
       
        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // display the world in the window
        
        frame.add(view);
        
        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        // make the window visible
        frame.setVisible(true);
                
        // start!
        world.start();
    }
    
    /** For the prestep of the player the Step listener does nothing */
    public void preStep(StepEvent e) {}
    
     
    /** checks y positions of player and checks for no of lives through the step listener's post step */
    public void postStep(StepEvent playerP){
    	
   
    	
    	  
            
        }
    
    /****************************************************************/
    /** 				All Methods in Game class 					*/
    /****************************************************************/
    
    /******** Next level *****************/
   	public void nextLevel(){
    	if(level.get(currentLevel).isCompleted() == true){
    		
      		if(currentLevel >= level.size()){
      			
      			gameOver();
      			      		      			      			
        	} else if(currentLevel <= level.size()) {
           				
        		level.get(currentLevel).clearLevel();
        		increaseCurrentLevel();
        		level.get(currentLevel).populate();
            	level.get(currentLevel).putPlayerAtStart();
            	level.get(currentLevel).isCompleted();  
        		        		
        	}
       	}
  
    }
   	

    /********* Return JFrame *************/
    public JFrame getFrame(){
    	
    	return frame;
    	
    }
    
    /********* Return level hash map *************/ 
    public HighScore getHighScoreP(){
     	
     	return savePanel;
     	
     }
       
    
    /********* Return level hash map *************/ 
   public GameLevel getlevel(){
    	
    	return level.get(currentLevel);
    	
    }
    
    /*** music playback **********/
    public void bgMusic(){
    	
    	try{
       		playbackBgMusic = new SoundClip("sound/kashmire.au");
    		playbackBgMusic.setVolume(1.0);
    		//playbackBgMusic.play();
    		playbackBgMusic.loop();
       		    	
    	}  catch (UnsupportedAudioFileException e) {
    		System.out.println("Sound unsupported file type");
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			System.out.println("line unavailable");
			e.printStackTrace();
		}catch(IOException e){
    		System.out.println("Sound io exception ");
    	}
    	
    }
    
    /** set and return checkPlaybackCheck **/
    public SoundClip getPlaybackbgMusic(){
    	
    return playbackBgMusic;
    }

    
    /** player shoots **/
    public void shoot(){
    	 if(currentLevel==2 || currentLevel == 3){
    	
    	bullet = new StarBullet(this);
    	   	   
    	   if(playerFacing == 1){
    		   
    		   bullet.move(new Vec2(getPlayer().getPosition().x + -40, getPlayer().getPosition().y));
    		   bullet.setAngularVelocity(12);
    		   bullet.setLinearVelocity(new Vec2(-150, 0));
    		
    	   }
    	   
    	   else if(playerFacing == 2){
    
    		   bullet.move(new Vec2(getPlayer().getPosition().x + 40, getPlayer().getPosition().y));
    		   bullet.setAngularVelocity(-12);
    		   bullet.setLinearVelocity(new Vec2(150, 0));
    	    		  
    	   }
    	   
    	 }
    	      	  
       	
    }
 
    /** returns bullet **/
    public Body getbullet(){
    	    	
    	return bullet;
    }
    
   /** returns value for which way the player is facing **/
    public int getPlayerFacing()
    {
        return playerFacing;
    }
    
    /** returns value for which way the player is facing **/
    public void setPlayerFacing(int playerFace)
    {
         playerFacing = playerFace;
    }
    
      
    /** returns currentLevel no **/
    public int getCurrentLevel()
    {
        return currentLevel;
    }
    
    /** returns currentLevel no **/
    public void increaseCurrentLevel()
    {
        currentLevel++;
    }

    /** returns isOver variables current value **/
    public boolean isOver()
    {
        return isOver;
    }
    
    /** Pauses game and changes the value of isOver to true **/
    public void gameOver()
    {
        world.pause();
        isOver = true;
    	getPlayer().destroy();
		level.get(currentLevel).clearLevel();
		jpanelstatus.hide();
		savePanel.show();
 		getPlaybackbgMusic().close();
        
    }
    
    /** Accesor method to return the world in which this game is played     */
    public World getWorld()
    {
        return world;
    }

        
   /** Accessor method to return the player                 */
    public Player getPlayer()
    {
        return player;
    }
       
    /** Main method to play the game                         */
    public static void main(String[] args) {
        new Game();
    }
}
