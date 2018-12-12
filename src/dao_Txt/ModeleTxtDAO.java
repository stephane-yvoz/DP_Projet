package dao_Txt;

import java.io.*;



import modele.Bateau;
import modele.Modele;
import modele.Plateau;

public class ModeleTxtDAO implements ModeleDAO{

	private static ModeleTxtDAO instance = null;
	private BufferedReader br = null;
	
	private ModeleTxtDAO(){
		
		
	}
	
	
	public static ModeleTxtDAO getInstance(){
		if(instance==null){
			instance = new ModeleTxtDAO();
		}
		return instance;
	}


	@Override
	public void save(Modele m) {
		StringBuilder s = new StringBuilder();
		
	}
	
	public void saveShip(StringBuilder s, Bateau b){
		
	}
	
	public void saveShots(StringBuilder s, Plateau p){
		
	}


	@Override
	public Modele load(int idModele) {
		
		return null;
	}
	
	

}
