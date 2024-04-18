import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class RentalScreen extends BorderPane {

	private TableView<Rental> table;
	private TableView<Car> table1;
	private TableView<Customer> table2;
	private TableView<Employee> table3;
	private Button back;
	private Button btCustomer;
	private DatePicker tx2;
	private DatePicker tx3;
	private Button btEmployee;
	private TextField tx5;
	private Button retention;
	private Button btSelectCar;
	private Button calcolate;

	public RentalScreen(Stage stage, Scene scene) {
		StringConverter<LocalDate> stringConverter = new StringConverter<>() {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			@Override
			public String toString(LocalDate localDate) {
				if (localDate != null) {
					return dateFormatter.format(localDate);
				} else {
					return "";
				}
			}

			@Override
			public LocalDate fromString(String dateString) {
				if (dateString != null && !dateString.isEmpty()) {
					return LocalDate.parse(dateString, dateFormatter);
				} else {
					return null;
				}
			}
		};
		table = TableViewGenerator.createRentalTableView(Driver.rentals);
		table1 = TableViewGenerator.createAvailableCarsTableView(Driver.cars);
		table2 = TableViewGenerator.createCustomerTableView(Driver.customers);
		table3 = TableViewGenerator.createEmployeeTableView(Driver.employees);

		super.setStyle(
				"-fx-background-image: url('file:///C:/java-2/DataBase%20Project/src/dl.beatsnoop.com-3000-MdFoZwuV0e.jpg');"
						+ "-fx-background-size: 1545 790;-fx-background-position: center center;");

		btSelectCar = new Button("Select Car          ");
		btSelectCar.setStyle(
				"-fx-background-color: #4B5320; -fx-text-fill: white; -fx-font-size: 14pt; -fx-padding: 10px 20px;");

		btSelectCar.setOnAction(e -> {
			StackPane pane = new StackPane(table1);
			pane.setAlignment(Pos.CENTER);
			Scene scene1 = new Scene(pane, 400, 400);

			Stage s = new Stage();
			s.setScene(scene1);
			s.show();
		});

		btEmployee = new Button("Select Employee");
		btEmployee.setStyle(
				"-fx-background-color: #4B5320; -fx-text-fill: white; -fx-font-size: 14pt; -fx-padding: 10px 20px;");

		btEmployee.setOnAction(e -> {
			StackPane pane = new StackPane(table3);
			pane.setAlignment(Pos.CENTER);
			Scene scene1 = new Scene(pane, 400, 400);

			Stage s = new Stage();
			s.setScene(scene1);
			s.show();
		});

		btCustomer = new Button("Select Customer");
		btCustomer.setStyle(
				"-fx-background-color: #4B5320; -fx-text-fill: white; -fx-font-size: 14pt; -fx-padding: 10px 20px;");

		btCustomer.setOnAction(e -> {
			StackPane pane = new StackPane(table2);
			pane.setAlignment(Pos.CENTER);
			Scene scene1 = new Scene(pane, 400, 400);

			Stage s = new Stage();
			s.setScene(scene1);
			s.show();
		});
//**********************************************************************************************************//
		retention = new Button("retention");

		retention.setStyle(
				"-fx-background-color: #4B5320; -fx-text-fill: white; -fx-font-size: 18pt; -fx-padding: 7px 16px;");

		tx2 = new DatePicker();
		tx3 = new DatePicker();

		tx5 = new TextField();
		tx2.setConverter(stringConverter);
		tx3.setConverter(stringConverter);

		tx2.setPromptText("Reantal Date");
		tx3.setPromptText("Return Date");
		tx5.setPromptText("required to pay");

		tx2.setStyle("-fx-background-color: gray;" + "-fx-background-radius: 5;" + "-fx-padding: 5px;"
				+ "-fx-font-size: 18px;" + "-fx-text-fill: white;" + "-fx-prompt-text-fill: #A9A9A9;"
				+ "-fx-border-color: #CCCCCC;" + "-fx-border-radius: 5;" + "-fx-border-width: 1px;");
		tx3.setStyle("-fx-background-color: gray;" + "-fx-background-radius: 5;" + "-fx-padding: 5px;"
				+ "-fx-font-size: 18px;" + "-fx-text-fill: white;" + "-fx-prompt-text-fill: #A9A9A9;"
				+ "-fx-border-color: #CCCCCC;" + "-fx-border-radius: 5;" + "-fx-border-width: 1px;");

		tx5.setStyle("-fx-background-color: gray;" + "-fx-background-radius: 5;" + "-fx-padding: 5px;"
				+ "-fx-font-size: 18px;" + "-fx-text-fill: white;" + "-fx-prompt-text-fill: #A9A9A9;"
				+ "-fx-border-color: #CCCCCC;" + "-fx-border-radius: 5;" + "-fx-border-width: 1px;");

		calcolate = new Button("Calculate the amount");
		calcolate.setStyle(
				"-fx-background-color: #4B5320; -fx-text-fill: white; -fx-font-size: 14pt; -fx-padding: 10px 20px;");
		calcolate.setOnAction(e -> {

			long daysDifference = ChronoUnit.DAYS.between(tx2.getValue(), tx3.getValue());
			double price = daysDifference * table1.getSelectionModel().getSelectedItem().getPrice();
			tx5.setText(price + "");
		});

		GridPane p = new GridPane();
		p.setVgap(15);
		p.setHgap(15);
		p.setAlignment(Pos.CENTER);
		p.setPadding(new Insets(10, 10, 10, 10));

		p.add(calcolate, 0, 5);
		p.add(btCustomer, 1, 0);
		p.add(btEmployee, 1, 1);
		p.add(btSelectCar, 1, 2);
		p.add(tx2, 1, 3);
		p.add(tx3, 1, 4);
		p.add(tx5, 1, 5);
		p.add(retention, 1, 6);

		p.setAlignment(Pos.TOP_RIGHT);

		super.setCenter(p);
		super.setAlignment(p, Pos.CENTER_RIGHT);
		StackPane p1 = new StackPane();
		p1.setPadding(new Insets(10, 50, 450, 10));
		p1.getChildren().add(table);
		super.setRight(p1);

		retention.setOnAction(e -> {

			int customerID = table2.getSelectionModel().getSelectedItem().getCustomerID();
			int carID = table1.getSelectionModel().getSelectedItem().getCarID();
			int employeeID = table3.getSelectionModel().getSelectedItem().getEmpID();

			Driver.addRental(customerID, carID, String.valueOf(tx2.getValue()), String.valueOf(tx3.getValue()),
					Double.valueOf(tx5.getText()), employeeID);

			Driver.updateCarAvailability(carID, "No");
			Driver.cars.clear();
			Driver.cars = Driver.getAllCars();
			table1.getItems().clear();
			table1 = TableViewGenerator.createAvailableCarsTableView(Driver.cars);
			Driver.rentals.clear();
			Driver.rentals = Driver.getAllRentals();
			table.getItems().clear();
			table = TableViewGenerator.createRentalTableView(Driver.rentals);
			p1.getChildren().remove(table);
			p1.getChildren().add(table);
			tx2.setValue(null);
			tx3.setValue(null);
			tx5.clear();
			showNotification("Addition succeeded", "Added successfully, you can check the database");

		});

		// **************************************************************************//
		back = new Button("Back");
		back.setStyle(
				"-fx-background-color: #4B5320; -fx-text-fill: white; -fx-font-size: 14pt; -fx-padding: 10px 20px;");

		back.setOnAction(e -> {
			stage.setScene(scene);
		});

		super.setBottom(back);

	}

	public TextField tx(String[] suggestions) {
		TextField searchBox = new TextField();
		ContextMenu suggestionsMenu = new ContextMenu();

		searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
			suggestionsMenu.getItems().clear();

			if (!newValue.isEmpty()) {
				for (String suggestion : suggestions) {
					if (suggestion.startsWith(newValue)) {
						MenuItem item = new MenuItem(suggestion);
						item.setOnAction(event -> {
							searchBox.setText(suggestion);
							suggestionsMenu.hide();
						});
						suggestionsMenu.getItems().add(item);
					}
				}
				if (!suggestionsMenu.getItems().isEmpty()) {
					suggestionsMenu.show(searchBox, Side.BOTTOM, 0, 0);
				} else {
					suggestionsMenu.hide();
				}
			} else {
				suggestionsMenu.hide();
			}
		});

		return searchBox;
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
