package cn.dingan.tsdingan.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;



/**
 * 
 * @ClassName: ExportUtil
 * @Description: 导出模板工具类
 * @author jyq#trasen.cn
 * @date 2018年4月21日 下午2:52:02
 *
 */
public class ExportUtil {

    private static final String CONTENT_TYPE = "application/vnd.ms-excel";
    
    @GetMapping("/post/export")
    public static void export(HttpServletRequest request, HttpServletResponse response,
            List<?> list, String name, String templateUrl) throws UnsupportedEncodingException {

        TemplateExportParams params = new TemplateExportParams(convertTemplatePath(templateUrl));
        // map对应的是模板参数
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置集合数据 其中list为在模板中定义的 fe 后面的值
        map.put("list", list);
        ServletOutputStream out = null;
        try {
            // 采用实体类加入注解的方式用于导出集合数据
            Workbook workbook = ExcelExportUtil.exportExcel(params, map);
            if (isIE(request)) {
                name = java.net.URLEncoder.encode(name, "UTF8");
            } else {
                name = new String(name.getBytes("UTF-8"), "ISO8859-1");
            }
            response.setContentType(CONTENT_TYPE);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + name);
            out = response.getOutputStream();
            workbook.write(out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param request
     * @return
     * @author yanhuajun
     * @date 2017年2月7日
     */
    private static boolean isIE(HttpServletRequest request) {
        String userAgent = request.getHeader("USER-AGENT").toLowerCase();
        if (userAgent.contains("MSIE 10.0")) {// Internet Explorer 10
            return true;
        } else if (userAgent.contains("MSIE 9.0")) {// Internet Explorer 9
            return true;
        } else if (userAgent.contains("MSIE 8.0")) {// Internet Explorer 8
            return true;
        } else if (userAgent.contains("MSIE 7.0")) {// Internet Explorer 7
            return true;
        } else if (userAgent.contains("MSIE 6.0")) {// Internet Explorer 6
            return true;
        }
        return false;
    }
    
    
    
    public static String convertTemplatePath(String path) {  
        Resource resource = new ClassPathResource(path);  
        FileOutputStream fileOutputStream = null;  
        // 将模版文件写入到系统临时目录  
        String folder = System.getProperty("java.io.tmpdir");  
        System.out.println(System.getProperty("java.io.tmpdir"));
        File tempFile = new File(folder + File.separator + path);  
        // System.out.println("文件路径：" + tempFile.getPath());  
        // 文件存在时 不再写入  
        if (tempFile.exists()) {  
            tempFile.delete();
        }  
        File parentFile = tempFile.getParentFile();  
        // 判断父文件夹是否存在  
        if (!parentFile.exists()) {  
            parentFile.mkdirs();  
        }  
        try {  
            BufferedInputStream bufferedInputStream = new BufferedInputStream(resource.getInputStream());  
            fileOutputStream = new FileOutputStream(tempFile);  
            byte[] buffer = new byte[10240];  
            int len = 0;  
            while ((len = bufferedInputStream.read(buffer)) != -1) {  
                fileOutputStream.write(buffer, 0, len);  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
           IOUtils.closeQuietly(fileOutputStream);
        }
        String filepath = tempFile.getPath();
        return filepath;
    }  
    
    
    
  
}
