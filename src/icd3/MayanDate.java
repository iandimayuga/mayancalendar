/**
 *
 */
package icd3;

/**
 * An immutable data structure to represent a date in different Mayan calendars.
 *
 * The date representation is only compatible with other date representations of the same type.
 */
public interface MayanDate<T>
{
    /**
     * Calculate a certain number of days away from this date.
     * @param days The number of days to add (can be negative for subtract). If days is 0, this will return itself.
     * @return A date object that is shifted from this date.
     */
    public T plus(int days);

    /**
     * Calculate the difference between this date and another date of the same type.
     * @param other The other date of the same type.
     * @return The difference between the two dates.
     */
    public T minus(T other);

    /**
     * Gives the internal integer representation of this date.
     * @return The integer representation of this date.
     */
    public int toInt();

    /**
     * Gives the String representation of this date.
     * @return The String representation of this date.
     */
    public String toString();

    /**
     * Determines whether this date is equivalent to another date of the same type.
     * @param o Another date of the same type with which to compare.
     * @return True if o is a non-null MayanDate of the same type that represents the same equivalence class.
     */
    public boolean equals(Object o);

    /**
     * Gives the regular expression matching all strings representing this date.
     * @return A regular expression that defines this date representation.
     */
    public String regex();
}
