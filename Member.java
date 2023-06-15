public class Member {
    // Class variables
    private String name;
    private int memberId;

    // Constructor
    public Member(String name, int memberId) {
        // Assign the given name to the name variable
        this.name = name;
        // Assign the given memberId to the memberId variable
        this.memberId = memberId;
    }

    // Method to get the name of the member
    public String getName() {
        // Return the name of the member
        return name;
    }

    // Method to get the ID of the member
    public int getMemberId() {
        // Return the ID of the member
        return memberId;
    }
}
