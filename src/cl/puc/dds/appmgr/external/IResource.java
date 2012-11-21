package cl.puc.dds.appmgr.external;

public interface IResource {
	
	String getType();
	
	
	/** EXTERNAL: Exigencias de IResource seg�n ResourceMgr */	
	public boolean isAvailable();
	public void cancelConsumption();
	//public void setObserver(IConsumptionObs observer);
	public boolean recieveAction(int action_id, String[] param);
	public int getStatus();
	public void setId(int id);
	/** Fin exigencias */
	public int getOwnerId();
	public int getId();
	
	
}
