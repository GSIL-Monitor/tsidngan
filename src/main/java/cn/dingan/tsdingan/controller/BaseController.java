package cn.dingan.tsdingan.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * Base controller.
 *
 * @author penghongbao
 * @date 2015年5月18日 上午2:04:09
 */
public class BaseController {
    protected static final String ERROR_UNAUTH = "/error/401";
    protected static final String ERROR_FORBIDDEN = "/error/403";
    protected static final String ERROR_NOT_FOUND = "/error/404";
    protected static final String ERROR_SYSTEM = "/error/500";
    protected static final String LOGIN_USER = "loginUser";

    public static HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {
        }
        return session;
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs =
            (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }

    
 

    /**
     * 取当前服务器路径
     *
     * @param request
     * @return
     */
    public String getRootPath(HttpServletRequest request) {
        String port = "";
        if (request.getServerPort() != 80) {
            port = ":" + request.getServerPort();
        }
        String basePath =
            request.getScheme() + "://" + request.getServerName() + port + request.getContextPath();
        return basePath;
    }

}
