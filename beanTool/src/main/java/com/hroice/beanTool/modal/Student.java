package com.hroice.beanTool.modal;

import com.hroice.beanTool.checkBeanNull.CanBeNull;
import lombok.Data;

import java.io.Serializable;

/**
 * DATE: 16/6/29 10:55 <br>
 * MAIL: lbw@terminus.io <br>
 * AUTHOR: macbook
 */
@Data
@CanBeNull({"momName","dadName"})
public class Student implements Serializable {
    private String name;

    private Integer age;

    private Integer grade;

    private Integer classes;

    private Integer score;

    private String momName;

    private String dadName;
}
