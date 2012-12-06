/**
 *
 */
package icd3;


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
        int haabCycle = HaabDate.cycle();

        int tzolkinValue = tzolkin.toInt();
        int haabValue = haab.toInt();

        // The integer representation is given by finding the position in the cycle
        int numberOfHaabs = (tzolkinValue - haabValue) % 52;

        // And then adding the total number of cycles
        this.initialize(numberOfHaabs * haabCycle + haabValue);
    }

    /**
     * Instantiate a CalendarRoundDate from its integer representation.
     *
     * @param value The integer representation.
     */
    public CalendarRoundDate(int value)
    {
        this.initialize(value);
    }

    private void initialize(int value)
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

    /**
     * Give the number of date representations possible in the Calendar Round system.
     *
     * @return The number of equivalence classes represented by Calendar Round dates.
     */
    public static int cycle()
    {
        return s_cycle;
    }
}
