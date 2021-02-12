public class Person {
    // private variables for attributes of the most basic class
    private String name = "Joe";
    private String address = "683 E 400 N";
    private long phoneNumber = 0;
    private String emailAddress = "joe@yoMama.com";

    // Constructor for Person class, which takes name, address, phone number, and email address
    Person(String name, String address, long phoneNum, String emailAdd){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNum;
        this.emailAddress = emailAdd;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    // Prints all info about person class
    public void printInfo() {
        System.out.println("Name: " + getName());
        System.out.println("Class Type: " + this.getClass());
        System.out.println("Address: " + getAddress());
        System.out.println("Phone #: " + getPhoneNumber());
        System.out.println("Email Address: " + getEmailAddress());
        System.out.println("\n");
    }
}
