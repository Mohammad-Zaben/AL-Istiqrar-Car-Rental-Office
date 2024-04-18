public class Rental {
	private int rentalID;
	private int customerID;
	private int carID;
	private String rentalDate;
	private String returnDate;
	private double price;
	private int empID;

	public Rental(int rentalID, int customerID, int carID, String rentalDate, String returnDate, double price,
			int empID) {
		this.rentalID = rentalID;
		this.customerID = customerID;
		this.carID = carID;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.price = price;
		this.empID = empID;
	}

	public int getRentalID() {
		return rentalID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public int getCarID() {
		return carID;
	}

	public String getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	@Override
	public String toString() {
		return "rentalID=" + rentalID + ", customerID=" + customerID + ", carID=" + carID + ", rentalDate='"
				+ rentalDate + '\'' + ", returnDate='" + returnDate + '\'' + ", price=" + price + ", empID=" + empID;
	}
}