import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewGenerator {

	public static TableView<Car> createCarTableView(List<Car> carList) {
		TableView<Car> tableView = new TableView<>();
		ObservableList<Car> data = FXCollections.observableArrayList(carList);

		TableColumn<Car, Integer> carIDColumn = new TableColumn<>("Car ID");
		carIDColumn.setCellValueFactory(new PropertyValueFactory<>("carID"));

		TableColumn<Car, String> brandColumn = new TableColumn<>("Brand");
		brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));

		TableColumn<Car, String> modelColumn = new TableColumn<>("Model");
		modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

		TableColumn<Car, Integer> yearColumn = new TableColumn<>("Year");
		yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

		TableColumn<Car, String> availableColumn = new TableColumn<>("Available");
		availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));

		TableColumn<Car, Double> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

		tableView.getColumns().addAll(carIDColumn, brandColumn, modelColumn, yearColumn, availableColumn, priceColumn);
		tableView.setItems(data);

		return tableView;
	}

	public static TableView<Employee> createEmployeeTableView(List<Employee> employeeList) {
		TableView<Employee> tableView = new TableView<>();
		ObservableList<Employee> data = FXCollections.observableArrayList(employeeList);

		TableColumn<Employee, Integer> empIDColumn = new TableColumn<>("Employee ID");
		empIDColumn.setCellValueFactory(new PropertyValueFactory<>("empID"));

		TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("empName"));

		TableColumn<Employee, String> addressColumn = new TableColumn<>("Address");
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("empAddress"));

		TableColumn<Employee, String> birthDateColumn = new TableColumn<>("Birth Date");
		birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("empBirthDate"));

		TableColumn<Employee, Double> salaryColumn = new TableColumn<>("Salary");
		salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

		tableView.getColumns().addAll(empIDColumn, nameColumn, addressColumn, birthDateColumn, salaryColumn);
		tableView.setItems(data);

		return tableView;
	}

	public static TableView<Customer> createCustomerTableView(List<Customer> customerList) {
		TableView<Customer> tableView = new TableView<>();
		ObservableList<Customer> data = FXCollections.observableArrayList(customerList);

		TableColumn<Customer, Integer> customerIDColumn = new TableColumn<>("Customer ID");
		customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));

		TableColumn<Customer, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Customer, String> addressColumn = new TableColumn<>("Address");
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

		TableColumn<Customer, String> phoneColumn = new TableColumn<>("Phone");
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

		tableView.getColumns().addAll(customerIDColumn, nameColumn, addressColumn, phoneColumn);
		tableView.setItems(data);

		return tableView;
	}

	public static TableView<Rental> createRentalTableView(List<Rental> rentalList) {
		TableView<Rental> tableView = new TableView<>();
		ObservableList<Rental> data = FXCollections.observableArrayList(rentalList);

		TableColumn<Rental, Integer> rentalIDColumn = new TableColumn<>("Rental ID");
		rentalIDColumn.setCellValueFactory(new PropertyValueFactory<>("rentalID"));

		TableColumn<Rental, Integer> carIDColumn = new TableColumn<>("Car ID");
		carIDColumn.setCellValueFactory(new PropertyValueFactory<>("carID"));

		TableColumn<Rental, Integer> empIDColumn = new TableColumn<>("Employee ID");
		empIDColumn.setCellValueFactory(new PropertyValueFactory<>("empID"));

		TableColumn<Rental, Integer> customerIDColumn = new TableColumn<>("Customer ID");
		customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));

		TableColumn<Rental, String> rentalDateColumn = new TableColumn<>("Rental Date");
		rentalDateColumn.setCellValueFactory(new PropertyValueFactory<>("rentalDate"));

		TableColumn<Rental, String> returnDateColumn = new TableColumn<>("Return Date");
		returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

		TableColumn<Rental, Double> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

		tableView.getColumns().addAll(rentalIDColumn, carIDColumn, empIDColumn, customerIDColumn, rentalDateColumn,
				returnDateColumn, priceColumn);
		tableView.setItems(data);

		return tableView;
	}

	public static TableView<Car> createAvailableCarsTableView(List<Car> allCars) {
		TableView<Car> tableView = new TableView<>();
		ObservableList<Car> availableCars = FXCollections.observableArrayList();

		// Retrieve all cars from the database

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
}