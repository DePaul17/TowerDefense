package element;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public abstract class AEnemy {
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;
    private int maxHealth;
    private int health;
    protected boolean isVisible;
    protected List<AEnemy> enemyList = new ArrayList<>();
    
    public AEnemy(int enx, int eny, int initialHealth, String imagePath) {
        this.x = enx;
        this.y = 250;
        this.width = 50; // Largeur initiale
        this.height = 50; // Hauteur initiale
        this.image = new ImageIcon(imagePath).getImage(); // Utilisation de l'imagePath passé en paramètre
        this.health = initialHealth; // Initialisation de la santé
        this.maxHealth = initialHealth; // Initialisation de la santé maximale
        this.isVisible = true;
    }

    public abstract void move();

    public abstract void addToEnemyList(List<AEnemy> enemyList);

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void draw(Graphics e){
        if (isVisible) {
            // Dessinez l'image de l'ennemi seulement s'il est visible
            e.drawImage(image, x, y, width, height, null);
            // Dessinez la barre de vie
            e.setColor(Color.RED);
            e.fillRect(x, y - 10, (int) ((double) health / maxHealth * width), 5); // Barre de vie au-dessus de l'ennemi
        }
    }

    public void moveRight(int speed) {
        x += speed;
    }

    public void moveLeft(int speed) {
        x -= speed;
    }

    public void moveDown(int speed) {
        y += speed;
    }

    public void moveUp(int speed) {
        y -= speed;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            setVisible(false);
            int goldReward = 10;
            addGoldToPlayer(goldReward);
        }
    }
    

    public void addGoldToPlayer(int goldReward) {
        Player player = Player.getInstance();
        player.addToGold(goldReward);
    }
    
    public List<AEnemy> getEnemyList() {
        return enemyList;
    }


}
