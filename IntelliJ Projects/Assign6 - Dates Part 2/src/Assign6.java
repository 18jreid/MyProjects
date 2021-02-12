/**
 * Assignment 6 for CS 1410
 * This program demonstrates the use of the GregorianDate and JulianDate classes
 *
 * @author James Dean Mathias
 */
public class Assign6 {
    public static void main(String[] args) {

        System.out.printf("--- Demonstrating Julian Dates ---\n\n");
        demoDateFromToday(new JulianDate());

        demoDate(new JulianDate(2100, 2, 27));      // Future year
        demoDate(new JulianDate(2000, 2, 27));      // Leap Year
        demoDate(new JulianDate(2001, 2, 27));      // Not leap year
        demoDate(new JulianDate(1999, 12, 30));     // End of year

        System.out.printf("\n--- Demonstrating Gregorian Dates ---\n\n");
        demoDateFromToday(new GregorianDate());

        demoDate(new GregorianDate(2100, 2, 27));      // Future year
        demoDate(new GregorianDate(2000, 2, 27));      // Leap Year
        demoDate(new GregorianDate(2001, 2, 27));      // Not leap year
        demoDate(new GregorianDate(1999, 12, 30));     // End of year
    }

    /**
     * @brief Helper method use to exercise the capabilities of a concrete Date class constructed from the
     * default constructor (today).
     *
     * @author James Dean Mathias
     */
    public static void demoDateFromToday(Date date) {
        System.out.print("Today's date is  : ");
        date.printLongDate();
        System.out.println();

        date.addDays(1);
        System.out.print("Tomorrow will be : ");
        date.printLongDate();
        System.out.println();

        date.subtractDays(2);
        System.out.print("Yesterday was    : ");
        date.printLongDate();
        System.out.println();
        if (date.isLeapYear()) {
            System.out.println("This year is a leap year!");
        }
        else {
            System.out.println("This year is not a leap year.");
        }
    }

    /**
     * @brief Helper method use to exercise the capabilities of a concrete Date class.
     *
     * @author James Dean Mathias
     */
    public static void demoDate(Date date) {
        System.out.println();
        System.out.print("Demonstrating: ");
        date.printShortDate();
        System.out.println();

        System.out.print("Adding 1 day to the date      : ");
        date.addDays(1);
        date.printShortDate();
        System.out.println();

        System.out.print("Adding another day            : ");
        date.addDays(1);
        date.printShortDate();
        System.out.println();

        System.out.print("Just one more  day            : ");
        date.addDays(1);
        date.printShortDate();
        System.out.println();

        System.out.print("Going backwards by 2 days     : ");
        date.subtractDays(2);
        date.printShortDate();
        System.out.println();
    }

}
