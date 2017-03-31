package gui2;

import java.util.HashMap;
import java.util.LinkedList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class LoginWindowController {
	
	private LinkedList<LogPass> logPassList;
	void setLogPassList(LinkedList<LogPass> logPassList) {
		this.logPassList = logPassList;
	}

	LogPass temp=new LogPass();
	@FXML TextField loginTextField;
	@FXML TextField passwordTextField;
	@FXML Label infoLabel;
	Stage stage;
	Scene newUserScene;
	Scene mainWindowScene;
	
	void setNewUserScene(Scene newUserScene) {
		this.newUserScene = newUserScene;
	}

	void setMainWindowScene(Scene mainWindowScene) {
		this.mainWindowScene = mainWindowScene;
	}

	void setStage(Stage stage) {
		this.stage = stage;
	}

	public void loginButtonClick(){
		temp.setLogin(loginTextField.getText());
		temp.setPassword(passwordTextField.getText());
		if (logPassList.contains(temp)){
			for (LogPass x:logPassList){
				if (x.equals(temp) && x.getPassword()==temp.getPassword()){
					stage.setScene(mainWindowScene);
				}
			}
		}
		else{
			infoLabel.setText("Wrong login or password!");
		}
	}
	
	public void newUserButtonClick(){
		stage.setScene(newUserScene);
	}
	
	@FXML public void initialize(){
		
	}
}
