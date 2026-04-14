package recherche;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Recherche {

    public static List<String> rechercher(String repertoireOuFichier, String aChercher) {
        List<String> resultats = new ArrayList<>();
        File file = new File(repertoireOuFichier);

        if (file.isFile()) { // si repertoireOufichier est un fichier
            resultats.addAll(inspecter(repertoireOuFichier, aChercher));
        } else {
            File[] fichiers = file.listFiles();
            if (fichiers != null) { // Liste de fichiers différents de null
                for (File f : fichiers) {
                    resultats.addAll(rechercher(f.getAbsolutePath(), aChercher));
                }
            }
        }
        return resultats; // return les résultats
    }

    public static List<String> inspecter(String fichier, String aChercher) {
        List<String> resultats = new ArrayList<>();
        File file = new File(fichier);

        if (file.getName().endsWith(".java")) {
            try {
                List<String> lignes = Files.readAllLines(file.toPath());
                for (int i = 0; i < lignes.size(); i++) {
                    if (lignes.get(i).indexOf(aChercher) > -1) {
                        resultats.add(file.getAbsolutePath() + "§" + (i + 1));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultats;
    }

    // TO DO à mettre en place dans le main
    public static void main(String[] args) {
        List<String> resultats = rechercher("src", "class");
        for (String resultat : resultats) {
            System.out.println(resultat);
        }
    }
}