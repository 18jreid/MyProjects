// This class was taken from my previous CS1410 class and was modified to work with this assignment. This is the Gregorian
// date class.

public class MyDate {
    private int year = 1970;
    private int month = 1;
    private int day =  1;

    // Default constructor
    public MyDate() {
        int days = (int) ((System.currentTimeMillis() + java.util.TimeZone.getDefault().getRawOffset()) / (1000 * 60 * 60 * 24));
        addDays(days);
        month -= 2;
        day += 11;
    }

    // Constructor with parameters
    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    //  If year is divisible by  4, it's a leap year, but if it is divisible by 4 and 100 and NOT by 400 then it isn't a leap year.
    private boolean isLeapYear(int year) {
        if (year % 4 == 0)  {
            if (year % 100 == 0) {
                return year % 400 == 0;
            }
            return true;
        }
        else {
            return false;
        }
    }

    // returns if this year is a leap year
    boolean isLeapYear() {
        return isLeapYear(year);
    }

    //  returns current year
    int getYear() {
        return year;
    }

    // returns current month
    int getMonth() {
        return month;
    }

    // returns current  day of the month
    int getDayOfMonth() {
        return day;
    }

    // returns number of days in month
    private int getNumberOfDaysInMonth(int year, int month) {
        int numOfDays = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 10:
            case 12:
                numOfDays = 31;
                break;
            case 2:
                if (isLeapYear(year)) {
                    numOfDays = 29;
                }
                else {
                    numOfDays = 28;
                }
                break;
            case 4:
            case 6:
            case 8:
            case 9:
            case 11:
                numOfDays = 30;
                break;
        }

        return numOfDays;
    }

    // returns month name
    String getMonthName(int month) {

        String monthString = "";
        switch (month) {
            case 1:
                monthString = "January";
                break;
            case 2:
                monthString = "February";
                break;
            case 3:
                monthString = "March";
                break;
            case 4:
                monthString = "April";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "June";
                break;
            case 7:
                monthString = "July";
                break;
            case 8:
                monthString = "August";
                break;
            case 9:
                monthString = "September";
                break;
            case 10:
                monthString = "October";
                break;
            case 11:
                monthString = "November";
                break;
            case 12:
                monthString = "December";
                break;
        }

        return monthString;
    }

    // returns current month name
    String getMonthName() {
        return getMonthName(month);
    }

    // returns number of days in year
    private int getNumberOfDaysInYear(int year) {
        if (isLeapYear(year)) {
            return 366;
        }
        else  {
            return 365;
        }
    }

    // prints mm/dd/yyyy
    void printShortDate() {
        System.out.print(month + "/" + day + "/" + year);
    }

    // prints Month dd, yyyy
    void printLongDate() {
        System.out.print(getMonthName(month) + " " + day +  ", " + year);
    }

    // adds days and calculates current month and current day, and figures out current year
    void addDays(int days) {
        for (int i = 0; i < days; i++) {
            day += 1;

            if (day > getNumberOfDaysInMonth(year, month)){
                day = 1;
                month += 1;

                if(month > 12){
                    month = 1;
                    year += 1;
                }
            }
        }
    }

    // subtracts days
    void subtractDays(int days) {
        for (int i = 0; i < days; i++) {
            day -= 1;

            if (day < 1){
                month -= 1;

                if (month < 1){
                    year -=1;
                    month = 12;
                }

                day = getNumberOfDaysInMonth(year, month);
            }
        }

    }
}