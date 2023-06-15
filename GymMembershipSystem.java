import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Member {
    // Class variables
    private String name;
    private int id;

    // Constructor
    public Member(String name, int id) {
        // Assign the given name to the name variable
        this.name = name;
        // Assign the given id to the id variable
        this.id = id;
    }

    // Method to get the name of the member
    public String getName() {
        // Return the name of the member
        return name;
    }

    // Method to get the ID of the member
    public int getId() {
        // Return the ID of the member
        return id;
    }
}

class GymClass {
    // Class variables
    private String className;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int maxCapacity;
    private int bookedCount;

    // Constructor
    public GymClass(String className, LocalDateTime startTime, LocalDateTime endTime, int maxCapacity) {
        // Assign the given className to the className variable
        this.className = className;
        // Assign the given startTime to the startTime variable
        this.startTime = startTime;
        // Assign the given endTime to the endTime variable
        this.endTime = endTime;
        // Assign the given maxCapacity to the maxCapacity variable
        this.maxCapacity = maxCapacity;
        // Initialize bookedCount to 0
        this.bookedCount = 0;
    }

    // Method to get the class name
    public String getClassName() {
        // Return the class name
        return className;
    }

    // Method to get the start time
    public LocalDateTime getStartTime() {
        // Return the start time
        return startTime;
    }

    // Method to get the end time
    public LocalDateTime getEndTime() {
        // Return the end time
        return endTime;
    }

    // Method to get the maximum capacity
    public int getMaxCapacity() {
        // Return the maximum capacity
        return maxCapacity;
    }

    // Method to get the count of booked members
    public int getBookedCount() {
        // Return the count of booked members
        return bookedCount;
    }

    // Method to check if the class can be booked
    public boolean canBook() {
        // Return true if the booked count is less than the maximum capacity, otherwise false
        return bookedCount < maxCapacity;
    }

    // Method to book the class
    public void bookClass() {
        // Increment the booked count by 1
        bookedCount++;
    }
}

class Booking {
    // Class variables
    private Member member;
    private GymClass gymClass;
    private LocalDateTime bookingTime;

    // Constructor
    public Booking(Member member, GymClass gymClass, LocalDateTime bookingTime) {
        // Assign the given member to the member variable
        this.member = member;
        // Assign the given gymClass to the gymClass variable
        this.gymClass = gymClass;
        // Assign the given bookingTime to the bookingTime variable
        this.bookingTime = bookingTime;
    }

    // Method to get the member
    public Member getMember() {
        // Return the member
        return member;
    }

    // Method to get the gym class
    public GymClass getGymClass() {
        // Return the gym class
        return gymClass;
    }

    // Method to get the booking time
    public LocalDateTime getBookingTime() {
        // Return the booking time
        return bookingTime;
    }
}

public class GymMembershipSystem {
    // Class variables
    private static List<Member> members = new ArrayList<>();
    private static List<GymClass> classes = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        // Define input and output file names
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
                    LocalDateTime startTime = LocalDateTime.parse(parts[2]);
                    LocalDateTime endTime = LocalDateTime.parse(parts[3]);
                    int maxCapacity = Integer.parseInt(parts[4]);
                    GymClass newClass = new GymClass(className, startTime, endTime, maxCapacity);
                    classes.add(newClass);
                } else if (parts[0].equals("book")) {
                    // Book a class for a member
                    int memberId = Integer.parseInt(parts[1]);
                    String className = parts[2];
                    LocalDateTime bookingTime = LocalDateTime.parse(parts[3]);
                    Member member = findMemberById(memberId);
                    GymClass gymClass = findClassByName(className);
                    if (member != null && gymClass != null) {
                         // Check if the gym class can be booked
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
            if (member.getId() == memberId) {
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

            // Write the header and format information to the output file
            writer.write("Output:\n------\n");
            writer.write("Format: <Member Name> booked <Class Name> at <Booking Time>\n\n");

            // Write the bookings to the output file
            for (Booking booking : bookings) {
                Member member = booking.getMember();
                GymClass gymClass = booking.getGymClass();
                LocalDateTime bookingTime = booking.getBookingTime();

                writer.write(member.getName() + " booked " + gymClass.getClassName() + " at " +
                        bookingTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\n");
            }

            // Check for fully booked classes and write them to the output file
            for (GymClass gymClass : classes) {
                if (!gymClass.canBook()) {
                    writer.write("Class " + gymClass.getClassName() + " is already full.\n");
                }
            }

            // Close the FileWriter object
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
