package element;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;

public abstract class ATower implements ITower {
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;
    protected int damage;
    protected int cost;
    protected int rangeInPixels;

    public ATower(int x, int y, String imagePath, int width, int height, int damage) {
        // Initialisation des attributs
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.image = new ImageIcon(imagePath).getImage();
    }

    public abstract void attack(List<AEnemy> enemies, int damage);

    public boolean isInRange(AEnemy enemy) {
        int distanceX = Math.abs(getX() - enemy.getX());
        int distanceY = Math.abs(getY() - enemy.getY());
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        return distance <= rangeInPixels;
    }

    public void attack(AEnemy enemy) {
        if (isInRange(enemy)) {
            // L'ennemi est dans la portée, donc attaque
            enemy.takeDamage(damage); // Appliquer des dégâts à l'ennemi
        }
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void draw(Graphics g) {
        // Dessinez la tour à la position spécifiée avec la taille spécifiée
        g.drawImage(image, x, y, width, height, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Object getType() {
        return null;
    }

    public void setRange(int newRange) {
        this.rangeInPixels = newRange; // Méthode pour définir la portée de la tour en pixels
    }

    public int getRangeInPixels() {
        return rangeInPixels; // Méthode pour obtenir la portée de la tour en pixels
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void upgradeDamage() {
        damage += 1000;
    }

}

