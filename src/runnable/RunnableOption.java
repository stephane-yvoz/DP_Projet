package runnable;

import modele.Option;
import vue.VueOption;

import javax.swing.*;

public class RunnableOption implements Runnable {
    private Option option;

    public RunnableOption(Option opt){
        this.option = opt;
    }

    private void makeFrameOption() {
        JFrame frame = new JFrame("Option");
        frame.add(new VueOption(this.option));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400, 200);
    }

    @Override
    public void run() {
        makeFrameOption();
    }
}
