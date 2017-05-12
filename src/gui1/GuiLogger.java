package gui1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import gui2.MainGUI2;
import javafx.application.Platform;
import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.Student;

public class GuiLogger extends UnicastRemoteObject implements Logger {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3388345027494332615L;
	MainGUI gui;
	MainGUI2 gui2;
	public GuiLogger(MainGUI gui) throws RemoteException{
		this.gui=gui;
		this.gui2=null;
	}
	public GuiLogger(MainGUI2 gui2)throws RemoteException{
		this.gui2=gui2;
		this.gui=null;
	}
	@Override
	public void log(String status, Student student) throws RemoteException{
		if (gui!=null){
			Platform.runLater(new Runnable(){public void run(){
				switch (status){

				case "ADDED":
					gui.addStudent(student);
					break;
				case "REMOVED":
					gui.removeStudent(student);
					break;
				case "NOT MODIFIED":
					gui.notModifiedStudent(student);
					break;
				}	
			}});

		}
		if (gui2!=null){
			Platform.runLater(new Runnable(){public void run(){
				switch (status){
				case "ADDED":
					MainGUI2.addStudent(student);
					break;
				case "REMOVED":
					MainGUI2.removeStudent(student);
					break;
				case "NOT MODIFIED":
					MainGUI2.notModifiedStudent(student);
					break;
				}	
			}});
		}

	}
}
