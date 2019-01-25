package cn.dingan.tsdingan.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;




public class HttpUtil {
    private static final Logger logger = Logger.getLogger(HttpUtil.class);
    public static final String ERROR = "error";



    public static String connectURLGET(String address, String xToken) {
        String rec_string = "";
        URL url = null;
        HttpURLConnection urlConn = null;
        try {
            url = new URL(address);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setConnectTimeout(1000 * 60 * 5);
            urlConn.setReadTimeout(1000 * 60 * 5);
            urlConn.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            urlConn.setRequestProperty("X-TOKEN", xToken);
            urlConn.connect();
            BufferedReader rd =
                    new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
            StringBuffer sb = new StringBuffer();
            int ch;
            while ((ch = rd.read()) > -1) {
                sb.append((char) ch);
            }
            rec_string = sb.toString().trim();
            rd.close();
        } catch (Exception e) {
            logger.info("http请求连接异常", e);
        } finally {
            if (urlConn != null) {
                urlConn.disconnect();
            }
        }
        return rec_string;
    }

    
    public static String getJSESSIONID(String address, String xToken) {
        try {
            HttpClient client = HttpClients.createDefault();            
            HttpGet get = new HttpGet(address);
            HttpResponse response = client.execute(get);
            
            String host = get.getURI().getHost();
            CookieStore cookieStore  = setCookieStore(response, host);
            
            String jseSessionId = "";
            if(cookieStore!=null) {
                jseSessionId   = cookieStore.getCookies().get(0).getValue();
            }
            return jseSessionId;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    
    /**
     * post请求(用于key-value格式的参数)
     * 
     * @param url
     * @param params
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, String> doPost(String url, Map<String, String> params) {
        Map<String, String> map = new HashMap<String, String>();
        BufferedReader in = null;
        try {
            // 定义HttpClient
            HttpClient client = HttpClients.createDefault();
            // 实例化HTTP方法
            HttpPost httpPost = new HttpPost();
            httpPost.setURI(new URI(url));

            // 设置参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));

            }
            
            String host = httpPost.getURI().getHost();
            map.put("host", host);
            
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            HttpResponse response = client.execute(httpPost);

            int code = response.getStatusLine().getStatusCode();
            Header[] header = response.getAllHeaders();
            for (int i = 0; i < header.length; i++) {
                String name = header[i].getName();
                if ("sessionId".equals(name)) {
                    String sessionId = header[i].getValue();
                    map.put("sessionId", sessionId);
                }
            }
            if (code == 200) { // 请求成功
                in = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent(), "utf-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }

                in.close();
                map.put("result", sb.toString());
                return map;
            } else { //
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }


    public static CookieStore setCookieStore(HttpResponse httpResponse, String host) {
        CookieStore cookieStore = new BasicCookieStore();
        // JSESSIONID
        if (null == httpResponse.getFirstHeader("Set-Cookie")) {
            cookieStore = null;
        } else {
            String setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
            String JSESSIONID = setCookie.substring("JSESSIONID=".length(), setCookie.indexOf(";"));
            // 新建一个Cookie
            BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", JSESSIONID);
            cookie.setVersion(0);
            cookie.setDomain(host);
            cookie.setPath("/");
            cookieStore.addCookie(cookie);
        }
        return cookieStore;
    }
    
    public static CookieStore getCookieStore(String jesSessionId,String token,String host) {
        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", jesSessionId);
        cookie.setVersion(0);
        cookie.setDomain(host);
        cookie.setPath("/");
        
        BasicClientCookie cookie2 = new BasicClientCookie("THPMSCookie", token);
        cookie2.setVersion(0);
        cookie2.setDomain(host);
        cookie2.setPath("/");
        
        cookieStore.addCookie(cookie);
        cookieStore.addCookie(cookie2);
        return cookieStore;
    }

    
    public static String doPost(String url, String params) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            } else {
                logger.error("请求返回:" + state + "(" + url + ")");
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        
       
    }

}
