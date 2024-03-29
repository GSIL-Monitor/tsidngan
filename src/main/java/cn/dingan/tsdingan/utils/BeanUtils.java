package cn.dingan.tsdingan.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cglib.beans.BeanMap;

/**
 * @ClassName: BeanUtils
 * @Description: BeanUtils
 * @author quedd#trasen.cn
 * @date 2018年5月2日 上午9:31:59
 *
 */
public class BeanUtils {

    /**
     * @Title: convertBeanToMap
     * @Description: bean转换Map
     * @param bean
     * @return
     * @date 2018年5月2日 上午9:32:06
     * @author quedd#trasen.cn
     */
    public static <T> Map<String, Object> convertBeanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }
    
    /**
     * map转对象
     * @param map
     * @param beanClass
     * @return
     */
    public static Object mapToObject(Map<String, String> map,Class<?> beanClass) {
    	 if (map == null)  
             return null;  
         try {
        	 Object obj = beanClass.newInstance();  
			 org.apache.commons.beanutils.BeanUtils.populate(obj, map);
			 return obj;
		} catch (Exception e) {
			
		}  
       return null;   
    }

}
