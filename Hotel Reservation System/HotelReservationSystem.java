import java.util.Scanner;

public class HotelReservationSystem {
    private boolean[] singleRooms;  // Array to keep track of single rooms
    private boolean[] deluxeRooms;  // Array to keep track of deluxe rooms
    private int totalSingleRooms = 20;  // Number of single rooms
    private int totalDeluxeRooms = 10;  // Number of deluxe rooms
    private final int singleRoomRate = 6000;  // Rate per single room per day
    private final int deluxeRoomRate = 10000;  // Rate per deluxe room per day

    public HotelReservationSystem() {
        this.singleRooms = new boolean[totalSingleRooms]; // Initially all single rooms are available (false)
        this.deluxeRooms = new boolean[totalDeluxeRooms]; // Initially all deluxe rooms are available (false)
    }

    // Book rooms
    public void bookRoom(String roomType, int numberOfRooms) {
        boolean[] rooms;
        int totalRooms;
        int roomRate;

        if (roomType.equalsIgnoreCase("single")) {
            rooms = singleRooms;
            totalRooms = totalSingleRooms;
            roomRate = singleRoomRate;
        } else if (roomType.equalsIgnoreCase("deluxe")) {
            rooms = deluxeRooms;
            totalRooms = totalDeluxeRooms;
            roomRate = deluxeRoomRate;
        } else {
            System.out.println("Invalid room type!");
            return;
        }

        int availableRooms = 0;
        for (boolean room : rooms) {
            if (!room) {
                availableRooms++;
            }
        }

        if (numberOfRooms > availableRooms) {
            System.out.println("Not enough rooms available!");
            return;
        }

        int booked = 0;
        for (int i = 0; i < totalRooms && booked < numberOfRooms; i++) {
            if (!rooms[i]) {
                rooms[i] = true;  // Mark room as booked
                booked++;
            }
        }

        System.out.println(numberOfRooms + " " + roomType + " room(s) have been successfully booked.");
        int totalPayment = calculatePayment(roomType, numberOfRooms);  // Calculate total payment
        System.out.println("Total payment: $" + totalPayment);
    }

    // Cancel a booking
    public void cancelBooking(String roomType, int roomNumber) {
        boolean[] rooms;
        int totalRooms;

        if (roomType.equalsIgnoreCase("single")) {
            rooms = singleRooms;
            totalRooms = totalSingleRooms;
        } else if (roomType.equalsIgnoreCase("deluxe")) {
            rooms = deluxeRooms;
            totalRooms = totalDeluxeRooms;
        } else {
            System.out.println("Invalid room type!");
            return;
        }

        if (roomNumber < 1 || roomNumber > totalRooms) {
            System.out.println("Invalid room number!");
            return;
        }

        if (rooms[roomNumber - 1]) {
            rooms[roomNumber - 1] = false;  // Mark room as available
            System.out.println("Booking for room " + roomNumber + " has been successfully canceled.");
        } else {
            System.out.println("Room " + roomNumber + " is not currently booked.");
        }
    }

    // Check if a room is available
    public void checkAvailability(String roomType, int roomNumber) {
        boolean[] rooms;
        int totalRooms;

        if (roomType.equalsIgnoreCase("single")) {
            rooms = singleRooms;
            totalRooms = totalSingleRooms;
        } else if (roomType.equalsIgnoreCase("deluxe")) {
            rooms = deluxeRooms;
            totalRooms = totalDeluxeRooms;
        } else {
            System.out.println("Invalid room type!");
            return;
        }

        if (roomNumber < 1 || roomNumber > totalRooms) {
            System.out.println("Invalid room number!");
            return;
        }

        if (!rooms[roomNumber - 1]) {
            System.out.println("Room " + roomNumber + " is available.");
        } else {
            System.out.println("Room " + roomNumber + " is booked.");
        }
    }

    // List all available rooms
    public void listAvailableRooms(String roomType) {
        boolean[] rooms;
        int totalRooms;
        int roomRate;

        if (roomType.equalsIgnoreCase("single")) {
            rooms = singleRooms;
            totalRooms = totalSingleRooms;
            roomRate = singleRoomRate;
        } else if (roomType.equalsIgnoreCase("deluxe")) {
            rooms = deluxeRooms;
            totalRooms = totalDeluxeRooms;
            roomRate = deluxeRoomRate;
        } else {
            System.out.println("Invalid room type!");
            return;
        }

        System.out.println("Available " + roomType + " rooms:");
        boolean anyAvailable = false;
        for (int i = 0; i < totalRooms; i++) {
            if (!rooms[i]) {
                System.out.println("Room " + (i + 1));
                anyAvailable = true;
            }
        }
        if (!anyAvailable) {
            System.out.println("No " + roomType + " rooms are available.");
        }
    }

    // Calculate total payment for a given number of rooms
    private int calculatePayment(String roomType, int numRooms) {
        if (roomType.equalsIgnoreCase("single")) {
            return numRooms * singleRoomRate;
        } else if (roomType.equalsIgnoreCase("deluxe")) {
            return numRooms * deluxeRoomRate;
        } else {
            return 0;
        }
    }

    // Collect customer details
    private void collectCustomerDetails() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String name = sc.nextLine();
        System.out.print("Enter customer contact number: ");
        String contact = sc.nextLine();

        System.out.println("Customer details collected:");
        System.out.println("Name: " + name);
        System.out.println("Contact: " + contact);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HotelReservationSystem system = new HotelReservationSystem();

        // Collect customer details
        system.collectCustomerDetails();

        while (true) {
            System.out.println("\nHotel Reservation System:");
            System.out.println("1. Book rooms");
            System.out.println("2. Cancel a booking");
            System.out.println("3. Check room availability");
            System.out.println("4. List available rooms");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter room type (single/deluxe): ");
                    String roomType = sc.nextLine();
                    System.out.print("Enter number of rooms to book: ");
                    int numberOfRooms = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    system.bookRoom(roomType, numberOfRooms);
                    break;
                case 2:
                    System.out.print("Enter room type (single/deluxe): ");
                    roomType = sc.nextLine();
                    System.out.print("Enter room number to cancel booking: ");
                    int roomToCancel = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    system.cancelBooking(roomType, roomToCancel);
                    break;
                case 3:
                    System.out.print("Enter room type (single/deluxe): ");
                    roomType = sc.nextLine();
                    System.out.print("Enter room number to check availability: ");
                    int roomToCheck = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    system.checkAvailability(roomType, roomToCheck);
                    break;
                case 4:
                    System.out.print("Enter room type (single/deluxe): ");
                    roomType = sc.nextLine();
                    system.listAvailableRooms(roomType);
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
