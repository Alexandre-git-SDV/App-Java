package export;

@Output("out.txt")
public class FormatExport {
    private final String nomFichier;
    private final String positionDansFichier;

    public FormatExport(String resultatRecherche) {
        String[] morceaux = resultatRecherche.split("\u00A7");
        this.nomFichier = morceaux.length > 0 ? morceaux[0] : "";
        this.positionDansFichier = morceaux.length > 1 ? morceaux[1] : "";
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public String getPositionDansFichier() {
        return positionDansFichier;
    }
}
