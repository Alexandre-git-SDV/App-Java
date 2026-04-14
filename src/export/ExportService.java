package export;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ExportService {
    // Nom du fichier utilise si aucune annotation ne fournit de chemin
    private static final String CHEMIN_EXPORT_PAR_DEFAUT = "out.txt";

    // Exporte les resultats dans un fichier texte apres les avoir convertis en minuscules
    public void exporter(List<String> resultats) throws IOException {
        String cheminExport = determinerCheminExport(resultats);

        List<String> lignesAExporter = resultats.stream()
                .map(String::toLowerCase)
                .toList();

        Files.write(Path.of(cheminExport), lignesAExporter);
    }

    // Determine le chemin du fichier d'export a partir de l'annotation presente sur FormatExport
    private String determinerCheminExport(List<String> resultats) {
        if (resultats.isEmpty()) {
            return CHEMIN_EXPORT_PAR_DEFAUT;
        }

        FormatExport formatExport = new FormatExport(resultats.get(0));
        Output annotation = formatExport.getClass().getAnnotation(Output.class);

        if (annotation != null && !annotation.value().isBlank()) {
            return annotation.value();
        }

        return CHEMIN_EXPORT_PAR_DEFAUT;
    }
}
