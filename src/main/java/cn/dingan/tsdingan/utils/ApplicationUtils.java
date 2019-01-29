package cn.dingan.tsdingan.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;


/**
 * ApplicationUtils : 程序工具类，提供大量的便捷方法
 *
 * @author g.jrlee@gmail.com
 * @since 2016-10-28 22:31
 */
public class ApplicationUtils {

    public static String usercode; //登录帐户
    public static String username; //登录名
    public static String corpcode; //机构代码
    public static String agentCode; //代理人代码
    public static String deptcode; //部门代码
    public static String WebRootName; //取当前项目名称
    public static String ip;            //当前登录的IP地址
    public static String machName;      //机器名称
    public static String sessionid;     //登录会话id;
    public static String userId;        //当前用户id;
    /**
     * 产生一个36个字符的UUID
     *
     * @return UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    /**
     *产生一个32位符的UUID; 
     * @return
     */
    public static String GUID32() {
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }

    /**
     * md5加密
     *
     * @param value 要加密的值
     * @return md5加密后的值
     */
    public static String md5Hex(String value) {
        return DigestUtils.md5Hex(value);
    }

    /**
     * sha1加密
     *
     * @param value 要加密的值
     * @return sha1加密后的值
     */
    public static String sha1Hex(String value) {
        return DigestUtils.sha1Hex(value);
    }

    /**
     * sha256加密
     *
     * @param value 要加密的值
     * @return sha256加密后的值
     */
    public static String sha256Hex(String value) {
        return DigestUtils.sha256Hex(value);
    }

    /**
     * 取配置文件里的值
     */
    public static String getConfig(String key) {
        ApplicationUtils loadProp = new ApplicationUtils();
        InputStream in = loadProp.getClass().getResourceAsStream("/application.properties");
        Properties prop = new Properties();
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }

    /**
     * 
    * @Title: socketCmd 
    * @Description: 执行Socket命令方法
    * @param @param ip
    * @param @param port
    * @param @param cmd
    * @param @return
    * @param @throws Exception    设定文件 
    * @return String    返回类型 
    * @throws
     */
    public static String socketCmd(String ip, int port, String cmd) throws Exception {
        Socket socket = new Socket(ip, port);
        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();
        out.write(cmd.getBytes());
        out.flush();
        //socket.shutdownOutput();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int read = 0;
        while ((read = in.read()) != -1) {
            baos.write(read);
        }
        String data = new String(baos.toByteArray());
        socket.close();
        return data;
    }

    public static String DateFormat(String formatStr, long date) {
        return new SimpleDateFormat(formatStr).format(new Date(date));
    }

    public static String DateFormat(String formatStr, Date date) {
        return new SimpleDateFormat(formatStr).format(date);
    }
    /**
     * 获取数据库类型
    * @Title: getDBType
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @param @return    参数
    * @return String    返回类型
    * @throws
     */
    public static String getDBType(){
        String dbDriver = getConfig("jdbc.driver");
        if (dbDriver.toLowerCase().contains("mysql")){
            return "mysql";
        }else if(dbDriver.toLowerCase().contains("oracle")){
            return "oracle";
        }else if (dbDriver.toLowerCase().contains("db2")){
            return "db2";
        }else if (dbDriver.toLowerCase().contains("sqlserver")){
            return "sqlserver";
        }else{
            return "unknow dbtype";
        }
    }
    /**
     * 单元测试
     * @param args
     */
    public static void main(String[] args) throws Exception {
        System.out.println(getConfig("jdbc.driver"));
    }
}
