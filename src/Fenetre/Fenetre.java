package Fenetre;

import export.ExportService;
import historique.Historique;
import historique.Saisie;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import javax.swing.*;
import recherche.Recherche;

public class Fenetre extends JFrame {
    private final ExportService exportService = new ExportService();
    private final Historique historique = new Historique();
    private final JTextField champRepertoire = new JTextField(20);
    private final JTextField champTexte = new JTextField(20);
    private final JMenuItem menuRetour = new JMenuItem("Retour");
    private final Modale modale = new Modale(this);

    public Fenetre() {
        //Titre de la fenetre
        super("Mon application");
        //Initialisation des élements de la fenetre
        initialiserFenetre();
        initialiserMenu();
        initialiserContenu();
        mettreAJourEtatRetour();
    }

    //Création de la fenetre
    private void initialiserFenetre() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    //Création du menu de la fenetre
    private void initialiserMenu() {
        JMenuBar barreMenu = new JMenuBar();
        JMenu menuApplication = new JMenu("Application");

        //Ajout d'évènements à réaliser lorsque les éléments du menu sont sélectionnés
        menuRetour.addActionListener(event -> retournerEnArriere());

        JMenuItem menuQuitter = new JMenuItem("Quitter");
        menuQuitter.addActionListener(event -> quitter());

        menuApplication.add(menuRetour);
        menuApplication.add(menuQuitter);
        barreMenu.add(menuApplication);
        setJMenuBar(barreMenu);
    }

    private void initialiserContenu() {
        //Mise en place des inputs et de leurs textes
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

        //Ajout d'un évènement pour réaliser la recherche
        JButton boutonAppliquer = new JButton("Appliquer");
        boutonAppliquer.addActionListener(event -> appliquer());

        contraintes.gridx = 1;
        contraintes.gridy = 2;
        contraintes.anchor = GridBagConstraints.EAST;
        panneau.add(boutonAppliquer, contraintes);

        setContentPane(panneau);
    }

    //Fonction pour fermer la fenetre
    public void quitter() {
        dispose();
    }

    //Afficher les anciens input sauvegardé dans l'historique
    public void retournerEnArriere() {
        Saisie saisie = historique.restaurerPrecedent();
        champRepertoire.setText(saisie.getNomRepertoire());
        champTexte.setText(saisie.getTexteRecherche());
        mettreAJourEtatRetour();
    }

    //Fonction pour ajouter les input dans l'historique et afficher les résultats de la recherche dans la console
    public void appliquer() {
        historique.sauvegarder(champRepertoire.getText(), champTexte.getText());
        mettreAJourEtatRetour();

        // Appel de rechercherEtAfficher pour afficher les resultats de la recherche dans la console
        try {
            List<String> resultats = rechercher();
            exportService.exporter(resultats);
            modale.afficherMessage("Recherche terminee. Resultats exportes.");
            }catch (IOException e) {
                    modale.afficherMessage("Erreur lors de l'export : " + e.getMessage());
            }
        }

    // Fonction pour réaliser la recherche et afficher les résultats dans la console
    private List<String> rechercher() {
        List<String> resultats = Recherche.getInstance()
                .rechercher(champRepertoire.getText(), champTexte.getText());

        System.out.println("=== Resultats de la recherche ===");
        resultats.forEach(System.out::println);
        System.out.println("=================================");
        return resultats;
    }

    //Active / Desactive le menu selon l'état de l'historique
    private void mettreAJourEtatRetour() {
        menuRetour.setEnabled(historique.peutRestaurer());
    }
}
