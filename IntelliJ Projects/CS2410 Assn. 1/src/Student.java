public class Student extends Person {
    private String classStatus;

    // Constructor for Student which has super inside to create the rest of the attributes
    Student(String name, String address, long phoneNum, String emailAdd, String status) {
        super(name, address, phoneNum, emailAdd);
        this.classStatus = status;
    }

    public String getClassStatus() {
        return this.classStatus;
    }

    // Prints all values about student
    @Override
    public void printInfo() {
        System.out.println("Name: " + getName());
        System.out.println("Class Type: " + this.getClass());
        System.out.println("Address: " + getAddress());
        System.out.println("Phone #: " + getPhoneNumber());
        System.out.println("Email Address: " + getEmailAddress());
        System.out.println("Class Status: " + getClassStatus());
        System.out.println("\n");
    }
}
