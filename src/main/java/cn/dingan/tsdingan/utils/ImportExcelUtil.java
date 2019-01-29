package cn.dingan.tsdingan.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @ClassName: ImportExcelUtil
 * @Description: excel模板导入工具类
 * @author jyq#trasen.cn
 * @date 2018年4月23日 上午9:54:36
 *
 */
public class ImportExcelUtil {

    /**
     * 
     * @Title: getExcelDatas
     * @Description: 读取导入的Excel文件 转换成数据集合
     * @param @param
     *            file
     * @param @param
     *            clazz
     * @param @return
     *            参数
     * @return List<?> 返回类型
     * @throws @author
     *             jyq#trasen.cn
     * @date 2018年4月23日 上午9:56:37
     */
    public static List<?> getExcelDatas(@RequestParam("file") MultipartFile file, Class<?> clazz) {
        try {
            InputStream in = file.getInputStream();
            ImportParams params = new ImportParams();
            List<?> datas = ExcelImportUtil.importExcel(in, clazz, params);
            return datas;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Title: importExcel
     * @Description: 导入excel
     * @param file
     * @return
     * @throws IOException
     * @date 2018年5月3日 下午3:46:39
     * @author quedd#trasen.cn
     */
    public static List<List<Object>> importExcel(File file) throws IOException {
        String fileName = file.getName();
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
        if ("xls".equals(extension)) {
            return read2003Excel(file);
        } else if ("xlsx".equals(extension)) {
            return read2007Excel(file);
        } else {
            throw new IOException("不支持的文件类型");
        }
    }

    /**
     * 读取 office 2003 excel
     * 
     * @throws IOException
     * @throws FileNotFoundException
     */
    @SuppressWarnings("deprecation")
    private static List<List<Object>> read2003Excel(File file) throws IOException {
        List<List<Object>> list = new LinkedList<List<Object>>();
        HSSFWorkbook hwb = null;
        try {
            hwb = new HSSFWorkbook(new FileInputStream(file));
            HSSFSheet sheet = hwb.getSheetAt(0);
            Object value = null;
            HSSFRow row = null;
            HSSFCell cell = null;
            for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                List<Object> linked = new LinkedList<Object>();
                for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    if (cell == null) {
                        continue;
                    }
                    DecimalFormat df = new DecimalFormat("0.00");// 格式化 number String 字符
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
                    DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
                    switch (cell.getCellTypeEnum()) {
                    case STRING:
                        value = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                            value = df.format(cell.getNumericCellValue());
                        } else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                            value = nf.format(cell.getNumericCellValue());
                        } else {
                            value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                        }
                        break;
                    case BOOLEAN:
                        value = cell.getBooleanCellValue();
                        break;
                    case BLANK:
                        value = "";
                        break;
                    default:
                        value = cell.toString();
                    }
                    if (value == null || "".equals(value)) {
                        continue;
                    }
                    linked.add(value);
                }
                list.add(linked);
            }
        } finally {
            if (hwb != null) {
                hwb.close();
            }
        }

        return list;
    }

    /**
     * 读取Office 2007 excel
     */
    @SuppressWarnings("deprecation")
    private static List<List<Object>> read2007Excel(File file) throws IOException {
        List<List<Object>> list = new LinkedList<List<Object>>();
        XSSFWorkbook xwb = null;
        try {
            // 构造 XSSFWorkbook 对象，strPath 传入文件路径
            xwb = new XSSFWorkbook(new FileInputStream(file));
            // 读取第一章表格内容
            XSSFSheet sheet = xwb.getSheetAt(0);
            Object value = null;
            XSSFRow row = null;
            Cell cell = null;
            for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                List<Object> linked = new LinkedList<Object>();
                for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    if (cell == null) {
                        continue;
                    }
                    DecimalFormat df = new DecimalFormat("0.00");// 格式化 number String 字符
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
                    DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
                    switch (cell.getCellTypeEnum()) {
                    case STRING:
                        value = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                            value = df.format(cell.getNumericCellValue());
                        } else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                            value = nf.format(cell.getNumericCellValue());
                        } else {
                            value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                        }
                        break;
                    case BOOLEAN:
                        value = cell.getBooleanCellValue();
                        break;
                    case BLANK:
                        value = "";
                        break;
                    default:
                        value = cell.toString();
                    }
                    if (value == null || "".equals(value)) {
                        continue;
                    }
                    linked.add(value);
                }
                list.add(linked);
            }
        } finally {
            if (xwb != null) {
                xwb.close();
            }
        }

        return list;
    }

    public static void main(String[] args) throws IOException {
        // excel 导入数据demo
        File file = new File("D://abc.xlsx");
        List<List<Object>> dataList = importExcel(file);
        for (int i = 1; i < dataList.size(); i++) {
            for (int j = 1; j < dataList.get(i).size(); j++) {
                System.out.println(dataList.get(i).get(j));
            }
            System.out.println("------------------");
        }
    }
}
