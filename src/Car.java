public class Car {
    private int carID;
    private String brand;
    private String model;
    private int year;
    private String available;
    private double price;

    public Car(int carID, String brand, String model, int year, String available, double price) {
        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.available = available;
        this.price = price;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return 
                "carID=" + carID +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", available='" + available + '\'' +
                ", price=" + price;
    }
}