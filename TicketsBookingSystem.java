package Work1;

import java.io.*;
import java.util.Scanner;

public class TicketsBookingSystem {

    public static void main(String[] args) {
        // Event data arrays to store event class names, default prices, and bookings
        String[] eventClasses = new String[4];
        double[] defaultPrices = new double[4];
        int[] bookings = new int[4];
        double[] customPrices = new double[4];
        
        // Scanner for user input
        Scanner input = new Scanner(System.in);

        // scan the tickets.txt file
        try (BufferedReader reader = new BufferedReader(new FileReader("tickets.txt.txt"))) {
            // Reading event data from the file into the arrays
            for (int i = 0; i < 4; i++) {
                eventClasses[i] = reader.readLine();  // Event class
                defaultPrices[i] = Double.parseDouble(reader.readLine());  // Ticket price
                bookings[i] = Integer.parseInt(reader.readLine());  // Number of bookings
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return;
        }

        double totalRevenue = 0.0;

        // Take input from the user
        while (true) {
            System.out.print("Specify Custom Ticket Prices [Y|N]: ");
            String customPricesOption = input.nextLine().trim().toUpperCase();

            if (customPricesOption.equals("Y")) {
                // Ask the user to input custom prices
                System.out.println("Enter Custom Ticket Prices:");

                for (int i = 0; i < eventClasses.length; i++) {
                    System.out.print("Enter Ticket Price for " + eventClasses[i] + ": ");
                    customPrices[i] = input.nextDouble();
                }

               
                input.nextLine();

                System.out.println("\n- - Event Ticket Booking System - -");

                //for loop
                // Calculate and display revenue with custom prices
                for (int i = 0; i < eventClasses.length; i++) {
                    double revenue = customPrices[i] * bookings[i];
                    totalRevenue += revenue;
                    System.out.printf("Event Class: %-20s Ticket Price: £%-10.2f Bookings: %-5d Revenue: £%-10.2f%n", 
                            eventClasses[i], customPrices[i], bookings[i], revenue);
                }

                // Display_the_total_revenue
                System.out.println("-----------------------------------------------");
                System.out.printf("Total Revenue: £%.2f%n", totalRevenue);
                break;  // Exit the loop after handling custom prices
            } else if (customPricesOption.equals("N")) {
                // Inform the user that custom prices were cancelLed
                System.out.println("You have canceled specifying custom ticket prices.");
                totalRevenue = 0.0;  // Reset total revenue for retry
                continue;  // Restart the loop to ask the question agai
            } else {
                System.out.println("Invalid option. Please enter Y or N.");
            }
        }

        // If the user eventually chooses N (no custom prices), assume default prices
        if (totalRevenue == 0.0) {
            System.out.println("\n- - Event Ticket Booking System - -");
            System.out.println("Assuming Default Ticket Prices");

            // Calculate and display revenue with default prices
            for (int i = 0; i < eventClasses.length; i++) {
                double revenue = defaultPrices[i] * bookings[i];
                totalRevenue += revenue;
                System.out.printf("Event Class: %-20s Ticket Price: £%-10.2f Bookings: %-5d Revenue: £%-10.2f%n", 
                        eventClasses[i], defaultPrices[i], bookings[i], revenue);
            }

            // Display the total revenue for default prices
            System.out.println("-----------------------------------------------");
            System.out.printf("Total Revenue: £%.2f%n", totalRevenue);
        }

        input.close(); 
    }
}
