package element;

import java.awt.Graphics;

public interface IEnemy {
    void setWidth(int width);
    void setHeight(int height);
    void draw(Graphics g);
    void moveRight(int speed);
    void moveLeft(int speed);
    void moveDown(int speed);
    void moveUp(int speed);
    int getX();
    int getY();
    void takeDamage(int damage);
    int getWidth();
    int getHeight();
}

