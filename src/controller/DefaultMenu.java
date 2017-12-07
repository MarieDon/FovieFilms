package controller;

import java.util.Iterator;
import java.util.TreeSet;

import com.google.common.base.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
import models.Movies;
import models.User;

public class DefaultMenu {

	  private String name;
	  private User user;
	  private FovieInterface fovieApi;

	  public DefaultMenu(FovieInterface fovieApi, User user) {
	    this.fovieApi = fovieApi;
	    this.setName(user.fName);
	    this.user = user;
	  }
	  @Command(description = "Get a Users detail")
	  public void getUser(@Param(name = "userID") Long id) {
	    User user = fovieApi.getUser(id);
	    System.out.println(user);
	  }
	  @Command(description = "Add Movie")
	  public void addMovies(@Param(name = "title") String title, @Param(name = "year") String year,
	      @Param(name = "url") String url) {
	    fovieApi.addMovie(title, year, url);
	  }
	  @Command(description = "Add Ratings")
	  public void addRatings(@Param(name = "ID") Long id, @Param(name = "userID") Long userID,
	      @Param(name = "movieID") Long movieID,  @Param(name = "rating") int ratings) {
	    Optional<Movies> movies = Optional.fromNullable(fovieApi.getMovie(movieID));
	    if (movies.isPresent()) {
	      fovieApi.addRatings(movies.get().movieID, userID, movieID, ratings);
	    }
	  }
	  public String getName() {
	    return name;
	  }
	  public void setName(String name) {
	    this.name = name;
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
	  
	//TreeSet instead of hash map, easier to use. Iterator is for loop using collections//
	  @Command(description = "Get movies and sort by title")
	  public void getAllMovies() {
		  TreeSet<Movies> sortedMovies = new TreeSet<Movies>();
			sortedMovies.addAll(fovieApi.getMovies());
			Iterator<Movies> iteratoR = sortedMovies.iterator();
			System.out.println("List of movies sorted by the title and released date");
			while(iteratoR.hasNext()) {
				Movies moviE = iteratoR.next();
				System.out.println("Title: " + moviE.title + " " + "Released Date: " +moviE.year);  
	  }
	  }
	  
	  
	}