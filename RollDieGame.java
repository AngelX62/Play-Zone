package Game;
import java.util.Random;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class RollDieGame extends GameProperties {
    Random random = new Random();
    Scanner keyboard = new Scanner(System.in);
    
    private int prizeNumber, rounds, playerRoll; 
    private int userScore, cpuScore;
    private DecimalFormat formatter = new DecimalFormat("#0.00");
    
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

    public void dieGame() {
        RollDieGame die = new RollDieGame();
        // Create object to us synchronize
        Object lock = new Object();
    
        System.out.println("How many rounds do you want to play?");
        rounds = keyboard.nextInt();

        for(int i = 1; i <= rounds; i++) {
            // User and Cpu choice is Inherited from game.java because the methods are public
            
            // Generate random numbers from 1-6
            System.out.print("Enter your roll (1-6): ");
            playerRoll = keyboard.nextInt();

            die.setCpuChoice(random.nextInt(6) + 1);
            //ensure one thread can access the object at a time
            synchronized (lock) {
                try {
                    lock.wait(3000);
                } catch (InterruptedException e) {
                    System.out.println("Time error");
                }
            } 

            System.out.println("AI is rolling die...");
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

            /* 
            PrizeRps randomPrize = new PrizeRps("Fluffy");

            // Wins an engine part 
            if (die.getPrizeNumber() < 50) {
                randomPrize.winPrize();
                randomPrize.enginePart();
            
            // Wins ticket
            } else if(die.getPrizeNumber() >=50 && die.getPrizeNumber() <= 70) {
                randomPrize.ticket();
            // Wins cash    
            } else{
                System.out.println("You have won $" 
                + formatter.format(randomPrize.winCash()) + "!");  
            } */

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
/* 
    public void win() {
        // super keyword calls on the parent class, win method. 
        // Can also be win();
        super.win();
        
        boolean validInput = false;
        
        while(!validInput) {
            try {
                System.out.println("Enter a random prizeNumber 1-100");
                prizeNumber = keyboard.nextInt();

                if (prizeNumber < 1 || prizeNumber > 100) {
                    throw new IllegalArgumentException("Input must be an integer and within range");
                } 
                // If input is valid, break from loop
                validInput = true;
           
            } catch(InputMismatchException | IllegalArgumentException error) {
                System.out.println(error.getMessage());
                keyboard.nextLine();
            }
        }
    } */
}
