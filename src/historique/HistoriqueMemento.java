package historique;

final class HistoriqueMemento {
    // Le memento conserve une copie des donnees de la saisie
    private final String nomRepertoire;
    private final String texteRecherche;

    // Copie les valeurs de la saisie pour les memoriser independamment
    HistoriqueMemento(Saisie etat) {
        this.nomRepertoire = etat.getNomRepertoire();
        this.texteRecherche = etat.getTexteRecherche();
    }

    // Reconstruit une saisie a partir des donnees memorisees
    Saisie toSaisie() {
        return new Saisie(nomRepertoire, texteRecherche);
    }
}
