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
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link icd3.TzolkinDate#minus(icd3.TzolkinDate)}.
     */
    @Test
    public void testMinus()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link icd3.TzolkinDate#toInt()}.
     */
    @Test
    public void testToInt()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link icd3.TzolkinDate#toString()}.
     */
    @Test
    public void testToString()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link icd3.TzolkinDate#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link icd3.TzolkinDate#pattern()}.
     */
    @Test
    public void testPattern()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link icd3.TzolkinDate#cycle()}.
     */
    @Test
    public void testCycle()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link icd3.TzolkinDate#parse(java.lang.String)}.
     */
    @Test
    public void testParse()
    {
        fail("Not yet implemented");
    }

}
