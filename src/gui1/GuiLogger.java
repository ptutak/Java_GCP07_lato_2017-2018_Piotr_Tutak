package gui1;

import javafx.application.Platform;
import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.Student;

public class GuiLogger implements Logger {
	MainGUI gui;
	GuiLogger(MainGUI gui){
		this.gui=gui;
	}
	@Override
	public void log(String status, Student student) {
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

}
