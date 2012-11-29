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
public class HaabDateTest
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
    }

    /**
     * Test method for {@link icd3.HaabDate#HaabDate(int)}.
     */
    @Test
    public void testHaabDate()
    {
        assertEquals(42, new HaabDate(42).toInt());
        assertEquals(0, new HaabDate(365).toInt());
        assertEquals(3, new HaabDate(-362).toInt());
    }

    /**
     * Test method for {@link icd3.HaabDate#plus(int)}.
     */
    @Test
    public void testPlus()
    {
        assertEquals(75, new HaabDate(0).plus(75).toInt());
        assertEquals(0, new HaabDate(364).plus(1).toInt());
        assertEquals(355, new HaabDate(10).plus(-20).toInt());
    }

    /**
     * Test method for {@link icd3.HaabDate#minus(icd3.HaabDate)}.
     */
    @Test
    public void testMinus()
    {
        HaabDate itself = new HaabDate(42);
        assertEquals(0, itself.minus(itself));
        assertEquals(250, new HaabDate(250).minus(new HaabDate(0)));
        assertEquals(115, new HaabDate(0).minus(new HaabDate(250)));
    }

    /**
     * Test method for {@link icd3.HaabDate#toString()}.
     */
    @Test
    public void testToString()
    {
        assertEquals("1.pohp", new HaabDate(0).toString());
        assertEquals("2.pohp", new HaabDate(1).toString());
        assertEquals("1.wo", new HaabDate(20).toString());
        assertEquals("5.wo", new HaabDate(24).toString());
    }

    /**
     * Test method for {@link icd3.HaabDate#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject()
    {
        assertEquals(new HaabDate(0), new HaabDate(0));
        assertEquals(new HaabDate(24), HaabDate.parse("5.wo"));
        assertEquals(new HaabDate(1), HaabDate.parse("2.pohp"));
        assertEquals(new HaabDate(364), HaabDate.parse("5.wayeb"));
    }

    /**
     * Test method for {@link icd3.HaabDate#parse(java.lang.String)}.
     */
    @Test
    public void testParse()
    {
        assertEquals(new HaabDate(0), new HaabDate(0));
        assertEquals(new HaabDate(24), HaabDate.parse(" 5 . wo "));
        assertEquals(new HaabDate(1), HaabDate.parse("02.pOhp"));
    }

}
