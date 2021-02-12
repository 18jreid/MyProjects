public class JulianDate extends Date {

    // Default constructor (date starts at 1/1/1)
    JulianDate() {
        year = 1;
        day = 1;
        month = 1;
        int days = (int) ((System.currentTimeMillis() + java.util.TimeZone.getDefault().getRawOffset()) / (1000 * 60 * 60 * 24));
        days += 719164;
        addDays(days);
    }

    // Parameter constructor
    JulianDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public boolean isLeapYear(int year) {
        return year % 4 == 0;
    }

    @Override
    boolean isLeapYear() {
        return isLeapYear(super.getYear());
    }
}