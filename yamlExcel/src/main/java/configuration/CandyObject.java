package configuration;

import lombok.Data;
import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
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

    @Getter
    private String className;

    private List<CandyField<T>> candyFields;

    private Object object;

    public void setClassName(String className) throws ClassNotFoundException{
        this.className = className;
        objClazz = Class.forName(className);
    }

    @Data
    public static class CandyField<T> {
        private String fieldName;

        private Object value;

        private Type fieldType;

        private String type;

        private Map enumMap;

        private Object getValue() throws Exception{
            Field field = objClazz.getField(fieldName);
            Method method = objClazz.getMethod("get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1));
//            method.invoke()
            return null;
        }
    }
}
