/**
 *
 */
package icd3;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class LongCountDateTest
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
    }

    /**
     * Test method for {@link icd3.LongCountDate#plus(int)}.
     */
    @Test
    public void testPlus()
    {
        assertEquals(new LongCountDate(5, 4, 3, 2, 9), new LongCountDate(0, 3, 3, 2, 9).plus(25));
    }

    /**
     * Test method for {@link icd3.LongCountDate#minus(icd3.LongCountDate)}.
     */
    @Test
    public void testMinus()
    {
        assertEquals(25, new LongCountDate(5, 4, 3, 2, 9).minus(new LongCountDate(0, 3, 3, 2, 9)));
    }

    /**
     * Test method for {@link icd3.LongCountDate#getPeriod()}.
     */
    @Test
    public void testGetPeriod()
    {
        LongCountDate date = new LongCountDate(1, 2, 3, 4, 8);
        assertEquals(1, date.getPeriod(LongCountDate.Period.KIN));
        assertEquals(2, date.getPeriod(LongCountDate.Period.WINAL));
        assertEquals(3, date.getPeriod(LongCountDate.Period.TUN));
        assertEquals(4, date.getPeriod(LongCountDate.Period.KATUN));
        assertEquals(8, date.getPeriod(LongCountDate.Period.BAKTUN));
    }

    /**
     * Test method for {@link icd3.LongCountDate#toInt()}.
     */
    @Test
    public void testToInt()
    {
        assertEquals(0, new LongCountDate(0).toInt());
    }

    /**
     * Test method for {@link icd3.LongCountDate#toString()}.
     */
    @Test
    public void testToString()
    {
        assertEquals("8.4.3.2.1", new LongCountDate(1, 2, 3, 4, 8).toString());
    }

    /**
     * Test method for {@link icd3.LongCountDate#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject()
    {
        assertEquals(new LongCountDate(0), new LongCountDate(0, 0, 0, 0, 0));
    }

    /**
     * Test method for {@link icd3.LongCountDate#calendarRoundToLongCount(icd3.CalendarRoundDate, icd3.LongCountDate)}.
     */
    @Test
    public void testCalendarRoundToLongCount()
    {
        TzolkinDate t = new TzolkinDate(9, TzolkinDate.Day.AJAW);
        HaabDate h = new HaabDate(3, HaabDate.Month.SIP);
        CalendarRoundDate cr = new CalendarRoundDate(t, h);
        assertEquals(new LongCountDate(0, 0, 0, 0, 8),
                LongCountDate.calendarRoundToLongCount(cr, new LongCountDate(0, 0, 0, 0, 8)));

        TzolkinDate t2 = new TzolkinDate(3, TzolkinDate.Day.LAMAT);
        HaabDate h2 = new HaabDate(6, HaabDate.Month.PAX);
        CalendarRoundDate cr2 = new CalendarRoundDate(t2, h2);
        assertEquals(new LongCountDate(8, 17, 17, 0, 8), LongCountDate.calendarRoundToLongCount(cr2,
                new LongCountDate(0, 0, 0, 0, 8)));
    }

    /**
     * Test method for
     * {@link icd3.LongCountDate#calendarRoundToLongCountList(icd3.CalendarRoundDate, icd3.LongCountDate, icd3.LongCountDate)}
     * .
     */
    @Test
    public void testCalendarRoundToLongCountList()
    {
        TzolkinDate t = new TzolkinDate(3, TzolkinDate.Day.LAMAT);
        HaabDate h = new HaabDate(6, HaabDate.Month.PAX);
        CalendarRoundDate cr = new CalendarRoundDate(t, h);
        assertEquals(new LongCountDate(8, 17, 17, 0, 8), LongCountDate.calendarRoundToLongCountList(cr,
                new LongCountDate(0, 0, 0, 0, 8), new LongCountDate(0, 0, 0, 0, 10)).get(0));
    }

}
