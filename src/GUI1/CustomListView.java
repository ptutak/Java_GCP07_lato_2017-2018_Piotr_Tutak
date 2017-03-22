package gui1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import kolekcje_i_algorytmy.Student;

public class CustomListView extends AnchorPane{
	
	private ObservableList<String> logList = FXCollections.observableArrayList();
	
	CustomListView(){
		 ListView<String> listView = new ListView<String>(logList);
		 this.getChildren().add(listView);
	}
	public void addedStudent(Student student){
		logList.add("ADDED: "+student.getFirstName()+" "+student.getLastName());
	}
	public void removedStudent(Student student){
		logList.add("REMOVED: "+student.getFirstName()+" "+student.getLastName());
	}
	public void notModifiedStudent(Student student){
		logList.add("NOT MODIFIED: "+student.getFirstName()+" "+student.getLastName());
	}
	
}
