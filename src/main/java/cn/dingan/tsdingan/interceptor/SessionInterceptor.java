package cn.dingan.tsdingan.interceptor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;

import cn.dingan.tsdingan.model.SysUser;
import cn.dingan.tsdingan.utils.UserUtil;



/**
 * 
 * ClassName: SessionInterceptor
 * 
 * @Description: 拦截器
 * @author penghb
 * @date 2016-4-26
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {
    
//    @Resource
//    RedisService redisService;
//    
	final Logger log=Logger.getLogger(SessionInterceptor.class);
    /**
     * 忽略的页面
     */
    private List<String> ignoreList = new ArrayList<String>();

    /**
     * 需要拦截的路径
     */
    private List<String> interceptorUrl = null;

    /**
     * 用户登录界面
     */
    private String uloginPage = null;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                    Object handler) throws Exception {
        // （1）忽略不需要拦截的请求
    	
        
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute(UserUtil.LOGIN_USER);
        if (user == null) {
            response.setCharacterEncoding("UTF-8");  
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = null ;
            JSONObject res = new JSONObject();
            res.put("success","false");
            res.put("msg","未登陆");
            out = response.getWriter();
            out.append(res.toString());
            return false;
        } else {
            String current = (String)session.getAttribute(UserUtil.CURRENT);
            UserUtil.setUser(user);
            UserUtil.setCurrent(current);
            return true;
        }
    }

    public List<String> getIgnoreList() {
        return ignoreList;
    }

    public void setIgnoreList(List<String> ignoreList) {
        this.ignoreList = ignoreList;
    }

    public List<String> getInterceptorUrl() {
        return interceptorUrl;
    }

    public void setInterceptorUrl(List<String> interceptorUrl) {
        this.interceptorUrl = interceptorUrl;
    }

    public String getUloginPage() {
        return uloginPage;
    }

    public void setUloginPage(String uloginPage) {
        this.uloginPage = uloginPage;
    }
    //TODO 临时添加的，主要是打印下当前controller对应的jsp页面的路径
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(modelAndView!=null&&modelAndView.getViewName()!=null){
//			if(log.isDebugEnabled()){
			if(handler instanceof HandlerMethod){
				HandlerMethod method = (HandlerMethod)handler;
				log.error("当前controller("+method.getBean().getClass().getName()+"),对应的方法为："+method.getMethod().getName()+"--->对应的页面地址为："+modelAndView.getViewName());
			}
				
//			}
		}
		super.postHandle(request, response, handler, modelAndView);
	}
	
//	public boolean checkToken(HttpServletRequest request){
//        Map<String, String> map = getHeadersInfo(request);
//        String token = map.get("token");
//        Long userId = Long.valueOf(map.get("userid"));
//        try {
//            //假如用户冻结这里验证不通过
//            SysUser user = UserUtil.getUser();
//            if(user==null)
//                return false;
//
//            Long tokenUserId = TokenUtils.getAppUID(token);
//            if(userId.equals(tokenUserId)){
//                return true;
//            }else{
//                return false;
//            }
//        }catch (Exception e){
//            return false;
//        }
//    }
//	
//	private Map<String, String> getHeadersInfo(HttpServletRequest request) {
//        Map<String, String> map = new HashMap<String, String>();
//        Enumeration headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String key = (String) headerNames.nextElement();
//            String value = request.getHeader(key);
//            map.put(key, value);
//        }
//        return map;
//    }
}
