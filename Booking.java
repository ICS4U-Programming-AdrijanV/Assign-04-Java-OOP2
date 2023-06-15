// Importing
import java.time.LocalDateTime;

public class Booking {
    // Class variables
    private Member member;
    private GymClass gymClass;
    private LocalDateTime bookingTime;

    // Constructor
    public Booking(Member member, GymClass gymClass, LocalDateTime bookingTime) {
        // Initialize class variables
        this.member = member;
        this.gymClass = gymClass;
        this.bookingTime = bookingTime;
    }
    // Getter methods to retrieve the values of member, gymClass, and bookingTime
    public Member getMember() {
        return member;
    }

    public GymClass getGymClass() {
        return gymClass;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
}