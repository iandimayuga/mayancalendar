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
    private int m_coefficient;
    private Day m_day;
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
        return String.format("%d.%s", getNumeral(), m_day.toString());
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
     * @return The numeral
     */
    public int getNumeral()
    {
        // Numeral is 1-based
        return m_coefficient + 1;
    }

    /**
     * Gets the named day in the Tzolkin representation.
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
     * Lookup table for day name representations
     */
    private static final Map<String, Day> s_nameTable = generateNameTable();

    /**
     * Regular expression for this date representation
     */
    private static final Pattern s_pattern = generatePattern();

    // Regex capture group names
    private static final String s_numeralGroup = "tzolkinDigit";

    private static final String s_dayGroup = "tzolkinDay";

    /**
     * Generate the lookup table given an array of day names.
     *
     * @param dayNames Array of day names.
     * @return The lookup table.
     */
    private static Map<String, Day> generateNameTable()
    {
        Day[] days = Day.values();
        // Initialize the lookup table
        Map<String, Day> nameTable = new HashMap<>();
        for (Day day : days)
        {
            // Keep everything lower case for case insensitivity
            nameTable.put(day.name().toLowerCase(), day);
        }

        return nameTable;
    }

    /**
     * Generate the regex pattern to match Tzolkin dates.
     *
     * @param dayNames The named days in the Tzolkin system.
     * @return A pattern that will match Tzolkin dates (case and whitespace insensitive).
     */
    private static Pattern generatePattern()
    {
        Day[] days = Day.values();

        // Build the regex string dynamically
        StringBuilder patternBuilder = new StringBuilder();

        // Add the digit, dot, and begin capturing group for day name
        patternBuilder.append(String.format("\\s*(?<%s>0*([1-9]|1[0-3]))\\s*\\.\\s*(?<%s>", s_numeralGroup, s_dayGroup));

        // First name not preceded by a pipe "|"
        patternBuilder.append(days[0].toString());

        // Loop through remaining names
        for (int i = 1; i < days.length; ++i)
        {
            patternBuilder.append(String.format("|%s", days[i].toString()));
        }

        patternBuilder.append(")\\s*");

        // Compile the pattern from the generated regex, with case insensitivity
        return Pattern.compile(patternBuilder.toString(), Pattern.CASE_INSENSITIVE);
    }

    /**
     * Return a regular expression describing the string representation of a Tzolkin date. The string representation is
     * case and whitespace insensitive.
     *
     * @return A regular expression pattern that will match the allowed representations of this date type.
     */
    public static Pattern pattern()
    {
        return s_pattern;
    }

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
        int numeral = Integer.parseInt(m.group(s_numeralGroup));
        String dayName = m.group(s_dayGroup);

        // Look up the day name with lower case for case insensitivity
        Day day = s_nameTable.get(dayName.toLowerCase());

        return new TzolkinDate(numeral, day);
    }
}
