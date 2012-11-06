package cl.puc.dds.appmgr.resources;

import cl.puc.dds.appmgr.external.IDevice;

public class DeviceState {
	
	IDevice device;
	String data;
	
	public int getId(){
		return device.getId();
	}



}
