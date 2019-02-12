package cn.dingan.tsdingan.service;

import java.util.List;

import cn.dingan.tsdingan.model.DaInsure;

/**
 * 
* @ClassName: InsureService
* @Description: 投保接口
* @author jyq#trasen.cn
* @date 2019年2月12日 下午5:12:43
*
 */
public interface InsureService {
    
    /**
     * 
    * @Title: insureTry
    * @Description: 投保试算接口
    * @param @param obj
    * @param @return    参数
    * @return Object    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月12日 下午5:13:20
     */
    String insureTry(List<DaInsure> list);
}
