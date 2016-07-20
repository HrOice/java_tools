import com.hroice.excel.enumMap.FieldTranslate;
import com.hroice.excel.enumMap.SingleEnumMap;
import com.hroice.excel.enums.ArticleType;
import com.hroice.excel.enums.UserType;
import org.junit.Test;

/**
 * DATE: 16/7/20 15:49 <br>
 * MAIL: lbw@terminus.io <br>
 * AUTHOR: macbook
 */
public class ExcelTest {
    @Test
    public void test() throws Exception{
        FieldTranslate fieldTranslate = new FieldTranslate();
        fieldTranslate.addTranslate("type",new SingleEnumMap<ArticleType>(ArticleType.class) );
        fieldTranslate.addTranslate("userType",new SingleEnumMap<UserType>(UserType.class));
        System.out.println(fieldTranslate.getTranslateMap());
        System.out.println(fieldTranslate.getTranslateMap().get("type").getSingleEnumMap());
        System.out.println(fieldTranslate.getTranslateMap().get("userType").getSingleEnumMap());
    }
}
