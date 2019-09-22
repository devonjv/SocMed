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
		return new User(username, password, firstName, lastName, email + ".com");
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/add/{username}/{password}/{firstName}/{lastName}/{email}/{file}")
	public User addUser(@PathVariable("username") String username, @PathVariable("password") String password,
			@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName,
			@PathVariable("email") String email, @PathVariable("file") File file) {
		/**
		 * For creating a user with a profile picture
		 */
		String key = "profiles/" + username + ".jpg";
		PictureStorage.post(file, key);
		return new User(username, password, firstName, lastName, email + ".com", key);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/checkUsername/{username}")
	public boolean checkUsername(@PathVariable("username") String username) {
		/**
		 * For checking if a username exists.
		 */
		return udao.usernameExists(username);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/checkEmail/{email}")
	public boolean checkEmail(@PathVariable("email") String email) {
		/**
		 * For checking if an email exists.
		 */
		return udao.usernameExists(email);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/checkPassword/{username}/{entry}")
	public boolean checkPassword(@PathVariable("username") String username, @PathVariable("entry") String entry) {
		/**
		 * To check if the password is correct.
		 */
		User user = udao.getUserByUsername(username);
		return user.checkPassword(entry);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/changePassword/{username}/{entry}")
	public User changePassword(@PathVariable("username") String username, @PathVariable("entry") String entry) {
		/**
		 * To change the password.
		 */
		User user = udao.getUserByUsername(username);
		user.changePassword(entry);
		return user;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/changeFirstName/{username}/{entry}")
	public User changeFirstName(@PathVariable("username") String username, @PathVariable("entry") String entry) {
		/**
		 * To change the first name.
		 */
		User user = udao.getUserByUsername(username);
		user.changeFirstName(entry);
		return user;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/changeLastName/{username}/{entry}")
	public User changeLastName(@PathVariable("username") String username, @PathVariable("entry") String entry) {
		/**
		 * To change the last name.
		 */
		User user = udao.getUserByUsername(username);
		user.changeLastName(entry);
		return user;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/changeEmail/{username}/{entry}")
	public User changeEmail(@PathVariable("username") String username, @PathVariable("entry") String entry) {
		/**
		 * To change the email.
		 */
		User user = udao.getUserByUsername(username);
		user.changeEmail(entry + ".com");
		return user;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/changePic/{username}/{file}")
	public User changePic(@PathVariable("username") String username, @PathVariable("file") File file) {
		/**
		 * To change the profile picture.
		 */
		User user = udao.getUserByUsername(username);
		String key = "profiles/" + username + ".jpg";
		PictureStorage.post(file, key);
		user.changePicture(key);
		return user;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/forgotPassword/{username}")
	public User changePic(@PathVariable("username") String username) {
		/**
		 * Calls forgot password.
		 */
		User user = udao.getUserByUsername(username);
		user.forgotPassword();
		return user;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/ban/{username}")
	public User banUser(@PathVariable("username") String username) {
		/**
		 * Bans user
		 */
		User user = udao.getUserByUsername(username);
		user.ban();
		return user;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/makeAdmin/{username}")
	public User adminUser(@PathVariable("username") String username) {
		/**
		 * Promotes to admin.
		 */
		User user = udao.getUserByUsername(username);
		user.makeAdmin();
		return user;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/makeActive/{username}")
	public User activeUser(@PathVariable("username") String username) {
		/**
		 * Sets to standard, active user.
		 */
		User user = udao.getUserByUsername(username);
		user.makeActive();
		return user;
	}

}
