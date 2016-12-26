package activity.bawe.com.ynf.utils;

import java.security.MessageDigest;

public class MD5Encoder {
/**
* autour:张钻
* date: 2016/11/28 14:03
* update: 2016/11/28
*/
	public static String encode(String string) throws Exception {
		byte[] hash = MessageDigest.getInstance("MD5").digest(
				string.getBytes("UTF-8"));
		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10) {
				hex.append("0");
			}
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}
}
