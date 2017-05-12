package gui2;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.Alert.AlertType;

public class MenuBarController {
	
	@FXML Menu menuAbout;
	Label menuAboutLabel;
	Alert info;
	
	@FXML public void closeClick(){
		System.exit(0);
	}
	
	@FXML public void initialize(){
		
		info = new Alert(AlertType.INFORMATION);
		info.setTitle("About");
		info.setHeaderText(null);
		info.setContentText("Java - programming excercises.\n"
				+ "Piotr Tutak, gr. LAB07\n");

		menuAboutLabel=new Label("About");
		menuAboutLabel.setOnMouseClicked((t)->{
			info.showAndWait();
		});
		menuAbout.setGraphic(menuAboutLabel);
	}
}
