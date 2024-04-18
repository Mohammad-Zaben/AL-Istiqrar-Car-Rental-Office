import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AddCarScreen extends BorderPane {

	private Button back;
	private TextField tx1;
	private TextField tx2;
	private TextField tx3;
	private TextField tx4;

	private Button Add;
	TableView<Car> table;
	Font font = Font.font("", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 15);

	public AddCarScreen(Stage stage, Scene scene) {
		super.setStyle(
				"-fx-background-image: url('file:///C:/java-2/DataBase%20Project/src/car-7975604_1280.jpg');"
						+ "-fx-background-size: 1545 790;-fx-background-position: center center;");

		super.setPadding(new Insets(10, 10, 10, 10));
		Add = new Button("Add");

		Add.setStyle(
				"-fx-background-color: #4B5320; -fx-text-fill: white; -fx-font-size: 18pt; -fx-padding: 7px 16px;");

		tx1 = new TextField();
		tx2 = new TextField();
		tx3 = new TextField();
		tx4 = new TextField();

		tx1.setStyle("-fx-background-color: #F5F5F5;" + "-fx-background-radius: 5;" + "-fx-padding: 5px;"
				+ "-fx-font-size: 14px;" + "-fx-text-fill: #333333;" + "-fx-prompt-text-fill: #A9A9A9;"
				+ "-fx-border-color: #CCCCCC;" + "-fx-border-radius: 5;" + "-fx-border-width: 1px;");
		tx2.setStyle("-fx-background-color: #F5F5F5;" + "-fx-background-radius: 5;" + "-fx-padding: 5px;"
				+ "-fx-font-size: 14px;" + "-fx-text-fill: #333333;" + "-fx-prompt-text-fill: #A9A9A9;"
				+ "-fx-border-color: #CCCCCC;" + "-fx-border-radius: 5;" + "-fx-border-width: 1px;");
		tx3.setStyle("-fx-background-color: #F5F5F5;" + "-fx-background-radius: 5;" + "-fx-padding: 5px;"
				+ "-fx-font-size: 14px;" + "-fx-text-fill: #333333;" + "-fx-prompt-text-fill: #A9A9A9;"
				+ "-fx-border-color: #CCCCCC;" + "-fx-border-radius: 5;" + "-fx-border-width: 1px;");
		tx4.setStyle("-fx-background-color: #F5F5F5;" + "-fx-background-radius: 5;" + "-fx-padding: 5px;"
				+ "-fx-font-size: 14px;" + "-fx-text-fill: #333333;" + "-fx-prompt-text-fill: #A9A9A9;"
				+ "-fx-border-color: #CCCCCC;" + "-fx-border-radius: 5;" + "-fx-border-width: 1px;");

		tx1.setPromptText("Enter Brand");
		tx2.setPromptText("Enter Model");
		tx3.setPromptText("Enter Year");
		tx4.setPromptText("Enter price");

		table = TableViewGenerator.createCarTableView(Driver.cars);

		GridPane p = new GridPane();
		p.setVgap(15);
		p.setHgap(15);
		p.setAlignment(Pos.CENTER);
		p.setPadding(new Insets(10, 10, 10, 10));
		p.add(tx1, 0, 0);
		p.add(tx2, 0, 1);
		p.add(tx3, 0, 2);
		p.add(tx4, 0, 3);
		p.add(table, 1, 4);
		p.add(Add, 1, 5);
		p.setAlignment(Pos.CENTER);

		super.setCenter(p);
		super.setAlignment(p, Pos.CENTER);

		Add.setOnAction(e -> {
			Driver.addCar(tx1.getText(), tx2.getText(), Integer.valueOf(tx3.getText()), "yes",
					Double.valueOf(tx4.getText()));
			Driver.cars.clear();
			Driver.cars = Driver.getAllCars();
			tx1.clear();
			tx2.clear();
			tx3.clear();
			tx4.clear();
			table.getItems().clear();
			table = TableViewGenerator.createCarTableView(Driver.cars);
			p.getChildren().remove(table);
			p.add(table, 1, 4);
			showNotification("Addition succeeded", "Added successfully, you can check the database");
		});

		// *****************************************************//
		back = new Button("Back");
		back.setStyle(
				"-fx-background-color: #4B5320; -fx-text-fill: white; -fx-font-size: 14pt; -fx-padding: 10px 20px;");

		back.setOnAction(e -> {
			stage.setScene(scene);
		});

		super.setBottom(back);
	}

	private void showNotification(String title, String message) {
		if (SystemTray.isSupported()) {
			SystemTray tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().getImage("path/to/notification-icon.png");

			TrayIcon trayIcon = new TrayIcon(image, "Notification");
			trayIcon.setImageAutoSize(true);
			trayIcon.setToolTip("Notification");

			try {
				tray.add(trayIcon);
				trayIcon.displayMessage(title, message, TrayIcon.MessageType.INFO);
			} catch (AWTException ex) {
				System.err.println("Error displaying notification");
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(title);
			alert.setHeaderText(null);
			alert.setContentText(message);
			alert.showAndWait();
		}
	}
}
