import bean.User;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import configuration.CandyObject;
import excel.in.ExcelBuilder;
import excel.out.ExcelParser;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Date;
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
        user1.setMobile("18012345678");
        user1.setCreatedAt(new Date());
        List<User> list = Lists.newArrayList(user1,user);


        /**************/
        ExcelBuilder excelBuilder = new ExcelBuilder("auth/user.yaml");
        Sheet s = excelBuilder.sheetBuild();
        excelBuilder.titleBuild(s);
        excelBuilder.contentBuild(list,s);
        Workbook wb = excelBuilder.getWb();
        /**************/

//        Workbook wb = excelBuilder.build(list);
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
    public void setT() throws Exception{
        String file = Resources.toString(Resources.getResource("auth/user.yaml"), Charsets.UTF_8);
        CandyObject candyObject = new Yaml().loadAs(file, CandyObject.class);
        User user = (User)candyObject.createCandy();

        candyObject.insertValue(123L);
        candyObject.insertValue("xiaoming");
        candyObject.insertValue("普通");
        candyObject.insertValue("冻结");
        candyObject.insertValue("18626410208");
        candyObject.insertValue("20160701 080000");
        System.out.println(user);

    }

    @Test
    public void importTest() throws Exception{
//        String file = Resources.toString(Resources.getResource("auth/user.yaml"), Charsets.UTF_8);
//        CandyObject fields = new Yaml().loadAs(file, CandyObject.class);
        ExcelParser excelParser = new ExcelParser("auth/user.yaml");
        List<User> list = excelParser.parseContent(new FileInputStream(new File("/Users/macbook/Desktop/user.xlsx")));
        System.out.println(list);
    }
}
