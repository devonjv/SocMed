package model;


public class TestPad {
	
	public static void main(String[] args) {
		System.out.println("here we go");
		User jdoe = new User("Jodoe", "password", "John","Doe", "jodoe@hotmail.com");
		System.out.println("user made");
		System.out.println(jdoe.getStatus());
		System.out.println("SUCCESS");
		User jadoe = new User("Jadoe", "password", "Jane","Doe", "jadoe@hotmail.com");
		System.out.println("user made");
		System.out.println(jadoe.getStatus());
		System.out.println("SUCCESS");
	}
}
