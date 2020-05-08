package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author 陌意随影 TODO :登录验证的工具类 2020年2月31日 下午2:30:15
 */
public class LoginAuthentication {
	/**正则表达式的ʽ */
	private static Pattern p = null;
	/** 用户名的格式 */
	public static String unameFormat = "";
	/** 密码的格式ʽ */
	public static String userPwdFormat = "";
	/** 管理员账号的格式 */
	public static String admitorFormat = "";
	/** 管理员的密码格式 */
	public static String admitorPwdformat = "";
	private static Properties pro = new Properties();
	private static InputStream in = null;
	static {
		try {
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream("Authentication.properties");
			pro.load(in);
			unameFormat = pro.getProperty("unameFormat");
			userPwdFormat = pro.getProperty("userPwdFormat");
			admitorFormat = pro.getProperty("admitorFormat");
			admitorPwdformat = pro.getProperty("admitorPwdformat");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 通过用户名和指定的用户名格式来验证该用户名的输入是否合法
	 * 
	 * @param uname  用户名
	 * @param format 用户名格式
	 * @return 合法则返回true，否则返回false
	 */
	public static boolean loginUnameAuthentication(String uname, String format) {
		p = Pattern.compile(format);
		Matcher m = p.matcher(uname);
		return m.matches();

	}

	/**
	 * 通过密码和指定的密码格式来验证该用户名的输入是否合法
	 * 
	 * @param password 密码
	 * @param format   密码格式
	 * @return 合法则返回true，否则返回false
	 */
	public static boolean loginPasswordAuthentication(String password, String format) {
		p = Pattern.compile(format);
		Matcher m = p.matcher(password);
		return m.matches();
	}

}
