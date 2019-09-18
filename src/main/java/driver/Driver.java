package driver;

import model.Post;
import model.User;
import utilities.Helper;

public class Driver {

	public static void main(String[] args) {
		System.out.println("begin");
		Helper.compile();
		System.out.println("helper compiled");
		System.out.println(Helper.userDAO().usernameExists("Dejavir"));
		User user = Helper.userDAO().getUserByUsername("Dejavir");
		Post post = new Post("This is a test post", user,
				Helper.statusService().getPublicPost());
		System.out.println("finished");

	}
}
