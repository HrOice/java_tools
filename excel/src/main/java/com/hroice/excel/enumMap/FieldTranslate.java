package com.hroice.excel.enumMap;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * DATE: 16/7/20 15:39 <br>
 * MAIL: lbw@terminus.io <br>
 * AUTHOR: macbook
 */

public class FieldTranslate {
    private Map<String,SingleEnumMap> translateMap = new HashMap<String, SingleEnumMap>();

    public void addTranslate(String name ,SingleEnumMap singleEnumMap) {
        translateMap.put(name,singleEnumMap);
    }

    public Map<String, SingleEnumMap> getTranslateMap() {
        return translateMap;
    }
}
