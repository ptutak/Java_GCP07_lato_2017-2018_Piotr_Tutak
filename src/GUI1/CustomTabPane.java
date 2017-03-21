package GUI1;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import kolekcje_i_algorytmy.Student;

public class CustomTabPane extends AnchorPane{
	
	CustomTabPane(){
		 TabPane tabPane = new TabPane();
		 Tab tab = new Tab();
		 tab.setText("new tab");
		 tab.setContent(new CustomListView());
		 tabPane.getTabs().add(tab);
		 this.getChildren().add(tabPane);
		 
	}
	
	public void addStudent(Student student){
		
	}
	
	public void removeStudent(Student student){
		
	}
	


}
