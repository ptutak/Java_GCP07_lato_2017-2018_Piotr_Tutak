package Tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLTest extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Parent node=FXMLLoader.load(this.getClass().getResource("test.fxml"));
		
		
		Scene scene=new Scene(node,400,400);
		arg0.setScene(scene);
		arg0.show();
	}
	
	public static void main(String[] args){
		Application.launch(args);
		
		
	}

}
