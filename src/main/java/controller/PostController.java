package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dao.PostDAO;
import model.Post;
import utilities.Helper;

@RestController
@RequestMapping(value = "/Posts")
public class PostController {

	@Autowired
	PostDAO pdao;

	public PostController() {
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/add/public/text/{username}/{text}")
	public Post addPublicText(@PathVariable("username") String username, @PathVariable("text") String text) {
		return new Post(text, Helper.userDAO().getUserByUsername(username), Helper.statusService().getPublicPost());
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/add/public/picture/{username}/{text}")
	public Post addPublicPicture(@PathVariable("username") String username, @PathVariable("text") String text) {
		return new Post(text, Helper.userDAO().getUserByUsername(username), Helper.statusService().getPublicPost());
	}
}
