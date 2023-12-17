package element;

import java.util.List;
import java.awt.Graphics;

public interface ITower {
    void attack(List<AEnemy> enemies, int damage);
    boolean isInRange(AEnemy enemy);
    void attack(AEnemy enemy);
    void setWidth(int width);
    void setHeight(int height);
    void draw(Graphics g);
    int getX();
    int getY();
    Object getType();
    void setRange(int newRange);
    int getRangeInPixels();
}