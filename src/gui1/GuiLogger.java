package gui1;

import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.Student;

public class GuiLogger implements Logger {
	CustomTabPane pane;
	GuiLogger(CustomTabPane pane){
		this.pane=pane;
	}
	@Override
	public void log(String status, Student student) {
		switch (status){
		case "ADDED":
			pane.addStudent(student);
			break;
		case "REMOVED":
			pane.removeStudent(student);
			break;
		case "NOT MODIFIED":
			pane.notModifiedStudent(student);
			break;
		}
	}

}
