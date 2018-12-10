package test;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import vue.VuePlacement;
import modele.Modele;

public class testVuePlacement {

	public testVuePlacement(){}
	

	public static void main(String[] args){
		Modele m = new Modele(10,"III");
		
		JFrame j = new JFrame();
		VuePlacement v = new VuePlacement(m);
		j.add(v);
		j.setVisible(true);
		j.setSize(500,500);
		
	}
}