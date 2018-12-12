package dao_Txt;

public class ConcreteTxtFactory extends AbstractDAOFactory{

	@Override
	public ModeleDAO getModeleDAO() {
		
		return ModeleTxtDAO.getInstance();
	}

	
	

}
