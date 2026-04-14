package Fenetre;

import javax.swing.*;
import java.awt.BorderLayout;

public class Modale extends JDialog {
    private final JLabel label;
    private final JButton valider;

    public Modale(JFrame frame) {
        super(frame, true);
        setTitle("Infos");
        setSize(250, 120);
        setLocationRelativeTo(frame);
        setLayout(new BorderLayout());

        label = new JLabel("Infos / Erreur", JLabel.CENTER);

        valider = new JButton("OK");
        valider.addActionListener(event -> dispose());

        add(label, BorderLayout.CENTER);
        add(valider, BorderLayout.SOUTH);
    }

    public void afficherMessage(String message) {
        label.setText(message);
        setVisible(true);
    }
}
