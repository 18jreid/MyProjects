public class GregorianDate extends Date {

    // Default constructor (date starts at 1/1/1970)
    GregorianDate() {
        day = 1;
        month = 1;
        year = 1970;
        int days = (int) ((System.currentTimeMillis() + java.util.TimeZone.getDefault().getRawOffset()) / (1000 * 60 * 60 * 24));
        addDays(days);

    }
    // Parameter constructor
    GregorianDate(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
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

    @Override
    boolean isLeapYear() {
        return isLeapYear(super.getYear());
    }
}