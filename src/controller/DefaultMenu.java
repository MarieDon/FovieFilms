package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import asg.cliche.Command;
import asg.cliche.Param;
import models.Movies;
import models.User;

public class DefaultMenu {

	  private String name;
	  private User user;
	  private FovieAPI fovieApi;

	  public DefaultMenu(FovieAPI fovieApi, User user) {
	    this.fovieApi = fovieApi;
	    this.setName(user.fName);
	    this.user = user;
	  }
	  
	  

	  
	  //Returning a name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
    }

	/*=================================
	 * Users
	 ================================*/
	
	@Command(description="Save File")
	public void safe() throws Exception {
		fovieApi.store();
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

	  @Command(description = "Get a Users detail")
	  public void getUser(@Param(name = "userID") Long id) {
	    User user = fovieApi.getUser(id);
	    System.out.println(user);
	  }
	  
	  @Command(description = "Create a new user")
	  public void CreateUser(@Param(name = "first name") String fName, @Param(name = "last name") String lName, 
	  @Param(name = "age") String age, @Param(name = "gender") String gender, @Param(name = "job") String job)
			  {
		  fovieApi.addUser(fName, lName, age, gender, job);
			  }
	  
	  @Command(description = "Search user by name")
	  public void getUserByName(String name) 
	  {
		  int i =0;
		  ArrayList<User> users = new ArrayList<User>();
		  users.addAll(fovieApi.getUsers());
		  while( i < users.size())
		  {
			  if(users.get(i).fName.toUpperCase().contains(name.toUpperCase()))
			  {
				  System.out.println(users.get(i));
			  }
			  i++;
		  }
	  }
	  	  
	   
	  /*=================================
		 * Movies
	    ================================*/
	  
	  @Command(description = "Add Movie")
	  public void addMovies(@Param(name = "title") String title, @Param(name = "year") String year,
	      @Param(name = "url") String url) {
	    fovieApi.addMovie(title, year, url);
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
	  
	  @Command(description = "Get a movie by ID")
	  public Movies getMovie(@Param(name = "Movie ID") Long id)
	  {
	    return fovieApi.getMovie(id);
	  }
	  
	  
	  /*=================================
	   * Ratings
	   ================================*/
	  
	  @Command(description = "Add a ratings")
	  public void addRatings(@Param(name = "userID") Long userID,
	      @Param(name = "movieID") Long movieID,  @Param(name = "rating") int ratings) {
	      fovieApi.addRating(userID, movieID, ratings);
	  }
	
	    
	  @Command(description = "Get user ratings")
	  public void getUserRatings(@Param(name = "User ID") long id)
	  {
		  System.out.println(fovieApi.getUserRating(id)); 
	  }
	  
	  @Command(description = "Get movie ratings")
	  public void getMovieRatings(@Param(name = "Movie ID") Long id)
	  {
		   System.out.println(fovieApi.getMovieRating(id)); 
	  }
	  
	  @Command(description = "Get a ratings")
	  public void getRatings(@Param(name = "Rating ID") Long id)
	  {
		  System.out.println(fovieApi.getRating(id)); 
	  }
	  
	  
	  @Command(description = "Get all ratings")
	 public void getRatings()
	  {
		 System.out.println(fovieApi.getRatings()); 
	  }
	  
	  @Command(description = "Delete a ratings")
	  public void deleteRating(@Param(name = "Rating ID") Long id)
	  {
			  fovieApi.deleteRating(id);
	  }
	  
	  
}