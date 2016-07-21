import bean.User;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import configuration.CandyObject;
import excel.in.ExcelBuilder;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DATE: 16/7/20 22:31 <br>
 * MAIL: lbw@terminus.io <br>
 * AUTHOR: macbook
 */
public class YamlTest {
    @Test
    public void yamlTest() throws Exception{
//        String file = Resources.toString(Resources.getResource("auth/user.yaml"), Charsets.UTF_8);
//        CandyObject fields = new Yaml().loadAs(file, CandyObject.class);
        User user = new User();
        user.setId(1L);
        user.setName("lilei");
        user.setStatus(2);
        user.setType(1);

        User user1 = new User();
        user1.setId(2L);
        user1.setName("polly");
        user1.setStatus(2);
        user1.setType(1);
        List<User> list = Lists.newArrayList(user1,user);
        ExcelBuilder excelBuilder = new ExcelBuilder("auth/user.yaml");
        Workbook wb = excelBuilder.build(list);
        OutputStream outputStream = new FileOutputStream(new File("/Users/macbook/Desktop/user.xlsx"));
        wb.write(outputStream);
    }

    @Test
    public void mapTest() throws Exception{
        Map<String,String> map = new HashMap();
        map.put("a","1");
        map.put("b","2");
        Yaml yaml = new Yaml();
        FileWriter fileWriter = new FileWriter(new File("object.yml"));
        yaml.dump(map,fileWriter);
    }

    @Test
    public void mapParseTest() throws Exception{
        String file = Resources.toString(Resources.getResource("auth/object.yml"), Charsets.UTF_8);
        Map map = new Yaml().loadAs(file,Map.class);
        System.out.println(map);
    }

    @Test
    public void propertyTest() throws Exception{
//        Class c = User.class;
//        Field f = c.getDeclaredField("name");
//        Object p = f.
    }
}
