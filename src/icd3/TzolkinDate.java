/**
 *
 */
package icd3;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the Tzolkin method of Mayan calendaring.
 */
public class TzolkinDate implements MayanDate<TzolkinDate>
{
    /**
     * The length of the cycle followed by the coefficients in a Tzolkin date.
     */
    private static final int s_numCoefficients = 13;

    /**
     * The day names in a Tzolkin date. The length of this array is the length of this second cycle.
     */
    private static final String[] s_dayNames = { "imix", "ik", "akbal", "kan", "chikchan", "kimi", "manik", "lamat",
            "muluk", "ok", "chuen", "eb", "ben", "ix", "men", "kib", "kaban", "etznab", "kawak", "ajaw" };

    /**
     * Lookup table for day name representations
     */
    private static final Map<String, Integer> s_nameTable;

    /**
     * Initialize the lookup table for Date instantiation
     */
    static {
        s_nameTable = new HashMap<>();
        for (int i = 0; i < s_dayNames.length; ++i)
        {
            s_nameTable.put(s_dayNames[i], i);
        }
    }

    /**
     * Integer representation of this date
     */
    private int m_Value;

    /**
     * Instantiates a TzolkinDate object from its textual representation.
     *
     * @param text The textual representation.
     */
    public TzolkinDate(String text)
    {

    }

    /**
     * Instantiates a TzolkinDate object from its integer representation.
     *
     * @param value The integer representation.
     */
    public TzolkinDate(int value)
    {

    }

    /*
     * (non-Javadoc)
     *
     * @see icd3.MayanDate#plus(int)
     */
    @Override
    public TzolkinDate plus(int days)
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
    public TzolkinDate minus(TzolkinDate other)
    {
        // TODO Auto-generated method stub
        return null;
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o)
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see icd3.MayanDate#regex()
     */
    @Override
    public String regex()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
