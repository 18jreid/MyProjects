public class Date {
    int day;
    int month;
    int year;

    // Adding days
    public void addDays(int days) {
        for (int i = 0; i < days; i++) {
            day++;
            if (day > getNumberOfDaysInMonth(year, month)){ // seeing if the number of days in month is more than the month
                day = 1;
                month++;
                if(month > 12){ // if the months are greater than 12, adding a year.
                    month = 1;
                    year++;
                }
            }
        }
    }
    // Subtracting days
    public void subtractDays(int days) {
        for (int i = 0; i < days; i++) {
            day--;
            if (day < 1){
                month--;
                if (month < 1){
                    year--;
                    month = 12;
                }
                day = getNumberOfDaysInMonth(year, month);
            }
        }

    }

    // returns true if the year is a leap year (works for both Julian or Gregorian)
    public boolean isLeapYear(int year) {
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

    boolean isLeapYear() {
        return isLeapYear(this.year);
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
        switch (this.month) {
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if (isLeapYear()) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 0;
        }
    }

    // returns month name
    public String getMonthName(int month) {

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
}
