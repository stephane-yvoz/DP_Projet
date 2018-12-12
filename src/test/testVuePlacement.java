package test;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import modele.Option;
import vue.VuePlacement;
import modele.Modele;

public class testVuePlacement {

	public testVuePlacement(){}
	

	public static void main(String[] args){
		Option o = new Option();
		Modele m = new Modele(o);
		
		JFrame j = new JFrame();
		VuePlacement v = new VuePlacement(m);
		j.add(v);
		j.setVisible(true);
		j.setSize(500,500);
		v.repaint();
		
	}
}