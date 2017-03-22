package gui1;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import kolekcje_i_algorytmy.Student;

public class CustomTabPane extends AnchorPane{
	CustomListView logList=new CustomListView();
	CustomTableView studentsList=new CustomTableView();
	TabPane tabs;
	Tab students;
	Tab log;
	
	CustomTabPane(){
		 tabs = new TabPane();
		 
		 students=new Tab();
		 students.setText("Students");
		 students.setContent(studentsList);
		 
		 log = new Tab();
		 log.setText("Log");
		 log.setContent(logList);
		 
		 tabs.getTabs().add(students);
		 tabs.getTabs().add(log);
		 this.getChildren().add(tabs);
	}
	
	public void addStudent(Student student){
		studentsList.addStudent(student);
		logList.addedStudent(student);
		
	}
	
	public void removeStudent(Student student){
		studentsList.removeStudent(student);
		logList.removedStudent(student);
		
	}
	
	public void logNotModifiedStudent(Student student){
		logList.notModifiedStudent(student);
	}


}
