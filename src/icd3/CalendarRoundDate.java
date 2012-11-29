/**
 *
 */
package icd3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An immutable data structure that represents the Tzolkin-Haab method of Mayan calendaring.
 */
public class CalendarRoundDate implements MayanDate<CalendarRoundDate>
{
    /**
     * Integer representation of this date
     */
    private int m_value;

    /**
     * Instantiate a CalendarRoundDate from its components.
     *
     * @param tzolkin The Tzolkin component.
     * @param haab The Haab component.
     */
    public CalendarRoundDate(TzolkinDate tzolkin, HaabDate haab)
    {
        this(tzolkin.toInt() * haab.toInt());
    }

    /**
     * Instantiate a CalendarRoundDate from its integer representation.
     *
     * @param value The integer representation.
     */
    public CalendarRoundDate(int value)
    {
        int cycle = CalendarRoundDate.cycle();

        // Ensure that value is within the positive equivalence class (mod cycle)
        m_value = (value % cycle + cycle) % cycle;
    }

    /*
     * (non-Javadoc)
     *
     * @see icd3.MayanDate#plus(int)
     */
    @Override
    public CalendarRoundDate plus(int days)
    {
        return new CalendarRoundDate(this.toInt() + days);
    }

    /*
     * (non-Javadoc)
     *
     * @see icd3.MayanDate#minus(java.lang.Object)
     */
    @Override
    public int minus(CalendarRoundDate other)
    {
        if (null == other)
        {
            throw new NullPointerException("Cannot subtract a null Calendar Round Date");
        }

        // Subtract the integer representations
        int difference = this.toInt() - other.toInt();
        int cycle = CalendarRoundDate.cycle();

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
        return String.format("%s %s", this.getTzolkinDate(), this.getHaabDate());
    }

    /**
     * Get the Tzolkin component of this.
     *
     * @return A TzolkinDate object representing the Tzolkin component.
     */
    public TzolkinDate getTzolkinDate()
    {
        // The Tzolkin integer is value % tzolkin cycle
        return new TzolkinDate(this.toInt() % TzolkinDate.cycle());
    }

    /**
     * Get the Haab component of this.
     *
     * @return A HaabDate object representing the Haab component.
     */
    public HaabDate getHaabDate()
    {
        // The Haab integer is value % year length
        return new HaabDate(this.toInt() % HaabDate.cycle());
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

    // Least common multiple of Tzolkin and Haab
    private static final int s_cycle = 18980;

    // Regular expression capture groups
    private static final String s_tzolkinGroup = "tzolkin";
    private static final String s_haabGroup = "haab";

    private static final Pattern s_pattern = generatePattern();

    /**
     * Generate the regex pattern to match Calendar Round dates.
     *
     * @param dayNames The named days in the Calendar Round system.
     * @return A pattern that will match Calendar Round dates (case and whitespace insensitive).
     */
    private static Pattern generatePattern()
    {
        String tzolkinPattern = TzolkinDate.pattern().toString();
        String haabPattern = HaabDate.pattern().toString();

        String calendarRoundPattern = String.format("\\s*(?<%s>%s)\\s+(?<%s>%s)\\s*", s_tzolkinGroup, tzolkinPattern,
                s_haabGroup, haabPattern);

        return Pattern.compile(calendarRoundPattern, Pattern.CASE_INSENSITIVE);
    }

    /**
     * Return a regular expression describing the string representation of a Calendar Round date. The string
     * representation is case and whitespace insensitive.
     *
     * @return A regular expression pattern that will match the allowed representations of this date type.
     */
    public static Pattern pattern()
    {
        return s_pattern;
    }

    /**
     * Give the number of date representations possible in the Calendar Round system.
     *
     * @return The number of equivalence classes represented by Calendar Round dates.
     */
    public static int cycle()
    {
        return s_cycle;
    }

    /**
     * Parse a string representation of a Calendar Round Date.
     *
     * @param s A string matching CalendarRoundDate.pattern().
     * @return A CalendarRoundDate object whose toString() will return an equivalent representation, or null if s does
     *         not match.
     */
    public static CalendarRoundDate parse(String s)
    {
        // Attempt to match the input string
        Matcher m = pattern().matcher(s);

        // Return null if s does not match pattern
        if (!m.matches())
        {
            return null;
        }

        // Extract capture groups
        String tzolkinPart = m.group(s_tzolkinGroup);
        String haabPart = m.group(s_haabGroup);

        // Parse each component
        TzolkinDate tzolkin = TzolkinDate.parse(tzolkinPart);
        HaabDate haab = HaabDate.parse(haabPart);

        // Neither should be null, since the capture groups are based on their own patterns
        assert(tzolkin != null);
        assert(haab != null);

        // Instantiate Calendar Round from components
        return new CalendarRoundDate(tzolkin, haab);
    }
}
