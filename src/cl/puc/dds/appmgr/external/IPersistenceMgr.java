package cl.puc.dds.appmgr.external;

import cl.puc.dds.appmgr.resources.Application;
import cl.puc.dds.appmgr.resources.DeviceState;

public interface IPersistenceMgr {
	
	boolean saveDeviceState(DeviceState ds);
	DeviceState loadDeviceState(IDevice d);
	void saveAppState(Application application);

}
