package gui2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGUI2 extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent mainNode=FXMLLoader.load(this.getClass().getResource("borderPane.fxml"));
		Scene scene=new Scene(mainNode);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}
}
