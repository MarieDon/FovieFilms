package models;

import static com.google.common.base.MoreObjects.toStringHelper;
import com.google.common.base.Objects;


public class Ratings {
	static Long counter = (long) 01;
	public Long id;
	public Long userID;
	public Long movieID;
	public int ratings;

	
	public Ratings(Long userID,Long movieID,int ratings)
	{
	this.id = counter++;
	this.userID=userID;
	this.movieID=movieID;
	this.ratings=ratings;
	}
	
	@Override
	public String toString()
	{
		return toStringHelper(this).addValue(id)
								   .addValue(userID)
								   .addValue(movieID)
								   .addValue(ratings)
								   .toString();
				
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hashCode(this.id,this.userID,this.movieID,this.ratings);
	}
	
	public boolean equals(final Object obj)
	{
		if(obj instanceof Ratings)
		{
			final Ratings other=(Ratings) obj;
			return Objects.equal(id, other.id)
				&& Objects.equal(userID, other.userID)
				&& Objects.equal(movieID, other.movieID)
				&& Objects.equal(ratings, other.ratings);
		}
		else
		{
			return false;
		}
	}
}
