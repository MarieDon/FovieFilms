package ModelTesting;

import static org.junit.Assert.*;

import org.junit.Test;
import static models.Lists.rating;

public class RatingsTest 
{
	@Test
	public void TestCreate()
	{
		assertEquals(1 , 1L, rating[0].userID);
		assertEquals(2 , 2L, rating[0].movieID);
		assertEquals(3 , 3L, rating[0].ratings);		
	}
	
	@Test
	public void TestIds()
	{
		assertNotEquals(rating[0].id, rating[1].id);
	}
	
	@Test
	public void TestToString()
	{
		assertEquals("Ratings{"+rating[0].id + ", 2, 3, 3}", rating[0].toString());
    }
}
