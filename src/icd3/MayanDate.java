/**
 *
 */
package icd3;

/**
 * An immutable data structure to represent a date in different Mayan calendars.
 *
 * The date representation is only comparable with other date representations of the same type.
 */
public interface MayanDate<T extends MayanDate<T>>
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
     * In an absolute date system (such as the Long Count), minus MAY return a negative.
     * However, in a cyclic system (such as the Tzolkin), minus SHOULD return the positive equivalence class.
     * @throws NullPointerException If other is null.
     */
    public int minus(T other);

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
}
