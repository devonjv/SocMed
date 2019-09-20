package controller;

import java.io.File;
import java.util.List;
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
	@GetMapping(value = "/getPublic")
	public List<Post> getPublicPosts() {
		/**
		 * Gets all public posts
		 */
		return pdao.getPublicPosts();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/add/public/text/{username}/{text}")
	public Post addPublicText(@PathVariable("username") String username, @PathVariable("text") String text) {
		return new Post(text, Helper.userDAO().getUserByUsername(username), Helper.statusService().getPublicPost());
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/add/public/picture/{username}/{text}/{file}")
	public Post addPublicPicture(@PathVariable("username") String username, @PathVariable("text") String text, @PathVariable("file") File file) {
		return new Post(text, Helper.userDAO().getUserByUsername(username), Helper.statusService().getPublicPost());
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/like/{postId}")
	public Post likePost(@PathVariable("postId") int id) {
		Post post = Helper.postDAO().getPostById(id);
		post.like();
		return post;
	}
}
