package historique;

import java.util.Objects;

public final class Saisie {
    // Represente l'etat complet de la saisie utilisateur
    private final String nomRepertoire;
    private final String texteRecherche;

    // Construit une saisie complete a partir du repertoire et du texte recherches
    public Saisie(String nomRepertoire, String texteRecherche) {
        // Les valeurs nulles sont remplacees par une chaine vide pour simplifier l'usage cote interface
        this.nomRepertoire = Objects.requireNonNullElse(nomRepertoire, "");
        this.texteRecherche = Objects.requireNonNullElse(texteRecherche, "");
    }

    // Renvoie le repertoire ou fichier associe a la saisie
    public String getNomRepertoire() {
        return nomRepertoire;
    }

    // Renvoie le texte que l'utilisateur souhaite rechercher
    public String getTexteRecherche() {
        return texteRecherche;
    }
}
