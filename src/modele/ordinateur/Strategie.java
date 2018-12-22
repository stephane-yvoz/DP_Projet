package modele.ordinateur;

import modele.Bateau;
import modele.Orientation;
import modele.Square;
import modele.joueurs.Joueur;
import modele.joueurs.JoueurMachine;

import java.awt.*;
import java.util.Random;

public abstract class Strategie {
    public Strategie(){
        ;
    }

    public void placerBateau(Joueur player, int[] listBateau){
        Square[][] grilleJoueur = player.getPlateau().getGrilleEnnemie();
        int maxY = grilleJoueur.length;
        int maxX = grilleJoueur[0].length;
        Orientation[] tab = {Orientation.EAST, Orientation.NORTH, Orientation.SOUTH, Orientation.WEST};
        int orient = 0;
        int x, y;
        int i = 0;
        boolean pos;
        Random rand = new Random();
        while (i != listBateau.length){
            orient = rand.nextInt(tab.length);
            pos = false;
            while (!pos){
                x = rand.nextInt(maxX);
                y = rand.nextInt(maxY);
                if (player.getPlateau().canAddShip(x, y, listBateau[i], tab[orient])){
                    player.getPlateau().addShip(x, y, listBateau[i], tab[orient]);
                    pos = true;
                }
            }
            i = i + 1;
        }
    }

    public abstract Point jouer(Joueur player);

    public Bateau getBateau(Joueur player){
        Bateau ret = null;
        return player.getPlateau().getStrongerShip();
    }
}
