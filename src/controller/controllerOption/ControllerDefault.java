package controller.controllerOption;

import modele.Option;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerDefault implements ActionListener {

    Option opt;

    public ControllerDefault(Option option) {
        opt = option;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        opt.setDefault();
    }
}
