package excel.out;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import configuration.CandyObject;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DATE: 16/7/21 17:08 <br>
 * MAIL: lbw@terminus.io <br>
 * AUTHOR: macbook
 */
public class ExcelParser<T> {
    private CandyObject<T> candyObject;

    private XSSFWorkbook wb;
    private XSSFSheet sheet;
    private XSSFRow row;
    public ExcelParser(String filePath){
        try {
            String file = Resources.toString(Resources.getResource(filePath), Charsets.UTF_8);
            this.candyObject = new Yaml().loadAs(file, CandyObject.class);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> parseContent(InputStream is) throws Exception{
        generateWorkBook(is);
        getSheet("Sheet0");
        return parse();
    }

    private XSSFWorkbook generateWorkBook(InputStream is) throws Exception{
        this.wb = new XSSFWorkbook(is);
        return this.wb;
    }
    private Sheet getSheet(String name ){
        sheet = wb.getSheet(name);
        return sheet;
    }
    private XSSFRow getRow(int i) {
        this.row = sheet.getRow(i);
        return row;
    }
    private List<T> parse() throws Exception{
        List<T> list = new ArrayList<>();
        int rowNum = sheet.getLastRowNum();
        for (int i=1;i < rowNum; i++){
            getRow(i);
            int colNum = row.getPhysicalNumberOfCells();
            int colIndex = 0;
            T t = (T)candyObject.createCandy();
            while(colIndex < colNum) {
                candyObject.insertValue(row.getCell(colIndex++).toString());
            }
            list.add(t);
        }
        return list;
    }

    private Object getCellFormatValue(XSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case XSSFCell.CELL_TYPE_NUMERIC:
                case XSSFCell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式

                        //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                        //cellvalue = cell.getDateCellValue().toLocaleString();

                        //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);

                    }
                    // 如果是纯数字
                    else {
                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case XSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                // 默认的Cell值
                default:
                    cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }
}
