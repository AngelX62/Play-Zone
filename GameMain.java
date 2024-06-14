package Game;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;
public class GameMain {
    public static Scanner scan = new Scanner(System.in);

     public static void main(String[] args) {
        
        // Creates gameType object to input complex or simple
        GameProperties gameType = null;
        
        // Store score
        ArrayList<Integer> totalScore = new ArrayList<>();
        try {
            do {
                String dialogue = JOptionPane.showInputDialog(null, 
                "Choose a game mode: Die roll or RPS?"); 

                switch(dialogue) {
                    case "die roll":
                        gameType = new RollDieGame("Single Player");
                        break;

                    case "rps":
                        gameType = new RockPaperScissor("Single Player");
                        break;
                    default:
                        System.out.println("Invalid choice. Exiting..");
                        System.exit(0);
                }
            
                modeType(gameType, scan, totalScore);
            
            } while(replay(scan));

        } catch(InputMismatchException e) {
            System.out.println("Cannot be a number");
        }

       
        // Prints total scores from games played
        System.out.println("end game: " + totalScore);
            
        scan.close();
    }
    
    public static void modeType(GameProperties gameType, Scanner scan, ArrayList<Integer> totalScore) { 
        //if(gameType.getGameSelect().equalsIgnoreCase("Die roll")) {
        if (gameType instanceof RollDieGame) {


            RollDieGame die = new RollDieGame("Welcome to the die game");
            
            // Since we implemented inheritance, RollDieGame class inherits methods from the parent class
            System.out.println("\n" + die.toString()); 
            
            // Calls game method
           // die.dieGame();
            
           ((RollDieGame) gameType).dieGame();

            // Stores user score
            totalScore.add(die.getUserScore());
            
            // prints score
            System.out.println(totalScore);
                  
        } else if(gameType instanceof RockPaperScissor) {
            // Calls method from RockPaperScissor class and passing scan object ;
            RockPaperScissor rockScissor = new RockPaperScissor("Welcome to the rock, paper, and scissosr game!");
            System.out.println("\n" + rockScissor.toString());

            ((RockPaperScissor) gameType).rockPapScissor();
        } 
    }

    static ArrayList<Integer> endScores(RollDieGame die) {
        ArrayList<Integer> totalScore = new ArrayList<>();
        
        totalScore.add(die.getUserScore()); // Add user score to totalScore list
        totalScore.add(die.getAiScore()); // Add CPU score to totalScore list
    
        return totalScore;
    }

    public static boolean replay(Scanner scan) {
        System.out.println("Play again?");
        
        return scan.nextLine().trim().equalsIgnoreCase("y");
    }
}
    


