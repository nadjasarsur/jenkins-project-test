//package games.hangman;
import javax.swing.*;
import BreezySwing.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;


public class HangmanGUI extends GBFrame {
    private JTextField nameField;
    private JButton enterButton;
    private JButton restartButton;
    private JTextArea output;
    private JTextArea directions;
    private JTextArea theMan;
   
    
    public static int numOfGuesses;
    public static int numOfStrikes;
    public static int numRight;
    public static int sizeOfAnswer;
    public static ArrayList words;
    public static ArrayList letters;
    public static ArrayList chosenWord;
    public static String guess;
    public static String answer;
    public static boolean[] lettersGuessedRight;
    public final static String[] POS={"First","Second","Third","Fourth","Fifth","Sixth","seventh","eighth","ninth","tenth", "eleventh", "twelfth",};
    public static String firstOffense;
    public static String secondOffense;
    public static String thirdOffense;
    public static String fourthOffense;
    public static String fifthOffense;
    public static String sixthOffense;
    
    public HangmanGUI() {
        nameField=addTextField("",1,1,1,1);
        enterButton=addButton("Enter", 2,1,1,1);
        restartButton=addButton("Restart?",3,1,1,1);
        output=addTextArea("", 1,2,2,2);
        directions=addTextArea("                                             \n",3,2,1,1);
        theMan=addTextArea("                            \n        ",3,3,1,1);
        
        //changes the font
        Font f= new Font("Kristen ITC", Font.PLAIN, 20);
        output.setFont(f);
        
        Font g= new Font("Castellar", Font.PLAIN, 15);
        directions.setFont(g);
        
        Font h= new Font("Arial", Font.BOLD, 18);
        theMan.setFont(h);
        
        numOfGuesses = 0;
        numOfStrikes=0;
        numRight=0;
        words=new ArrayList();
        sizeOfAnswer=0;
        letters=new ArrayList();
        chosenWord = new ArrayList();
        answer = "";
        guess=" ";
        
        //wat appears of the man after each time u guess a letter wrong
        firstOffense=("\t------ \n\t |  | \n\t |  () \n\t |   \n\t | \n\t | \n\t===");
        secondOffense=("\t------ \n\t |  | \n\t |  () \n\t |  /\\ \n\t | \n\t | \n\t===");
        thirdOffense=("\t---------- \n\t |        |\n\t |       ()\n\t |      -/\\ \n\t | \n\t | \n\t===");
        fourthOffense=("\t---------- \n\t |       | \n\t |       () \n\t |      -/\\- \n\t | \n\t | \n\t===");
        fifthOffense=("\t---------- \n\t |       | \n\t |       () \n\t |      -/\\- \n\t |       | \n\t | \n\t===");
        sixthOffense=("---------- \n |       | \n |       () \n |      -/\\- \n |       || \n | \n===\n\nYOU'VE BEEN\n    HANGED!!!");
        
        //an array of 20 words
        words.add("whatever"); words.add("keyboard");
        words.add("actually"); words.add("alabama");
        words.add("sixteen"); words.add("computer");
        words.add("antelope"); words.add("screen");
        words.add("cowhide"); words.add("printer");
        words.add("telephone"); words.add("firetruck");
        words.add("sadistic"); words.add("habitat");
        words.add("empire"); words.add("restaurant");
        words.add("garbage"); words.add("hydrogen");
        words.add("outfit"); words.add("laptop");
        
        //an array of every letter to help prevent double guessing
        letters.add("a"); letters.add("b");
        letters.add("c"); letters.add("d");
        letters.add("e"); letters.add("f");
        letters.add("g"); letters.add("h");
        letters.add("i"); letters.add("j");
        letters.add("k"); letters.add("l");
        letters.add("m"); letters.add("n");
        letters.add("o"); letters.add("p");
        letters.add("q"); letters.add("r");
        letters.add("s"); letters.add("t");
        letters.add("u"); letters.add("v");
        letters.add("w"); letters.add("x");
        letters.add("y"); letters.add("z");
        
        getWord();
    }
    
       //makes the program react to pushing the enter button
    public void buttonClicked(JButton buttonObj){
        if (buttonObj == enterButton){
            processInputs();
            nameField.requestFocus();
               
        }
        else restart();
        }
    
    public void restart(){
        numOfGuesses = 0;
        numOfStrikes=0;
        numRight=0;
        words=new ArrayList();
        sizeOfAnswer=0;
        letters=new ArrayList();
        chosenWord = new ArrayList();
        answer = "";
        guess=" ";
        
        words.add("whatever"); words.add("keyboard");
        words.add("actually"); words.add("alabama");
        words.add("sixteen"); words.add("computer");
        words.add("antelope"); words.add("screen");
        words.add("cowhide"); words.add("printer");
        words.add("telephone"); words.add("firetruck");
        words.add("sadistic"); words.add("habitat");
        words.add("empire"); words.add("restaurant");
        words.add("garbage"); words.add("hydrogen");
        words.add("outfit"); words.add("laptop");
        
        //an array of every letter to help prevent double guessing
        letters.add("a"); letters.add("b");
        letters.add("c"); letters.add("d");
        letters.add("e"); letters.add("f");
        letters.add("g"); letters.add("h");
        letters.add("i"); letters.add("j");
        letters.add("k"); letters.add("l");
        letters.add("m"); letters.add("n");
        letters.add("o"); letters.add("p");
        letters.add("q"); letters.add("r");
        letters.add("s"); letters.add("t");
        letters.add("u"); letters.add("v");
        letters.add("w"); letters.add("x");
        letters.add("y"); letters.add("z");
        
        directions.setText("");
        output.setText("");
        theMan.setText("");
        nameField.requestFocus();
        Font h= new Font("Arial", Font.BOLD, 18);
        theMan.setFont(h);
        
        getWord();
    }
    
