/**
 * Assignment 5 for CS 1410
 * This program demonstrates the use of the GregorianDate and JulianDate classes
 *
 * @author James Dean Mathias
 */
public class Assign5 {
    public static void main(String[] args) {

        System.out.printf("--- Demonstrating Julian Dates ---\n\n");
        demoJulianFromToday();

        JulianDate futureYear = new JulianDate(2100, 2, 27);
        demoDateJulian2(futureYear);
        JulianDate leapYear = new JulianDate(2000, 2, 27);
        demoDateJulian2(leapYear);
        JulianDate notLeapYear = new JulianDate(2001, 2, 27);
        demoDateJulian2(notLeapYear);
        JulianDate endOfYear = new JulianDate(1999, 12, 30);
        demoDateJulian2(endOfYear);

        System.out.printf("\n--- Demonstrating Gregorian Dates ---\n\n");
        demoGregorianFromToday();

        GregorianDate futureYearG = new GregorianDate(2100, 2, 27);
        demoDateGregorian2(futureYearG);
        GregorianDate leapYearG = new GregorianDate(2000, 2, 27);
        demoDateGregorian2(leapYearG);
        GregorianDate notLeapYearG = new GregorianDate(2001, 2, 27);
        demoDateGregorian2(notLeapYearG);
        GregorianDate endOfYearG = new GregorianDate(1999, 12, 30);
        demoDateGregorian2(endOfYearG);
    }

    /**
     * @brief Helper method use to exercise the capabilities of a concrete JulianDate class constructed from the
     * default constructor (today).
     *
     * @author James Dean Mathias
     */
    public static void demoJulianFromToday() {
        JulianDate date = new JulianDate();
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
     * @brief Helper method use to exercise the capabilities of a concrete GregorianDate class constructed from the
     * default constructor (today).
     *
     * @author James Dean Mathias
     */
    public static void demoGregorianFromToday() {
        GregorianDate date = new GregorianDate();
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
     * Helper method use to exercise the capabilities of the JulianDate class.
     *
     * @author James Dean Mathias
     */
    public static void demoDateJulian2(JulianDate date) {
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

    /**
     * Helper method use to exercise the capabilities of the GregorianDate class.
     *
     * @author James Dean Mathias
     */
    public static void demoDateGregorian2(GregorianDate date) {
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
