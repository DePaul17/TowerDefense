package option;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import gameMap.GamePanel;

public class Menu extends JPanel implements ActionListener {
    private GamePanel gamePanel;

    public Menu(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        // Créez des boutons pour chaque type de tour disponible
        JButton pistoletLaserButton = new JButton("Pistolet Laser");
        JButton pistoletBlasterButton = new JButton("Pistolet Blaster");
        JButton pistoletRayonButton = new JButton("Pistolet Rayon");
        JButton razorButton = new JButton("Razor");

        // Ajoutez des écouteurs d'événements aux boutons
        pistoletLaserButton.addActionListener(this);
        pistoletBlasterButton.addActionListener(this);
        pistoletRayonButton.addActionListener(this);
        razorButton.addActionListener(this);

        // Ajoutez les boutons au panneau de menu
        add(pistoletLaserButton);
        add(pistoletBlasterButton);
        add(pistoletRayonButton);
        add(razorButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedTowerType = "";

        // Détectez quel bouton a été cliqué et obtenez le type de tour correspondant
        if (e.getActionCommand().equals("Pistolet Laser")) {
            selectedTowerType = "PistoletLaser";
        } else if (e.getActionCommand().equals("Pistolet Blaster")) {
            selectedTowerType = "PistoletBlaster";
        } else if (e.getActionCommand().equals("Pistolet Rayon")) {
            selectedTowerType = "PistoletRayon";
        } else if (e.getActionCommand().equals("Razor")) {
            selectedTowerType = "Razor";
        }

        // Passez le type de tour sélectionné à GamePanel
        gamePanel.setSelectedTowerType(selectedTowerType);
    }
}
