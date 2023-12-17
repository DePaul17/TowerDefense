package gameMap;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import element.AEnemy;
import element.PistoletBlaster;
import element.PistoletLaser;
import element.ATower;

import element.EnemyA;
import element.EnemyB;
import element.EnemyC;




public class GamePanel extends JPanel {
    private Map gameMap;
    private Image decorationImage;
    private Image towerImage;
    private Image enemyPathImage;
    private Image defensePointImage;
    private Image enemySpawnImage;
    private boolean placingTower = false;
    private int gold = 100;
    private Timer timer;
    private final int UPGRADE_COST = 1;
    

    private Image currentImage;

    private boolean[][] towerPlacements;
    private String selectedTowerType;
    private List<ATower> towerList; // Liste des tours placées
    private List<AEnemy> enemyList;
    
    private final int COST_PISTOLET_LASER = 30;
    private final int COST_PISTOLET_BLASTER = 50;
    private final int COST_PISTOLET_RAYON = 80;
    private final int COST_RAZOR = 99;



    public GamePanel(Map gameMap) {
        this.gameMap = gameMap;
        decorationImage = new ImageIcon("img/big-bang.png").getImage();
        towerImage = new ImageIcon("img/parquet.png").getImage();        defensePointImage = new ImageIcon("img/Fusée.png").getImage();
        enemySpawnImage = new ImageIcon("img/Téléportation.png").getImage();
        currentImage = new ImageIcon("img/1.jpg").getImage();

        selectedTowerType = "PistoletLaser"; // Par exemple, commencer avec la Tour A par défaut

        towerList = new ArrayList<>();
        enemyList = new ArrayList<>();
        

        

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int cellSize = Math.min(getWidth() / gameMap.getColumnCount(), getHeight() / gameMap.getRowCount());
                int clickedRow = e.getY() / cellSize;
                int clickedCol = e.getX() / cellSize;

                // Vérification pour un clic simple
                if (e.getClickCount() == 1) {
                    if (gameMap.getElementAt(clickedRow, clickedCol) == 1) {
                        placeTower(clickedCol * cellSize, clickedRow * cellSize, clickedCol);
                        repaint();
                    }
                }
                // Vérification pour un double clic
                else if (e.getClickCount() == 2) {
                    for (ATower tower : towerList) {
                        int towerX = tower.getX();
                        int towerY = tower.getY();
                        int towerWidth = tower.getWidth();
                        int towerHeight = tower.getHeight();

                        if (e.getX() >= towerX && e.getX() <= towerX + towerWidth &&
                            e.getY() >= towerY && e.getY() <= towerY + towerHeight) {
                            handleTowerUpgrade(tower);
                            return;
                        }
                    }
                }
            }
        });

        initializeEnemies();
        startEnemiesMovement();
        towerPlacements = new boolean[gameMap.getRowCount()][gameMap.getColumnCount()];
    }

    private void handleTowerUpgrade(ATower tower) {
        if (gold >= UPGRADE_COST) {
            tower.upgradeDamage();
            gold -= UPGRADE_COST;
            repaint();
        }
    }
    
    private void initializeEnemies() {
        int numRows = gameMap.getRowCount();
        int numCols = gameMap.getColumnCount();
        Math.min(getWidth() / numCols, getHeight() / numRows);
    
        // Ajoutez une logique pour réguler l'apparition des ennemis avec un délai entre chaque ajout
        // Vous pouvez ajuster les délais entre chaque type d'ennemi selon vos besoins
        Thread enemySpawnerThread = new Thread(() -> {
            while (true) {
                addEnemyTypeA();
                waitInterval(5000); // Attendre 5 secondes avant l'apparition du prochain type d'ennemi
                addEnemyTypeB();
                waitInterval(7000); // Attendre 7 secondes avant l'apparition du prochain type d'ennemi
                addEnemyTypeC();
                waitInterval(10000); // Attendre 10 secondes avant la répétition
            }
        });
        enemySpawnerThread.start();
    }
    private void addEnemyTypeA() {
    EnemyA newEnemyA = new EnemyA(0, 0, 80, "img/extraterrestre.png");
    newEnemyA.addToEnemyList(enemyList);
}

