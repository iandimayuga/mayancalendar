/**
 *
 */
package icd3;

import java.util.regex.Pattern;

/**
 * An immutable data structure representing the Mayan Long Count absolute date.
 */
public class LongCountDate implements MayanDate<LongCountDate>
{
    /**
     * Return a regular expression describing the string representation of a Mayan Long Count date. The string
     * representation is whitespace insensitive.
     *
     * @return A regular expression pattern that will match the allowed representations of this date type.
     */
    public static Pattern pattern()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see icd3.MayanDate#plus(int)
     */
    @Override
    public LongCountDate plus(int days)
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
    public int minus(LongCountDate other)
    {
        // TODO Auto-generated method stub
        return 0;
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
