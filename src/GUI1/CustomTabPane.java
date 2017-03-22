package gui1;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import kolekcje_i_algorytmy.Student;

public class CustomTabPane extends AnchorPane{
	CustomListView logList=new CustomListView();
	
	CustomTabPane(){
		 TabPane tabs = new TabPane();
		 
		 Tab students=new Tab();
		 students.setText("Students");
		 
		 Tab log = new Tab();
		 log.setText("Log");
		 log.setContent(logList);
		 tabs.getTabs().add(log);
		 this.getChildren().add(tabs);
	}
	
	public void logAddedStudent(Student student){
		logList.addedStudent(student);
		//TODO: Dodaæ logikê dodawania logu i z wykresu :P
	}
	
	public void logRemovedStudent(Student student){
		logList.removedStudent(student);
		//TODO: Dodaæ logikê usuwania logu i z wykresu :P
		
	}
	
	public void logNotModifiedStudent(Student student){
		logList.removedStudent(student);
	}


}
