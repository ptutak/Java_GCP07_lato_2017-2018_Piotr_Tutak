package GUI1;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class CustomListView extends AnchorPane{
	CustomListView(){
		 ObservableList<String> names = FXCollections.observableArrayList(
		          "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");
		 ListView<String> listView = new ListView<String>(names);
		 this.getChildren().add(listView);
	}
}
