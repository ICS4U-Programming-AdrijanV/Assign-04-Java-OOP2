import java.time.LocalDateTime;

public class Booking {
    // Private member variables
    private Member member;
    private GymClass gymClass;
    private LocalDateTime bookingTime;

    // Constructor for creating a new Booking object
    public Booking(Member member, GymClass gymClass, LocalDateTime bookingTime) {
        this.member = member;
        this.gymClass = gymClass;
        this.bookingTime = bookingTime;
    }

    // Getter method for retrieving the member associated with the booking
    public Member getMember() {
        return member;
    }

    // Getter method for retrieving the gym class associated with the booking
    public GymClass getGymClass() {
        return gymClass;
    }

    // Getter method for retrieving the booking time
    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
}
