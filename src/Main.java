import Fenetre.Fenetre;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Lancement de l'application dans le thread de l'interface graphique
        SwingUtilities.invokeLater(() -> {
            Fenetre fenetre = new Fenetre();
            fenetre.setVisible(true);
        });
    }
}
