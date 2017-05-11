package gui2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import kolekcje_i_algorytmy.Student;

public class TableViewController {
	
	private final ObservableList<Student> studentsList=FXCollections.observableArrayList();
	
	@FXML private TableColumn<Student,String> markCol;
	@FXML private TableColumn<Student, String> firstNameCol;
	@FXML private TableColumn<Student,String> lastNameCol;
	@FXML private TableColumn<Student,String> ageCol;
	
	@FXML private TableView<Student> tableView;
	
	@FXML public void initialize(){
		tableView.setItems(studentsList);
		markCol.setCellValueFactory(new PropertyValueFactory<Student,String>("mark"));
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Student,String>("firstName"));
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Student,String>("lastName"));
		ageCol.setCellValueFactory(new PropertyValueFactory<Student,String>("age"));
	}
	public void addStudent(Student student){
		studentsList.add(student);
	}
	public void removeStudent(Student student){
		studentsList.remove(student);
	}

}
