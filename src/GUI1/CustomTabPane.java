package GUI1;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import kolekcje_i_algorytmy.Student;

public class CustomTabPane extends AnchorPane{
	CustomListView log=new CustomListView();
	
	CustomTabPane(){
		 TabPane tabPane = new TabPane();
		 Tab tab = new Tab();
		 tab.setText("new tab");
		 tab.setContent(log);
		 tabPane.getTabs().add(tab);
		 this.getChildren().add(tabPane);
		 
	}
	
	public void addStudent(Student student){
		log.addStudent(student);
		//TODO: Dodaæ logikê dodawania logu i z wykresu :P
	}
	
	public void removeStudent(Student student){
		log.removeStudent(student);
		//TODO: Dodaæ logikê usuwania logu i z wykresu :P
		
	}
	


}
