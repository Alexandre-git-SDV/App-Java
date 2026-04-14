package Fenetre;

import javax.swing.*;


public class Fenetre extends JFrame {
    public Fenetre() {
        super();
        this.setTitle("Mon application");
        this.setSize(600, 400);
// action au clic sur la croix : sortie du programme
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// affichage
        this.setVisible(true);
    }

    public void quitter() {
        this.dispose();
    }

    public void retournerEnArriere() {
        this.setVisible(false);
    }

    public void apply(){}
}