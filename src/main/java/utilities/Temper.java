package utilities;

import java.util.Random;

/**
 * Creates a temporary password
 * 
 * @author Devon
 *
 */

public class Temper {

	private Temper() {
		super();
	}

	public static String getTempString() {
		return (chunk(5) + "#" + chunk(4) + "=" + chunk(5));
	}

	public static String chunk(int length) {
		Random set = new Random();
		String str = new String();
		for (int i = 0; i < length; i++) {
			if (i == 0) {
				str += (char) (65 + set.nextInt(26));
			} else if (i == 1) {
				str += (char) (48 + set.nextInt(10));
			} else {
				str += (char) (97 + set.nextInt(26));
			}
		}
		return str;
	}
}
