package element;

import java.util.List;

public class EnemyC extends AEnemy {

    public EnemyC(int x, int y, int initialHealth, String imagePath) {
        super(x, y, 3000, "img/monstre.png");
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
