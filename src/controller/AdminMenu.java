package controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import asg.cliche.Command;
import asg.cliche.Param;
import models.User;

public class AdminMenu {

	  private String name;
	  private User user;
	  private FovieInterface fovieApi;

	  public AdminMenu(FovieInterface fovieApi, String userName) {

	    this.fovieApi = fovieApi;
	    this.setName(userName);
	  }

	  @Command(description = "Get all users details")
	  public void getUsers() {

	    Collection<User> users = fovieApi.getUsers();
	    System.out.println(users);

	  }
	  public String getName() {
	    return name;
	  }
	  public void setName(String name) {
	    this.name = name;
	  }
	  @Command(description = "Create a new User")
	  public void createUser(@Param(name = "first name") String fName, @Param(name = "last name") String lName,
	      @Param(name = "age") String age, @Param(name = "gender") String gender, @Param(name = "job") String job) {

		  fovieApi.addUser(fName, lName, age, gender, job);

	  }
	  @Command(description = "Get a Users detail")
	  public void getUser(@Param(name = "id") Long id) {

	    User user = fovieApi.getUser(id);
	    System.out.println(user);

	  }
//	  @Command(description = "Delete a User")
//	  public void deleteUser(@Param(name = "email") String Name) {
//
//	    Optional<User> user = Optional.fromNullable(fovieApi.deleteUser(Name));
//	    if (user.isPresent()) {
//	    	fovieApi.deleteUser(user.get().userID);
//	    }
//	  }
	  
	  
	  @Command(description = "Add an activity")
	  public void addMovie(@Param(name = "title") String title, @Param(name = "year") String year,
		      @Param(name = "url") String url) {
		    fovieApi.addMovie(title, year, url);
	  }
	  
	  /*TreeSet instead of hash map, easier to use*/
	  @Command(description = "Get users and sort by full name")
	  public void GetAllUsers() {
		  TreeSet<User> sortedUsers = new TreeSet<User>();
			sortedUsers.addAll(fovieApi.getUsers());
			Iterator<User> iteratoR = sortedUsers.iterator();
			while(iteratoR.hasNext()) {
				User usrs = iteratoR.next();
				System.out.println(usrs.lName + " " + usrs.fName);  
	  }
	  }
	  
//	//TreeSet instead of hash map, easier to use. Iterator is for loop using collections//
//	  @Command(description = "Get movies and sort by year")
//	  public void GetAllMovies() {
//		  TreeSet<Movies> sortedMovies = new TreeSet<Movies>();
//			sortedMovies.addAll(fovieApi.getMovies());
//			Iterator<Movies> iteratoR = sortedMovies.iterator();
//			while(iteratoR.hasNext()) {
//				Movies moviE = iteratoR.next();
//				System.out.println(moviE.year);  
//	  }
//	  }
	  
	  
	  
//	  @Command(description = "Add a Rating to Movie")
//	  public void addRatings(@Param(name = "movieID") Long movieID, @Param(name = "userID") Long userID,
//	      @Param(name = "longitude") float longitude) {
//	    Optional<Movies> movies = Optional.fromNullable(fovieApi.getMovie(movieID));
//	    if (movies.isPresent()) {
//	    	fovieApi.addRatings(movies.get().movieID, userID, movieID, ratings);
//	    }
//	  }
	}
