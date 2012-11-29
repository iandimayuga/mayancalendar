/**
 *
 */
package icd3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An immutable data structure representing the Mayan Long Count absolute date.
 */
public class LongCountDate implements MayanDate<LongCountDate>
{
    /**
     * Integer representation of this date
     */
    private int m_value;

    /**
     * Instantiates a LongCountDate object from its integer representation. Negatives are not permitted and will be
     * interpreted as zero.
     *
     * @param value The integer representation.
     */
    public LongCountDate(int value)
    {
        m_value = value < 0 ? 0 : value;
    }

    /*
     * (non-Javadoc)
     *
     * @see icd3.MayanDate#plus(int)
     */
    @Override
    public LongCountDate plus(int days)
    {
        return new LongCountDate(this.toInt() + days);
    }

    /*
     * (non-Javadoc)
     *
     * @see icd3.MayanDate#minus(java.lang.Object)
     */
    @Override
    public int minus(LongCountDate other)
    {
        if (null == other)
        {
            throw new NullPointerException("Cannot subtract a null Long Count Date");
        }
        return this.toInt() - other.toInt();
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
        return o != null && o instanceof LongCountDate && ((LongCountDate) o).toInt() == this.toInt();
    }

    private static int[] s_placeValues = { 1, 20, 360, 7200, 144000 };
    private static String[] s_placeNames = { "kin", "winal", "tun", "katun", "baktun" };
    private static String[] s_placeValueRegexes = { "0*([0-9]|1[0-9])", "0*([0-9]|1[0-7])", "0*([0-9]|1[0-9])",
            "0*([0-9]|1[0-9])", "\\d+" };

    // The Calendar Round date corresponding to 0.0.0.0.0
    private static String s_zeroDay = "4.ajaw 8.kumku";

    private static Pattern s_pattern = generatePattern(s_placeValueRegexes, s_placeNames);

    private static Pattern generatePattern(String[] placeValueRegexes, String[] placeNames)
    {
        StringBuilder patternBuilder = new StringBuilder();

        patternBuilder.append("\\s*");

        for (int i = s_placeValueRegexes.length - 1; i >= 0; --i)
        {
            patternBuilder.append(String.format("(?<%s>%s)\\s*", placeNames[i], placeValueRegexes[i]));

            // Dots only go in between places
            if (i > 0)
            {
                patternBuilder.append("\\.\\s*");
            }
        }

        return Pattern.compile(patternBuilder.toString());
    }

    /**
     * Return a regular expression describing the string representation of a Mayan Long Count date. The string
     * representation is whitespace insensitive.
     *
     * @return A regular expression pattern that will match the allowed representations of this date type.
     */
    public static Pattern pattern()
    {
        return s_pattern;
    }

    /**
     * Parse a string representation of a Long Count Date.
     *
     * @param s A string matching LongCountDate.pattern().
     * @return A LongCountDate object whose toString() will return an equivalent representation, or null if s does not
     *         match.
     */
    public static LongCountDate parse(String s)
    {
        // Attempt to match the input string
        Matcher m = pattern().matcher(s);

        // Return null if s does not match pattern
        if (!m.matches())
        {
            return null;
        }

        int total = 0;

        for (int i = 0; i < s_placeNames.length; ++i)
        {
            // Multiply each place number by its corresponding place value and add to the total
            total += Integer.parseInt(m.group(s_placeNames[i])) * s_placeValues[i];
        }

        return new LongCountDate(total);
    }
}
