package controller.controllerOption;

import modele.Option;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerValidate implements ActionListener {

    Option opt;

    public ControllerValidate(Option option) {
        opt = option;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        opt.stopDisplayOption();
    }

}
