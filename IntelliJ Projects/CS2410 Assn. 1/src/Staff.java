public class Staff extends Employee {
    private String title = "Awesome";

    // Constructor for Staff which has super inside to create the rest of the attributes
    Staff(String name, String address, long phoneNum, String emailAdd, String office, int salary, String title, MyDate dateHired) {
        super(name, address, phoneNum, emailAdd, office, salary, dateHired);

        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    // Prints all info about Staff class
    @Override
    public void printInfo() {
        System.out.println("Name: " + getName());
        System.out.println("Class Type: " + this.getClass());
        System.out.println("Address: " + getAddress());
        System.out.println("Phone #: " + getPhoneNumber());
        System.out.println("Email Address: " + getEmailAddress());

        MyDate date = getDateHired();
        System.out.print("Date Hired: ");
        date.printLongDate();
        System.out.println();

        System.out.println("Office Location: " + getOffice());
        System.out.println("Salary: " + getSalary());
        System.out.println("Job Title: " + getTitle());
        System.out.println("\n");
    }
}
