import java.time.LocalDateTime;

public class GymClass {
    // Private member variables
    private String className;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int maxCapacity;
    private int bookedCount;

    // Constructor for creating a new GymClass object
    public GymClass(String className, LocalDateTime startTime, LocalDateTime endTime, int maxCapacity) {
        this.className = className;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxCapacity = maxCapacity;
        this.bookedCount = 0;
    }

    // Getter method for retrieving the class name
    public String getClassName() {
        return className;
    }

    // Getter method for retrieving the start time
    public LocalDateTime getStartTime() {
        return startTime;
    }

    // Getter method for retrieving the end time
    public LocalDateTime getEndTime() {
        return endTime;
    }

    // Getter method for retrieving the maximum capacity
    public int getMaxCapacity() {
        return maxCapacity;
    }

    // Getter method for retrieving the count of booked members
    public int getBookedCount() {
        return bookedCount;
    }

    // Method to check if the class can be booked
    public boolean canBook() {
        return bookedCount < maxCapacity;
    }

    // Method to book the class
    public void bookClass() {
        // Check if the class can be booked
        if (canBook()) {
            bookedCount++;
        } else {
            // Display if class is full
            System.out.println("Class is already fully booked.");
        }
    }
}
