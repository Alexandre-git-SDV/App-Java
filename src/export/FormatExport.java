package export;

// Associe un resultat de recherche a un format exploitable pour l'export
@Output("out.txt")
public class FormatExport {
    // Contient le chemin du fichier dans lequel une occurrence a ete trouvee
    private final String nomFichier;
    // Contient la position de l'occurrence dans le fichier
    private final String positionDansFichier;

    // Decoupe le resultat de recherche pour separer le nom du fichier et la position
    public FormatExport(String resultatRecherche) {
        String[] morceaux = resultatRecherche.split("\u00A7");
        this.nomFichier = morceaux.length > 0 ? morceaux[0] : "";
        this.positionDansFichier = morceaux.length > 1 ? morceaux[1] : "";
    }

    // Renvoie le chemin du fichier extrait depuis le resultat de recherche
    public String getNomFichier() {
        return nomFichier;
    }

    // Renvoie la position de l'occurrence extraite depuis le resultat de recherche
    public String getPositionDansFichier() {
        return positionDansFichier;
    }
}