private void addEnemyTypeB() {
    EnemyB newEnemyB = new EnemyB(0, 0, 80, "img/extraterrestre (1).png");
    newEnemyB.addToEnemyList(enemyList);
}

private void addEnemyTypeC() {
    EnemyC newEnemyC = new EnemyC(0, 0, 80, "img/monstre.png");
    newEnemyC.addToEnemyList(enemyList);
}

private void waitInterval(int milliseconds) {
    try {
        Thread.sleep(milliseconds);
    } catch (InterruptedException ex) {
        ex.printStackTrace();
    }
}

    private void startEnemiesMovement() {
        new Thread(() -> {
            while (true) {
                moveEnemies();
                repaint();
                try {
                    Thread.sleep(70);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    private void moveEnemies() {
        for (int i = 0; i < enemyList.size(); i++) {
            AEnemy enemy = enemyList.get(i);
            enemy.moveRight(2); // Supposons que les ennemis se déplacent vers la droite
            
            boolean enemyExistsBeforeAttack = enemyList.contains(enemy); // Vérifie si l'ennemi existe avant l'attaque
            
            for (ATower tower : towerList) {
                if (isInRange(tower, enemy)) {
                    tower.attack(enemy); // Appel de la méthode attack de la tour pour infliger des dégâts à l'ennemi
                }
            }
            
            boolean enemyExistsAfterAttack = enemyList.contains(enemy); // Vérifie si l'ennemi existe après l'attaque
            
            if (enemyExistsBeforeAttack && !enemyExistsAfterAttack) {
                // L'ennemi a disparu
                gold += 10; // Augmenter la quantité de Gold lorsque l'ennemi disparaît
                // Vous pouvez ajuster le montant de l'or gagné selon vos besoins
            }
        }
        repaint(); // Redessinez le panneau après le déplacement des ennemis
    }
    
    
    // Méthode pour vérifier si un ennemi est dans la portée d'une tour
    private boolean isInRange(ATower tower, AEnemy enemy) {
        int distanceX = Math.abs(tower.getX() - enemy.getX()); // Calcul de la différence horizontale
        int distanceY = Math.abs(tower.getY() - enemy.getY()); // Calcul de la différence verticale
        
        // Calcul de la distance euclidienne entre la tour et l'ennemi
        double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
        
        // Vérification si la distance est inférieure ou égale à la portée de la tour
        return distance <= tower.getRangeInPixels();
    }
    

    public void placeTower(int x, int y, int rangeInPixels) {
        int cellX = x / 80;
        int cellY = y / 80;

        placingTower = true;
        timer = new Timer(3000, e -> {
            placingTower = false;
            repaint();
            timer.stop();
        });
        timer.start();
        repaint();
    
        // Vérifie si le joueur a assez de Gold pour acheter la tour
        boolean canAffordTower = false;
        switch (selectedTowerType) {
            case "PistoletLaser":
                canAffordTower = gold >= COST_PISTOLET_LASER;
                break;
            case "PistoletBlaster":
                canAffordTower = gold >= COST_PISTOLET_BLASTER;
                break;
            case "PistoletRayon":
                canAffordTower = gold >= COST_PISTOLET_RAYON;
                break;
            case "Razor":
                canAffordTower = gold >= COST_RAZOR;
                break;
            // ... vérifiez pour les autres types de tours
            default:
                break;
        }
    
        if (canAffordTower && !towerPlacements[cellY][cellX] && gameMap.getElementAt(cellY, cellX) == 1) {

            ATower newTower = null; // Initialisation avec une valeur par défaut

            // Déduction du Gold pour acheter la tour et création de la tour
            switch (selectedTowerType) {
                case "PistoletLaser":
                    gold -= COST_PISTOLET_LASER;
                    newTower = new PistoletLaser(x, y, "img/pistolet-laser.png", 80, 80, cellX, cellY);
                    break;
                case "PistoletBlaster":
                    gold -= COST_PISTOLET_BLASTER;
                    newTower = new PistoletBlaster(x, y, "img/pistolet-blaster.png", 80, 80, cellX, cellY);
                    break;
                case "PistoletRayon":
                    gold -= COST_PISTOLET_RAYON;
                    newTower = new PistoletBlaster(x, y, "img/pistolet-rayons.png", 80, 80, cellX, cellY);
                    break;
                case "Razor":
                    gold -= COST_RAZOR;
                    newTower = new PistoletBlaster(x, y, "img/Razor.png", 80, 80, cellX, cellY);
                    break;
                // ... déduction du Gold pour les autres types de tours
                default:
                    break;
            }
    
            // Vérifie si newTower n'est pas null avant de l'ajouter à la liste des tours placées
            if (newTower != null) {
                towerList.add(newTower);
                towerPlacements[cellY][cellX] = true; // Marquer la case comme occupée par une tour
                repaint(); // Redessinez le panneau pour afficher la nouvelle tour
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(currentImage, 0, 0, getWidth(), getHeight(), this);

        int numRows = gameMap.getRowCount();
        int numCols = gameMap.getColumnCount();
        int cellSize = Math.min(getWidth() / numCols, getHeight() / numRows);
        
        // Dessinez le terrain en fonction de la matrice
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int element = gameMap.getElementAt(i, j);
                Image image = null;
                // Sélectionnez l'image en fonction de la valeur de l'élément
                switch (element) {
                    case 0:
                        image = decorationImage; // Décoration
                        break;
                    case 1:
                        image = towerImage; // Emplacement des tours
                        break;
                    case 2:
                        image = enemyPathImage; // Chemin des ennemis
                        break;
                    case 3:
                        image = defensePointImage; // Point à défendre
                        break;
                    case 4:
                        image = enemySpawnImage; // Apparition des ennemis
                        break;
                    default:
                        // Gérez le cas par défaut ou utilisez une image spécifique
                        break;
                }
                // Dessinez l'image à la place du rectangle
                if (image != null) {
                    g.drawImage(image, j * cellSize, i * cellSize, cellSize, cellSize, this);
                }
                // Récupération des dimensions du panneau de jeu
                
                String goldText = "Gold: " + gold;
                int fontSize = 20; // Taille de la police
                g.setFont(new Font("Arial", Font.PLAIN, fontSize)); // Modification de la police avec une taille plus grande
                int textHeight = g.getFontMetrics().getHeight();
            
                // Position pour afficher le texte en bas à gauche de l'écran
                int textX = 10; // 10 pixels de marge à gauche
                int textY = getHeight() - textHeight - 10; // 10 pixels de marge en bas
            
                // Affichage du nombre de gold en noir et avec une taille de police plus grande en bas à gauche
                g.setColor(Color.YELLOW);
                g.drawString(goldText, textX, textY);
            }
            
        }

        for (ATower tower : towerList) {
            tower.draw(g);
        }

        for (AEnemy enemy : enemyList) {
            enemy.draw(g);
        }
        
        for (ATower tower : towerList) {
            if (placingTower && tower == towerList.get(towerList.size() - 1)) {
                int centerX = tower.getX() + (cellSize / 2); // Calcul du centre X de la tour
                int centerY = tower.getY() + (cellSize / 2); // Calcul du centre Y de la tour
                
                int rangeX = centerX - tower.getRangeInPixels(); // Utilisation de la portée de la tour en pixels
                int rangeY = centerY - tower.getRangeInPixels(); // Utilisation de la portée de la tour en pixels
                
                g.setColor(new Color(255, 0, 0, 100)); // Couleur rouge semi-transparente
                g.fillOval(rangeX, rangeY, tower.getRangeInPixels() * 2, tower.getRangeInPixels() * 2);
            }
            // Autres dessins de tours...
        }
        
    }

    public void setSelectedTowerType(String selectedTowerType) {
        this.selectedTowerType = selectedTowerType;
    }

    
}