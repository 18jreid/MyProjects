public class Employee extends Person {
    private String office = "Who knows where";
    private int salary = 0;
    private MyDate dateHired;

    // Constructor for Employee which has super inside to create the rest of the attributes
    Employee(String name, String address, long phoneNum, String emailAdd, String office, int salary, MyDate dateHired) {
        super(name, address, phoneNum, emailAdd);

        this.office = office;
        this.salary = salary;
        this.dateHired = dateHired;
    }

    public String getOffice() {
        return this.office;
    }

    public int getSalary() {
        return this.salary;
    }

    public MyDate getDateHired() {
        return this.dateHired;
    }

    // Prints all info about Employee
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
        System.out.println("\n");
    }
}
