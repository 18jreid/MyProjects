public class JulianDate {
    // Julian calender started on 1/1/1
    private int year = 1;
    private int months = 1;
    private int day = 1;

    // Default constructor
    public JulianDate() {
        // Finds days since 1970
        int days = (int) ((System.currentTimeMillis() + java.util.TimeZone.getDefault().getRawOffset()) / (1000 * 60 * 60 * 24));
        // Days from 1/1/1 to 1/1/1970
        days += 717145;

        // adds days together and finds how many years have passed, the current month, and current  day
        addDays(days);
    }

    // Parameter constructor
    public JulianDate(int year, int month, int day) {
        this.year = year;
        this.months = month;
        this.day = day;
    }

    // Finds leap  year of current year
    boolean isLeapYear() {
        return isLeapYear(year);
    }

    //  If year is divisible by 4 it's a leap year
    private boolean isLeapYear(int year) {
        return year % 4 == 0;
    }

    // returns current year
    int getYear() {
        return year;
    }

    // returns current month
    int getMonth() {
        return months;
    }

    // returns current day in month
    int getDayOfMonth() {
        return day;
    }

    //  returns number of days in month
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

    // gets month name
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

    // gets current month name  (default parameter)
    String getMonthName() {
        return  getMonthName(months);
    }

    //  finds number of days in year
    private int getNumberOfDaysInYear(int year) {
        if (isLeapYear(year)) {
            return 366;
        }
        else  {
            return 365;
        }
    }

    // prints date mm/dd/yyyy
    void printShortDate() {
        System.out.print(this.months + "/" + this.day + "/" + this.year);
    }

    // prints date Month dd, yyyy
    void printLongDate() {
        System.out.print(getMonthName(this.months) + " " + this.day +  ", " + this.year);
    }

    //  adds days together and finds months and days  and year
    public void addDays(int days) {
        for (int i = 0; i < days; i++) {
            day += 1;

            if (day > getNumberOfDaysInMonth(year, months)){
                months += 1;
                day = 1;

                if(months > 12){
                    months = 1;
                    year += 1;
                }
            }
        }
    }

    // subtracts days
    public void subtractDays(int days) {
        for (int i = 0; i < days; i++) {
            day -= 1;

            if (day < 1){
                months -= 1;

                if (months < 1){
                    months = 12;
                    year -=1 ;
                }
                day = getNumberOfDaysInMonth(year, months);
            }
        }

    }
}
