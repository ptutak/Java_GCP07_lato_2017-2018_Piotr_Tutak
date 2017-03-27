package gui1;

import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kolekcje_i_algorytmy.Student;

public class MainGUI extends Application {
	private BorderPane borderPane;
	private static CustomTabPane tabs;
	private CustomMenuBar menu;
	private Scene scene;
	private Label keyLabel;
	private Stage stage;

	@Override
	public void start(Stage stage) {      
		this.stage=stage;
		borderPane = new BorderPane();    
		borderPane.setMinSize(400, 400);
		scene = new Scene(borderPane);

	    borderPane.setTop(menu);
	    tabs=new CustomTabPane();

	    menu=new CustomMenuBar();
	    keyLabel = new Label();
	    borderPane.setCenter(tabs);
	    borderPane.setBottom(keyLabel);

	    scene.setOnKeyPressed((ke) -> {
                keyLabel.setText("Key Pressed: " + ke.getCode());
                if (ke.isControlDown() && ke.getCode().equals(KeyCode.C))
                	System.exit(0);
        });

		this.stage.setTitle("Crawler GUI"); 
	    this.stage.setScene(scene);
	    this.stage.show();
	}
	
	public static synchronized void addStudent(Student student){
		tabs.addStudent(student);
	}
	
	public static synchronized void removeStudent(Student student){
		tabs.removeStudent(student);
	}
	
	public static synchronized void notModifiedStudent(Student student){
		tabs.notModifiedStudent(student);
	}

	public static void main(String[] args){
	    MainGUI gui=new MainGUI();
		Thread loggerThread=new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				GuiLogger logger=new GuiLogger(gui);
				logger.log("ADDED", new Student("Piotr","Tutak",29,3.0));
				logger.log("REMOVED", new Student("Piotr","Tutak",29,3.0));
				logger.log("ADDED", new Student("Piotr","Tutak",29,3.0));
				logger.log("ADDED", new Student("Piotr","Tutakk",29,3.0));
				logger.log("ADDED", new Student("Piotr","Tutakkk",29,3.0));
				logger.log("NOT MODIFIED", new Student("Piotr","Tutakkk",29,3.0));
				
			
	
			}	
		});
		loggerThread.start();
	Application.launch(gui.getClass());
	try {
		loggerThread.join();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	}

}
