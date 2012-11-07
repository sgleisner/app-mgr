package cl.puc.dds.appmgr.resources;

import java.util.ArrayList;
import java.util.HashMap;

import cl.puc.dds.appmgr.external.IApplication;
import cl.puc.dds.appmgr.external.IDevice;
import cl.puc.dds.appmgr.external.IResource;

public class ApplicationListener {
	
	IApplication app;
	
		
	// otorgar un recurso a alg�n dipositivo
	public boolean handleResourceRequest(IDevice sender , String resourceType){
		ArrayList<IResource> resources = app.getResources();
		HashMap< IDevice,ArrayList<IResource> > assignedDevices = app.getAssignedDevices();
		
		
		
		for(IResource r : resources){
			if(r.getType().equals(resourceType) && app.getResourceMgr().isAvailable(r)){
				
				if(!assignedDevices.containsKey(sender)){
					ArrayList<IResource> a = new ArrayList<IResource>();
					a.add(r);
					assignedDevices.put(sender, a);					
				}
				else{
					assignedDevices.get(sender).add(r);
				}							
				
				return true;
			}
		}
		return false;
	}


	/** Exigencias de IAppObs en ResourceMrg equivalente a este ApplicationListener */
	public void resourceFinished(int resource_id, Object object) {
		// TODO		
	}

	public void resourceFailed(int resource_id, String error) {
		// TODO
	}

	public void resourceInterrupted(int resource_id, String error) {
		// TODO
	}

	/**Fin de las exigencias*/
	
	
}
