package Tests;

import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Kontroler {
	@FXML
	private Label dowolna;
	@FXML
	private void click(){
		dowolna.setText("Klik!");
	}
	
	
	@FXML
	public void initialize(){
		dowolna.setText("Czeœæ !!! :D");
	}

}
