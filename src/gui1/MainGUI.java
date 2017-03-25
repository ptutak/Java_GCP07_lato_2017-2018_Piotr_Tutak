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
	    notifyAll();
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
		Thread guiThread=new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Application.launch(gui.getClass());
			}	
		});
		guiThread.start();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		GuiLogger logger=new GuiLogger(gui);
		logger.log("ADDED", new Student("Piotr","Tutak",29,3.0));
		logger.log("REMOVED", new Student("Piotr","Tutak",29,3.0));
		logger.log("ADDED", new Student("Piotr","Tutak",29,3.0));
		logger.log("ADDED", new Student("Piotr","Tutakk",29,3.0));
		logger.log("ADDED", new Student("Piotr","Tutakkk",29,3.0));
	
	try {
		guiThread.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
