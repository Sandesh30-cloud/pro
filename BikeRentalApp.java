import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Bike {
    private int bikeId;
    private String model;
    private double hourlyRate;
    private double dailyRate;
    private boolean isRented;

    public Bike(int bikeId, String model, double hourlyRate, double dailyRate) {
        this.bikeId = bikeId;
        this.model = model;
        this.hourlyRate = hourlyRate;
        this.dailyRate = dailyRate;
        this.isRented = false;
    }

    public int getBikeId() {
        return bikeId;
    }

    public String getModel() {
        return model;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }
}

class BikeRentalSystem {
    private List<Bike> bikes = new ArrayList<>();

    public void addBike(Bike bike) {
        bikes.add(bike);
    }

    public List<Bike> displayAvailableBikes() {
        System.out.println("Available Bikes:");
        for (Bike bike : bikes) {
            if (!bike.isRented()) {
                System.out.println("Bike ID: " + bike.getBikeId() + " - " + bike.getModel());
            }
        }
        return bikes;
    }

    public double rentBike(int bikeId, int hours) {
        for (Bike bike : bikes) {
            if (bike.getBikeId() == bikeId && !bike.isRented()) {
                bike.setRented(true);
                return bike.getHourlyRate() * hours;
            }
        }
        return -1; // Bike not found or already rented
    }

    public boolean returnBike(int bikeId) {
        for (Bike bike : bikes) {
            if (bike.getBikeId() == bikeId && bike.isRented()) {
                bike.setRented(false);
                return true;
            }
        }
        return false; // Bike not found or not rented
    }
}

public class BikeRentalApp {
    public static void main(String[] args) {
        BikeRentalSystem rentalSystem = new BikeRentalSystem();

        Bike bike1 = new Bike(1, "Mountain Bike", 5.0, 20.0);
        Bike bike2 = new Bike(2, "City Bike", 3.0, 15.0);

        rentalSystem.addBike(bike1);
        rentalSystem.addBike(bike2);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nBike Rental System Menu:");
            System.out.println("1. Display Available Bikes");
            System.out.println("2. Rent a Bike");
            System.out.println("3. Return a Bike");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    rentalSystem.displayAvailableBikes();
                    break;
                case 2:
                    System.out.print("Enter the Bike ID to rent: ");
                    int rentBikeId = scanner.nextInt();
                    System.out.print("Enter the number of hours to rent: ");
                    int rentHours = scanner.nextInt();
                    double rentAmount = rentalSystem.rentBike(rentBikeId, rentHours);
                    if (rentAmount == -1) {
                        System.out.println("Bike not available for rent.");
                    } else {
                        System.out.println("Bike rented successfully. Rental amount: $" + rentAmount);
                    }
                    break;
                case 3:
                    System.out.print("Enter the Bike ID to return: ");
                    int returnBikeId = scanner.nextInt();
                    boolean returned = rentalSystem.returnBike(returnBikeId);
                    if (returned) {
                        System.out.println("Bike returned successfully.");
                    } else {
                        System.out.println("Bike not found or not rented.");
                    }
                    break;
                case 4:
                    scanner.close();
                    System.out.println("Exiting Bike Rental System. Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
