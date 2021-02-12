import org.junit.Assert;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class GregorianDateTest {
    /**
     * @brief This test verifies that the default constructor successfully sets the current day, month, and year
     */
    @org.junit.Test
    public void GregorianDateTestDefaultConstructor() {
        GregorianDate date = new GregorianDate();

        GregorianCalendar today = new GregorianCalendar();
        today.setGregorianChange(new java.util.Date(Long.MIN_VALUE));
        Assert.assertEquals(today.get(Calendar.YEAR), date.getYear());
        Assert.assertEquals(today.get(Calendar.MONTH) + 1, date.getMonth());
        Assert.assertEquals(today.getDisplayName(Calendar.MONTH, Calendar.LONG, new java.util.Locale("en","US")), date.getMonthName());
        Assert.assertEquals(today.get(Calendar.DAY_OF_MONTH), date.getDayOfMonth());
    }

    /**
     * @brief This test checks that the constructor correctly sets the year, month, and day
     */
    @org.junit.Test
    public void GregorianDateTestConstructorWithParameters() {
        int year = 2016;
        int month = 4;
        int day = 25;
        GregorianDate date = new GregorianDate(year, month, day);

        Assert.assertEquals(year, date.getYear());
        Assert.assertEquals(month, date.getMonth());
        Assert.assertEquals("April", date.getMonthName());
        Assert.assertEquals(day, date.getDayOfMonth());
    }

    /**
     * @brief This test adds days from a known date to jump days, months, and years
     */
    @org.junit.Test
    public void GregorianDateTestAddDaysMethod() {
        int year = 2016;
        int month = 4;
        int day = 15;

        // Test an increase in days
        for (int i = 0; i < 10; i++) {
            GregorianDate date = new GregorianDate(year, month, day);
            date.addDays(i);

            Assert.assertEquals(day + i, date.getDayOfMonth());
            Assert.assertEquals(year, date.getYear());
            Assert.assertEquals(month, date.getMonth());
        }

        // Test an increase in months
        for (int i = 0; i < 5; i++) {
            int dayIncrease = i * 28;
            GregorianDate date = new GregorianDate(year, month, day);
            date.addDays(dayIncrease);

            Assert.assertEquals(year, date.getYear());
            Assert.assertEquals(month + i, date.getMonth());
        }

        // Test an increase in years
        for (int i = 0; i < 10; i++) {
            int dayIncrease = i * 365;
            GregorianDate date = new GregorianDate(year, month, day);
            date.addDays(dayIncrease);

            Assert.assertEquals(year + i, date.getYear());
            Assert.assertEquals(month, date.getMonth());
        }
    }

    /**
     * @brief This test subtracts days from a known date to jump days, months, and years
     */
    @org.junit.Test
    public void GregorianDateTestSubtractDaysMethod() {
        int year = 2016;
        int month = 12;
        int day = 15;

        // Test an increase in days
        for (int i = 0; i < 10; i++) {
            GregorianDate date = new GregorianDate(year, month, day);
            date.subtractDays(i);

            Assert.assertEquals(day - i, date.getDayOfMonth());
            Assert.assertEquals(year, date.getYear());
            Assert.assertEquals(month, date.getMonth());
        }

        // Test an increase in months
        for (int i = 0; i < 5; i++) {
            int dayIncrease = i * 28;
            GregorianDate date = new GregorianDate(year, month, day);
            date.subtractDays(dayIncrease);

            Assert.assertEquals(year, date.getYear());
            Assert.assertEquals(month - i, date.getMonth());
        }

        // Test an increase in years
        for (int i = 0; i < 10; i++) {
            int dayIncrease = i * 365;
            GregorianDate date = new GregorianDate(year, month, day);
            date.subtractDays(dayIncrease);

            Assert.assertEquals(year - i, date.getYear());
            Assert.assertEquals(month, date.getMonth());
        }
    }

    /**
     * @brief This test runs through a set of years and checks if they are leap years using an array of known leap years
     */
    @org.junit.Test
    public void GregorianDateTestIsLeapYearMethod() {
        int [] leapYears = {2004, 2008, 2012, 2016, 2020, 2024, 2028, 2032, 2036, 2040, 2044, 2048, 2052, 2056, 2060, 2064, 2068, 2072, 2076, 2080, 2084, 2088, 2092, 2096};
        int leapYearIndex = 0;
        int month = 12;
        int day = 15;

        for (int year = leapYears[0]; year < leapYears[leapYears.length - 1]; year++) {
            GregorianDate date = new GregorianDate(year, month, day);

            if (year == leapYears[leapYearIndex]) {
                Assert.assertTrue(date.isLeapYear());
                leapYearIndex++;
            }
            else {
                Assert.assertFalse(date.isLeapYear());
            }
        }
    }
}