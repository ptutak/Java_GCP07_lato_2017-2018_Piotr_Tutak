package gui2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import kolekcje_i_algorytmy.Student;

public class ListViewController {
	
	private ObservableList<String> logList = FXCollections.observableArrayList();
	private DateFormat dateFormat;

	@FXML private ListView<String> listView;
	
	@FXML public void initialize(){
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	}
	
	public void addedStudent(Student student){
		logList.add(dateFormat.format(new Date())+":  ADDED:  "+student);
	}
	public void removedStudent(Student student){
		logList.add(dateFormat.format(new Date())+":  REMOVED:  "+student);
	}
	public void notModifiedStudent(Student student){
		logList.add(dateFormat.format(new Date())+":  NOT MODIFIED:  "+student);
	}
	
	
}
