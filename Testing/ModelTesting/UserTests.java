package ModelTesting;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import models.User;
import static models.Lists.users;

public class UserTests
{
	User marie= new User ("Marie", "Donoval", "19", "F", "Student");
	
@Test
public void addUser()
{	
assertEquals("Marie",marie.fName);
assertEquals("Donoval", marie.lName);
assertEquals("19", marie.age);
assertEquals("F", marie.gender);
assertEquals("Student", marie.job);
}


@Test
public void testIds()
{
  Set<Long> ids = new HashSet<>();
  for (User user : users)
  {
    ids.add(user.userID);
  }
  assertEquals (users.length, ids.size());
}

@Test
public void testToString()
{
  assertEquals ("User{" + marie.userID + ", Marie, Donoval, 19, F, Student}", marie.toString());
}
}

