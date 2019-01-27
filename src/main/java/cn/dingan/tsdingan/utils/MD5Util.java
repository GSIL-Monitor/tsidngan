package cn.dingan.tsdingan.utils;

import java.security.MessageDigest;

public class MD5Util {
	  private static String salt="qwertyuiopasdfghjklzxcvbnm";//盐值
    /*** 
     * MD5加密 生成32位md5码
     * @param 待加密字符串
     * @return 返回32位md5码
     */
    public static String md5Encode(String inStr) {
        try {
        	MessageDigest md5 = MessageDigest.getInstance("MD5");
        
	        byte[] byteArray = inStr.getBytes("UTF-8");
	        byte[] md5Bytes = md5.digest(byteArray);
	        StringBuffer hexValue = new StringBuffer();
	        for (int i = 0; i < md5Bytes.length; i++) {
	            int val = ((int) md5Bytes[i]) & 0xff;
	            if (val < 16) {
	                hexValue.append("0");
	            }
	            hexValue.append(Integer.toHexString(val));
	        }
	        return hexValue.toString();
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        return null;
    }
    
    public static String md5EncodeSalt(String inStr) {
    	inStr=addSalt(inStr);
        return md5Encode(inStr);
    }
    
    /**
     * 系统密码md5方案md5(salt+md5(password))
     * @param password
     * @return
     */
    public static String md5Password(String md5password){
    	return md5EncodeSalt(md5password);
    }
    /**
     * 在加密对象后加盐
     * @param object
     * @return
     */
    private static String addSalt(String object){
        if(object == null){
            object = "";
        }
        if(salt == null || "".equals(salt)){
            return object;
        } else {
            return object + "{"+ salt.toString() +"}";
        }
    }

  
    /**
     * 测试主函数
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        String str = new String("sunniancun");
    
        System.out.println("MD5后：" + md5Encode(str));
        
    }
    /**
     * 
如何验证密码呢？一个可行的方法是，客户端提交 md5(password) 密码（如上所述，此方法只是简单保护了密码，是可能被查表获取密码的）。服务端数据库通过 md5(salt+md5(password)) 的规则存储密码，该 salt 仅存储在服务端，且在每次存储密码时都随机生成。这样即使被拖库，制作字典的成本也非常高。
密码被 md5() 提交到服务端之后，可通过 md5(salt + form['password']) 与数据库密码比对。此方法可以在避免明文存储密码的前提下，实现密码加密提交与验证。
这里还有防止 replay 攻击（请求被重新发出一次即可能通过验证）的问题，由服务端颁发并验证一个带有时间戳的可信 token （或一次性的）即可。

服务器存md5(password)，然后每次验证时，生成一个随机串s，发送给客户端，让客户端使用md5(s, md5(password))进行混淆，然后发回给服务器。服务器使用存储的用户密码使用同样的方法进行混淆。如果一致，则认证成功。
     */
}