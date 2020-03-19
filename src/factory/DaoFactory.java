package factory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import dao.BookDao;
import domain.Book;
import util.ConfigPropertiesUtil;

/**
 * @author 陌意随影
 TODO :dao的工厂类
 *2020年3月15日  下午5:42:36
 */
public class DaoFactory {
  private static Map<String,Object> daoMap = new HashMap<>();
  private static Properties propertiesByName = null;
  static {
	   propertiesByName = ConfigPropertiesUtil.getPropertiesByName("dao");
	  for(Entry<Object, Object> entry:propertiesByName.entrySet()) {
		  Object key = entry.getKey();
		  Object value = entry.getValue();
		  if(value instanceof String) {
			  try {
				Class<?> obj = Class.forName((String)value);
				  try {
					Constructor<?> constructor = obj.getConstructor();
					Object object = constructor.newInstance();
					daoMap.put((String)key, object);
					System.out.println(key+"----->"+value);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			  
		  }
	  }
  }
/**
 * 通过dao的类名来获取对应的dao实现类
 * @param daoName
 * @return   返回对应的dao对象
 */
public static Object newInstanceDao(String daoName) {
	if(daoMap.containsKey(daoName)) {
		return daoMap.get(daoName);
	}
	return null;
	  
  }
	public static void main(String[] args) {
		BookDao dao = (BookDao) DaoFactory.newInstanceDao("BookDao");
		List<Book> all = dao.getAll();
		System.out.println(all);
		
	}
}
