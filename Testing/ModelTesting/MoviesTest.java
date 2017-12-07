package ModelTesting;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import models.Movies;

public class MoviesTest
{

Movies test= new Movies("Focus", "2011", "456rfc");

	@Test
	public void testCreateMovies() {
		assertEquals("Focus",    test.title);
		assertEquals("2011",    test.year);
		assertEquals("456rfc",    test.url);	
	}
	
	@Test
	public void testToString()
	{
		assertEquals("Movies{"+test.movieID+", Focus, 2011, 456rfc}",test.toString());
	}
}

