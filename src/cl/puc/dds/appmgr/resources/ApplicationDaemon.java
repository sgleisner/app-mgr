package cl.puc.dds.appmgr.resources;

public class ApplicationDaemon extends Thread{
	
	private Application app;
	
	public ApplicationDaemon(String s, Application app){
		super(s);
		this.app = app;
	}
	
	public void run(){
		//Métodos de rutina
		
	}

}
