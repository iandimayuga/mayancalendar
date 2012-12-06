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
     * Test method for {@link icd3.CalendarRoundDate#CalendarRoundDate(int)}.
     */
    @Test
    public void testCalendarRoundDateInt()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link icd3.CalendarRoundDate#plus(int)}.
     */
    @Test
    public void testPlus()
    {
        assertEquals(CalendarRoundDate.parse("6.kan 2.kayab"), CalendarRoundDate.parse("3.lamat 6.pax").plus(16));
    }

    /**
     * Test method for {@link icd3.CalendarRoundDate#minus(icd3.CalendarRoundDate)}.
     */
    @Test
    public void testMinus()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link icd3.CalendarRoundDate#toInt()}.
     */
    @Test
    public void testToInt()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link icd3.CalendarRoundDate#toString()}.
     */
    @Test
    public void testToString()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link icd3.CalendarRoundDate#getTzolkinDate()}.
     */
    @Test
    public void testGetTzolkinDate()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link icd3.CalendarRoundDate#getHaabDate()}.
     */
    @Test
    public void testGetHaabDate()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link icd3.CalendarRoundDate#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link icd3.CalendarRoundDate#pattern()}.
     */
    @Test
    public void testPattern()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link icd3.CalendarRoundDate#cycle()}.
     */
    @Test
    public void testCycle()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link icd3.CalendarRoundDate#parse(java.lang.String)}.
     */
    @Test
    public void testParse()
    {
        assertEquals("6.kan 2.kayab", CalendarRoundDate.parse("6.kan 2.kayab").toString());
    }

}
