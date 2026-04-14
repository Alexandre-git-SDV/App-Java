package historique;

import java.util.Objects;

public final class Saisie {
    private final String nomRepertoire;
    private final String texteRecherche;

    public Saisie(String nomRepertoire, String texteRecherche) {
        this.nomRepertoire = Objects.requireNonNullElse(nomRepertoire, "");
        this.texteRecherche = Objects.requireNonNullElse(texteRecherche, "");
    }

    public String getNomRepertoire() {
        return nomRepertoire;
    }

    public String getTexteRecherche() {
        return texteRecherche;
    }
}
