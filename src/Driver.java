
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Driver extends Application {

	private static final String URL = "jdbc:mysql://localhost:3306/CarRentalOffice?useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1202068";

	private PasswordField passwordField;
	static List<Customer> customers;
	static List<Car> cars;
	static List<Rental> rentals;
	static List<Employee> employees;

	@Override
	public void start(Stage stage) {

		customers = getAllCustomers();
		cars = getAllCars();
		rentals = getAllRentals();
		employees = getAllEmployees();
		passwordField = new PasswordField();
		passwordField.setPromptText("Enter Password");
		passwordField.setStyle(
				"-fx-background-color: #f2f2f2; -fx-text-inner-color: #333333; -fx-prompt-text-fill: #999999;");
		passwordField.setStyle("-fx-font-size: 16px;");

		Button loginButton = new Button("Log In");
		loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px;");
		loginButton.setPrefWidth(120);

		HBox hbox = new HBox(10);
		hbox.setPadding(new Insets(20));
		hbox.getChildren().addAll(passwordField, loginButton);
		hbox.setAlignment(Pos.CENTER);

		BorderPane pane = new BorderPane();
		BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(backgroundFill);
		// pane.setBackground(background);
		pane.setStyle(
				"-fx-background-image: url('file:///C:/java-2/DataBase%20Project/src/WhatsApp%20Image%202023-07-01%20at%2016.00.50.jpg');"
						+ "-fx-background-size: 940 600;-fx-background-position: center center;");

		Label titleLabel = new Label("ÇåáÇ Èßã Ýí ãÚÑÖ ÇáãÓÊÞÈá ááÊÇÌíÑ ÇáÓíÇÑÇÊ");
		titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
		titleLabel.setTextFill(Color.WHEAT);
		titleLabel.setAlignment(Pos.CENTER);
		titleLabel.setPadding(new Insets(20));

		pane.setAlignment(titleLabel, Pos.CENTER);
		pane.setTop(titleLabel);
		pane.setCenter(hbox);

		Scene scene = new Scene(pane, 1545, 790);

		loginButton.setOnAction(e -> {
			String password = passwordField.getText();
			if (password.equals("admin")) {
				passwordField.clear();
				addButtonsToPane(stage, scene);
			} else {
				showNotification("The entered password is incorrect", "Try again with a valid password");
			}
		});

		stage.setScene(scene);
		stage.setTitle("Login Application");
		// stage.setFullScreen(true);
		stage.show();
	}

	private void addButtonsToPane(Stage stage, Scene lastscene) {

		VBox vbox = new VBox(20);
		vbox.setPadding(new Insets(20));
		vbox.setAlignment(Pos.CENTER);

		Button addEmployeeButton = new Button("           Employee Department");
		addEmployeeButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 20px;");
		addEmployeeButton.setPrefWidth(350);

		// Set the logo image for addEmployeeButton
		Image addEmployeeLogo = new Image("file:///C:/java-2/DataBase%20Project/src/add-male-user-icon.png");
		ImageView addEmployeeLogoImageView = new ImageView(addEmployeeLogo);
		addEmployeeLogoImageView.setFitWidth(50);
		addEmployeeLogoImageView.setPreserveRatio(true);
		addEmployeeButton.setGraphic(addEmployeeLogoImageView);

		Button showAvailableCarsButton = new Button("                    New Rental Car");
		showAvailableCarsButton.setStyle("-fx-background-color: #FFC107; -fx-text-fill: white; -fx-font-size: 20px;");
		showAvailableCarsButton.setPrefWidth(350);

		// Set the logo image for showAvailableCarsButton
		Image showAvailableCarsLogo = new Image("file:///C:/java-2/DataBase%20Project/src/img_466156.png");
		ImageView showAvailableCarsLogoImageView = new ImageView(showAvailableCarsLogo);
		showAvailableCarsLogoImageView.setFitWidth(50);
		showAvailableCarsLogoImageView.setPreserveRatio(true);
		showAvailableCarsButton.setGraphic(showAvailableCarsLogoImageView);

		Button CreateReportButton = new Button("                  Create a report");
		CreateReportButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: white; -fx-font-size: 20px;");
		CreateReportButton.setPrefWidth(350);

		// Set the logo image for showUnavailableCarsButton
		Image showUnavailableCarsLogo = new Image("file:///C:/java-2/DataBase%20Project/src/2603636-200.png");
		ImageView showUnavailableCarsLogoImageView = new ImageView(showUnavailableCarsLogo);
		showUnavailableCarsLogoImageView.setFitWidth(50);
		showUnavailableCarsLogoImageView.setPreserveRatio(true);
		CreateReportButton.setGraphic(showUnavailableCarsLogoImageView);

		Button addNewCarButton = new Button("     Add New Car & Show Cars");
		addNewCarButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 20px;");
		addNewCarButton.setPrefWidth(350);

		// Set the logo image for addNewCarButton
		Image addNewCarLogo = new Image("file:///C:/java-2/DataBase%20Project/src/7124344.png");
		ImageView addNewCarLogoImageView = new ImageView(addNewCarLogo);
		addNewCarLogoImageView.setFitWidth(50);
		addNewCarLogoImageView.setPreserveRatio(true);
		addNewCarButton.setGraphic(addNewCarLogoImageView);

		Button newCustomerButton = new Button("New Customer & Show Customer");
		newCustomerButton.setStyle("-fx-background-color: #9C27B0; -fx-text-fill: white; -fx-font-size: 18px;");
		newCustomerButton.setPrefWidth(350);

		// Set the logo image for newCustomerButton
		Image newCustomerLogo = new Image("file:///C:/java-2/DataBase%20Project/src/add-user.png");
		ImageView newCustomerLogoImageView = new ImageView(newCustomerLogo);
		newCustomerLogoImageView.setFitWidth(50);
		newCustomerLogoImageView.setPreserveRatio(true);
		newCustomerButton.setGraphic(newCustomerLogoImageView);

		vbox.getChildren().addAll(addEmployeeButton, showAvailableCarsButton, CreateReportButton, addNewCarButton,
				newCustomerButton);

		Button back = new Button("Log out");
		back.setStyle(
				"-fx-background-color: #CCCCCC; -fx-text-fill: black; -fx-font-size: 14pt; -fx-padding: 10px 20px;");

		back.setOnAction(e -> {
			stage.setScene(lastscene);
		});

		BorderPane pane = new BorderPane();
		BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(backgroundFill);
		pane.setBackground(background);

		pane.setCenter(vbox);
		pane.setStyle(
				"-fx-background-image: url('file:///C:/java-2/DataBase%20Project/src/dl.beatsnoop.com-3000-z035FZ84DX.jpg');"
						+ "-fx-background-size: 1545 790;-fx-background-position: center center;");

		// Handle the action for the newCustomerButton here

		Scene scene = new Scene(pane, 1545, 790);
		stage.setScene(scene);

		pane.setBottom(back);

		addEmployeeButton.setOnAction(e -> {
			AddEmployeeScreen employeeScreen = new AddEmployeeScreen(stage, scene);
			Scene scene1 = new Scene(employeeScreen, 1545, 790);
			stage.setScene(scene1);
		});

		addNewCarButton.setOnAction(e -> {
			AddCarScreen addCarScreen = new AddCarScreen(stage, scene);
			Scene scene2 = new Scene(addCarScreen, 1545, 790);
			stage.setScene(scene2);
		});

		newCustomerButton.setOnAction(e -> {
			AddCustomerScreen addCustomerScreen = new AddCustomerScreen(stage, scene);
			Scene scene3 = new Scene(addCustomerScreen, 1545, 790);
			stage.setScene(scene3);
		});

		showAvailableCarsButton.setOnAction(e -> {
			RentalScreen rentalscreen = new RentalScreen(stage, scene);
			Scene scene4 = new Scene(rentalscreen, 1545, 790);
			stage.setScene(scene4);
		});

		CreateReportButton.setOnAction(e -> {
			ReportScreen reportscreen = new ReportScreen(stage, scene);
			Scene scene5 = new Scene(reportscreen, 1545, 790);
			stage.setScene(scene5);
		});

	}

	public static void main(String[] args) {
		launch();

	}

	// Method to add a customer to the Customer table
	public static void addCustomer(String name, String address, String phone) {
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "INSERT INTO Customer (Namee, Address, Phone) VALUES (?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, address);
			statement.setString(3, phone);

			statement.executeUpdate();
			System.out.println("Customer added successfully.");
		} catch (SQLException e) {
			System.err.println("Error adding customer: " + e.getMessage());
		}
	}

	// Method to add a car to the Car table
	public static void addCar(String brand, String model, int year, String available, double price) {
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "INSERT INTO Car (Brand, Model, Year, available, price) VALUES (?, ?, ?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, brand);
			statement.setString(2, model);
			statement.setInt(3, year);
			statement.setString(4, available);
			statement.setDouble(5, price);

			statement.executeUpdate();
			System.out.println("Car added successfully.");
		} catch (SQLException e) {
			System.err.println("Error adding car: " + e.getMessage());
		}
	}

	// Method to add a rental to the Rental table
	public static void addRental(int customerID, int carID, String rentalDate, String returnDate, double price,
			int empID) {
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "INSERT INTO Rental (CustomerID, CarID, RentalDate, ReturnDate, price, empID) VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, customerID);
			statement.setInt(2, carID);
			statement.setString(3, rentalDate);
			statement.setString(4, returnDate);
			statement.setDouble(5, price);
			statement.setInt(6, empID);

			statement.executeUpdate();
			System.out.println("Rental added successfully.");
		} catch (SQLException e) {
			System.err.println("Error adding rental: " + e.getMessage());
		}
	}

	// Method to add an employee to the Employee table
	public static void addEmployee(String name, String address, String birthDate, double salary) {
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "INSERT INTO Employee (empName, empAddress, empBirthDate, salary) VALUES (?, ?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, address);
			statement.setString(3, birthDate);
			statement.setDouble(4, salary);

			statement.executeUpdate();
			System.out.println("Employee added successfully.");
		} catch (SQLException e) {
			System.err.println("Error adding employee: " + e.getMessage());
		}
	}

	// Method to delete a customer from the Customer table
	public static void deleteCustomer(int customerID) {
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "DELETE FROM Customer WHERE CustomerID = ?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, customerID);

			int rowsAffected = statement.executeUpdate();
			System.out.println(rowsAffected + " customer(s) deleted successfully.");
		} catch (SQLException e) {
			System.err.println("Error deleting customer: " + e.getMessage());
		}
	}

	// Method to delete a car from the Car table
	public static void deleteCar(int carID) {
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "DELETE FROM Car WHERE CarID = ?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, carID);

			int rowsAffected = statement.executeUpdate();
			System.out.println(rowsAffected + " car(s) deleted successfully.");
		} catch (SQLException e) {
			System.err.println("Error deleting car: " + e.getMessage());
		}
	}

	// Method to delete a rental from the Rental table
	public static void deleteRental(int rentalID) {
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "DELETE FROM Rental WHERE RentalID = ?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, rentalID);

			int rowsAffected = statement.executeUpdate();
			System.out.println(rowsAffected + " rental(s) deleted successfully.");
		} catch (SQLException e) {
			System.err.println("Error deleting rental: " + e.getMessage());
		}
	}

	// Method to delete an employee from the Employee table
	public static void deleteEmployee(int empID) {
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "DELETE FROM Employee WHERE empID = ?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, empID);

			int rowsAffected = statement.executeUpdate();
			System.out.println(rowsAffected + " employee(s) deleted successfully.");
		} catch (SQLException e) {
			System.err.println("Error deleting employee: " + e.getMessage());
		}
	}

	static public void updateSalary(int empId, double newSalary) {
		String query = "UPDATE employee SET salary = ? WHERE empID = ?";

		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setDouble(1, newSalary);
			statement.setInt(2, empId);

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Salary updated successfully.");
			} else {
				System.out.println("No employee found with empId: " + empId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateEmployeeAddress(int empID, String newAddress) {
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "UPDATE Employee SET empAddress = ? WHERE empID = ?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, newAddress);
			statement.setInt(2, empID);

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Employee address updated successfully.");
			} else {
				System.out.println("No employee found with empID: " + empID);
			}
		} catch (SQLException e) {
			System.err.println("Error updating employee address: " + e.getMessage());
		}
	}

	public static void updateCarAvailability(int carID, String availability) {
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "UPDATE Car SET available = ? WHERE carID = ?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, availability);
			statement.setInt(2, carID);

			int rowsAffected = statement.executeUpdate();
			System.out.println(rowsAffected + " car(s) updated successfully.");
		} catch (SQLException e) {
			System.err.println("Error updating car availability: " + e.getMessage());
		}
	}

	public static List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "SELECT * FROM Customer";

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				int customerID = resultSet.getInt("CustomerID");
				String name = resultSet.getString("Namee");
				String address = resultSet.getString("Address");
				String phone = resultSet.getString("Phone");

				Customer customer = new Customer(customerID, name, address, phone);
				customers.add(customer);
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving customers: " + e.getMessage());
		}

		return customers;
	}

	// Method to retrieve all cars from the Car table and store them in an ArrayList
	public static List<Car> getAllCars() {
		List<Car> cars = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "SELECT * FROM Car";

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				int carID = resultSet.getInt("CarID");
				String brand = resultSet.getString("Brand");
				String model = resultSet.getString("Model");
				int year = resultSet.getInt("Year");
				String available = resultSet.getString("available");
				double price = resultSet.getDouble("price");

				Car car = new Car(carID, brand, model, year, available, price);
				cars.add(car);
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving cars: " + e.getMessage());
		}

		return cars;
	}

	// Method to retrieve all rentals from the Rental table and store them in an
	// ArrayList
	public static List<Rental> getAllRentals() {
		List<Rental> rentals = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "SELECT * FROM Rental";

			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int rentalID = resultSet.getInt("RentalID");
				int customerID = resultSet.getInt("CustomerID");
				int carID = resultSet.getInt("CarID");
				String rentalDate = resultSet.getString("RentalDate");
				String returnDate = resultSet.getString("ReturnDate");
				double price = resultSet.getDouble("price");
				int empID = resultSet.getInt("empID");

				Rental rental = new Rental(rentalID, customerID, carID, rentalDate, returnDate, price, empID);
				rentals.add(rental);
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving rentals: " + e.getMessage());
		}

		return rentals;
	}

	// Method to retrieve all employees from the Employee table and store them in an
	// ArrayList
	public static List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "SELECT * FROM Employee";

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				int empID = resultSet.getInt("empID");
				String name = resultSet.getString("empName");
				String address = resultSet.getString("empAddress");
				String birthDate = resultSet.getString("empBirthDate");
				double salary = resultSet.getDouble("salary");

				Employee employee = new Employee(empID, name, address, birthDate, salary);
				employees.add(employee);
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving employees: " + e.getMessage());
		}

		return employees;
	}

	public TableView<Car> createAvailableCarsTableView() {
		TableView<Car> tableView = new TableView<>();
		ObservableList<Car> availableCars = FXCollections.observableArrayList();

		// Retrieve all cars from the database
		List<Car> allCars = getAllCars();

		System.out.println(allCars.toString());
		// Filter cars based on availability
		for (int i = 0; i < allCars.size(); i++) {
			if (allCars.get(i).getAvailable().equals("yes") || allCars.get(i).getAvailable().equals("Yes")) {
				availableCars.add(allCars.get(i));
			}
		}

		// Create table columns and map them to Car class properties
		TableColumn<Car, Integer> carIDColumn = new TableColumn<>("Car ID");
		carIDColumn.setCellValueFactory(new PropertyValueFactory<>("carID"));

		TableColumn<Car, String> brandColumn = new TableColumn<>("Brand");
		brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));

		TableColumn<Car, String> modelColumn = new TableColumn<>("Model");
		modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

		TableColumn<Car, Integer> yearColumn = new TableColumn<>("Year");
		yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

		TableColumn<Car, String> availableColumn = new TableColumn<>("Availability");
		availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));

		TableColumn<Car, Double> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

		// Add columns to the table view
		tableView.getColumns().addAll(carIDColumn, brandColumn, modelColumn, yearColumn, availableColumn, priceColumn);

		// Set the data in the table view
		tableView.setItems(availableCars);

		return tableView;
	}

	private void showNotification(String title, String message) {
		if (SystemTray.isSupported()) {
			SystemTray tray = SystemTray.getSystemTray();
			java.awt.Image image = Toolkit.getDefaultToolkit().getImage("path/to/notification-icon.png");

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
