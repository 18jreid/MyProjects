public class TestPerson {
    public static void main(String[] args) {
        // All classes are instantiated here
        Person testPerson =  new Person("Holly",  "359 N 1225 E", 1234567, "holly@gmail.com");
        Student testStudent = new Student("Justin", "683 E 400 N", 7654321, "justin@gmail.com", "Sophomore");
        Employee testEmployee =  new Employee("Josh", "no idea", 1253476, "josh@gmail.com", "653 E 200 n", 40000, new MyDate(2008, 5, 27));
        Faculty testFaculty = new Faculty("Richard", "464 E 500 N", 7463542, "richard@gmail.com", "500 W 600 N", 50000, 50, 10, new MyDate(2002, 3, 10));
        Staff testStaff = new Staff("Keith", "249 E 400 N", 1769340, "Keith@gmail.com", "500 W 600 N", 30000, "Team Member", new MyDate(2015, 9, 19));

        // All classes are printed here
        testPerson.printInfo();
        testStudent.printInfo();
        testEmployee.printInfo();
        testFaculty.printInfo();
        testStaff.printInfo();
    }
}
