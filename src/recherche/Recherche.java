package recherche;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Recherche {

    // Instance unique pour le pattern singleton
    private static Recherche instance;

    // Constructeur privé pour empêcher l'instanciation directe
    private Recherche() {
    }

    // Methode getInstance avec verification synchronisee pour le thread-safety
    public static synchronized Recherche getInstance() {
        if (instance == null) {
            instance = new Recherche();
        }
        return instance;
    }

    // Methode principale de recherche qui utilise le pool Fork/Join pour la parallelisation
    public List<String> rechercher(String repertoireOuFichier, String aChercher) {
        // Creation du pool Fork/Join commun
        ForkJoinPool pool = ForkJoinPool.commonPool();
        // Creation de la tache de recherche pour le fichier ou repertoire donne
        RechercheTask task = new RechercheTask(repertoireOuFichier, aChercher);
        // Invocation de la tache et retour des resultats
        return pool.invoke(task);
    }

    // Methode deleguée vers Inspecteur pour inspector un fichier specifique
    public List<String> inspecter(String fichier, String aChercher) {
        return Inspecteur.inspecter(fichier, aChercher);
    }

    // Methode main pour tester la recherche
    public static void main(String[] args) {
        List<String> resultats = getInstance().rechercher("src", "class");
        for (String resultat : resultats) {
            System.out.println(resultat);
        }
    }
}