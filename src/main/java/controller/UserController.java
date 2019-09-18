package controller;

import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dao.UserDAO;
import model.User;
import utilities.PictureStorage;

@RestController
@RequestMapping(value = "/Users")
public class UserController {

	@Autowired
	UserDAO udao;

	public UserController() {
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/getActive")
	public List<User> getActiveUsers() {
		/**
		 * Gets all users
		 */
		return udao.getActiveUsers();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/get.{username}")
	public User getUser(@PathVariable("username") String username) {
		/**
		 * Gets user by username
		 */
		return udao.getUserByUsername(username);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/add/{username}/{password}/{firstName}/{lastName}/{email}")
	public User addUser(@PathVariable("username") String username, @PathVariable("password") String password,
			@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName,
			@PathVariable("email") String email) {
		/**
		 * For creating a user without a profile picture
		 */
		return new User(username, password, firstName, lastName, email);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/add/{username}/{password}/{firstName}/{lastName}/{email}/{file}")
	public User addUser(@PathVariable("username") String username, @PathVariable("password") String password,
			@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName,
			@PathVariable("email") String email, @PathVariable("file") File file) {
		/**
		 * For creating a user with a profile picture
		 */
		String key = "/profiles/" + username + ".jpg";
		PictureStorage.post(file, key);
		return new User(username, password, firstName, lastName, email, key);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/checkUsername/{username}")
	public boolean checkUsername(@PathVariable("username") String username) {
		return udao.usernameExists(username);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/checkEmail/{email}")
	public boolean checkEmail(@PathVariable("email") String email) {
		return udao.usernameExists(email);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/checkPassword/{username}/{entry}")
	public boolean checkPassword(@PathVariable("username") String username, @PathVariable("entry") String entry) {
		User user = udao.getUserByUsername(username);
		return user.checkPassword(entry);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/updatePic/{username}/{file}")
	public User changePic(@PathVariable("username") String username, @PathVariable("file") File file) {
		User user = udao.getUserByUsername(username);
		String key = "/profiles/" + username + ".jpg";
		PictureStorage.post(file, key);
		user.changePicture(key);
		return user;
	}

}
