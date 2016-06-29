package com.hroice.beanTool.test;

import com.hroice.beanTool.checkBeanNull.CheckField;
import com.hroice.beanTool.modal.Student;
import org.junit.Test;

/**
 * DATE: 16/6/29 10:59 <br>
 * MAIL: lbw@terminus.io <br>
 * AUTHOR: macbook
 */
public class CheckFieldTest {
    @Test
    public void testCheckNull(){
        Student s = mock();
        CheckField<Student> checkField = new CheckField<Student>(s);
        String res = checkField.check();
        System.out.println(res);
    }

    public Student mock(){
        Student s = new Student();
        s.setName("小明");
        s.setAge(17);
        s.setClasses(12);
        s.setGrade(5);
//        s.setScore(89);
        return s ;
    }
}
