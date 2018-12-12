package dao_Txt;

import modele.Modele;




public interface ModeleDAO {

	
	public abstract void save(Modele m);
	public abstract Modele load(int idMap);
	
}
