public class Faculty extends Employee {
    private int hours = 40;
    private int rank = 5;

    // Constructor for Faculty which has super inside to create the rest of the attributes
    Faculty(String name, String address, long phoneNum, String emailAdd, String office, int salary, int hours, int rank, MyDate dateHired) {
        super(name, address, phoneNum, emailAdd, office, salary, dateHired);

        this.hours = hours;
        this.rank = rank;
    }

    public int getHours() {
        return this.hours;
    }

    public int getRank() {
        return this.rank;
    }

    // Prints all info about Faculty Class
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
        System.out.println("Hours: " + getHours());
        System.out.println("Office Rank: " + getRank());
        System.out.println("\n");
    }
}
