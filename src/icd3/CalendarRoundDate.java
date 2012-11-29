/**
 *
 */
package icd3;

import java.util.regex.Pattern;

/**
 * An immutable data structure that represents the Tzolkin-Haab method of Mayan calendaring.
 */
public class CalendarRoundDate implements MayanDate<CalendarRoundDate>
{

    /**
     * Return a regular expression describing the string representation of a Calendar Round date. The string
     * representation is case and whitespace insensitive.
     *
     * @return A regular expression pattern that will match the allowed representations of this date type.
     */
    public static Pattern pattern()
    {
        return null;
    }

    /**
     * Give the number of date representations possible in the Calendar Round system.
     *
     * @return The number of equivalence classes represented by Calendar Round dates.
     */
    public static int cycle()
    {
        return 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see icd3.MayanDate#plus(int)
     */
    @Override
    public CalendarRoundDate plus(int days)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see icd3.MayanDate#minus(java.lang.Object)
     */
    @Override
    public int minus(CalendarRoundDate other)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see icd3.MayanDate#toInt()
     */
    @Override
    public int toInt()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return null;
    }

    /**
     * Get the Tzolkin component of this.
     *
     * @return A TzolkinDate object representing the Tzolkin component.
     */
    public TzolkinDate getTzolkinDate()
    {
        return null;
    }

    /**
     * Get the Haab component of this.
     *
     * @return A HaabDate object representing the Haab component.
     */
    public HaabDate getHaabDate()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o)
    {
        // Must be non-null, also a CalendarRoundDate, and have the same integer representation
        return o != null && o instanceof CalendarRoundDate && ((CalendarRoundDate) o).toInt() == this.toInt();
    }
}
