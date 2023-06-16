import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GymMembershipSystem {
    // List to store the gym members
    private static List<Member> members = new ArrayList<>();
    
    // List to store the gym classes
    private static List<GymClass> classes = new ArrayList<>();
    
    // List to store the bookings
    private static List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        // Define the input and output file names
        String inputFileName = "input.txt";
        String outputFileName = "output.txt";

        // Process the input file
        processInputFile(inputFileName);

        // Generate the output file
        generateOutputFile(outputFileName);
    }

    // Method to process the input file
    public static void processInputFile(String inputFileName) {
        try {
            // Create a File object for the input file
            File inputFile = new File(inputFileName);

            // Create a Scanner object to read from the input file
            Scanner scanner = new Scanner(inputFile);

            // Create a FileWriter object for the output file
            FileWriter writer = new FileWriter("output.txt");

            // Process each line of the input file
            while (scanner.hasNextLine()) {
                // Read a line from the input file
                String line = scanner.nextLine();

                // Split the line into parts using comma and space as the delimiter
                String[] parts = line.split(", ");

                // Check the type of operation based on the first part of the line
                if (parts[0].equals("register")) {
                    // Register a new member
                    int memberId = Integer.parseInt(parts[2]);
                    Member member = new Member(parts[1], memberId);
                    members.add(member);
                } else if (parts[0].equals("schedule")) {
                    // Schedule a new gym class
                    String className = parts[1];
                    // Creating the time and capacity.
                    LocalDateTime startTime = LocalDateTime.parse(parts[2]);
                    LocalDateTime endTime = LocalDateTime.parse(parts[3]);
                    int maxCapacity = Integer.parseInt(parts[4]);
                    GymClass newClass = new GymClass(className, startTime, endTime, maxCapacity);
                    classes.add(newClass);
                } else if (parts[0].equals("book")) {
                    // Book a class for a member
                    int memberId = Integer.parseInt(parts[1]);
                    String className = parts[2];
                    // Checking the time, member, and class.
                    LocalDateTime bookingTime = LocalDateTime.parse(parts[3]);
                    Member member = findMemberById(memberId);
                    GymClass gymClass = findClassByName(className);
                    if (member != null && gymClass != null) {
                        if (gymClass.canBook()) {
                            // Increase the booked count for the gym class
                            gymClass.bookClass();
                            Booking newBooking = new Booking(member, gymClass, bookingTime);
                            // Add the new booking to the bookings list
                            bookings.add(newBooking);
                        } else {
                            // Write a message indicating that the gym class is already full
                            writer.write("Class " + className + " is already full.\n");
                        }
                    }
                }
            }

            // Close the FileWriter object
            writer.close();

            // Close the Scanner object
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to find a member by ID
    public static Member findMemberById(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    // Method to find a class by name
    public static GymClass findClassByName(String className) {
        for (GymClass gymClass : classes) {
            if (gymClass.getClassName().equals(className)) {
                return gymClass;
            }
        }
        return null;
    }

    // Method to generate the output file
    public static void generateOutputFile(String outputFileName) {
        try {
            // Create a FileWriter object for the output file
            FileWriter writer = new FileWriter(outputFileName);

            // Write the header for the output file
            writer.write("Output:\n------\n");
            writer.write("Format: <Member Name> booked <Class Name> at <Booking Time>\n\n");

            // Write the bookings information to the output file
            for (Booking booking : bookings) {
                // Get the member, class, and booking time.
                Member member = booking.getMember();
                GymClass gymClass = booking.getGymClass();
                LocalDateTime bookingTime = booking.getBookingTime();

                writer.write(member.getName() + " booked " + gymClass.getClassName() + " at " +
                        bookingTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\n");
            }

            // Check if any classes are already full and write a message for each one
            for (GymClass gymClass : classes) {
                if (!gymClass.canBook()) {
                    // Display to output file.
                    writer.write("Class " + gymClass.getClassName() + " is already full.\n");
                }
            }

            // Close the FileWriter object
            writer.close();

        } catch (IOException e) {
            // Error
            e.printStackTrace();
        }
    }
}
