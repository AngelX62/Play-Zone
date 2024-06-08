package Game;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class RockPaperScissor extends GameProperties {
    
    private Random random = new Random();
    private Scanner keyboard = new Scanner(System.in);
    private String[] abbreviation = {"Rock", "Paper", "Scissor"};
    private String aiMove;
    private char playerMove;
    
    // Calls parent class
    public RockPaperScissor(String mode) {
        super(mode);
        aiMove = abbreviation[random.nextInt(abbreviation.length)];
    }

    public void setPlayerMove(char playerMove) {
        this.playerMove = playerMove;
    }

    public char getPlayerMove() {
        return playerMove;
    }

    public void setaiMove(String aiMove) {
        this.aiMove = aiMove;
    }

    public String getaiMove() {
        return aiMove;
    }

    public void rockPapScissor() {
        // Uses hashset to store the available inputs, no duplicates
        Set<Character> availableMoves = new HashSet<>(Arrays.asList('r', 'p', 's'));
        char move;
        boolean validMoves = false; 
        
        // Calls the toString method, and print the string above. toString is overriden
        System.out.println("\n" + this.toString());
        // Will keep printing until user has a valid input
        while(!validMoves) {
            System.out.println("Enter your move: (r, p, or s)");
            move = keyboard.next().charAt(0);
            
            if(availableMoves.contains(move)) {
                this.setPlayerMove(move);
               // this.setaiMove(move);
                validMoves = true;

                System.out.println("The ai played " + this.getaiMove());
            } else {
                validMoves = false;              
            } 
        }

        switch(this.getPlayerMove()) {

            case 'r': 
                
                if(this.getaiMove().equals("Paper") ) {
                    this.lose();
                
                }else if(this.getaiMove().equals("Scissor")) {
                    this.win();
                
                }else {
                    this.tie();
                }
                break;
            case 'p':

                if(this.getaiMove().equals("Rock")) {
                    this.win();
                
                }else if(this.getaiMove().equals("Scissor")) {
                    this.lose();
                
                }else {
                    this.tie();
                }

                break;

            case 's':

                if(this.getaiMove().equals("Paper")) {
                    this.win();
                
                }else if(this.getaiMove().equals("Rock")) {
                    this.lose();
                
                }else {
                    this.tie();
                }   
                break;
        }
    } 
}
