package gameMap;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import option.Menu;

public class TowerDefenseGame extends JFrame {
    private Map gameMap;
    private GamePanel gamePanel;
    private Menu menu;

    public TowerDefenseGame(int rows, int cols) {
        // Initialisation de la carte avec votre matrice
        int[][] terrainMatrix = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 4, 2, 2, 2, 2, 2, 2, 2, 2, 3 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        };
        gameMap = new Map(terrainMatrix);

        gamePanel = new GamePanel(gameMap);
        menu = new Menu(gamePanel); // Création de l'instance du menu avec une référence vers GamePanel

        // Ajout du panneau de jeu et du menu à la fenêtre
        add(gamePanel);
        add(menu);

        int cellSize = 80;
        int panelWidth = gameMap.getColumnCount() * cellSize;
        int panelHeight = gameMap.getRowCount() * cellSize;

        // Ajuster la taille du panneau de jeu
        gamePanel.setPreferredSize(new Dimension(panelWidth, panelHeight));

        // Configuration de la fenêtre de jeu
        setTitle("Tower Defense Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(panelWidth, panelHeight);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // Utilisation d'un layout pour disposer les composants verticalement
        pack();
    }

    public static void main(String[] args) {
        int rows = 10;
        int cols = 10;

        // Création de l'instance de la classe principale et affichage de la fenêtre
        TowerDefenseGame game = new TowerDefenseGame(rows, cols);
        game.setVisible(true);
    }
}

