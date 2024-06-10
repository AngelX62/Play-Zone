package Game;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class RollDieGame extends GameProperties {
    Random random = new Random();
    Scanner keyboard = new Scanner(System.in);
    
    private int userChoice, AiChoice;
    private int rounds, playerRoll; 
    private int userScore, AiScore;
    
    RollDieGame(){}
    
    /*
     * @param String to display what mode the user has landed on
     */
    public RollDieGame(String mode) {
        // Calls parent class (Polymorphism)
        super(mode);    
    }
    public void setPlayerRoll(int playerRoll) {
        this.playerRoll = playerRoll;
    }

    public int getPlayerRoll() {
        return playerRoll;
    }

    public int getUserScore() {
        return this.userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public int getAiScore() {
        return this.AiScore;
    }

    public void setAiScore(int AiScore) {
        this.AiScore = AiScore;
    }
    public void setUserChoice(int userChoice) {
        this.userChoice = userChoice;
    }

    public int getUserChoice() {
        return this.userChoice;
    }

    public void setAiChoice(int AiChoice) {
        this.AiChoice = AiChoice;
    }
    
    public int getAiChoice() {
        return this.AiChoice;
    }

    public void dieGame() {
        RollDieGame die = new RollDieGame();
        // Create object to us synchronize
        Object lock = new Object();
    
        System.out.println("How many rounds do you want to play?");
        rounds = keyboard.nextInt();

        for(int i = 1; i <= rounds; i++) {
            // User and Ai choice is Inherited from game.java because the methods are public
            // User enters a number from 1-6
            //System.out.print("Enter your roll (1-6): ");
            do {
                System.out.print("Enter your roll (1-6): ");
                try {
                    die.setUserChoice(keyboard.nextInt());
            
                    if (die.getUserChoice() < 0 || die.getUserChoice() > 6) {
                        throw new IllegalArgumentException("Only positive numbers & no number greater than 6");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }

            } while (die.getUserChoice() < 0 || die.getUserChoice() > 6);

            System.out.println("You rolled a " + die.getUserChoice());

            // Generate random numbers from 1-6
            die.setAiChoice(random.nextInt(6) + 1);

            System.out.println("AI is rolling die...");

            //ensure one thread can access the object at a time
            synchronized (lock) {
                try {
                    lock.wait(3000);
                } catch (InterruptedException e) {
                    System.out.println("Time error");
                }
            } 

            System.out.println("Rolled a " + die.getAiChoice());
             // Storing the scores from user and Ai
            if(die.getUserChoice() < die.getAiChoice()) {
                die.setAiScore(AiScore++);
                System.out.println("Points go to AI");
                
            } else if(die.getUserChoice() > die.getAiChoice()) {
                die.setUserScore(userScore++);
                System.out.println("Points go to User");
                
            } else if(die.getUserChoice() == die.getAiChoice()) {
                System.out.println("Tied, no points given to both parties");
            }
        }

         // Prints the final score    
        System.out.printf("User score: %d\nAi Score: %d\n", userScore, AiScore);

        if (die.getUserScore() > die.getAiScore()) {
            System.out.println();
            // When user wins, asks for prizeNumber
            die.win();

            storeScoreFinal(die.getUserScore());

            /* Class no longer has a default constructor (one argument)
             * Has an attribute of the prize
             * Creates object from PrizeRps
            */

        // Prints lose statment
        } else if(die.getAiScore() > die.getUserScore()) {
            // lose method from game class
            die.lose();
        
        // Prints tied statement    
        } else{
            // tie method from game class
            die.tie();
        }
    }
   
    public int[] storeScoreFinal(int getUserScore) {
        ArrayList<Integer> scores = new ArrayList<Integer>();
        // Uses .add() method and stores the score in the list
        scores.add(userScore);
        scores.add(AiScore);
    
        // Converted arraylist to array
        int[] convertArrayScore = new int[scores.size()];
        
        return convertArrayScore;
    }
}
