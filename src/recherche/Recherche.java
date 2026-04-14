package recherche;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Recherche {

    private static Recherche instance;

    private Recherche() {
    }

    public static synchronized Recherche getInstance() {
        // Classe singleton pour vérifié qu'il n'y a qu'une instance de recherche
        if (instance == null) {
            instance = new Recherche();
        }
        return instance;
    }

    public List<String> rechercher(String repertoireOuFichier, String aChercher) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        RechercheTask task = new RechercheTask(repertoireOuFichier, aChercher);
        return pool.invoke(task);
    }

    public List<String> inspecter(String fichier, String aChercher) {
        File file = new File(fichier);

        if (!file.getName().endsWith(".java")) { // Si le fichier n'a pas de .java
            return new ArrayList<>(); // On ne l'inspecte pas et on fait une nouvelle ArrawList
        }

        try {
            List<String> lignes = Files.readAllLines(file.toPath());
            return IntStream.range(0, lignes.size()) // Utilisation de stream pour rendre la lecture plus facile
                    .filter(i -> lignes.get(i).indexOf(aChercher) > -1) // Filtre avec la méthode aChercher
                    .mapToObj(i -> file.getAbsolutePath() + "§" + (i + 1))  // Cherche avec mapToObj
                    .collect(Collectors.toList());
        } catch (IOException e) { // Utilisation de IO exception pour la gestion d'erreur
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private class RechercheTask extends RecursiveTask<List<String>> {
        // Classe interne représentant une tâche de recherche dans un fichier ou répertoire
        // Étend RecursiveTask pour permettre l'exécution parallèle via Fork/Join
        // Le type de retour est List<String> contenant les résultats de la recherche

        private final String repertoireOuFichier;
        private final String aChercher;

        public RechercheTask(String repertoireOuFichier, String aChercher) {
            this.repertoireOuFichier = repertoireOuFichier;
            this.aChercher = aChercher;
        }

        @Override
        protected List<String> compute() {
            // Méthode principale exécutée par le pool Fork/Join
            // Découpe le travail en sous-tâches pour les répertoires, traite directement les fichiers
            List<String> resultats = new ArrayList<>();
            File file = new File(repertoireOuFichier);

            if (file.isFile()) {
                // Si c'est un fichier : on inspecte directement son contenu
                resultats.addAll(inspecter(repertoireOuFichier, aChercher));
            } else {
                // Si c'est un répertoire : on crée une sous-tâche pour chaque élément-enfant
                File[] fichiers = file.listFiles();
                if (fichiers != null) {
                    List<RechercheTask> tasks = new ArrayList<>();
                    for (File f : fichiers) {
                        // Création d'une nouvelle tâche pour chaque fichier/sous-répertoire
                        RechercheTask task = new RechercheTask(f.getAbsolutePath(), aChercher);
                        task.fork(); // Exécution asynchrone de la tâche dans le pool
                        tasks.add(task);
                    }
                    // Attente de la fin de toutes les tâches et collecte des résultats
                    for (RechercheTask task : tasks) {
                        resultats.addAll(task.join());
                    }
                }
            }
            return resultats;
        }
    }

    public static void main(String[] args) {
        List<String> resultats = getInstance().rechercher("src", "class");
        for (String resultat : resultats) {
            System.out.println(resultat);
        }
    }
}