package historique;

import java.util.Stack;

public final class Historique {
    private static final Saisie ETAT_VIDE = new Saisie("", "");

    private final Stack<Saisie> sauvegardes = new Stack<>();

    public void sauvegarder(String nomRepertoire, String texteRecherche) {
        sauvegarder(new Saisie(nomRepertoire, texteRecherche));
    }

    public void sauvegarder(Saisie saisie) {
        HistoriqueMemento memento = new HistoriqueMemento(saisie);
        sauvegardes.add(memento.toSaisie());
    }

    public Saisie restaurerPrecedent() {
        if (!peutRestaurer()) {
            return ETAT_VIDE;
        }

        sauvegardes.removeLast();
        return consulterEtatCourant();
    }

    public Saisie consulterEtatCourant() {
        if (sauvegardes.isEmpty()) {
            return ETAT_VIDE;
        }

        return sauvegardes.peek();
    }

    public boolean peutRestaurer() {
        return sauvegardes.size() > 1;
    }
}
