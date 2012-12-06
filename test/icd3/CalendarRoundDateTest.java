/**
 *
 */
package icd3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class CalendarRoundDateTest
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
    }

    /**
     * Test method for {@link icd3.CalendarRoundDate#CalendarRoundDate(icd3.TzolkinDate, icd3.HaabDate)}.
     */
    @Test
    public void testCalendarRoundDateTzolkinDateHaabDate()
    {
        assertEquals(0, new CalendarRoundDate(new TzolkinDate(0), new HaabDate(0)).toInt());
        assertEquals(42, new CalendarRoundDate(new TzolkinDate(42), new HaabDate(42)).toInt());
    }

    /**
     * Test method for {@link icd3.CalendarRoundDate#plus(int)}.
     */
    @Test
    public void testPlus()
    {
        TzolkinDate t1 = new TzolkinDate(6, TzolkinDate.Day.KAN);
        TzolkinDate t2 = new TzolkinDate(3, TzolkinDate.Day.LAMAT);
        HaabDate h1 = new HaabDate(2, HaabDate.Month.KAYAB);
        HaabDate h2 = new HaabDate(6, HaabDate.Month.PAX);
        assertEquals(new CalendarRoundDate(t1, h1), new CalendarRoundDate(t2, h2).plus(16));
    }

    /**
     * Test method for {@link icd3.CalendarRoundDate#minus(icd3.CalendarRoundDate)}.
     */
    @Test
    public void testMinus()
    {
        TzolkinDate t1 = new TzolkinDate(6, TzolkinDate.Day.KAN);
        TzolkinDate t2 = new TzolkinDate(3, TzolkinDate.Day.LAMAT);
        HaabDate h1 = new HaabDate(2, HaabDate.Month.KAYAB);
        HaabDate h2 = new HaabDate(6, HaabDate.Month.PAX);
        assertEquals(16, new CalendarRoundDate(t1, h1).minus(new CalendarRoundDate(t2,h2)));
    }

    /**
     * Test method for {@link icd3.CalendarRoundDate#toInt()}.
     */
    @Test
    public void testToInt()
    {
        assertEquals(0, new CalendarRoundDate(0).toInt());
    }

    /**
     * Test method for {@link icd3.CalendarRoundDate#toString()}.
     */
    @Test
    public void testToString()
    {
        TzolkinDate t1 = new TzolkinDate(6, TzolkinDate.Day.KAN);
        HaabDate h1 = new HaabDate(2, HaabDate.Month.KAYAB);
        assertEquals("6.KAN 2.KAYAB", new CalendarRoundDate(t1, h1).toString());
    }
}
