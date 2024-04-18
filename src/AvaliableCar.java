import java.util.List;

import javafx.application.Application;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AvaliableCar extends BorderPane {
	TableView<Car> table;
	private ComboBox<Employee> cb;
	private TextField tx1;
	private TextField tx2;
	private TextField tx3;

	public AvaliableCar(TableView<Car> table, List<Employee> empmloyeeList) {
		cb = new ComboBox<Employee>();
		cb.getItems().addAll(empmloyeeList);
		tx1 = new TextField();
		tx2 = new TextField();
		tx3 = new TextField();

		
	}

}
