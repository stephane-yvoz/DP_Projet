import modele.Modele;
import modele.Option;
import vue.VueGrilleJoueur;
import vue.VueGrilleEnemie;
import vue.VueOption;

import javax.swing.*;

public class BatailleNavale {

    public final static int GRID_WIDTH = 500;
    public final static int GRID_HEIGHT = 500;
    
    public static int NUMBER = 2;
  
    JFrame[] windows;

    private void makeGame(Modele modele) {
        windows = new JFrame[NUMBER];
        String s;
        for (int i = 0; i != NUMBER; i++){
            if (i == 0)
                s = "Votre plateau";
            else
                s = "Plateau de tir";
            windows[i] = new JFrame(s);
            windows[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            windows[i].setSize(GRID_WIDTH, GRID_HEIGHT);
            windows[i].setVisible(true);
        }
        VueGrilleJoueur vj1 = new VueGrilleJoueur(modele);
        VueGrilleEnemie vj2 = new VueGrilleEnemie(modele);
        windows[0].add(vj1);
        windows[1].add(vj2);
    }

    public BatailleNavale(Option option) {
        Modele modele = new Modele(option);
        makeGame(modele);
    }

    private static void makeFrameOption(Option option) {
        JFrame frame = new JFrame("Option");
        frame.add(new VueOption(option));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400, 200);
    }

    public static void main(String[] args) {
        Option option = new Option();
        Thread t = new Thread(){
            public void run(){
                makeFrameOption(option);
            }
        };
        t.start();
        while (option.isDisplayOption()){
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BatailleNavale(option);
            }
        });
    }
    
}
