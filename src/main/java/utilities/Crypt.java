package utilities;

import java.util.Random;

/**
 * Used to encrypt and decrypt a password
 * 
 * @author Devon
 *
 */

public class Crypt {

	private Crypt() {
		super();
	}

	public static String decryptWord(String word) {
		return decrypt(word);
	}

	public static String encryptWord(String word) {
		return encrypt(word);
	}

	private static String junk(int i) {
		String str = new String();
		Random set = new Random();
		for (int j = 0; j < i; j++) {
			str += (char) (33 + set.nextInt(100));
		}
		return str;
	}

	private static String encrypt(String word) {
		String str = new String();
		String tmp = new String();
		str += junk(4);
		for (int i = 0; i < 2; i++) {
			str += (char) ((((int) word.charAt(i)) * 2) - 42);
		}
		str += junk(6);
		for (int i = (word.length() - 3); i > 1; i--) {
			str += (char) ((((int) word.charAt(i)) * 2) - 34);
		}
		str += junk(2);
		for (int i = (word.length() - 2); i < word.length(); i++) {
			str += (char) ((((int) word.charAt(i)) * 2) - 56);
		}
		str += junk(7);
		tmp = str;
		str = "";
		for (int i = 0; i < tmp.length(); i++) {
			str += (char) ((int) (tmp.charAt(i) * 4) - (tmp.length() - i));
		}
		return str;
	}

	private static String decrypt(String word) {
		String str = new String();
		String tmp = new String();
		for (int i = 0; i < word.length(); i++) {
			tmp += (char) (((word.length() - i) + (int) (word.charAt(i))) / 4);
		}
		for (int i = 4; i < 6; i++) {
			str += (char) (((int) tmp.charAt(i) + 42) / 2);
		}
		for (int i = (tmp.length() - 12); i >= 12; i--) {
			str += (char) (((int) tmp.charAt(i) + 34) / 2);
		}
		for (int i = (tmp.length() - 9); i < (tmp.length() - 7); i++) {
			str += (char) (((int) tmp.charAt(i) + 56) / 2);
		}
		return str;
	}
}
