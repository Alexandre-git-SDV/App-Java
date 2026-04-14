import Fenetre.Fenetre;
import Fenetre.Modale;
import historique.Historique;
import historique.Saisie;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        Fenetre fenetre = new Fenetre();
        fenetre.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Ajouter text
        JLabel title = new JLabel("Mon application");
        c.gridx = 1;
        c.gridy = 0;
        fenetre.add(title,c);

        JLabel reprtoireText = new JLabel("Répertoire recherche");
        c.gridx = 0;
        c.gridy = 1;
        fenetre.add(reprtoireText,c);

        JLabel rechercheText = new JLabel("Texte recherché");
        c.gridx = 0;
        c.gridy = 2;
        fenetre.add(rechercheText,c);

        // Ajouter un input texte
        JTextField repoertoireInput = new JTextField("saisie répertoire");
        c.gridx = 1;
        c.gridy = 1;
        fenetre.add(repoertoireInput,c);

        JTextField textInput = new JTextField("saisie répertoire");
        c.gridx = 1;
        c.gridy = 2;
        fenetre.add(textInput,c);

        // Ajouter un menu et sa bar
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Application");
        menu.setMnemonic('m');

        //Ajout d'item au  menu
        JMenuItem menuItem1 = new JMenuItem("Retour");
        menuItem1.setMnemonic('r');
        menuItem1.addActionListener(e -> fenetre.retournerEnArriere());
        menu.add(menuItem1);


        JMenuItem menuItem2 = new JMenuItem("Quitter");
        menuItem2.setMnemonic('q');
        menuItem2.addActionListener(e -> fenetre.quitter());
        menu.add(menuItem2);

        //Ajout du menu dans la bar
        bar.add(menu);

        //Ajout de la bar dans la fenetre
        fenetre.setJMenuBar(bar);

        //Ajout des bouton
        JButton button = new JButton("Appliquer");
        Modale modale = new Modale(fenetre);
        button.addActionListener(event -> {modale.appliquer();});
        c.gridx = 1;
        c.gridy =3;
        fenetre.add(button,c);

        fenetre.setVisible(true);

        // TODO Test manuel pour historique / A remplacer par le lancement de la fênetre
        /*Historique historique = new Historique();

        historique.sauvegarder("C:/docs", "rapport");
        historique.sauvegarder("C:/docs/archive", "compte rendu");

        Saisie etatRestaure = historique.restaurerPrecedent();
        System.out.println("Repertoire : " + etatRestaure.getNomRepertoire());
        System.out.println("Texte : " + etatRestaure.getTexteRecherche());
         */
    }
}
