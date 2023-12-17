package element;

public class Player {
    
    private int gold;

    private static Player instance;

    private Player() {
        // Initialisation du joueur avec 0 pièces d'or
        this.gold = 0;
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public void addToGold(int goldAmount) {
        // Ajoute la quantité d'or au stock actuel
        this.gold += goldAmount;
    }

    public int getGold() {
        return gold;
    }
}
