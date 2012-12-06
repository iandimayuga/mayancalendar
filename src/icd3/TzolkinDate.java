/**
 *
 */
package icd3;

/**
 * An immutable data structure that represents the Tzolkin method of Mayan calendaring.
 */
public class TzolkinDate extends CyclicDate<TzolkinDate>
{
    /**
     * The coefficient of this date (zero-based)
     */
    private int m_coefficient;

    /**
     * The day part of this date
     */
    private Day m_day;

    /**
     * Instantiates a TzolkinDate object from its calendar representation.
     *
     * @param numeral 1-based day coefficient
     * @param day Day name
     */
    public TzolkinDate(int numeral, TzolkinDate.Day day)
    {
        super(calculateValue(numeral, day.ordinal()));

        m_coefficient = (numeral - 1) % s_numCoefficients;
        m_day = day;
    }

    /**
     * Instantiates a TzolkinDate object from its integer representation.
     *
     * @param value The integer representation.
     */
    public TzolkinDate(int value)
    {
        super(value);
        Day[] days = Day.values();

        m_coefficient = toInt() % s_numCoefficients;
        m_day = days[toInt() % days.length];
    }

    /*
     * (non-Javadoc)
     *
     * @see icd3.CyclicDate#cycle()
     */
    @Override
    public int cycle()
    {
        return tzolkinCycle();
    }

    /*
     * (non-Javadoc)
     *
     * @see icd3.MayanDate#plus(int)
     */
    @Override
    public TzolkinDate plus(int days)
    {
        // Simply add days to the integer representation
        return new TzolkinDate(this.toInt() + days);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("%d.%s", getNumeral(), getDay());
    }

    /**
     * Gets the 1-based numeral in the Tzolkin representation.
     *
     * @return The numeral
     */
    public int getNumeral()
    {
        // Numeral is 1-based
        return m_coefficient + 1;
    }

    /**
     * Gets the named day in the Tzolkin representation.
     *
     * @return The day
     */
    public Day getDay()
    {
        return m_day;
    }

    /**
     * The names and order of the days in the Tzolkin system of Mayan calendaring
     */
    public enum Day
    {
        IMIX,
        IK,
        AKBAL,
        KAN,
        CHIKCHAN,
        KIMI,
        MANIK,
        LAMAT,
        MULUK,
        OK,
        CHUEN,
        EB,
        BEN,
        IX,
        MEN,
        KIB,
        KABAN,
        ETZNAB,
        KAWAK,
        AJAW;
    }

    /**
     * The length of the cycle followed by the coefficients in a Tzolkin date.
     */
    private static final int s_numCoefficients = 13;

    private static int calculateValue(int numeral, int day)
    {
        // Convert to zero-based and mod by total number of coefficients
        int coefficient = (numeral - 1) % s_numCoefficients;

        // Get the position in the cycle
        return (coefficient - day) * Day.values().length * 2 + (day % s_numCoefficients);
    }

    /**
     * Give the number of date representations possible in this date type.
     *
     * @return The number of equivalence classes represented by this cyclic date.
     */
    public static int tzolkinCycle()
    {
        // The cycle is the product of the two lengths, since they are mutually prime
        return s_numCoefficients * Day.values().length;
    }
}
