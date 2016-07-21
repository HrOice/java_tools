package configuration;

import lombok.Data;
import lombok.Getter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public void setClassName(String className) throws ClassNotFoundException {
        this.className = className;
        objClazz = Class.forName(className);
    }

    public void putCandy(T t) {
        this.t = t;
        this.obj = this.t;
        this.fieldPoint = candyFields.iterator();
    }

    public Object getCandy() {
        return this.obj;
    }

    public Object pointOut() {
        if (this.fieldPoint.hasNext()) {
            CandyField<T> pointer = this.fieldPoint.next();
            try {
                return pointer.getFieldValue();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IndexOutOfBoundsException("pointer out of " + t.getClass().getName() + " fields num");
        }
    }

    public List<Title> buildTitle(Row titleRow) {
        titles = new ArrayList<Title>();
        int i = 0;
        for (CandyField f : candyFields) {
            Cell cell = titleRow.createCell(i);
            cell.setCellValue(f.title);
            Title title = new Title(f.title, i++);
            titles.add(title);
        }
        return titles;
    }

    private List<Title> titles;

    @Data
    private static class Title {
        int titleIndex;
        private String title;

        private Title(String title, int index) {
            this.title = title;
            this.titleIndex = index;
        }
    }


    @Data
    public static class CandyField<T> {

        private static final String JAVA_LANG = "java.lang.";
        private static final String JAVA_UTIL = "java.util.";

        private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        private String fieldName;

        private String title;

        private Type fieldType;

        private String type;

        private String timeFormat;

        private Map<Object,Object> enumMap;

        private Object getFieldValue() throws Exception {
            Method method = objClazz.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
            Object value = method.invoke(obj, null);
            if (this.enumMap != null && this.enumMap.containsKey(value)) {
                return this.enumMap.get(value);
            } else {
                return value;
            }
        }

        /**
         * 给对象赋值 Long String Integer Date
         * @param input
         * @throws Exception
         */
        private void setFieldValue(Object input) throws Exception{
            Method method = objClazz.getMethod("set" + fieldName.substring(0, 1).toUpperCase()
                    + fieldName.substring(1),parseType());
            Object trueValue;
            if (this.enumMap != null && this.enumMap.containsValue(input)) {
                trueValue = getKeyViaValue(input);
            } else {
                trueValue = input;
            }
            if (type.equals("Long")){
                method.invoke(obj, (Long)trueValue);
            } else if (type.equals("String")) {
                method.invoke(obj, (String)trueValue);
            } else if (type.equals("Integer")) {
                method.invoke(obj, (Integer)trueValue);
            } else if (type.equals("Date")) {
                if (this.timeFormat == null) {
                    method.invoke(obj, DEFAULT_DATE_FORMAT.parse(trueValue.toString()));
                } else{
                    SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
                    method.invoke(obj, sdf.parse(trueValue.toString()));
                }
            }
        }

        private Object getKeyViaValue(Object input){
            for (Map.Entry entry: this.enumMap.entrySet()) {
                if (entry.getValue().toString().equals(input.toString())) {
                    return entry.getKey();
                }
            }
            return input;
        }

        private Class parseType() throws ClassNotFoundException{
            switch (this.type) {
                case "String":
                    return Class.forName(JAVA_LANG + "String");
                case "Integer":
                    return Class.forName(JAVA_LANG + "Integer");
                case "Long":
                    return Class.forName(JAVA_LANG + "Long");
                case "Date":
                    return Class.forName(JAVA_UTIL + "Date");
                default:
                    throw new IllegalArgumentException("undefined type" + this.type);
            }
        }
    }

    /**
     * 新建T,设置field
     */
    public Object createCandy() throws Exception{
        this.t = (T)objClazz.newInstance();
        this.obj = objClazz.newInstance();
        this.fieldPoint = candyFields.iterator();
        return this.obj;
    }

    public void insertValue(Object input){
        if (this.fieldPoint.hasNext()) {
            CandyField<T> pointer = this.fieldPoint.next();
            try {
                pointer.setFieldValue(input);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IndexOutOfBoundsException("pointer out of " + t.getClass().getName() + " fields num");
        }
    }
}
