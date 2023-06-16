public class Member {
    // Private member variables
    private String name;
    private int memberId;

    // Constructor for creating a new Member object
    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
    }

    // Getter method for retrieving the member's name
    public String getName() {
        return name;
    }

    // Getter method for retrieving the member's ID
    public int getMemberId() {
        return memberId;
    }
}
