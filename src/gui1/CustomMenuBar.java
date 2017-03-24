package gui1;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class CustomMenuBar extends MenuBar {

	Menu menuProgram;
	Menu menuAbout;
	
	MenuItem close;
	
	CustomMenuBar(){
		menuProgram=new Menu("Program");
		close=new MenuItem("Close Ctrl+C");
		close.setOnAction((t) -> {
			System.exit(0);
		});
		menuProgram.getItems().add(close);
		menuAbout=new Menu("About");
		
		this.getMenus().addAll(menuProgram,menuAbout);
		
	}
}
