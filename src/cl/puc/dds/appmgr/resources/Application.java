package cl.puc.dds.appmgr.resources;

import java.util.ArrayList;
import java.util.HashMap;

import cl.puc.dds.appmgr.external.IApplication;
import cl.puc.dds.appmgr.external.ICommunicationMgr;
import cl.puc.dds.appmgr.external.IPersistenceMgr;
import cl.puc.dds.appmgr.external.IResource;
import cl.puc.dds.appmgr.external.IResourceMgr;

public abstract class Application{
	//probando el gittttttt
	String appID; /*Permite reconocer la misma aplicación en diferentes dispositivos. Ejemplo: Angry birds siempre tendrá id "AngryBirds01" */
	String version; 
	
	ApplicationListener listener; /*Maneja peticiones de recursos de otros dispositivos a este*/
	DeviceState state; /*Contiene el dispositivo LOCAL donde corre esta app*/
	ArrayList<IResource> resources = new ArrayList<IResource>(); /*Lista de dispositivos locales*/

	
	// Acceso a los managers externos
	ICommunicationMgr communicationMgr;	
	IPersistenceMgr persistenceMgr;
	IResourceMgr resourceMgr;

	ApplicationDaemon deamon; /*Thread que corre métodos de rutina (persistencia, revisar dispositivos que se caen, etc.)*/
	
	// Lista de dispositivos actualmente conectados a la "red" de la aplicación
	// Key: ID del dispositivo, Value: El dispositivo mismo (más su estado en esta aplicación)
	HashMap<Integer, DeviceState> connectedDevices = new HashMap<Integer, DeviceState>(); 
		
	// Lista con todos los dispositivos que tienen algún recurso asignado
	// Key: El disp. mismo (más su estado en esta app.) , Value: ArrayList con todos los disp. asignados al disp. del key.
	HashMap< DeviceState , ArrayList<IResource> > assignedDevices = new HashMap<DeviceState, ArrayList<IResource>>();

	
	// Agregar-remover dispositivos al entorno de la aplicación
	public void addDevice(DeviceState device){
		this.connectedDevices.put(new Integer(device.getId()) , device);
	}
	public boolean removeDevice(DeviceState device){

		for( Integer i : connectedDevices.keySet() ){
			if(i.intValue() == device.getId()){
				connectedDevices.remove(i);
				return true;
			}			
		}
		return false;
	}



	
	//Guardar y recuperar estado de la aplicación
	public void saveAppState(){
		persistenceMgr.saveAppState(this);
	}
	
	

	// Getters & Setters
	
	public HashMap<DeviceState, ArrayList<IResource>> getAssignedDevices() {
		return assignedDevices;
	}
	
	public ArrayList<IResource> getResources() {
		return resources;
	}


	public IResourceMgr getResourceMgr() {
		return resourceMgr;
	}
	





}
