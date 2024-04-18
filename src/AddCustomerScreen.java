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
import javafx.stage.Stage;

public class AddCustomerScreen extends BorderPane {
	private Button back;
	private TextField tx1;
	private TextField tx2;
	private TextField tx3;
	private Button Add;
	private TableView<Customer> table;

	public AddCustomerScreen(Stage stage, Scene scene) {//
		super.setStyle(
				"-fx-background-image: url('file:///C:/java-2/DataBase%20Project/src/dl.beatsnoop.com-3000-omRCbG3026.jpg');"
						+ "-fx-background-size: 1545 790;-fx-background-position: center center;");

		Add = new Button("Add");

		Add.setStyle(
				"-fx-background-color: #4B5320; -fx-text-fill: white; -fx-font-size: 18pt; -fx-padding: 7px 16px;");

		tx1 = new TextField();
		tx2 = new TextField();
		tx3 = new TextField();
		tx1.setStyle("-fx-background-color: gray;" + "-fx-background-radius: 5;" + "-fx-padding: 5px;"
				+ "-fx-font-size: 18px;" + "-fx-text-fill: white;" + "-fx-prompt-text-fill: #A9A9A9;"
				+ "-fx-border-color: #CCCCCC;" + "-fx-border-radius: 5;" + "-fx-border-width: 1px;");
		tx2.setStyle("-fx-background-color: gray;" + "-fx-background-radius: 5;" + "-fx-padding: 5px;"
				+ "-fx-font-size: 18px;" + "-fx-text-fill: white;" + "-fx-prompt-text-fill: #A9A9A9;"
				+ "-fx-border-color: #CCCCCC;" + "-fx-border-radius: 5;" + "-fx-border-width: 1px;");
		tx3.setStyle("-fx-background-color: gray;" + "-fx-background-radius: 5;" + "-fx-padding: 5px;"
				+ "-fx-font-size: 18px;" + "-fx-text-fill: white;" + "-fx-prompt-text-fill: #A9A9A9;"
				+ "-fx-border-color: #CCCCCC;" + "-fx-border-radius: 5;" + "-fx-border-width: 1px;");

		tx1.setPromptText("Enter Name");
		tx2.setPromptText("Enter Address");
		tx3.setPromptText("Enter Phone Num");

		table = TableViewGenerator.createCustomerTableView(Driver.customers);
		table.setStyle("-fx-background-color: gray;");

		GridPane p = new GridPane();
		p.setVgap(15);
		p.setHgap(15);
		p.setAlignment(Pos.CENTER);
		p.setPadding(new Insets(10, 10, 10, 10));
		p.add(tx1, 0, 0);
		p.add(tx2, 0, 1);
		p.add(tx3, 0, 2);
		p.add(table, 1, 4);
		p.add(Add, 1, 5);
		p.setAlignment(Pos.CENTER_RIGHT);

		super.setCenter(p);
		super.setAlignment(p, Pos.CENTER_RIGHT);

		Add.setOnAction(e -> {
			Driver.addCustomer(tx1.getText(), tx2.getText(), tx3.getText());
			Driver.customers.clear();
			Driver.customers = Driver.getAllCustomers();
			tx1.clear();
			tx2.clear();
			tx3.clear();
			p.getChildren().remove(table);
			table = TableViewGenerator.createCustomerTableView(Driver.customers);
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