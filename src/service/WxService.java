package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class WxService {
	private static final String TOKEN = "abc";

	// 验证签名

	// 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
	public static boolean check(String timestamp, String nonce, String signature) throws Exception {
		// 1）将token、timestamp、nonce三个参数进行字典序排序
		String[] s = new String[] { TOKEN, timestamp, nonce };
		Arrays.sort(s);
		// 2）将三个参数字符串拼接成一个字符串进行sha1加密
		String sha1String = s[0] + s[1] + s[2];
		String mysig = sha1(sha1String);
		System.out.println(mysig);
		return mysig.equalsIgnoreCase(signature);
	}

	// sha1加密
	private static String sha1(String src) throws NoSuchAlgorithmException {
		try {
			MessageDigest digest = MessageDigest.getInstance("sha1");
			byte[] digest2 = digest.digest(src.getBytes());
			char[] cs = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			StringBuilder builder = new StringBuilder();
			for (byte b : digest2) {
				builder.append(cs[(b >> 4) & 15]);
				builder.append(cs[b & 15]);
			}
			return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
