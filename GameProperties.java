package Game;


// Parent class (Heirarchical inheritance)
public  abstract class GameProperties implements GameFeedback {
    private int prizeNumber, userChoice, cpuChoice;
    private String mode;
    private static String gameSelect;

    // no args constuctor
    GameProperties() {}
    
    GameProperties(String mode) {
        this.mode = mode;      
    }
    // Overriding the toString() method
    @Override
    public String toString() {
        return mode;
    } 

    public void setGameSelect(String gameSelect) {
        GameProperties.gameSelect = gameSelect;
    }

    public String getGameSelect() {
        return gameSelect;
    } 
    
    public void win() {
        System.out.println("You win!");
    }

    public void lose() {
        System.out.println("You lose!");
    }

    public void tie() {
        System.out.println("It is a tie!");
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
    // We do not need a setter, number variable name is already declared
    public int getPrizeNumber() {
        return this.prizeNumber;
    } 
}
