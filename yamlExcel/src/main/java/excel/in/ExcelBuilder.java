package excel.in;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import configuration.CandyObject;
import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.yaml.snakeyaml.Yaml;
import org.apache.poi.ss.usermodel.Workbook;


import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * DATE: 16/7/21 11:36 <br>
 * MAIL: lbw@terminus.io <br>
 * AUTHOR: macbook
 */
@Data
public class ExcelBuilder<T> {

    private CandyObject<T> candyObject;
    private Workbook wb = new SXSSFWorkbook();
    public ExcelBuilder(String filePath){
        try {
            String file = Resources.toString(Resources.getResource(filePath), Charsets.UTF_8);
            this.candyObject = new Yaml().loadAs(file, CandyObject.class);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param list
     * @return
     */
    public Workbook build(List<T> list){
        Sheet s = sheetBuild();
        titleBuild(s);
        contentBuild(list,s);
        return wb;
    }

    /**
     * 创建sheet
     * @return
     */
    public Sheet sheetBuild(){
        return wb.createSheet();
    }

    /**
     * 创建标题row
     * @param s
     */
    public void titleBuild(Sheet s){
        Row titleRow = s.createRow(0);
        candyObject.buildTitle(titleRow);
    }

    /**
     * 创建内容
     * @param list 数据列表
     * @param s sheet
     */
    public void contentBuild(List<T> list,Sheet s){
        int rowNum = 1;
        for(T t: list) {
            Row contentRow = s.createRow(rowNum);
            candyObject.putCandy(t);
            int i = 0;
            while(i<candyObject.getCandyFields().size()){
                Cell cell = contentRow.createCell(i++);
                Object v = candyObject.pointOut();
                cell.setCellValue(v == null ? "" : v.toString());
            }
            rowNum++;
        }
    }


}
