package recherche;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class RechercheTask extends RecursiveTask<List<String>> {

    // Classe representant une tache de recherche dans un fichier ou repertoire
    // Etend RecursiveTask pour permettre l'execution parallele via Fork/Join
    // Le type de retour est List<String> contenant les resultats de la recherche

    // Chemin du fichier ou repertoire a traiter
    private final String repertoireOuFichier;
    // Texte a rechercher dans les fichiers
    private final String aChercher;

    // Constructeur initialisant les attributs de la tache
    public RechercheTask(String repertoireOuFichier, String aChercher) {
        this.repertoireOuFichier = repertoireOuFichier;
        this.aChercher = aChercher;
    }

    // Methode executee par lepool Fork/Join lors de l'invocation de la tache
    @Override
    protected List<String> compute() {
        // Liste des resultats accumules par cette tache
        List<String> resultats = new ArrayList<>();
        // Creation de l'objet File pour le chemin fournis
        File file = new File(repertoireOuFichier);

        if (file.isFile()) {
            // Si c'est un fichier : on inspecte directement son contenu
            // Delegation vers Inspecteur.inspecter pour l'analyse du fichier
            resultats.addAll(Inspecteur.inspecter(repertoireOuFichier, aChercher));
        } else {
            // Si c'est un repertoire : on cree une sous-tache pour chaque element-enfant
            // Recuperation de la liste des fichiers et sous-repertoires
            File[] fichiers = file.listFiles();
            if (fichiers != null) {
                // Liste pour stocker les sous-taches creees
                List<RechercheTask> tasks = new ArrayList<>();
                // Pour chaque fichier ou sous-repertoire, creer une nouvelle tache
                for (File f : fichiers) {
                    RechercheTask task = new RechercheTask(f.getAbsolutePath(), aChercher);
                    // Execution asynchrone de la tache dans le pool Fork/Join
                    task.fork();
                    tasks.add(task);
                }
                // Attente de la fin de todas les sous-taches et collecte des resultats
                for (RechercheTask task : tasks) {
                    resultats.addAll(task.join());
                }
            }
        }
        // Retour des resultats accumules
        return resultats;
    }
}