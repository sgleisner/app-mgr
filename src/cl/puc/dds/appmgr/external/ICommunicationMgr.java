package cl.puc.dds.appmgr.external;

import java.util.ArrayList;

public interface ICommunicationMgr{
	
	ArrayList<Object> requestDevicesOnline();
	boolean isDeviceOnline(String id);

}
