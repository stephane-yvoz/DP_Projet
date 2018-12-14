package modele.ordinateur;

import modele.Square;
import modele.joueurs.Joueur;

import java.awt.*;

public interface Strategie {
    public Point jouer(Joueur player);
    public void placerBateau(Square[][] grilleJoueur);
}
