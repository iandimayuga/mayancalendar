/**
 *
 */
package icd3;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An immutable data structure that represents the Haab method of Mayan calendaring.
 */
public class HaabDate implements MayanDate<HaabDate>
{
    /**
     * Integer representation of this date
     */
    private int m_value;

    private int m_day;

    private Month m_month;

    public HaabDate(int numeral, Month month)
    {
        // Numeral is 1-based, store 0-based
        int day = numeral - 1;

        m_month = month;
        m_day = Math.min(Math.max(0, day), month.days());

        this.initialize(m_month.daysBefore() + m_day);
    }

    /**
     * Instantiates a HaabDate object from its integer representation.
     *
     * @param value The integer representation.
     */
    public HaabDate(int value)
    {
        this.initialize(value);

        m_month = Month.values()[m_value / s_daysPerMonth];
        m_day = value - m_month.daysBefore();
    }

    private void initialize(int value)
    {
        int cycle = HaabDate.cycle();

        // Ensure that value is within the positive equivalence class (mod cycle)
        m_value = (value % cycle + cycle) % cycle;
    }

    /*
     * (non-Javadoc)
     *
     * @see icd3.MayanDate#plus(int)
     */
    @Override
    public HaabDate plus(int days)
    {
        // Simply add days to the integer representation
        return new HaabDate(this.toInt() + days);
    }

    /*
     * (non-Javadoc)
     *
     * @see icd3.MayanDate#minus(java.lang.Object)
     */
    @Override
    public int minus(HaabDate other)
    {
        if (null == other)
        {
            throw new NullPointerException("Cannot subtract a null Haab Date");
        }

        // Subtract the integer representations
        int difference = this.toInt() - other.toInt();
        int cycle = HaabDate.cycle();

        // Ensure that difference is within the positive equivalence class (mod cycle)
        return (difference % cycle + cycle) % cycle;
    }

    /*
     * (non-Javadoc)
     *
     * @see icd3.MayanDate#toInt()
     */
    @Override
    public int toInt()
    {
        // Return the internal integer value
        return m_value;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("%d.%s", getNumeral(), getMonth());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o)
    {
        // Must be non-null, also a HaabDate, and have the same integer representation
        return o != null && o instanceof HaabDate && ((HaabDate) o).toInt() == this.toInt();
    }

    /**
     * Gets the 1-based numeral in the Haab representation.
     *
     * @return The numeral
     */
    public int getNumeral()
    {
        // Return the 1-based numeral
        return m_day + 1;
    }

    /**
     * Gets the named month in the Haab representation.
     *
     * @return The month name.
     */
    public Month getMonth()
    {
        return m_month;
    }

    public enum Month
    {
        POHP,
        WO,
        SIP,
        ZOTZ,
        SEK,
        XUL,
        YAXKIN,
        MOL,
        CHEN,
        YAX,
        SAK,
        KEH,
        MAK,
        KANKIN,
        MUAN,
        PAX,
        KAYAB,
        KUMKU,
        WAYEB(5);

        private int m_days;

        private Month()
        {
            this(s_daysPerMonth);
        }

        private Month(int days)
        {
            m_days = days;
        }

        public int days()
        {
            return m_days;
        }

        /**
         * The number of days in the year prior to this month.
         *
         * @return The number of days prior to this month. Also, the "index" of the beginning of the month.
         */
        public int daysBefore()
        {
            return s_daysPerMonth * this.ordinal();
        }
    }

    /**
     * The length of the months in the first 360 days of the Haab calendar.
     */
    private static final int s_daysPerMonth = 20;

    /**
     * The number of days in a year. Note that 20 * 19 > 365
     */
    private static final int s_daysPerYear = 365;

    /**
     * Give the number of date representations possible in the Haab system.
     *
     * @return The number of equivalence classes represented by Haab dates.
     */
    public static int cycle()
    {
        // The cycle is the year length
        return s_daysPerYear;
    }
}
