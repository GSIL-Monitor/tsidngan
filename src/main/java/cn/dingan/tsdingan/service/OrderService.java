package cn.dingan.tsdingan.service;

import java.util.List;

import cn.dingan.tsdingan.model.DaInsure;
import cn.dingan.tsdingan.model.DaOrderDetail;
import cn.dingan.tsdingan.model.DriverSchool;
import cn.trasen.core.feature.orm.mybatis.Page;

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
    
    /**
     * 
    * @Title: getOrderList
    * @Description: 查询报单明细
    * @param @param page
    * @param @param record
    * @param @return    参数
    * @return List<DaOrderDetail>    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月18日 下午4:21:00
     */
    List<DaOrderDetail> getOrderDetailList(Page page,DaOrderDetail record);
}
