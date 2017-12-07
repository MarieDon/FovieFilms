package controller;

import java.io.IOException;
import java.util.Collection;

import models.Movies;
import models.Ratings;
import models.User;

public interface FovieInterface {

	Collection<User> getUsers();
	
	Collection<Movies> getMovies();

	boolean login(Long userID, String lName);

	void logout();

	User addUser(String fName, String lName, String age, String gender, String job);

	User getUser(Long id);

	User getUserByName(String fName);

	void deleteUsers();

	void removeUser(Long userID);

	void deleteUser(String name);

	void addMovie( String title, String year, String url);

	void addRating(Long userID, Long movieID, int rating);

	Movies getMovie(Long id);

	void addRatings(Long id, Long userID, Long movieID, int ratings);

	void initalLoad() throws IOException;

	void load() throws Exception;

	void store() throws Exception;

}