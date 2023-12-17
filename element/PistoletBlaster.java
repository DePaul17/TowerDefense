package element;

import java.util.List;

public class PistoletBlaster extends ATower {

public PistoletBlaster(int x, int y, String imagePath, int width, int height, int cellX, int cellY) {
        super(x, y, imagePath, width, height, 20); // Exemple de statistiques pour un pistolet laser
        setRange(100);
    }

    @Override
    public void attack(List<AEnemy> enemies, int damage) {
        for (AEnemy enemy : enemies) {
            if (isInRange(enemy)) {
                // L'ennemi est dans la portée, infliger des dégâts
                enemy.takeDamage(damage);
                // Vous pouvez également ajouter d'autres logiques ici, comme ralentir l'ennemi, etc.
            }
        }
    }
public boolean isInRange(AEnemy enemy) {
        int enemyX = enemy.getX();
        int enemyY = enemy.getY();

        // Calculer la distance entre la tour et l'ennemi
        int distance = (int) Math.sqrt(Math.pow((enemyX - getX()), 2) + Math.pow((enemyY - getY()), 2));

        // Si la distance est inférieure ou égale à la portée de la tour, l'ennemi est dans la portée
        return distance <= getRangeInPixels();
    }

    @Override
    public int getRangeInPixels() {
        return rangeInPixels;
    }
}