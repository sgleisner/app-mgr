package cl.puc.dds.appmgr.resources;

import java.util.ArrayList;
import java.util.HashMap;

import cl.puc.dds.appmgr.external.IApplication;
import cl.puc.dds.appmgr.external.ICommunicationMgr;
import cl.puc.dds.appmgr.external.IPersistenceMgr;
import cl.puc.dds.appmgr.external.IResource;
import cl.puc.dds.appmgr.external.IResourceMgr;

public abstract class Application{

	String appID; /*Permite reconocer la misma aplicaci�n en diferentes dispositivos. Ejemplo: Angry birds siempre tendr� id "AngryBirds01" */
	String version; 

	ApplicationListener listener; /*Maneja peticiones de recursos de otros dispositivos a este*/
	DeviceState state; /*Contiene el dispositivo LOCAL donde corre esta app*/
	ArrayList<IResource> resources = new ArrayList<IResource>(); /*Lista de dispositivos locales*/


	// Acceso a los managers externos
	ICommunicationMgr communicationMgr;	
	IPersistenceMgr persistenceMgr;
	IResourceMgr resourceMgr;

	ApplicationDaemon deamon; /*Thread que corre m�todos de rutina (persistencia, revisar dispositivos que se caen, etc.)*/

	// Lista de dispositivos actualmente conectados a la "red" de la aplicaci�n
	// Key: ID del dispositivo, Value: El dispositivo mismo (m�s su estado en esta aplicaci�n)
	HashMap<String, DeviceState> connectedDevices = new HashMap<String, DeviceState>(); 

	public ICommunicationMgr getCommunicationMgr() {
		return communicationMgr;
	}

	public void setCommunicationMgr(ICommunicationMgr communicationMgr) {
		this.communicationMgr = communicationMgr;
	}

	public IPersistenceMgr getPersistenceMgr() {
		return persistenceMgr;
	}

	public void setPersistenceMgr(IPersistenceMgr persistenceMgr) {
		this.persistenceMgr = persistenceMgr;
	}

	public void setResourceMgr(IResourceMgr resourceMgr) {
		this.resourceMgr = resourceMgr;
	}

	public HashMap<String, DeviceState> getConnectedDevices() {
		return connectedDevices;
	}

	public void setConnectedDevices(HashMap<String, DeviceState> connectedDevices) {
		this.connectedDevices = connectedDevices;
	}
	// Lista con todos los dispositivos que tienen alg�n recurso asignado
	// Key: El disp. mismo (m�s su estado en esta app.) , Value: ArrayList con todos los disp. asignados al disp. del key.
	HashMap< DeviceState , ArrayList<IResource> > assignedDevices = new HashMap<DeviceState, ArrayList<IResource>>();


	// Agregar-remover dispositivos al entorno de la aplicaci�n
	public void addDevice(DeviceState deviceState){
		this.connectedDevices.put(deviceState.getDevice().getId() , deviceState);
	}
	
	public boolean removeDevice(DeviceState deviceState){
		for( String i : connectedDevices.keySet() ){
			if(i.equals(deviceState.getDevice().getId())){
				connectedDevices.remove(i);
				return true;
			}			
		}
		return false;
	}
	



	//Guardar y recuperar estado de la aplicaci�n
	public void saveAppState(){
		state.setToken(persistenceMgr.save(state.getUserID(), state.getDevice().getId(), state.getData()));
	}
	
	public void loadAppState(byte[] token){
		persistenceMgr.retriveByDigest(token);
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
	public ApplicationListener getApplicationListener(){
		return listener;
	}	
	public void setApplicationListener(ApplicationListener al){
		listener = al;
	}






}
