package element;

import java.util.List;

public class EnemyB extends AEnemy {

    public EnemyB(int x, int y, int initialHealth, String imagePath) {
        super(x, y, 3000, "img/extraterrestre (1).png");
    }

    @Override
    public void move() {
        moveRight(1);
    }

    @Override
    public void addToEnemyList(List<AEnemy> enemyList) {
        
        enemyList.add(this);
    }
}
