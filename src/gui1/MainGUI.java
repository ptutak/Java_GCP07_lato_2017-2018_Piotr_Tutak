package gui1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import kolekcje_i_algorytmy.Student;

public class MainGUI extends Application {
	private BorderPane borderPane;
	private CustomTabPane tabs;
	private CustomMenuBar menu;
	private Scene scene;
	private Label keyLabel;
	private Stage stage;
	@Override
	public void start(Stage stage) {      
		this.stage=stage;
		this.stage.setTitle("Crawler GUI"); 
	    
		borderPane = new BorderPane();    
	    borderPane.setMinSize(400, 400);
		    	    
	    tabs=new CustomTabPane();

	    menu=new CustomMenuBar();
	    keyLabel = new Label();
	    borderPane.setTop(menu);
	    borderPane.setCenter(tabs);
	    borderPane.setBottom(keyLabel);

	    scene = new Scene(borderPane);

	    scene.setOnKeyPressed((ke) -> {
                keyLabel.setText("Key Pressed: " + ke.getCode());
                if (ke.isControlDown() && ke.getCode().equals(KeyCode.C))
                	System.exit(0);
        });
	    
	    this.stage.setScene(scene);
	    this.stage.show();      
	}
	
	public void run(){
		Application.launch();
	}
	
	public void addStudent(Student student){
		tabs.addStudent(student);
	}
	
	public void removeStudent(Student student){
		tabs.removeStudent(student);
	}
	
	public void notModifiedStudent(Student student){
		tabs.notModifiedStudent(student);
	}
	
	public static void main(String[] args){
		MainGUI gui=new MainGUI();
		GuiLogger logger=new GuiLogger(gui);
		gui.run();
		
		logger.log("ADDED", new Student());
		
	}
	

}
