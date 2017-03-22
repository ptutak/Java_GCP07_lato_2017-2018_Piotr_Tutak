package gui1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import kolekcje_i_algorytmy.Student;

public class MainG extends Application {

	@Override
	public void start(Stage stage) {      
	      GridPane gridPane = new GridPane();    
	      
	      gridPane.setMinSize(400, 400); 
	      
	      gridPane.setAlignment(Pos.CENTER); 

	      Scene scene = new Scene(gridPane);
	      
	      stage.setTitle("Crawler GUI"); 
	         
	      
	      CustomTabPane tabs=new CustomTabPane();
	     
	      tabs.addStudent(new Student(){{
	    	 setFirstName("Piotr");
	    	 setLastName("Tutak");
	    	 setAge(29);
	    	 setMark(3.0);
	      }});
	      gridPane.add(tabs, 0, 0);

	      stage.setScene(scene);
	      stage.show(); 
	      
	   }  
	
	public static void main(String[] args){
		Application.launch(args);
		
	}
	

}
