package com.hroice.excel.enumMap;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * DATE: 16/7/20 16:02 <br>
 * MAIL: lbw@terminus.io <br>
 * AUTHOR: macbook
 */
public class SingleEnumMap<T>  {
    private Map<String,String> singleEnumMap = new HashMap<String, String>();

    public SingleEnumMap(Class clazz) {
        if (!clazz.isEnum()) {
            throw new IllegalArgumentException(clazz.getName() + "is not a enum!");
        }
        Object[] enumConstants = clazz.getEnumConstants();
        for (int i = 0 ; i< enumConstants.length ; i++ ) {
            T t = (T) enumConstants[i];
            String key = this.getEnumValue(t);
            String value = getEnumDesc(t);
            singleEnumMap.put(key,value);
        }
    }

    public Map<String, String> getSingleEnumMap() {
        return singleEnumMap;
    }

    private String getEnumValue(T t){
        try {
            Method method = t.getClass().getMethod("value", null);
            return method.invoke(t,null).toString();
        }catch (NoSuchMethodException nsme) {
            throw new RuntimeException(t.getClass().getName() + " doesn't contains value of field");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getEnumDesc(T t){
        return t.toString();
    }
}
