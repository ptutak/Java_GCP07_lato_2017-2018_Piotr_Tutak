package gui2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Properties;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoadFromFileWindowController {
	
	@FXML Label infoLabel;
	@FXML TextField textField;
	@FXML Button loadDataButton;
	@FXML Button cancelButton;
	
	Stage stage;
	Scene mainWindowScene;
	Scene loginScene;
	
	private LinkedList<LogPass> logPassList;
	
	void setLogPassList(LinkedList<LogPass> logPassList) {
		this.logPassList = logPassList;
	}

	void setStage(Stage stage) {
		this.stage = stage;
	}

	void setMainWindowScene(Scene mainWindowScene) {
		this.mainWindowScene = mainWindowScene;
	}

	void setLoginScene(Scene loginScene) {
		this.loginScene = loginScene;
	}
	
	void loadDataButtonClick(){
		LogPass logPass=new LogPass();
		Properties properties=new Properties();
		InputStream input = null;
		
		String fileName=textField.getText();

		try {
			input = new FileInputStream(fileName);

			properties.load(input);
			
			logPass.setLogin(properties.getProperty("login"));
			logPass.setPassword(properties.getProperty("password"));
			logPass.setAge(Integer.parseInt(properties.getProperty("age")));
			logPass.setLocation(properties.getProperty("location"));
			logPass.setSex(properties.getProperty("sex"));
			
			if (logPassList.contains(logPass)){
				for (LogPass x:logPassList){
					if (x.equals(logPass) && x.getPassword().equals(logPass.getPassword())){
						stage.setScene(mainWindowScene);
					}
				}
			}
			else{
				logPassList.add(logPass);
				stage.setScene(loginScene);
			}
		} catch (FileNotFoundException e){
			infoLabel.setText("File Not Found !!!");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	void cancelButtonClick(){
		textField.setText("");
		stage.setScene(loginScene);
	}

}
