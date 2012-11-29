/**
 *
 */
package icd3;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An immutable data structure that represents the Haab method of Mayan calendaring.
 */
public class HaabDate implements MayanDate<HaabDate>
{
    /**
     * Integer representation of this date
     */
    private int m_value;

    /**
     * Instantiates a HaabDate object from its integer representation.
     *
     * @param value The integer representation.
     */
    public HaabDate(int value)
    {
        int cycle = HaabDate.cycle();

        // Ensure that value is within the positive equivalence class (mod cycle)
        m_value = (value % cycle + cycle) % cycle;
    }

    /*
     * (non-Javadoc)
     *
     * @see icd3.MayanDate#plus(int)
     */
    @Override
    public HaabDate plus(int days)
    {
        // Simply add days to the integer representation
        return new HaabDate(this.toInt() + days);
    }

    /*
     * (non-Javadoc)
     *
     * @see icd3.MayanDate#minus(java.lang.Object)
     */
    @Override
    public int minus(HaabDate other)
    {
        if (null == other)
        {
            throw new NullPointerException("Cannot subtract a null Haab Date");
        }

        // Subtract the integer representations
        int difference = this.toInt() - other.toInt();
        int cycle = HaabDate.cycle();

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
        // Return the internal integer value
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
        // Month number corresponds to a month name in the static array
        int monthNumber = m_value / s_daysPerMonth;
        String monthName = s_monthNames[monthNumber];

        // Day number is the remainder + 1
        int dayNumber = m_value % s_daysPerMonth + 1;

        return String.format("%d.%s", dayNumber, monthName);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o)
    {
        // Must be non-null, also a HaabDate, and have the same integer representation
        return o != null && o instanceof HaabDate && ((HaabDate) o).toInt() == this.toInt();
    }

    /**
     * The length of the months in the first 360 days of the Haab calendar.
     */
    private static final int s_daysPerMonth = 20;

    /**
     * The month names in a Haab date.
     */
    private static final String[] s_monthNames = { "pohp", "wo", "sip", "zotz", "sek", "xul", "yaxkin", "mol", "chen",
            "yax", "sak", "keh", "mak", "kankin", "muan", "pax", "kayab", "kumku", "wayeb" };

    /**
     * The number of days in a year. Note that 20 * 19 > 365
     */
    private static final int s_daysPerYear = 365;

    /**
     * Lookup table for month name representations
     */
    private static final Map<String, Integer> s_nameTable = generateNameTable(s_monthNames);

    /**
     * Regular expression for this date representation
     */
    private static final Pattern s_pattern = generatePattern(s_monthNames);

    // Regex capture group names
    private static final String s_digitGroup = "haabDigit";

    private static final String s_monthGroup = "haabMonth";

    /**
     * Generate the lookup table given an array of month names.
     *
     * @param monthNames Array of month names.
     * @return The lookup table.
     */
    private static Map<String, Integer> generateNameTable(String[] monthNames)
    {
        // Initialize the lookup table
        Map<String, Integer> nameTable = new HashMap<>();
        for (int i = 0; i < monthNames.length; ++i)
        {
            // Keep everything lower case for case insensitivity
            s_nameTable.put(monthNames[i].toLowerCase(), i);
        }

        return nameTable;
    }

    private static Pattern generatePattern(String[] monthNames)
    {
        // Build the regex string dynamically
        StringBuilder patternBuilder = new StringBuilder();

        // Add the digit, dot, and begin capturing group for month name
        patternBuilder.append(String.format("(\\s*(?<%s>0*([1-9]|1[0-9]|20))\\s*\\.\\s*(?<%s>", s_digitGroup,
                s_monthGroup));

        // First name not preceded by a pipe "|"
        patternBuilder.append(monthNames[0]);

        // Loop through remaining names excluding the last month
        for (int i = 1; i < monthNames.length - 1; ++i)
        {
            patternBuilder.append(String.format("|%s", monthNames[i]));
        }

        // Append the special case for the last month
        patternBuilder.append(String.format(")\\s*)|(\\s*(<%s>0*[1-5])\\s*\\.\\s*(?<%s>%s))", s_digitGroup,
                s_monthGroup, monthNames[monthNames.length - 1]));

        // Compile the pattern from the generated regex, with case insensitivity
        return Pattern.compile(patternBuilder.toString(), Pattern.CASE_INSENSITIVE);
    }

    /**
     * Return a regular expression describing the string representation of a Haab date. The string representation is
     * case and whitespace insensitive.
     *
     * @return A regular expression pattern that will match the allowed representations of this date type.
     */
    public static Pattern pattern()
    {
        return s_pattern;
    }

    /**
     * Give the number of date representations possible in the Haab system.
     *
     * @return The number of equivalence classes represented by Haab dates.
     */
    public static int cycle()
    {
        // The cycle is the year length
        return s_daysPerYear;
    }

    /**
     * Parse a string representation of a HaabDate.
     *
     * @param s A string matching HaabDate.pattern().
     * @return A HaabDate object whose toString() will return an equivalent representation, or null if s does not match.
     */
    public static HaabDate parse(String s)
    {
        // Attempt to match the input string
        Matcher m = pattern().matcher(s);

        // Return null if s does not match pattern
        if (!m.matches())
        {
            return null;
        }

        // Extract capture groups
        int day = Integer.parseInt(m.group(s_digitGroup));
        String month = m.group(s_monthGroup);

        // The digit is one-based, but we use zero-based for multiplicative purposes
        day -= 1;

        // Look up the month name with lower case for case insensitivity
        int monthNumber = s_nameTable.get(month.toLowerCase());

        // The integer representation is given by month * daysPerMonth + day
        return new HaabDate(monthNumber * s_daysPerMonth + day);
    }
}
