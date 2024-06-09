package Game;


// Parent class (Heirarchical inheritance)
public  abstract class GameProperties implements GameFeedback {
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
        System.out.println("Result: You win!");
    }

    public void lose() {
        System.out.println("Result: You lose!");
    }

    public void tie() {
        System.out.println("Result: It is a tie!");
    }
}
