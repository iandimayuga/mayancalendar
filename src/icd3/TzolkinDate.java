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

    // Regex capture group names
    private static final String GROUP_DIGIT = "digit";
    private static final String GROUP_DAY = "day";

    /**
     * Generate the lookup table given an array of day names.
     * @param dayNames Array of day names.
     * @return The lookup table.
     */
    private static Map<String, Integer> generateNameTable(String[] dayNames)
    {
        // Initialize the lookup table
        Map<String, Integer> nameTable = new HashMap<>();
        for (int i = 0; i < dayNames.length; ++i)
        {
            // Keep everything lower case for case insensitivity
            s_nameTable.put(dayNames[i].toLowerCase(), i);
        }

        return nameTable;
    }

    private static Pattern generatePattern(String[] dayNames)
    {
        // Build the regex string dynamically
        StringBuilder patternBuilder = new StringBuilder();

        // Add the digit, dot, and begin capturing group for day name
        patternBuilder.append(String.format("\\s*(?<%s>\\d)+\\s*\\.\\s*(?<%s>", GROUP_DIGIT, GROUP_DAY));

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
     *
     * @return A regular expression pattern that will match the allowed representations of this date type.
     */
    public static Pattern pattern()
    {
        return s_pattern;
    }

    /**
     * Give the number of date representations possible in the Tzolkin system.
     * @return The number of equivalence classes represented by Tzolkin dates.
     */
    public static int cycle()
    {
        // The cycle is the product of the two lengths, since they are mutually prime
        return s_numCoefficients * s_dayNames.length;
    }

    /**
     * Parse a string representation of a TzolkinDate.
     *
     * @param s A string matching TzolkinDate.pattern().
     * @return A TzolkinDate object whose toString() will return an equivalent representation, or null if s does not
     *         match.
     */
    public static TzolkinDate parse(String s)
    {
        // Attempt to match the input string
        Matcher m = pattern().matcher(s);

        // Return null if s does not match pattern
        if (!m.matches())
        {
            return null;
        }

        // Extract capture groups
        int digit = Integer.parseInt(m.group(GROUP_DIGIT));
        String day = m.group(GROUP_DAY);

        // The digit is one-based, but we use zero-based for multiplicative purposes
        digit -= 1;

        // Look up the day name with lower case for case insensitivity
        int dayNumber = s_nameTable.get(day.toLowerCase());

        // The integer representation is given by the multiplication of the two
        return new TzolkinDate(digit * dayNumber);
    }

    /**
     * Integer representation of this date
     */
    private int m_value;

    /**
     * Instantiates a TzolkinDate object from its integer representation.
     *
     * @param value The integer representation.
     */
    public TzolkinDate(int value)
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
