/**********************************************************************************************/
/** Class description: PlayerStatus (GUI)													 **/
/** Author: Kenneth Monteiro                                                                 **/
/** Class version: Version 11                                                                 **/
/**********************************************************************************************/

/** Import game engine library class's */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Pick-ups in a game. When the player collides with a star, the
 * player's stars gets destroyed is increased and the star count is increased
 * for the player
 */
public class NotifyUser extends JFrame implements ActionListener
{   
    /** The game in which the player is playing. */
  
    
    //private JLabels;
    private JLabel notifyError;
   
    //Jbuttons
    private JButton ok;
   
    
    //JPanel
    private JPanel jpanel;
    
     
    /** Initialise a new star, @param g The game, */
    public NotifyUser()
    {
    	super();
    	   	   	
        setVisible(true);
        setLayout(new BorderLayout());
        setSize(250, 100);
        setResizable(false);
        setTitle("Error Message");
        
        //instantiate labels
        notifyError = new JLabel();
        notifyError.setOpaque(true);
        
        //instantiate jpanel
        jpanel = new JPanel();
        add("Center", jpanel); // sets the new middle pane to the center of the form
        jpanel.setSize(800, 100);
        jpanel.setVisible(true);
     
        
        //instantiate buttons
        ok = new JButton();
        ok.setSize(1000, 1000);
        ok.setOpaque(true);
        ok.setFocusable(false);

        jpanel.add(notifyError);
      
   
        //add buttons to panel
        jpanel.add(ok);
        ok.addActionListener(this); 
       
        
        //add labels to panel
        updatelabels();
             
           
    }
    
        
    /** checks for button clicks on each button **/
    public void actionPerformed(ActionEvent e)  {
    	
    	if(e.getSource() == ok){
    		    		
    		dispose();
    			    			
    		}
        		
    	} 
    
    /****************************************************************/
    /** 				All Methods in PlayerStatus class			*/
    /****************************************************************/
   
    /** Text for each label **/
    public void updatelabels(){
    	
    	
        notifyError.setText("There was an error for your action");
       
        ok.setText("OK");
        
     
    }
    
     

}
