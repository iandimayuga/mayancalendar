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
public class TzolkinDateTest
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
    }

    /**
     * Test method for {@link icd3.TzolkinDate#TzolkinDate(int)}.
     */
    @Test
    public void testTzolkinDate()
    {
        assertEquals(42, new TzolkinDate(42).toInt());
        assertEquals(0, new TzolkinDate(260).toInt());
        assertEquals(3, new TzolkinDate(-257).toInt());
    }

    /**
     * Test method for {@link icd3.TzolkinDate#plus(int)}.
     */
    @Test
    public void testPlus()
    {
        assertEquals(75, new TzolkinDate(0).plus(75).toInt());
        assertEquals(0, new TzolkinDate(259).plus(1).toInt());
        assertEquals(250, new TzolkinDate(10).plus(-20).toInt());
    }

    /**
     * Test method for {@link icd3.TzolkinDate#minus(icd3.TzolkinDate)}.
     */
    @Test
    public void testMinus()
    {
        TzolkinDate itself = new TzolkinDate(42);
        assertEquals(0, itself.minus(itself));
        assertEquals(250, new TzolkinDate(250).minus(new TzolkinDate(0)));
        assertEquals(10, new TzolkinDate(0).minus(new TzolkinDate(250)));
    }

    /**
     * Test method for {@link icd3.TzolkinDate#toString()}.
     */
    @Test
    public void testToString()
    {
        assertEquals("1.imix", new TzolkinDate(0).toString());
        assertEquals("2.ik", new TzolkinDate(1).toString());
        assertEquals("8.imix", new TzolkinDate(20).toString());
        assertEquals("12.chikchan", new TzolkinDate(24).toString());
    }

    /**
     * Test method for {@link icd3.TzolkinDate#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject()
    {
        assertEquals(new TzolkinDate(0), new TzolkinDate(0));
        assertEquals(new TzolkinDate(24), TzolkinDate.parse("12.chikchan"));
        assertEquals(new TzolkinDate(148), TzolkinDate.parse("6.muluk"));
    }

    /**
     * Test method for {@link icd3.TzolkinDate#parse(java.lang.String)}.
     */
    @Test
    public void testParse()
    {
        assertEquals(new TzolkinDate(0), new TzolkinDate(0));
        assertEquals(new TzolkinDate(44), TzolkinDate.parse("006.chiKChan"));
        assertEquals(new TzolkinDate(243), TzolkinDate.parse(" 10 . kan "));
        assertEquals("6.kan", TzolkinDate.parse("6.kan").toString());
    }

}
