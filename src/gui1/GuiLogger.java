package gui1;

import java.io.Serializable;

import gui2.MainGUI2;
import javafx.application.Platform;
import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.Student;

public class GuiLogger implements Logger, Serializable {
	MainGUI gui;
	MainGUI2 gui2;
	public GuiLogger(MainGUI gui){
		this.gui=gui;
		this.gui2=null;
	}
	public GuiLogger(MainGUI2 gui2){
		this.gui2=gui2;
		this.gui=null;
	}
	@Override
	public void log(String status, Student student) {
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
					gui2.addStudent(student);
					break;
				case "REMOVED":
					gui2.removeStudent(student);
					break;
				case "NOT MODIFIED":
					gui2.notModifiedStudent(student);
					break;
				}	
			}});
		}

	}
}
