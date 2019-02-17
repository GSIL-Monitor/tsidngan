package cn.dingan.tsdingan.service;

import cn.dingan.tsdingan.model.DaInsure;
import cn.dingan.tsdingan.model.DriverSchool;

public interface OrderService {
    
    /**
     * 
    * @Title: insertOrderByTry
    * @Description: 根据试算接口添加订单
    * @param @param record
    * @param @return    参数
    * @return boolean    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月18日 上午10:40:17
     */
    boolean insertOrderByTry(DaInsure record,DriverSchool school,String transSerialno);
}
