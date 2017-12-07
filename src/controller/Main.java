package controller;

import java.io.File;
import java.io.IOException;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellDependent;
import asg.cliche.ShellFactory;
import models.User;
import utils.Serializer;
import utils.XMLSerializer;

public class Main implements ShellDependent {
	private static final String ADMIN = "admin";
  public FovieAPI fovieApi = new FovieAPI();
 private Shell theShell;
	  
	public FovieInterface fovie;
	public Main() throws Exception {
		File movies = new File ("./libs/users1234.xml");
		Serializer serializer = new XMLSerializer(movies);
		fovie= new FovieAPI(serializer);
		if (movies.isFile()) 
		{
			fovie.load();
		}
	}
	
	public void cliSetShell(Shell theShell) {
	    this.theShell = theShell;
	  }
	
	


	@Command(description = "Log in")
	  public void logIn(@Param(name = "userId") Long userid, @Param(name = "Last name") String lastName)
	      throws IOException {

	    if (fovieApi.login(userid, lastName) && fovieApi.currentUser.isPresent()) {
	      User user = fovieApi.currentUser.get();
	      System.out.println("You are logged in as " + user.fName);
	      if (user.role !=null && user.role.equals(ADMIN)) {
	        AdminMenu adminMenu = new AdminMenu(fovieApi, user.fName);
	        ShellFactory.createSubshell(user.fName, theShell, "Admin", adminMenu).commandLoop();
	      } else {
	        DefaultMenu defaultMenu = new DefaultMenu(fovieApi, user);
	        ShellFactory.createSubshell(user.fName, theShell, "Default", defaultMenu).commandLoop();
	      }
	    } else
	      System.out.println("Unknown username/password.");
	  }

	
	 public static void main(String[] args) throws Exception {
		    Main main = new Main();
		    main.fovieApi.initalLoad();
		    Shell shell = ShellFactory.createConsoleShell("you", "Welcome to Fovie - ?help for instructions", main);
		    shell.commandLoop();
		    main.fovieApi.store();
		  }
}
