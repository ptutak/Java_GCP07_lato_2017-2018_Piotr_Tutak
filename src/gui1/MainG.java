package gui1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import kolekcje_i_algorytmy.Student;

public class MainG extends Application {

	@Override
	public void start(Stage stage) {      
		stage.setTitle("Crawler GUI"); 
	    
		BorderPane borderPane = new BorderPane();    
	    borderPane.setMinSize(400, 400);
	    
	    	    
	    CustomTabPane tabs=new CustomTabPane();
	    tabs.addStudent(new Student(){{
	     setFirstName("Piotr");
	     setLastName("Tutak");
	     setAge(29);
	     setMark(3.0);
	    }});
	    
	    CustomMenuBar menu=new CustomMenuBar();
	    
	    borderPane.setTop(menu);
	    borderPane.setCenter(tabs);

	    Scene scene = new Scene(borderPane);
	    stage.setScene(scene);
	    stage.show(); 
	      
   }  
	
	public static void main(String[] args){
		Application.launch(args);
		
	}
	

}
