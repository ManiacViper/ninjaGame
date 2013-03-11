/**********************************************************************************************/
/** Class description: PlayerStatus (GUI)													 **/
/** Author: Kenneth Monteiro                                                                 **/
/** Class version: Version 11                                                                **/
/**********************************************************************************************/

/** Import game engine library class's */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;





/**
 * Pick-ups in a game. When the player collides with a star, the
 * player's stars gets destroyed is increased and the star count is increased
 * for the player
 */
public class PlayerStatus extends JPanel implements ChangeListener, ActionListener
{   
    /** The game in which the player is playing. */
    private Game game;
    
    //private JLabels;
    private JLabel noOfLives;
    private JLabel ramen;
    private JLabel space;
    private JLabel score;
    private JLabel levelstatus;
    
    //Jbuttons
    private JButton pause;
    private JButton restart;
    private JButton nextlevel;
    private int checkButtonClick;
    private JButton endGame;
    
     
    /** Initialise a new star, @param g The game, */
    public PlayerStatus(Game game)
    {
    	super();
    	this.game = game;
    
    	   	
        setVisible(true);
        setSize(800, 100); 
        
        //instantiate labels
        noOfLives = new JLabel();
        noOfLives.setOpaque(true);
        ramen = new JLabel();
        ramen.setOpaque(true);
        space = new JLabel();
        space.setOpaque(true);
        score = new JLabel();
        score.setOpaque(true);
        levelstatus = new JLabel();
        levelstatus.setOpaque(true);
        
        //instantiate buttons
        pause = new JButton();
        pause.setSize(1000, 1000);
        pause.setOpaque(true);
        pause.setFocusable(false);
        restart = new JButton();
        restart.setSize(1000, 1000);
        restart.setOpaque(true);
        restart.setFocusable(false);
        nextlevel = new JButton();
        nextlevel.setSize(1000, 1000);
        nextlevel.setOpaque(true);
        nextlevel.setFocusable(false);
        endGame = new JButton("End Game");
        endGame.setSize(1000, 1000);
        endGame.setOpaque(true);
        endGame.setFocusable(false);
      
        
        //add labels to panel
        updatelabels();
        add(nextlevel);
        nextlevel.addActionListener(this);
        //add labels to panel
        add(noOfLives);
        add(ramen);
        add(score);
        add(levelstatus);
      
        checkButtonClick = 1;
        
        //add buttons to panel
        add(pause);
        pause.addActionListener(this); 
        add(space); //label
        add(restart);
        restart.addActionListener(this);
        add(endGame);
        endGame.addActionListener(this);
        //add Change listener to world
        game.getWorld().addChangeListener(this);
        
           
    }
    
    /****************************************************************/
    /** 				All Methods in PlayerStatus class			*/
    /****************************************************************/
   
    /** Text for each label **/
    public void updatelabels(){
    	
    	nextlevel.setText("Next Level");
        noOfLives.setText("  |  Lives: " + game.getPlayer().getLives()+ "   |  ");
        ramen.setText("Yin-Yangs: " + game.getPlayer().getStars() + "  |  ");
        pause.setText("Pause");
        space.setText("  |  ");
        restart.setText("Restart");
        score.setText("Score: " + game.getPlayer().getScore() + " |");
        levelstatus.setText("Level: " + game.getCurrentLevel() + "   |  ");
     
    }
    
    
/** checks if players lives and stars have changed **/
    public void stateChanged(ChangeEvent e){
    
 	 
		updatelabels();
		
		   
  }
    
    /** checks for button clicks on each button **/
    public void actionPerformed(ActionEvent e)  {
    	
    	if(e.getSource() == pause){
    		
    		if(checkButtonClick == 1){
    			game.getWorld().pause();
    			checkButtonClick = 2;
    			 pause.setText("Resume");
    			
    		}
    			
    		else if(checkButtonClick == 2){
    			checkButtonClick = 1;
    			game.getWorld().unpause();
    		     pause.setText("Pause");
    			
    		}
    	} else if(e.getSource() == restart){
    		
    	   		
    		game.getlevel().clearLevel();
       		game.getlevel().populate();
    		game.getlevel().putPlayerAtStart();
    		game.getlevel().isCompleted();
            game.getPlayer().setLives(5);
    	  		
    	
    		
    	} else if(e.getSource() == nextlevel){
    		   		
    	    	
        		game.nextLevel();

        		
    		
    		
    	} else if(e.getSource() == endGame){
    		
    		
    		
   
    		game.gameOver();
    		
    	}
    	
    	
    	
    	
    }
    	
  
       	
 
                    
        
    


 

}
