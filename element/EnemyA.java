package element;

import java.util.List;

public class EnemyA extends AEnemy {

    public EnemyA(int x, int y, int initialHealth, String imagePath) {
        super(x, y, 3000, "img/extraterrestre.png");
    }

    @Override
    public void move() {
        moveRight(1);
    }

    @Override
    public void addToEnemyList(List<AEnemy> enemyList) {
        // Ajoutez l'instance d'EnemyA à la liste enemyList
        enemyList.add(this);
    }
}

