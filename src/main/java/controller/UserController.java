package controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import dao.UserDAO;
import model.User;

@RestController
public class UserController {

	@Autowired
	UserDAO udao;

	public UserController() {
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/Users/getActive")
	public List<User> getActiveUsers() {
		/**
		 * Gets all users
		 */
		return udao.getActiveUsers();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/Users/get.{username}")
	public User getUser(@PathVariable("username") String username) {
		/**
		 * Gets user by username
		 */
		return udao.getUserByUsername(username);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/Users/add{username}.{password}.{firstName}.{lastName}.{email}")
	public User addUser(@PathVariable("username") String username, @PathVariable("password") String password,
			@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName,
			@PathVariable("email") String email) {
		/**
		 * For creating a user without a profile picture
		 */
		return new User(username, password, firstName, lastName, email);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/Users/add{username}.{password}.{firstName}.{lastName}.{email}.{key}")
	public User addUser(@PathVariable("username") String username, @PathVariable("password") String password,
			@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName,
			@PathVariable("email") String email, @PathVariable("email") String key) {
		/**
		 * For creating a user with a profile picture
		 */
		return new User(username, password, firstName, lastName, email, key);
	}
}
