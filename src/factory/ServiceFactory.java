package factory;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import util.ConfigPropertiesUtil;

/**
 * @author 陌意随影
 TODO :业务逻辑的工厂
 *2020年3月15日  下午5:43:13
 */
public class ServiceFactory {
  private static Map<String,Object> serviceMap = new HashMap<>();
  private static Properties propertiesByName = null;
  static {
	   propertiesByName = ConfigPropertiesUtil.getPropertiesByName("service");
	  for(Entry<Object, Object> entry:propertiesByName.entrySet()) {
		  Object key = entry.getKey();
		  Object value = entry.getValue();
		  if(value instanceof String) {
			  try {
				Class<?> obj = Class.forName((String)value);
				  try {
					Constructor<?> constructor = obj.getConstructor();
					Object object = constructor.newInstance();
					serviceMap.put((String)key, object);
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
 * 通过service的类名来获取对应的service实现类
 * @param serviceName service的名称
 * @return   返回对应的service实现类对象
 */
public static Object newInstanceService(String serviceName) {
//	System.out.println(serviceName);
	if(serviceMap.containsKey(serviceName)) {
		return serviceMap.get(serviceName);
	}
	return null;
	  
  }
}

