package gui1;

import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.Student;

public class GuiLogger implements Logger {
	MainGUI gui;
	GuiLogger(MainGUI gui){
		this.gui=gui;
	}
	@Override
	public void log(String status, Student student) {
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
	}

}
