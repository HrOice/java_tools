package configuration;

import lombok.Data;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * DATE: 16/7/20 22:06 <br>
 * MAIL: lbw@terminus.io <br>
 * AUTHOR: macbook
 */
@Data
public class CandyField<T> {
    private String fieldName;

    private Type fieldType;

    private Map<Object,Object> enumMap;

    public CandyField(String fieldName, Type fieldType, Map<Object, Object> enumMap) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.enumMap = enumMap;
    }
}
