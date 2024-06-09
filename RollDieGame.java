package Game;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class RollDieGame extends GameProperties {
    Random random = new Random();
    Scanner keyboard = new Scanner(System.in);
    
    private int userChoice, cpuChoice;
    private int rounds, playerRoll; 
    private int userScore, cpuScore;
    
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

    public int getCpuScore() {
        return this.cpuScore;
    }

    public void setCpuScore(int cpuScore) {
        this.cpuScore = cpuScore;
    }
    public void setUserChoice(int userChoice) {
        this.userChoice = userChoice;
    }

    public int getUserChoice() {
        return this.userChoice;
    }

    public void setCpuChoice(int cpuChoice) {
        this.cpuChoice = cpuChoice;
    }
    
    public int getCpuChoice() {
        return this.cpuChoice;
    }

    public void dieGame() {
        RollDieGame die = new RollDieGame();
        // Create object to us synchronize
        Object lock = new Object();
    
        System.out.println("How many rounds do you want to play?");
        rounds = keyboard.nextInt();

        for(int i = 1; i <= rounds; i++) {
            // User and Cpu choice is Inherited from game.java because the methods are public
            // User enters a number from 1-6
            //System.out.print("Enter your roll (1-6): ");
            do {
                System.out.print("Enter your roll (1-6): ");
                try {
                    playerRoll = keyboard.nextInt();
            
                    if (playerRoll < 0 || playerRoll > 6) {
                        throw new IllegalArgumentException("Only positive numbers & no number greater than 6");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }

            } while (playerRoll < 0 || playerRoll > 6);

            System.out.println("You rolled a " + playerRoll);

            // Generate random numbers from 1-6
            die.setCpuChoice(random.nextInt(6) + 1);

            System.out.println("AI is rolling die...");
            
            //ensure one thread can access the object at a time
            synchronized (lock) {
                try {
                    lock.wait(3000);
                } catch (InterruptedException e) {
                    System.out.println("Time error");
                }
            } 

            System.out.println("Rolled a " + die.getCpuChoice());
             // Storing the scores from user and cpu
            if(die.getUserChoice() < die.getCpuChoice()) {
                die.setCpuScore(cpuScore++);
                System.out.println("Points go to user");
                
            } else if(die.getUserChoice() > die.getCpuChoice()) {
                die.setUserScore(userScore++);
                System.out.println("Points go to AI");
                
            } else if(die.getUserChoice() == die.getCpuChoice()) {
                System.out.println("Result: Tied, no points given to both parties");
            }
        }

         // Prints the final score    
        System.out.printf("User score: %d\nCPU Score: %d\n", userScore, cpuScore);

        if (die.getUserScore() > die.getCpuScore()) {
            System.out.println();
            // When user wins, asks for prizeNumber
            die.win();

            storeScoreFinal(die.getUserScore());

            /* Class no longer has a default constructor (one argument)
             * Has an attribute of the prize
             * Creates object from PrizeRps
            */

        // Prints lose statment
        } else if(die.getCpuScore() > die.getUserScore()) {
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
        scores.add(cpuScore);
    
        // Converted arraylist to array
        int[] convertArrayScore = new int[scores.size()];
        
        return convertArrayScore;
    }
}
