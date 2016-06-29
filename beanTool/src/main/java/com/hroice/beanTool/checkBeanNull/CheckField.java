package com.hroice.beanTool.checkBeanNull;



import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 判断对象是否为空,并返回空值信息
 * DATE: 16/6/28 14:25 <br>
 * MAIL: lbw@terminus.io <br>
 * AUTHOR: macbook
 */
public class CheckField<E> {
    private static final String SerialVersionUID = "serialVersionUID";
    private Object object;
    public CheckField(Object o) {
        this.object = o;
    }

    public Class<?> getObjClass(){
        return object.getClass();
    }

    private Field[] getField(E e) {
        return e.getClass().getDeclaredFields();
    }

    private Set<String> getValue(E e) {
        Set<String> set = new HashSet<String>();
        if (e.getClass().isAnnotationPresent(CanBeNull.class)) {
            String[] value = e.getClass().getAnnotation(CanBeNull.class).value();
            for (int i = 0; i < value.length; i++) {
                set.add(value[i]);
            }
        }
        return set;
    }

    private String checkNull(Field[] fields,E e,Set<String> set){
        String returnStr = "";
        try {
            for (Field f : fields) {
                String fName = f.getName();
                if (Objects.equals(fName, SerialVersionUID)) {
                    continue;
                }
                String getMethodName = "get" + fName.substring(0, 1).toUpperCase() + fName.substring(1);
                Method method = e.getClass().getMethod(getMethodName);
                Object res = method.invoke(e);
                if (res == null && !set.contains(fName)) {
                    returnStr = fName + ".can.not.be.null";
                    throw new NullPointerException(fName + ".can.not.be.null");
                }
            }
        }catch (NullPointerException npe) {
            return returnStr;
        }catch (Exception exception) {
            return "parse error";
        }
        return null;
    }

    public String check(){
        E e = (E) object;
        return checkNull(getField(e),e,getValue(e));
    }
}
