package cl.puc.dds.appmgr.resources;


import java.io.Serializable;

public class AMMessage implements Serializable{
	public int sender_id;
	public String action;
	public Object pack;
	
	public AMMessage(int id, String act, Object obj)
	{
		this.sender_id = id;
		this.action = act;
		this.pack = obj;
	}
	
}
