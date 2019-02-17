package cn.dingan.tsdingan.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dingan.tsdingan.dao.DaOrderDetailMapper;
import cn.dingan.tsdingan.dao.DaOrderMapper;
import cn.dingan.tsdingan.model.DaInsure;
import cn.dingan.tsdingan.model.DaOrder;
import cn.dingan.tsdingan.model.DaOrderDetail;
import cn.dingan.tsdingan.model.DriverSchool;
import cn.dingan.tsdingan.service.OrderService;
import cn.trasen.BootComm.Contants;
import cn.trasen.commons.util.ApplicationUtils;


@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
public class OrderServiceImpl implements OrderService{
    
    
    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    
    @Autowired
    private DaOrderMapper daOrderMapper;
    
    @Autowired
    private DaOrderDetailMapper daOrderDetailMapper;
    
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
    @Transactional(readOnly=false)
    public boolean insertOrderByTry(DaInsure record,DriverSchool school,String transSerialno) {
        try {
            DaOrder order = new DaOrder();
            order.setOrderId(ApplicationUtils.GUID32());
            order.setCreateDate(new Date());
            order.setStatus("0");
            order.setIsDeleted(Contants.IS_DELETED_FALSE);
            order.setDriverSchoolId(school.getDriverSchoolId());
            order.setOrderAmount(record.getBaofei());
            order.setIsDeleted("N");
            order.setCusorderid(transSerialno);
            daOrderMapper.insert(order);
            
            for(DaInsure user : record.getInsuredArray()) {
                DaOrderDetail detail = new DaOrderDetail();
                detail.setOrderDetailId(ApplicationUtils.GUID32());
                detail.setOrderId(order.getOrderId());
                detail.setDriverSchoolId(school.getDriverSchoolId());
                detail.setStatus("0");
                detail.setCreateDate(new Date());
                detail.setIsDeleted("N");
                detail.setInsureId(user.getInsureId());
                detail.setSeqNo(user.getSeqNo());
                daOrderDetailMapper.insert(detail);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        }
        
        
        return true;
    }
}
