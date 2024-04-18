import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ReportScreen extends BorderPane {
	private Button back;
	private Button export;
	private TextArea ta;

	public ReportScreen(Stage stage, Scene scene) {
		super.setStyle(
				"-fx-background-image: url('file:///C:/java-2/DataBase%20Project/src/quality-of-earnings-reports-assessment-tips.jpeg');"
						+ "-fx-background-size: 1545 790;-fx-background-position: center center;");

		super.setPadding(new Insets(10, 10, 10, 10));

		ta = new TextArea();
		ta.setStyle("-fx-background-color: gray;" + "-fx-background-radius: 5;" + "-fx-padding: 5px;"
				+ "-fx-font-size: 18px;" + "-fx-text-fill: black;" + "-fx-prompt-text-fill: #A9A9A9;"
				+ "-fx-border-color: #CCCCCC;" + "-fx-border-radius: 5;" + "-fx-border-width: 1px;");
		ta.appendText("                                                            Almustaqbal Car Rental Company\n");
		ta.appendText("Our available cars\n");
		for (int i = 0; i < Driver.cars.size(); i++)
			ta.appendText(Driver.cars.get(i).toString() + "\n");

		ta.appendText("\nOur customer records" + "\n");
		for (int i = 0; i < Driver.customers.size(); i++)
			ta.appendText(Driver.customers.get(i).toString() + "\n");

		ta.appendText("\nOur Employee records" + "\n");
		for (int i = 0; i < Driver.employees.size(); i++) {
			ta.appendText(Driver.employees.get(i).toString() + "\n");
			Driver.employees.get(i).count=0;
		}

		double earnings = 0;
		ta.appendText("\nReservation history" + "\n");
		for (int i = 0; i < Driver.rentals.size(); i++) {
			ta.appendText(Driver.rentals.get(i).toString() + "\n");
			earnings = earnings + Driver.rentals.get(i).getPrice();
		}

		ta.appendText("\n\nTotal profits from reservations is : " + earnings);

		for (int j = 0; j < Driver.rentals.size(); j++)
			for (int i = 0; i < Driver.employees.size(); i++) {
				if (Driver.rentals.get(j).getEmpID() == Driver.employees.get(i).getEmpID()) {
					Driver.employees.get(i).setCount();
				}

			}

		ta.appendText("\n\nThe number of sales per employee : \n");
		for (int i = 0; i < Driver.employees.size(); i++)
			ta.appendText(Driver.employees.get(i).getEmpName() + " : " + Driver.employees.get(i).getCount() + "\n");

		export = new Button("Export the Report");
		StackPane pane = new StackPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(100, 300, 100, 300));

		pane.getChildren().add(ta);
		super.setCenter(pane);

		export.setStyle(
				"-fx-background-color: #4B5320; -fx-text-fill: white; -fx-font-size: 14pt; -fx-padding: 10px 20px;");
		FileChooser fch = new FileChooser();
		export.setOnAction(e -> {

			File f = fch.showOpenDialog(stage);
			if (f != null) {
				try {
					PrintWriter pr = new PrintWriter(f);

					pr.print(ta.getText());
					ta.clear();
					ta.appendText("The report has been exported successfully\n Have a nice day (^-^)");
					pr.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// *****************************************************//
		back = new Button("Back");
		back.setStyle(
				"-fx-background-color: #4B5320; -fx-text-fill: white; -fx-font-size: 14pt; -fx-padding: 10px 20px;");

		back.setOnAction(e -> {
			stage.setScene(scene);
		});

		HBox p = new HBox();
		p.setSpacing(1255);
		p.getChildren().addAll(back, export);
		super.setBottom(p);
	}

}
