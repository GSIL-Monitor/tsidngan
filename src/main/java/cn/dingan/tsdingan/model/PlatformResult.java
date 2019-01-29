package cn.dingan.tsdingan.model;

import java.io.Serializable;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: PlatformResult
 * @Description: 統一結果列表返回MODEL
 * @author quedd#trasen.cn
 * @date 2018年7月25日 下午2:40:28
 *
 */
@Setter
@Getter
@ToString
public class PlatformResult<T> implements Serializable {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 8968766969795754248L;

    /**
     * 信息
     */
    private String message;

    /**
     * 状态码
     */

    private int statusCode;

    /**
     * 是否成功
     */
    private boolean success;

    private T object;

    public static <T> PlatformResult<T> success() {
        PlatformResult<T> result = new PlatformResult<>();
        result.setSuccess(true);
        result.setStatusCode(200);
        return result;
    }
    
    public static <T> PlatformResult<T> success(T data) {
        PlatformResult<T> result = new PlatformResult<>();
        result.setSuccess(true);
        result.setStatusCode(200);
        result.setObject(data);
        return result;
    }
    
    public static <T> PlatformResult<T> failure() {
        PlatformResult<T> result = new PlatformResult<>();
        result.setSuccess(false);
        return result;
    }
    
    public static <T> PlatformResult<T> failure(String message) {
        PlatformResult<T> result = new PlatformResult<>();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }
    
    public static <T> PlatformResult<T> failure(String message, T data) {
        PlatformResult<T> result = new PlatformResult<>();
        result.setSuccess(false);
        result.setMessage(message);
        result.setObject(data);
        return result;
    }
    

}
