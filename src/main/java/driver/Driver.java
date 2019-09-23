package driver;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JFrame;

import model.User;
import utilities.Helper;
import utilities.PictureStorage;

public class Driver {

	public static void main(String[] args) throws IOException {
		/**
		 * Purely for manual tests.
		 */
		System.out.println("begin");
//		Helper.compile();
//		System.out.println("helper compiled");
//		Helper.setInfoTables();
//		System.out.println("tables set");
//		User u1=new User("Jodoe","P455word","John","Doe", "jodoe@gamil.com");
//		User u2=new User("Jadoe","P455word","Jane","Doe", "jadoe@gamil.com");
//		User u3=new User("Jidoe","P455word","Jimmy","Doe", "jidoe@gamil.com");
//		User u4=new User("Judoe","P455word","Jummy","Doe", "judoe@gamil.com");
//		User u5=new User("Inferno","BurnItAll","Azareth","Heim", "devonjvirden@gmail.com");
//		User u6=new User("Dejavir","pass6842","Devon","Virden", "devonvirden@hotmail.com");
//		System.out.println("users added");
//		User u1=Helper.userDAO().getUserByUsername("Jodoe");
//		User u2=Helper.userDAO().getUserByUsername("Jadoe");
//		User u3=Helper.userDAO().getUserByUsername("Jidoe");
//		User u4=Helper.userDAO().getUserByUsername("Judoe");
//		User u5=Helper.userDAO().getUserByUsername("Inferno");
//		User u6=Helper.userDAO().getUserByUsername("Dejavir");
//		System.out.println("users fetched");
//		u1.makeActive();
//		u2.makeActive();
//		u3.makeActive();
//		u4.makeActive();
//		u5.makeActive();
//		u6.makeAdmin();
//		System.out.println("status set");
//		System.out.println(Helper.userDAO().usernameExists("Dejavir"));
//		User user = Helper.userDAO().getUserByUsername("Dejavir");
//		System.out.println(user.checkPassword("pass6842"));
//		Post p1 = new Post("This is a post", user, Helper.statusService().getPublicPost());
//		user.changePicture("Vandal");
//		JFrame jf = new JFrame();
//		FileDialog fd = new FileDialog(jf);
//		fd.setVisible(true);
//		File[] pic = fd.getFiles();
//		if (pic.length > 0) {
//			byte[] file = Files.readAllBytes(pic[0].toPath());
//			PictureStorage.post(file, "inferno.jpg");
//		}
//		User ctg = Helper.userDAO().getUserByUsername("Inferno");
//		System.out.println("fetched");
//		ctg.changePicture("inferno.jpg");
		System.out.println("finished");
	}
}
