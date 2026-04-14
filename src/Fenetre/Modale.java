package Fenetre;

import java.awt.BorderLayout;
import javax.swing.*;

public class Modale extends JDialog {
    private final JLabel label;
    private final JButton valider;

    public Modale(JFrame frame) {
        // Initialisation de la modale
        super(frame, true);
        setTitle("Infos");
        setSize(250, 120);
        setLocationRelativeTo(frame);
        setLayout(new BorderLayout());

        // Création des éléments de la modale
        label = new JLabel("Infos / Erreur", JLabel.CENTER);

        valider = new JButton("OK");
        // Ferme la modale lorsque le bouton "OK" est cliqué
        valider.addActionListener(event -> dispose());

        add(label, BorderLayout.CENTER);
        add(valider, BorderLayout.SOUTH);
    }

    public void afficherMessage(String message) {
        label.setText(message);
        setVisible(true);
    }
}
