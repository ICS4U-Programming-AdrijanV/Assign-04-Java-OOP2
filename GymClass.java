import java.time.LocalDateTime;

public class GymClass {
    // Class variables
    private String className;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int maxCapacity;
    private int bookedCount;

    // Constructor
    public GymClass(String className, LocalDateTime startTime, LocalDateTime endTime, int maxCapacity) {
        // Initialize class variables
        this.className = className;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxCapacity = maxCapacity;
        this.bookedCount = 0;
    }

    // Getter methods
    public String getClassName() {
        return className;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getBookedCount() {
        return bookedCount;
    }

    // Method to check if a class can be booked
    public boolean canBook() {
        return bookedCount < maxCapacity;
    }

    // Method to book a class
    public void bookClass() {
        if (canBook()) {
            bookedCount++;
        } else {
            // Print a message if the class is already fully booked
            System.out.println("Class is already fully booked.");
        }
    }
}
