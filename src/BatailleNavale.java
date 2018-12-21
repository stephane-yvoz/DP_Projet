import java.awt.Dimension;

import modele.Modele;
import modele.Option;
import runnable.RunnableOption;
import vue.VueGrilleJoueur;
import vue.VueGrilleEnemie;

import javax.swing.*;

public class BatailleNavale {

    public final static int GRID_WIDTH = 500;
    public final static int GRID_HEIGHT = 500;
    
    public final static Dimension DIMENSIONS = new Dimension(GRID_WIDTH, GRID_HEIGHT);
    
    public static int NUMBER = 2;
  
    JFrame[] windows;

    private void makeGame(Modele modele) {
        windows = new JFrame[NUMBER];
        String s;
        
        for (int i = 0; i < NUMBER; i ++) {
            if (i == 0)
                s = "Votre plateau";
            else
                s = "Plateau de tir";
            
            windows[i] = new JFrame(s);
            windows[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        }
        
        VueGrilleJoueur vj1 = new VueGrilleJoueur(modele);
        VueGrilleEnemie vj2 = new VueGrilleEnemie(modele);
        
        vj1.setPreferredSize(DIMENSIONS);
        vj2.setPreferredSize(DIMENSIONS);
        
        // windows[0].add(new VueTailleBateau(modele), BorderLayout.NORTH);
        windows[0].add(vj1);
        windows[1].add(vj2);
        
        for (int i = 0; i < NUMBER; i ++) {
        	windows[i].pack();
        	windows[i].setVisible(true);
        }
    }

    public BatailleNavale(Option option) {
        Modele modele = new Modele(option);
        makeGame(modele);
    }

    public static void main(String[] args) {
        Option option = new Option();
        Runnable frameOption = new RunnableOption(option);
        
        new Thread(frameOption).start();
        
        while (option.isDisplayOption()) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        final Option batailleOption = option;
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BatailleNavale(batailleOption);
            }
        });
    }
    
}
