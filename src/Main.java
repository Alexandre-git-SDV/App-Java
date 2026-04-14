import historique.Historique;
import historique.Saisie;

public class Main {
    public static void main(String[] args) {
        // TODO Test manuel pour historique / A remplacer par le lancement de la fênetre
        Historique historique = new Historique();

        historique.sauvegarder("C:/docs", "rapport");
        historique.sauvegarder("C:/docs/archive", "compte rendu");

        Saisie etatRestaure = historique.restaurerPrecedent();
        System.out.println("Repertoire : " + etatRestaure.getNomRepertoire());
        System.out.println("Texte : " + etatRestaure.getTexteRecherche());
    }
}
