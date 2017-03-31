package gui2;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import kolekcje_i_algorytmy.Student;

public class MBorderPane {
	
	@FXML private BorderPane borderPane;
	
	@FXML private TabPane tabPane;
	@FXML private MTabPane tabPaneController;
	
	
	@FXML public void keyPressed(KeyEvent ke){
		 if (ke.isControlDown() && ke.getCode().equals(KeyCode.C))
         	System.exit(0);
	}
	
	@FXML public void initialize(){
		
	}
	
	public void addStudent(Student student){
		tabPaneController.addStudent(student);
	}
	
	public void removeStudent(Student student){
		tabPaneController.removeStudent(student);
	}
	
	public void notModifiedStudent(Student student){
		tabPaneController.notModifiedStudent(student);
	}

}
