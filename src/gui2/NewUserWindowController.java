package gui2;

import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewUserWindowController {
	
	@FXML Label infoLabel;
	@FXML Button okButton;
	@FXML Button clearButton;
	@FXML Button cancelButton;
	@FXML TextField loginTextField;
	@FXML TextField passwordTextField;
	@FXML TextField ageTextField;
	@FXML ChoiceBox sexChoiceBox;
	@FXML TextField locationTextField;
	
	private Stage stage;
	private Scene loginScene;
	private LinkedList<LogPass> logPassList;
	
	void setLogPassList(LinkedList<LogPass> logPassList) {
		this.logPassList = logPassList;
	}

	void setStage(Stage stage) {
		this.stage = stage;
	}

	void setLoginScene(Scene loginScene) {
		this.loginScene = loginScene;
	}
	
	public void newUserButtonClick(){
		LogPass tmp=new LogPass();
		tmp.setLogin(loginTextField.getText());
		tmp.setPassword(passwordTextField.getText());
		int age=0;
		try{
			age=Integer.parseInt(ageTextField.getText());	
		} catch(NumberFormatException e){
			infoLabel.setText("Wrong Age");
		}
		tmp.setAge(age);
		tmp.setSex((String)sexChoiceBox.getValue());
		tmp.setLocation(locationTextField.getText());
		if (!logPassList.contains(tmp) && tmp.getLogin()!="" && tmp.getPassword()!=""){
			logPassList.add(tmp);
			clearButtonClick();
			stage.setScene(loginScene);
		} else{
			infoLabel.setText("User exists or login or password is empty");
		}
	}
	
	public void clearButtonClick(){
		loginTextField.setText("");
		passwordTextField.setText("");
		ageTextField.setText("");
		locationTextField.setText("");
	}
	
	public void cancelButtonClick(){
		clearButtonClick();
		stage.setScene(loginScene);
		
	}

	@FXML public void initialize(){
		sexChoiceBox.setItems(FXCollections.observableArrayList("Male","Female"));

	}

}
