package test;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

import util.ConfigContant;
import util.ConfigPropertiesUtil;

public class test {
public static void main(String[] args) throws IOException {
////	System.out.println(AdminitratorAllImageIcons.BTN_LOGIN.getIconHeight());
////	System.out.println(AdminitratorAllImageIcons.DELETE.getIconHeight());
////	System.out.println(AdminitratorAllImageIcons.BTN_LOGIN);
////	System.out.println(AdminitratorAllImageIcons.DELETE);
////	System.out.println(AdminitratorAllImageIcons.INDEX.getIconHeight());
////	System.out.println(ConfigContant.BTN_NEXT);
	
//	ImageIcon icno = new ImageIcon("C:/Users/Administrator/workspace03/java-booksystem/resources/administratorImgs/btnnext.png");
//	System.out.println(icno.getIconWidth());
//	
//	File f = new File("resources\\PropertiesDatas");
////	System.out.println(f.getName());
//	String[] list = f.list();
//	for(String s:list) {
//		Properties pro = new Properties();
//		pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(f.getName()+"/"+s));
//		System.out.println(pro);
//		System.out.println(f.getName()+"/"+s);
//	}
//	Properties properties = ConfigPropertiesUtil.getPropertiesByName("druid");
//	String[] propertiesValuesByName = ConfigContant.BOOKTSTATUS;
//	for(String s:propertiesValuesByName) {
//		System.out.println(s);
//	}
	System.out.println(isDouble("10.2044444"));
	
	
}
/**
 * @param str
 * @return
 */
public static boolean isDouble(String str) {
	Pattern pattern = Pattern.compile("^[-//+]?//d+(//.//d*)?|//.//d+$");
	return pattern.matcher(str).matches();
	}

}