    //makes sure every letter can only be guessed once
    public boolean preventDoubleGuessing(){
        int index=letters.indexOf(guess);
        if (index==-1){
            return true;
        }
        else letters.remove(index);
        return false;
    }
    
    //picks a word at random
    public void getWord(){
        Random generator = new Random();
        //gets a word from the arraylist called words
        answer = (String)words.get(generator.nextInt(20));
        int sizeOfAnswer = answer.length();
        chosenWord = new ArrayList();
        lettersGuessedRight= new boolean[answer.length()];
        //makes every letter in the arraylist called ChosenWord
        //a different letter of the word that we chose
        for(int i=0; i<sizeOfAnswer; i++){
            char character=answer.charAt(i);
            chosenWord.add(new Character(character));
            }
        for(int j=0; j<sizeOfAnswer; j++){
                lettersGuessedRight[j]=false;
                output.append("_ ");
          }
        System.out.println(answer);
    }
   
    
    
    
    //displays more of the man every time u get a strike
    public void showMan(){
        if (numOfStrikes==1)
            theMan.setText(firstOffense);
        if (numOfStrikes==2)
            theMan.setText(secondOffense);
        if (numOfStrikes==3)
            theMan.setText(thirdOffense);
        if (numOfStrikes==4)
            theMan.setText(fourthOffense);
        if (numOfStrikes==5)
            theMan.setText(fifthOffense);
        if (numOfStrikes==6){
            theMan.setText(sixthOffense);
           Font h= new Font("Arial", Font.PLAIN, 18);
           theMan.setFont(h);
             }
    }
    
    
    //wat the program should do after the enter button is clicked
    private void processInputs(){
        
        boolean wordCorrect=false;
        
        while(numOfStrikes<6){
            //gets the letter that the user types in and sets it to guess
            String guess= nameField.getText();
           //checks if the letter has been used or not
            int index=letters.indexOf(guess.toLowerCase());
            //if the letter has already been guessed then it tells you to 
            //guess another letter
            if (index==-1){
                directions.append("       enter another letter\n ");
                nameField.setText("");
                break;
            }
            else letters.remove(index);
            
            
            //checks if the guess is i the answer
            int i;
            for (i=0; i<chosenWord.size();i++){
                //if the letter IS in the answer then it tells the user that
                // which letter he got and increases the counter of how 
                //many he's gotten right
                if(chosenWord.get(i).toString().equalsIgnoreCase(guess)){
                    wordCorrect=true;
                    directions.append("       You Got The "+POS[i]+" Letter\n");
                    numRight++;
                
                    lettersGuessedRight[i]=true;
                    output.setText("");
                     for(int k=0; k<answer.length(); k++){
                        if (lettersGuessedRight[k]){
                           //System.out.println(chosenWord.get(k));                    
                          output.append(""+answer.charAt(k)+" ");
                        }
                       else {
                          //System.out.println("_");
                          output.append("_ ");
                       }
                 }    
                    //checks to see if the number of guesses the user has
                    //guessed right is the same amount of letters in the 
                    //answer and if it is it tells the user he has won
                    //and outputs the word, then it stops the program
                     if(numRight==chosenWord.size()){
                    directions.setText("\n\n\n\n\n         YOU GOT IT! The word was:\n                         " + answer.toUpperCase());
                    theMan.setText("\n\n\n          ( )    ( )     ( )    ( )\n        --/\\-----/\\-----/\\-----/\\--\n" +
                    "          | |      | |     | |     | |\n         YOU'RE FREE!!!!");
                    break; 
                }
            }
            }
            nameField.setText("");
            if (wordCorrect)
                break;
            //if the letter is not in the word, it tells them to guess again
            //and increases the counter of how many mistakes he made. it also
            //displays the man
            if (!wordCorrect) {
                directions.append("       WROONNGG!! Guess Again\n");
                numOfStrikes++;
                showMan();
                break;
            }
           
        }
                           
        //the user only has six chances.  if he goes over this then the program
        //stops and tells the user he loses and what the word was
        if(numOfStrikes==6){
            directions.setText("\n\n\n\n\n         YOU LOSE!!! The word was:\n                       " + answer.toUpperCase());
       
        }
 } 
    
    
    public static void main(String[] args){
        HangmanGUI theGUI= new HangmanGUI();
        theGUI.setSize(900,600);
        theGUI.setVisible(true);
    }
    
}




