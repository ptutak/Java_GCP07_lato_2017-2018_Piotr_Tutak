package gui2;

import java.util.LinkedList;

import io_serializacja.HashPass;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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
	Scene loadFromFileScene;
	
	void setLoadFromFileScene(Scene loadFromFileScene) {
		this.loadFromFileScene = loadFromFileScene;
	}

	void setNewUserScene(Scene newUserScene) {
		this.newUserScene = newUserScene;
	}

	void setMainWindowScene(Scene mainWindowScene) {
		this.mainWindowScene = mainWindowScene;
	}

	void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML public void loginButtonClick(){
		temp.setLogin(loginTextField.getText());
		HashPass hash=new HashPass();
		temp.setPassword(hash.toHash(passwordTextField.getText()));
		if (logPassList.contains(temp)){
			for (LogPass x:logPassList){
				if (x.equals(temp) && x.getPassword().equals(temp.getPassword())){
					stage.setScene(mainWindowScene);
				}
			}
		}
		else{
			infoLabel.setText("Wrong login or password!");
		}
	}
	
	
	@FXML public void newUserButtonClick(){
		loginTextField.setText("");
		passwordTextField.setText("");
		stage.setScene(newUserScene);
	}
	
	@FXML public void loadFromFileButtonClick(){
		loginTextField.setText("");
		passwordTextField.setText("");
		stage.setScene(loadFromFileScene);
	}
	
	@FXML public void initialize(){
		
	}
}
