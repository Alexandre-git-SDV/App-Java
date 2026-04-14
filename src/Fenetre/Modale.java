package Fenetre;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Modale extends JDialog {
    private JButton valider;
    public Modale(JFrame frame) {
// paramètres : fenêtre parente, verrouillage
        super(frame, true);
        this.setTitle("Infos");
        this.setSize(200, 100);

        //Mise en place du layout
        this.setLayout(new BorderLayout());

        //Ajout de texte par défaut
        JLabel label = new JLabel("Infos / Erreur", JLabel.CENTER);

        //Le bouton pour fermer la pop up
        this.valider = new JButton("OK");
        this.valider.addActionListener(event -> this.dispose());

        //Organisation des éléments
        this.add(label, BorderLayout.CENTER);
        this.add(valider, BorderLayout.SOUTH);
    }
    public void appliquer() {
        this.setVisible(true);
    }
}
