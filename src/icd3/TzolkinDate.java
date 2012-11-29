/**
 *
 */
package icd3;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

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
    private static final Map<String, Integer> s_nameTable = generateNameTable(s_dayNames);

    /**
     * Regular expression for this date representation
     */
    private static final Pattern s_pattern = generatePattern(s_dayNames);

    private static Map<String, Integer> generateNameTable(String[] dayNames)
    {
        // Initialize the lookup table
        Map<String, Integer> nameTable = new HashMap<>();
        for (int i = 0; i < dayNames.length; ++i)
        {
            s_nameTable.put(dayNames[i], i);
        }

        return nameTable;
    }

    private static Pattern generatePattern(String[] dayNames)
    {
        // Build the regex string dynamically
        StringBuilder patternBuilder = new StringBuilder();

        // Add the digit, dot, and begin capturing group for day name
        patternBuilder.append("\\s*(?<digit>\\d)+\\s*\\.\\s*(?<day>");

        // First name not preceded by a pipe "|"
        patternBuilder.append(dayNames[0]);

        // Loop through remaining names
        for (int i = 1; i < dayNames.length; ++i)
        {
            patternBuilder.append(String.format("|%s", dayNames[i]));
        }

        // Compile the pattern from the generated regex, with case insensitivity
        return Pattern.compile(patternBuilder.toString(), Pattern.CASE_INSENSITIVE);
    }

    /**
     * Return a regular expression describing the string representation of a Tzolkin date.
     * @return A regular expression pattern that will match the allowed representations of this date type.
     */
    public static Pattern pattern()
    {
        return s_pattern;
    }

    /**
     * Integer representation of this date
     */
    private int m_value;

    /**
     * Instantiates a TzolkinDate object from its textual representation.
     *
     * @param text The textual representation. This must match exactly the regular expression returned by regex() in
     *            order to be a valid object.
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
    public int minus(TzolkinDate other)
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
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o)
    {
        return false;
    }
}
