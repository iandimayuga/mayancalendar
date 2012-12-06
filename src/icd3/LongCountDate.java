/**
 *
 */
package icd3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An immutable data structure representing the Mayan Long Count absolute date.
 */
public class LongCountDate implements MayanDate<LongCountDate>
{
    /**
     * Integer representation of this date
     */
    private int m_value;

    /**
     * Instantiates a LongCountDate object from its integer representation. Negatives are not permitted and will be
     * interpreted as zero.
     *
     * @param value The integer representation.
     */
    public LongCountDate(int value)
    {
        m_value = value < 0 ? 0 : value;
    }

    /*
     * (non-Javadoc)
     *
     * @see icd3.MayanDate#plus(int)
     */
    @Override
    public LongCountDate plus(int days)
    {
        return new LongCountDate(this.toInt() + days);
    }

    /*
     * (non-Javadoc)
     *
     * @see icd3.MayanDate#minus(java.lang.Object)
     */
    @Override
    public int minus(LongCountDate other)
    {
        if (null == other)
        {
            throw new NullPointerException("Cannot subtract a null Long Count Date");
        }
        return this.toInt() - other.toInt();
    }

    /**
     * Get the baktun that this date is in.
     *
     * @return The number in the baktun place.
     */
    public int getBaktun()
    {
        return m_value / s_placeValues[s_placeValues.length - 1];
    }

    /*
     * (non-Javadoc)
     *
     * @see icd3.MayanDate#toInt()
     */
    @Override
    public int toInt()
    {
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
        StringBuilder builder = new StringBuilder();

        // Running total value
        int value = m_value;

        // Separate out each place value, starting from highest order
        for (int i = s_placeValues.length - 1; i >= 0; --i)
        {
            // Divide by the magnitude of the place value, and floor it (implicit)
            int placeValue = value / s_placeValues[i];

            // Subtract the amount from the running total
            value -= placeValue;

            // Append to the builder
            builder.append(String.format("%d", placeValue));

            // Do not place a dot after the last digit
            if (i > 0)
            {
                builder.append(".");
            }
        }

        return builder.toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o)
    {
        return o != null && o instanceof LongCountDate && ((LongCountDate) o).toInt() == this.toInt();
    }

    // The Calendar Round date corresponding to 0.0.0.0.0
    private static CalendarRoundDate s_zeroDay = new CalendarRoundDate(new TzolkinDate(4, TzolkinDate.Day.AJAW),
            new HaabDate(8, HaabDate.Month.KUMKU));

    /**
     * Return the first Long Count date represented by a Calendar Round date after the specified date.
     *
     * @param calendarRound The Calendar Round expression of the desired date.
     * @param start The minimum Long Count date, inclusive.
     * @return The first occurrence of the date at or following the start.
     */
    public static LongCountDate calendarRoundToLongCount(CalendarRoundDate calendarRound, LongCountDate start)
    {
        // Find when the specified date begins
        int startSinceZero = start.toInt();

        // Find the calendarRoundDate corresponding to that beginning
        CalendarRoundDate calendarRoundStart = s_zeroDay.plus(startSinceZero);

        // Find the difference between the given calendarRound date and the start
        int daysSinceStart = calendarRound.minus(calendarRoundStart);

        // Instantiate the LongCountDate corresponding to that many days after the start
        return new LongCountDate(startSinceZero + daysSinceStart);
    }

    /**
     * Return all Long Count dates represented by a Calendar Round date within a range of dates
     *
     * @param calendarRound The Calendar Round expression of the desired dates.
     * @param start The minimum date, inclusive.
     * @param end The maximum date, exclusive.
     * @return All occurrences of the date within the range.
     */
    public static List<LongCountDate> calendarRoundToLongCountList(CalendarRoundDate calendarRound,
                                                                   LongCountDate start,
                                                                   LongCountDate end)
    {
        List<LongCountDate> dates = new ArrayList<LongCountDate>();

        // Get the length of the Calendar Round
        int cycle = CalendarRoundDate.cycle();

        // Find the first occurrence at or after start
        LongCountDate occurrence = calendarRoundToLongCount(calendarRound, start);

        // Iterate, adding all Long Count Dates between start and end
        while (end.minus(occurrence) > 0)
        {
            // No need to copy, dates are immutable
            dates.add(occurrence);

            // Cycle forward
            occurrence = occurrence.plus(cycle);
        }

        return dates;
    }
}
