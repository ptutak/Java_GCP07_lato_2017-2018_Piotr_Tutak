package gui2;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import kolekcje_i_algorytmy.Student;

public class MTabPane {
	
	@FXML TabPane tabPane;
	@FXML ListView listView;
	@FXML TableView tableView;
	@FXML BarChart barChart;
	@FXML MListView listViewController;
	@FXML MTableView tableViewController;
	@FXML MBarChart barChartController;
	
	@FXML public void initialize(){
		
	}
	
	public void addStudent(Student student){
		tableViewController.addStudent(student);
		listViewController.addedStudent(student);
		barChartController.addMark(student.getMark());
	}
	
	public void removeStudent(Student student){
		tableViewController.removeStudent(student);
		listViewController.removedStudent(student);
		barChartController.removeMark(student.getMark());
	}
	
	public void notModifiedStudent(Student student){
		listViewController.notModifiedStudent(student);
	}

}
