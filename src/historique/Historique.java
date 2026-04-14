package historique;

import java.util.Stack;

public final class Historique {
    private static final Saisie ETAT_VIDE = new Saisie("", "");

    // Stocke les saisies successives pour permettre un retour en arriere
    private final Stack<Saisie> sauvegardes = new Stack<>();

    // Cree une saisie a partir des deux champs puis l'ajoute dans l'historique
    public void sauvegarder(String nomRepertoire, String texteRecherche) {
        sauvegarder(new Saisie(nomRepertoire, texteRecherche));
    }

    // Sauvegarde une copie stable de la saisie courante dans la pile
    public void sauvegarder(Saisie saisie) {
        // On passe par un memento pour figer une copie de la saisie courante
        HistoriqueMemento memento = new HistoriqueMemento(saisie);
        sauvegardes.add(memento.toSaisie());
    }

    // Supprime l'etat courant et renvoie l'etat precedent s'il existe
    public Saisie restaurerPrecedent() {
        if (!peutRestaurer()) {
            return ETAT_VIDE;
        }

        // On retire l'etat courant pour revenir a la saisie precedente
        sauvegardes.removeLast();
        return consulterEtatCourant();
    }

    // Renvoie la derniere saisie sauvegardee sans modifier l'historique
    public Saisie consulterEtatCourant() {
        if (sauvegardes.isEmpty()) {
            return ETAT_VIDE;
        }

        return sauvegardes.peek();
    }

    // Indique si un retour arriere est possible
    public boolean peutRestaurer() {
        return sauvegardes.size() > 1;
    }
}
