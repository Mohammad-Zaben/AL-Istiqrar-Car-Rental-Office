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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AddEmployeeScreen extends BorderPane {

	private Button btAdd;
	private Button btUpdate;
	private VBox leftpane;
	private Button back;
	private TextField tx1;
	private TextField tx2;
	private TextField tx3;
	private TextField tx4;
	private Button Add;
	private Button Update;
	TableView<Employee> table;
	Font font = Font.font("", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 15);

	Driver d = new Driver();

	public AddEmployeeScreen(Stage stage, Scene scene) {
		super.setStyle(
				"-fx-background-image: url('file:///C:/java-2/DataBase%20Project/src/Flat_openSpace_offices_03.jpg');"
						+ "-fx-background-size: 1545 790;-fx-background-position: center center;");

		super.setPadding(new Insets(10, 10, 10, 10));
		Add = new Button("Add");
		btAdd = new Button("Add Employee");
		btUpdate = new Button("Update info");
		btAdd.setStyle(
				"-fx-background-color: #4B5320; -fx-text-fill: white; -fx-font-size: 18pt; -fx-padding: 7px 16px;");
		btUpdate.setStyle(
				"-fx-background-color: #4B5320; -fx-text-fill: white; -fx-font-size: 18pt; -fx-padding: 7px 16px;");
		Add.setStyle(
				"-fx-background-color: #4B5320; -fx-text-fill: white; -fx-font-size: 18pt; -fx-padding: 7px 16px;");

		leftpane = new VBox();
		leftpane.setSpacing(50);
		leftpane.getChildren().addAll(btAdd, btUpdate);
		leftpane.setAlignment(Pos.CENTER);
		super.setLeft(leftpane);
		super.setAlignment(leftpane, Pos.CENTER);

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

		tx1.setPromptText("Enter Name");
		tx2.setPromptText("Enter Address");
		tx3.setPromptText("Enter BirthDate");
		tx4.setPromptText("Enter Salary");

		GridPane p = new GridPane();
		p.setVgap(15);
		p.setHgap(15);
		p.setAlignment(Pos.CENTER);
		p.setPadding(new Insets(10, 10, 10, 10));
		p.add(tx1, 0, 0);
		p.add(tx2, 0, 1);
		p.add(tx3, 0, 2);
		p.add(tx4, 0, 3);
		p.add(Add, 0, 4);

		btAdd.setOnAction(e -> {
			super.setCenter(p);
		});
		Add.setOnAction(e -> {
			Driver.addEmployee(tx1.getText(), tx2.getText(), tx3.getText(), Double.valueOf(tx4.getText()));
			Driver.employees.clear();
			Driver.employees = Driver.getAllEmployees();
			tx1.clear();
			tx2.clear();
			tx3.clear();
			tx4.clear();
			showNotification("Addition succeeded", "Added successfully, you can check the database");

		});

		// ***************************************************************//
		Update = new Button("Update");
		Update.setStyle(
				"-fx-background-color: #4B5320; -fx-text-fill: white; -fx-font-size: 18pt; -fx-padding: 7px 16px;");
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10));
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		CheckBox updateSalaryCheckbox = new CheckBox("Update Salary");
		CheckBox updateAddressCheckbox = new CheckBox("Update Address");

		TextField salaryTextField = new TextField();
		TextField addressTextField = new TextField();
		addressTextField.setDisable(true);
		salaryTextField.setDisable(true);
		updateSalaryCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				salaryTextField.setDisable(false);
				salaryTextField.setStyle("-fx-background-color: white; -fx-text-fill: black;");
			} else {
				salaryTextField.setDisable(true);
				salaryTextField.setStyle("-fx-background-color: gray; -fx-text-fill: darkgray;");
			}
		});

		updateAddressCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				addressTextField.setDisable(false);
				addressTextField.setStyle("-fx-background-color: white; -fx-text-fill: black;");
			} else {
				addressTextField.setDisable(true);
				addressTextField.setStyle("-fx-background-color: gray; -fx-text-fill: darkgray;");
			}
		});

		gridPane.add(updateSalaryCheckbox, 0, 0);
		gridPane.add(salaryTextField, 1, 0);
		gridPane.add(updateAddressCheckbox, 0, 1);
		gridPane.add(addressTextField, 1, 1);
		gridPane.add(Update, 0, 2);

		HBox pane = new HBox();
		pane.setSpacing(10);
		pane.setAlignment(Pos.CENTER);
		btUpdate.setOnAction(e -> {
			pane.getChildren().removeAll(table, gridPane);
			table = TableViewGenerator.createEmployeeTableView(Driver.employees);
			pane.getChildren().addAll(table, gridPane);
			super.setCenter(pane);
		});

		Update.setOnAction(e -> {
			int empID = table.getSelectionModel().getSelectedItem().getEmpID();
			if (updateSalaryCheckbox.isSelected()) {
				double salary = Double.valueOf(salaryTextField.getText());
				Driver.updateSalary(empID, salary);
				Driver.employees.get(table.getSelectionModel().getSelectedIndex()).setSalary(salary);
			}
			if (updateAddressCheckbox.isSelected()) {
				String Address = addressTextField.getText();
				Driver.updateEmployeeAddress(empID, Address);
				Driver.employees.get(table.getSelectionModel().getSelectedIndex()).setEmpAddress(Address);
			}

			salaryTextField.clear();
			addressTextField.clear();
			showNotification("Update succeeded", "Update successfully, you can check the database");

		});

		// **************************************************************//
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
