package recherche;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Inspecteur {
    // Methode d'inspection d'un fichier Java pour rechercher un texte specifique.
    // Retourne une liste de chaines au format "chemin§numeroLigne" pour chaque correspondance.
    public static List<String> inspecter(String fichier, String aChercher) {
        File file = new File(fichier);

        // Ignore les fichiers qui ne sont pas des sources Java.
        if (!file.getName().endsWith(".java")) {
            return new ArrayList<>();
        }

        try {
            List<String> lignes = Files.readAllLines(file.toPath());

            return IntStream.range(0, lignes.size())
                    .filter(i -> lignes.get(i).indexOf(aChercher) > -1) // filter: garde uniquement les lignes contenant le texte a chercher
                    .mapToObj(i -> file.getAbsolutePath() + " - Ligne \u00A7" + (i + 1)) // mapToObj: construit la chaîne de resultat au format "chemin§ligne"
                    .collect(Collectors.toList()); // collect: rassemble les resultats dans une liste
        } catch (IOException e) {
            // Gestion des erreurs de lecture de fichier
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}