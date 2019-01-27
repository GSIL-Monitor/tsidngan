package cn.dingan.tsdingan.utils;

import cn.dingan.tsdingan.model.SysUser;


public class UserUtil {

    public static final String LOGIN_USER = "loginUser";
    public static final String RESOURCES = "resources";
    
    public static final String CURRENT = "current";
    
    
    private static final ThreadLocal<String> current = new ThreadLocal<>();
    private static final ThreadLocal<SysUser> user = new ThreadLocal<>();
    
    
    
    public static void setUser(SysUser u) {
        user.set(u);
    }
    
    public static SysUser getUser() {
        return user.get();
    }
    
    
    
    public static String getCurrent() {
        return current.get();
    }
    public static void setCurrent(String s) {
        current.set(s);
    }
}

