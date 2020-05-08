package util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * @author 陌意随影
 * @create 2020-02-31 17:57
 * @desc 配置文件的工具类
 **/
public class ConfigPropertiesUtil {
	
	/**所有配置文件的文件夹路径*/
	public static final String rootPath = "resources/PropertiesDatas";
	private static File file = new File(rootPath);
	private static Properties[] properties = null;
	private static  int len ;
	private static String[] filesPaths =null;
	private static Map<String,String[]> valuesMap = new HashMap<>();
	static {
		//判断所有配置文件的路径是否是正确的，并且判定是否是一个文件夹
		if(file.exists() && file.isDirectory()) {
			//获得配置文件夹下的所有的文件的 名称+格式，比如  druid.properties
			filesPaths =file.list();
			//文件路径非空并且长度大于零
			if(filesPaths !=null && filesPaths.length >0 ) {
	            //获取文件的个数
				len = filesPaths.length;
				properties = new Properties[len];
				//动态加载每个配置文件到 Properties中
				for (int i = 0; i < len; i++) {
					properties[i] = new Properties();
					try {
						properties[i].load(Thread.currentThread().getContextClassLoader().getResourceAsStream(file.getName()+"/"+filesPaths[i]));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		
	}

	/**
	 * 通过key来获取对应的value
	 * 
	 * @param keyName
	 * @return 返回对应的value
	 */
	public static String getPropertiesValueByName(String keyName) {
		for (int i = 0; i < properties.length; i++) {
			if (properties[i].containsKey(keyName)) {
				return properties[i].getProperty(keyName);
			}
		}
		return null;
	}
  /**
   * 通过配置文件的名字来返回对应的 Properties，如 druid.properties的名称为druid
 * @param name  文件的名称
 * @return Properties
 */
public static Properties getPropertiesByName(String name) {
	if(len > 0) {
		for(int i = 0 ;i < len;i++) {
			if(filesPaths[i].contains(name)) {
				return properties[i];
			}
		}
		
	}
	return null;
	  
  }
/**
 * 通过配置文件的名字来返回对应的value数组
* @param name  文件的名称
* @return Properties 的value数组
*/
public static String[] getPropertiesValuesByName(String name) {
	if( name == null||name.trim().length() ==0) {
		return null;
	}
	if(valuesMap.containsKey(name)) {
	return valuesMap.get(name);
	}
	String[] values = null;
	if(len > 0) {
		for(int i = 0 ;i < len;i++) {
			if(filesPaths[i].contains(name)) {
				 Properties pro= properties[i];
				 if(pro.size()>0) {
					 values = new String[pro.size()];
					 int index = 0;
					 for(Entry<Object, Object> entry:pro.entrySet()) {
						 values[index++]= (String) entry.getValue();
					 }
					 //对状态进行排序
					 Arrays.sort(values);
					 valuesMap.put(name, values);
					 return values;
				 }
			}
		}
		
	}
	return values;
	  
}
}
