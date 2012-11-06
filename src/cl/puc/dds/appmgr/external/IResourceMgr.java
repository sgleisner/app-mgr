package cl.puc.dds.appmgr.external;

import java.util.ArrayList;

public interface IResourceMgr {
	
	boolean isAvailable(IResource r);
	ArrayList<Object> getAllResources();
	
}
