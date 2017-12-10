package controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.common.base.Optional;

import models.Movies;
import models.Ratings;
import models.User;
import utils.Serializer;

public class FovieAPI implements FovieInterface {
	private Serializer serializer;

	private Map<Long, User> userIndex = new HashMap<>();
	private Map<Long, Movies> moviesIndex = new HashMap<>();
	private Map<String, User> name = new HashMap<>();
	private Map<Long, Ratings> ratingIndex = new HashMap<>();
	Optional<User> currentUser;
	
	/*============================
	 * Constructors
	 *==========================*/

	public FovieAPI() {
	}

	public FovieAPI(Serializer serializer) {
		this.serializer = serializer;
	}
	
	/*=============================
	 * Getters
	 * ==========================*/
	
	@Override
	public Collection<User> getUsers() {
		return userIndex.values();
	} 
	
	@Override
	public Collection<Movies> getMovies() {
		return moviesIndex.values();
	}
	
	@Override
	public User getUser(Long id)
	{
		return userIndex.get(id);
	}
	

	@Override
	public User getUserByName(String fName) {
		return name.get(fName);
	}
	
	@Override
	public Movies getMovie(Long id) {
		return moviesIndex.get(id);
	}
	
	public Collection<Ratings> getRatings()
	{
		return ratingIndex.values();
	}
	
	public Ratings getRating(long id)
	{
		return ratingIndex.get(id);
	}
	
	public Map<Long, Ratings> getUserRating(long id)
	{
		Optional<User> user = Optional.fromNullable(userIndex.get(id));
		return user.get().TheRatings;
	}
	
	public Map<Long, Ratings> getMovieRating(long id)
	{
		Optional<Movies> movies = Optional.fromNullable(moviesIndex.get(id));
		return movies.get().theratings;
	}
	
	
	/*====================================
	 * Adders
	 *==================================*/
	
	public void addMovie(String title, String year, String url) {
		Movies movie = new Movies(title, year, url);
		moviesIndex.put(movie.movieID, movie);
	}
	
	
	@Override
	public void addRating(Long userID, Long movieID, int rating) {
		Ratings ratings;
		Optional<User> user=Optional.fromNullable(userIndex.get(userID));
	    Optional<Movies> movie=Optional.fromNullable(moviesIndex.get(movieID));
	    if(movie.isPresent()&& user.isPresent())
	    {
	    	ratings = new Ratings(userID, movieID, rating); 
	    	user.get().TheRatings.put(ratings.id, ratings); //connect user to rating
	    	movie.get().theratings.put(ratings.id, ratings); //connect movie to rating
	    	ratingIndex.put(ratings.id, ratings); //connect rating to collection(list of ---)
	    }
	}
	
	@Override
	public User addUser(String fName, String lName, String age, String gender, String job) {
		User user = new User(fName, lName, age, gender, job);
		userIndex.put(user.userID, user);
		name.put(fName, user);
		return user;
	}
	
	/*===============================
	 * Remove and Delete
	 *=============================*/
	
	@Override
	public void deleteUsers() {
		userIndex.clear();
	}

	@Override
	public void removeUser(Long userID) {
			User user= userIndex.remove(userID);
			name.remove(user.fName);
	}

	@Override
	public void deleteUser(String name) {
		User user = userIndex.remove(name);
		userIndex.remove(user.fName);
	}

	public void deleteRating(long id)
	{
		ratingIndex.remove(id);
	}
	
	
	/*=========================
	 * Utilities
	 *=======================*/
	
	@Override
	public void initalLoad() throws IOException {
		String delims = "[|]";
		Scanner scanner = new Scanner(new File("./libs/users5.dat"));
		while (scanner.hasNextLine()) {
			String userDetails = scanner.nextLine();
			// parse user details string
			String[] userTokens = userDetails.split(delims);

			if (userTokens.length == 7) {
				addUser(userTokens[1], userTokens[2], userTokens[3], userTokens[4], userTokens[5]);
			} else {
				scanner.close();
				throw new IOException("Invalid member length: " + userTokens.length);
			}
		}
		
		scanner = new Scanner(new File("./libs/items5.dat"));
		while (scanner.hasNextLine()) {
			String userDetails = scanner.nextLine();
			// parse user details string
			String[] userTokens = userDetails.split(delims);

			if (userTokens.length == 23) {
				addMovie(userTokens[1], userTokens[2], userTokens[3]);
			} else {
				scanner.close();
				throw new IOException("Invalid member length: " + userTokens.length);
			}
		}
		
		scanner = new Scanner(new File("./libs/ratings5.dat"));
		while (scanner.hasNextLine()) {
			String userDetails = scanner.nextLine();
			// parse user details string
			String[] userTokens = userDetails.split(delims);

			if (userTokens.length == 4) {
				addMovie(userTokens[1], userTokens[2], userTokens[3]);
			} else {
				scanner.close();
				throw new IOException("Invalid member length: " + userTokens.length);
			}
		}
		scanner.close();
	}
	
	@Override
	public boolean login(Long userID, String lName) {
	    Optional<User> user = Optional.fromNullable(userIndex.get(userID));
	    if (user.isPresent() && user.get().lName.equals(lName)) {
	      currentUser = user;    
	      return true;
	    }
	    return false;
	  }
	

	@Override
	public void logout() {
	    Optional<User> user = currentUser;
	    if (user.isPresent()) {
	      currentUser = Optional.absent();
	    }
	  }

	
	@Override
	@SuppressWarnings("unchecked")
	public void load() throws Exception {
		serializer.read();
		userIndex = (Map<Long, User>) serializer.pop();
		moviesIndex = (Map<Long, Movies>) serializer.pop();
		ratingIndex = (Map<Long, Ratings>) serializer.pop();
		
	}

	
	@Override
	public void store() throws Exception {
		serializer.push(ratingIndex);
		serializer.push(moviesIndex);
		serializer.push(userIndex);	
		serializer.write();
	}

}
