/**
 *
 */
package icd3;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An immutable data structure that represents the Tzolkin method of Mayan calendaring.
 */
public class TzolkinDate implements MayanDate<TzolkinDate>
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
     * The integer representation of this date
     */
    private int m_value;

    /**
     * Instantiates a TzolkinDate object from its calendar representation.
     *
     * @param numeral 1-based day coefficient
     * @param day Day name
     */
    public TzolkinDate(int numeral, TzolkinDate.Day day)
    {
        // Convert to zero-based and mod by total number of coefficients
        m_coefficient = (numeral - 1) % s_numCoefficients;
        m_day = day;

        // Get the position in the cycle
        int positionInCycle = (m_coefficient - m_day.ordinal()) * 2 % s_numCoefficients;

        // And then add the total number of cycles
        this.initialize(positionInCycle * Day.values().length + m_day.ordinal());
    }

    /**
     * Instantiates a TzolkinDate object from its integer representation.
     *
     * @param value The integer representation.
     */
    public TzolkinDate(int value)
    {
        this.initialize(value);
        Day[] days = Day.values();

        m_coefficient = m_value % s_numCoefficients;
        m_day = days[m_value % days.length];
    }

    private void initialize(int value)
    {
        int cycle = TzolkinDate.cycle();

        // Ensure that value is within the positive equivalence class (mod cycle)
        m_value = (value % cycle + cycle) % cycle;
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
     * @see icd3.MayanDate#minus(java.lang.Object)
     */
    @Override
    public int minus(TzolkinDate other)
    {
        if (null == other)
        {
            throw new NullPointerException("Cannot subtract a null Tzolkin Date");
        }

        // Subtract the integer representations
        int difference = this.toInt() - other.toInt();
        int cycle = TzolkinDate.cycle();

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
        return String.format("%d.%s", getNumeral(), getDay());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o)
    {
        // Must be non-null, also a TzolkinDate, and have the same integer representation
        return o != null && o instanceof TzolkinDate && ((TzolkinDate) o).toInt() == this.toInt();
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

    /**
     * Give the number of date representations possible in the Tzolkin system.
     *
     * @return The number of equivalence classes represented by Tzolkin dates.
     */
    public static int cycle()
    {
        // The cycle is the product of the two lengths, since they are mutually prime
        return s_numCoefficients * Day.values().length;
    }
}
