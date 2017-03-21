package GUI1;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import kolekcje_i_algorytmy.Student;

public class CustomListView extends AnchorPane{
	private ObservableList<String> names = FXCollections.observableArrayList();
	CustomListView(){

		 ListView<String> listView = new ListView<String>(names);
		 this.getChildren().add(listView);
	}
	public void addStudent(Student student){
		names.add("ADDED: "+student.getFirstName()+" "+student.getLastName());
	}
	
	public void removeStudent(Student student){
		names.add("REMOVED: "+student.getFirstName()+" "+student.getLastName());
	}
	
}
