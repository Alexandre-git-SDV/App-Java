package Fenetre;

import historique.Historique;
import historique.Saisie;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {
    private final Historique historique = new Historique();
    private final JTextField champRepertoire = new JTextField(20);
    private final JTextField champTexte = new JTextField(20);
    private final JMenuItem menuRetour = new JMenuItem("Retour");
    private final Modale modale = new Modale(this);

    public Fenetre() {
        super("Mon application");
        initialiserFenetre();
        initialiserMenu();
        initialiserContenu();
        mettreAJourEtatRetour();
    }

    private void initialiserFenetre() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    private void initialiserMenu() {
        JMenuBar barreMenu = new JMenuBar();
        JMenu menuApplication = new JMenu("Application");

        menuRetour.addActionListener(event -> retournerEnArriere());

        JMenuItem menuQuitter = new JMenuItem("Quitter");
        menuQuitter.addActionListener(event -> quitter());

        menuApplication.add(menuRetour);
        menuApplication.add(menuQuitter);
        barreMenu.add(menuApplication);
        setJMenuBar(barreMenu);
    }

    private void initialiserContenu() {
        JPanel panneau = new JPanel(new GridBagLayout());
        GridBagConstraints contraintes = new GridBagConstraints();
        contraintes.insets = new Insets(8, 8, 8, 8);
        contraintes.anchor = GridBagConstraints.WEST;
        contraintes.fill = GridBagConstraints.HORIZONTAL;

        contraintes.gridx = 0;
        contraintes.gridy = 0;
        panneau.add(new JLabel("Repertoire recherche :"), contraintes);

        contraintes.gridx = 1;
        panneau.add(champRepertoire, contraintes);

        contraintes.gridx = 0;
        contraintes.gridy = 1;
        panneau.add(new JLabel("Texte recherche :"), contraintes);

        contraintes.gridx = 1;
        panneau.add(champTexte, contraintes);

        JButton boutonAppliquer = new JButton("Appliquer");
        boutonAppliquer.addActionListener(event -> appliquer());

        contraintes.gridx = 1;
        contraintes.gridy = 2;
        contraintes.anchor = GridBagConstraints.EAST;
        panneau.add(boutonAppliquer, contraintes);

        setContentPane(panneau);
    }

    public void quitter() {
        dispose();
    }

    public void retournerEnArriere() {
        Saisie saisie = historique.restaurerPrecedent();
        champRepertoire.setText(saisie.getNomRepertoire());
        champTexte.setText(saisie.getTexteRecherche());
        mettreAJourEtatRetour();
    }

    public void appliquer() {
        historique.sauvegarder(champRepertoire.getText(), champTexte.getText());
        mettreAJourEtatRetour();
        modale.afficherMessage("Saisie sauvegardee.");
    }

    private void mettreAJourEtatRetour() {
        menuRetour.setEnabled(historique.peutRestaurer());
    }
}
