/**
 *
 */
package icd3;


/**
 * An immutable data structure that represents the Tzolkin-Haab method of Mayan calendaring.
 */
public class CalendarRoundDate extends CyclicDate<CalendarRoundDate>
{
    private TzolkinDate m_tzolkin;
    private HaabDate m_haab;

    /**
     * Instantiate a CalendarRoundDate from its components.
     *
     * @param tzolkin The Tzolkin component.
     * @param haab The Haab component.
     */
    public CalendarRoundDate(TzolkinDate tzolkin, HaabDate haab)
    {
        super(calculateValue(tzolkin.toInt(), haab.toInt()));
        m_tzolkin = tzolkin;
        m_haab = haab;
    }

    /**
     * Instantiate a CalendarRoundDate from its integer representation.
     *
     * @param value The integer representation.
     */
    public CalendarRoundDate(int value)
    {
        super(value);
        m_tzolkin = new TzolkinDate(toInt() % TzolkinDate.tzolkinCycle());
        m_haab = new HaabDate(toInt() % HaabDate.haabCycle());
    }

    /*
     * (non-Javadoc)
     * @see icd3.CyclicDate#cycle()
     */
    @Override
    public int cycle()
    {
        return s_cycle;
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
        return m_tzolkin;
    }

    /**
     * Get the Haab component of this.
     *
     * @return A HaabDate object representing the Haab component.
     */
    public HaabDate getHaabDate()
    {
        return m_haab;
    }

    // Least common multiple of Tzolkin and Haab
    private static final int s_cycle = 18980;

    private static int calculateValue(int tzolkin, int haab)
    {
        int haabCycle = HaabDate.haabCycle();

        // Also = tzolkinCycle / gcf(haabCycle, tzolkinCycle)
        int commonModulus = s_cycle / haabCycle;

        int numberOfHaabs = ((tzolkin - haab) % commonModulus + commonModulus) % commonModulus;
        return haabCycle * numberOfHaabs + haab;
    }
}
