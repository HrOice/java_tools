package configuration;

import lombok.Data;
import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * DATE: 16/7/20 22:04 <br>
 * MAIL: lbw@terminus.io <br>
 * AUTHOR: macbook
 */
@Data
public class CandyObject<T> {
    private static Class objClazz;

    private T t;

    private static Object obj;

    @Getter
    private String className;

    private List<CandyField<T>> candyFields;

    private Iterator<CandyField<T>> fieldPoint;

    public void setClassName(String className) throws ClassNotFoundException{
        this.className = className;
        objClazz = Class.forName(className);
    }

    public void putCandy(T t) {
        this.t = t;
        this.obj = this.t;
        this.fieldPoint = candyFields.iterator();
    }

    public Object getCandy(){
        return this.obj;
    }

    public Object pointOut(){
        if (this.fieldPoint.hasNext()) {
            CandyField<T> pointer = this.fieldPoint.next();
            try {
                return pointer.getFieldValue();
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IndexOutOfBoundsException("pointer out of " + t.getClass().getName() + "fields num");
        }
    }

    @Data
    public static class CandyField<T> {
        private String fieldName;

        private String title;

        private Type fieldType;

        private String type;

        private Map enumMap;

        private Object getFieldValue() throws Exception{
            Method method = objClazz.getMethod("get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1));
            Object value = method.invoke(obj, null);
            if (this.enumMap != null && this.enumMap.containsKey(value)) {
                return this.enumMap.get(value);
            }else{
                return value;
            }
        }
    }
}
