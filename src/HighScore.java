/**********************************************************************************************/
/** Class description: PlayerStatus (GUI)                                                    **/
/** Author: Kenneth Monteiro                                                                 **/
/** Class version: Version 11                                                                **/
/**********************************************************************************************/

/** Import game engine library class's */
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Pick-ups in a game. When the player collides with a star, the
 * player's stars gets destroyed is increased and the star count is increased
 * for the player
 */
public class HighScore extends JPanel implements ChangeListener, ActionListener
{   
    /** All fields declared here */
    private Game game;
    
    //getText
    private String pName;
    private int scoreName;
    //fields for sorting and converting
    private ArrayList<String> getWord;
    private ArrayList<Integer> convert;
    private HashMap<Integer, String> associate;
    
    private String line;
    //NotifyUser
    
    
    //private JTextArea
    private JTextArea playerName;
    private JTextArea enterScore;
       
    private JTextArea showScores;
    private JScrollPane scrollPane;
    
    //JLabels
    private JLabel scoreLabel;
    private JLabel gameOverMessage;
        
    //Jbuttons
    private JButton saveGame;
    private JButton loadGame;
   
    /** Initialise a new star, @param g The game, */
    public HighScore(Game game)
    {
        super();
        this.game = game;               
     
        //JPanel properties
        //setLayout(new BorderLayout());
        setSize(800, 300);
        setVisible(true);
              
        //instantiate buttons
        saveGame = new JButton();
        saveGame.setOpaque(true);
        saveGame.setFocusable(false); 
        
        loadGame = new JButton("Load Score");
        loadGame.setOpaque(true);
        loadGame.setFocusable(false); 
        
        //instantiate labels
        scoreLabel = new JLabel();
        scoreLabel.setOpaque(true);
        gameOverMessage = new JLabel();
        gameOverMessage.setOpaque(true);
        gameOverMessage.setFont(new Font("Serif", Font.BOLD, 30));
                   
        //instantiate JTextArea
        playerName = new JTextArea();
        playerName.setOpaque(true);
        playerName.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        playerName.setLineWrap(true);
        playerName.setWrapStyleWord(true);
        playerName.setPreferredSize(new Dimension(100, 30));
        playerName.setFocusable(true);
        
        enterScore = new JTextArea();
        enterScore.setOpaque(true);
        enterScore.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        enterScore.setLineWrap(true);
        enterScore.setWrapStyleWord(true);
        enterScore.setPreferredSize(new Dimension(100, 30));
        enterScore.setFocusable(true);
   
        showScores = new JTextArea();
        showScores.setOpaque(true);
        showScores.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        showScores.setLineWrap(true);
        showScores.setWrapStyleWord(true);
        showScores.setPreferredSize(new Dimension(105, 250));
        showScores.setFocusable(true);  
        
        scrollPane = new JScrollPane(showScores);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       // scrollPane.setPreferredSize(new Dimension(200, 150));

        add(gameOverMessage);      
        //add buttons to panel
        add(saveGame);
        saveGame.addActionListener(this); 
        //enterSName.
        add(playerName);
        add(scoreLabel);
       
        
        add(loadGame);
        loadGame.addActionListener(this);
        
               
        add(scrollPane); // adds the scrollpane to the middle of the form
   
        //call setlabel for save game
        setlabel();
        
        
        //add Change listener to world
        game.getWorld().addChangeListener(this);
        
        //instantiate arrayList
        getWord = new ArrayList<String>();
        convert = new ArrayList<Integer>();
        associate = new HashMap<Integer, String>();
       
       
           
    }
    
    
    /** checks if players lives and stars have changed **/
        public void stateChanged(ChangeEvent e){
            
            
            setlabel();
               
      }
        
        /** checks for button clicks on each button **/
        public void actionPerformed(ActionEvent e)  {
            
            if(e.getSource() == saveGame){
                      
                    saveScore();
                     System.out.println("saved");
                
            } else if(e.getSource() == loadGame){
                          
                    loadScore();
            
            }
        
        }
   /****************************************************************/
   /**              All Methods in HighScore class                 */
   /****************************************************************/
        
    /***********    Save Game   *****************/
    public void  saveScore()  {
        
        pName = getPlayerName().getText() + "\n";
        scoreName = game.getPlayer().getScore();
        BufferedWriter out;
        
        try{
            // Create file 
            FileWriter fstream = new FileWriter("AllScores.txt", true);
            out = new BufferedWriter(fstream);
            out.write(pName + scoreName);
            out.newLine();
           
            out.close();
            }catch (IOException e){//Catch exception if any
              System.err.println("Error: " + e.getMessage());
              new NotifyUser();     
            }
        
        
    }
        
    /*** Adds a header for the scores list at the top ***/
    public void scoreHeader(){
        
        String header = "Name   Score\t ____________ \n";
        
        showScores.append(header);
        
    }
    
    /*********** Load Game *****************/
    public void loadScore() {
    
        try {

        FileInputStream fileStream = new FileInputStream("AllScores.txt");
        DataInputStream inputS = new DataInputStream(fileStream);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputS));
        scoreHeader();
        while((line = br.readLine())!= null){
            getWord.add(line);  //adds each word including white space to the array list separately to each key 
            
        }
            br.close();
            convertScores();//calls convert score which converts to integer (includes method sortScore as well)
        
        } catch(IOException e){
            
             new NotifyUser();  
             System.err.println("Error: " + e.getMessage());
        }
            
    }
    
    /*** converts the scores from string to int ***/
    public void convertScores(){
        
        int count = 1;//for all integers in getWord arraylist
        int count2 = 0; //for names in getWord arraylist
        int count3 = 0; // for all integers in convert arraylist
                 
        while(count <= getWord.size()){
            try{                    
            
                convert.add(Integer.parseInt(getWord.get(count))); //converts each string-integer to an actual integer value
                associate.put(convert.get(count3), getWord.get(count2)); //associates each converted integer as key to the player name before through hash maps
                        
            } catch(NumberFormatException e){
                
                System.out.println("Not an integer value");     
            }
            
            count = count + 2; //count starts from 1
            count2 = count2 + 2; //count2 starts from 0
            count3++; //count3 only increases by 1
            
        }
        
        sortScores(); //calls sortScore method that sorts out the scores in descending order
        
    }
    
    /*** sort's and display's the converted scores ***/
    public void sortScores(){
        
        int k = 0;
         
        Comparator<Integer> compare = Collections.reverseOrder(); //call reverseOrder on the collections and store it in compare
        Collections.sort(convert, compare);//sort the 'convert' collection in descending order(compare)
        while(k < convert.size()){
            showScores.append(associate.get(convert.get(k))+ "\t" +  convert.get(k) + "\n"); //append and display each score to textArea starting from the highest to lowest and also the player name associated with the score
            k++;
        }
   
   }    
 
  /*********** returns enterScore JTextArea ***/ 
  private JTextArea getScoreEntry(){
        
        return enterScore;
        
    }
  
  /*********** returns playerName JTextArea ***/ 
  private JTextArea getPlayerName(){
      
      
      return playerName;
      
  }
  
    /** Text for saveGame component **/
 public void setlabel(){
        
        saveGame.setText("Save Score");
        scoreLabel.setText(" | Your Score: " + game.getPlayer().getScore());
        gameOverMessage.setText("Game Over");
     
    }
    
}
