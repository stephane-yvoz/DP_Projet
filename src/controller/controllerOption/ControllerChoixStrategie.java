package controller.controllerOption;

import modele.Option;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerChoixStrategie implements ActionListener {

    Option opt;

    public ControllerChoixStrategie(Option option) {
        opt = option;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String command = button.getActionCommand();
        opt.setStrategie(command);
    }
}
