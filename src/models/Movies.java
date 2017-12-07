package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.google.common.base.Objects;

import asg.cliche.Command;

public class Movies implements Comparable<Movies> {
	public String title,url;
	public String year;
	public Long movieID;
	public static Long movieCounter=(long) 01;
	
	public Map<Long, Ratings> theratings = new HashMap<>();

	public Movies(String title,String year, String url)
	{
	this.movieID=movieCounter++;
	this.title=title;
	this.year=year;
	this.url=url;
	}
	
	@Override
	public String toString()
	{
		

		return toStringHelper(this).addValue(movieID)
				                   .addValue(title)
				                   .addValue(year)
				                   .addValue(url)
				                   .toString();
				
				                   
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hashCode(this.movieID, this.title, this.year, this.url);
	}
	
	@Override
	public boolean equals(final Object obj)
	{
		if(obj instanceof Movies)
		{
			final Movies other=(Movies) obj;
			return Objects.equal(movieID, other.movieID)
				&& Objects.equal(title, other.title)
				&& Objects.equal(year, other.year)
				&& Objects.equal(url, other.url);
		}
		else
		{
			return false;
		}
	}

	@Override
	 public int compareTo(Movies movies)
		{
			return this.title.compareTo(movies.title);
	}
	
	
	  
}

