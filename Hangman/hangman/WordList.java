//Written By Yitzchak Weiser

import java.util.*;
public class WordList {
    //declare an array of all the words
    private String[] words = {"insert","moron","fool","bored","crazy","hello","nice","word","brother","senior","junior",
    "glasses","tiny","floor","code","internet","lake","sport","prince","aunt","seven","cartoon","trump","zebra","chalk",
    "random","person","movie","place","thing","rabbi","chest","hairy","clothes","close","open","closed","filled",
    "waste","find","easy","hard","pitch","base","come","twins","cracka","whatever","keyboard","actually","alabama","sixteen","computer","telephone","habitat","hangman","java" };
    
    
    
    public String getword() {
        //instantiate the random generator
        Random generator = new Random();
        //pick a random number 0-56
        int i = generator.nextInt(57);
        //returns the corresponding word in the array
        return words[i];
    }
    
    
}
