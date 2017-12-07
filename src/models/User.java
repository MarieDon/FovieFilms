package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;

public class User implements Comparable<User> {
	public String fName, lName, job;
	public String age;
	public String gender;
	public static Long userCount = (long) 01;
	public Long userID;
	public Map<Long, Ratings> TheRatings = new HashMap<>();
	public String role;
	
	public User(String fName, String lName, String age, String gender, String job)
	  {
	    this(fName,lName, age, gender, job, "default");
	  }

	public User(String fName, String lName, String age, String gender, String job,String role) {
		this.userID = userCount++;
		this.fName = fName;
		this.lName = lName;
		this.age = age;
		this.gender = gender;
		this.job = job;
		this.role = role;
	}

	@Override
	public String toString() {

		return toStringHelper(this)
				.addValue(userID)
				.addValue(fName)
				.addValue(lName)
				.addValue(age)
				.addValue(gender)
				.addValue(job)
				.toString();

	}
	
	@Override  
	  public int hashCode()  
	  {  
	     return Objects.hashCode(this.fName, this.lName, this.age, this.gender, this.job);  
	  }
	
	@Override
	public boolean equals(final Object obj)
	{
		if(obj instanceof User)
		{
			final User other=(User) obj;
			return Objects.equal(fName, other.fName)
				&& Objects.equal(lName, other.lName)
				&& Objects.equal(age, other.age)
				&& Objects.equal(gender, other.gender)
				&& Objects.equal(job, other.job);
		}
		else
		{
			return false;
		}
	}
	
	@Override
	//return users sorted by last name
	public int compareTo(User user)
	{
		return this.lName.compareTo(user.lName);
	}
}
