package cn.dingan.tsdingan.service;

import java.util.List;

import cn.dingan.tsdingan.model.DaInsure;
import cn.dingan.tsdingan.model.DaOrder;
import cn.dingan.tsdingan.model.DaOrderDetail;
import cn.trasen.core.entity.Result;

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
	Result  insureTry(DaInsure record);
    
    /**
     * 
    * @Title: insureTry
    * @Description: 测试试算
    * @param @return    参数
    * @return String    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月18日 上午11:40:18
     */
    String insureTry();
    
    /**
     * 
    * @Title: finishOrderByPay
    * @Description: 支付完成回调保险公司下单
    * @param @param order
    * @param @return    参数
    * @return String    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月18日 上午11:40:38
     */
    String finishOrderByPay(DaOrder order,List<DaOrderDetail> detailList);
}
