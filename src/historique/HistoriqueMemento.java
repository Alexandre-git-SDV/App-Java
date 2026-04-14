package historique;

final class HistoriqueMemento {
    private final String nomRepertoire;
    private final String texteRecherche;

    HistoriqueMemento(Saisie etat) {
        this.nomRepertoire = etat.getNomRepertoire();
        this.texteRecherche = etat.getTexteRecherche();
    }

    Saisie toSaisie() {
        return new Saisie(nomRepertoire, texteRecherche);
    }
}
