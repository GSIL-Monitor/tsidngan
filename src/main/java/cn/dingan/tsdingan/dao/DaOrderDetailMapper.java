package cn.dingan.tsdingan.dao;

import java.util.List;

import cn.dingan.tsdingan.model.DaOrderDetail;
import cn.trasen.core.feature.orm.mybatis.Page;
import tk.mybatis.mapper.common.Mapper;

public interface DaOrderDetailMapper extends Mapper<DaOrderDetail> {
    
    
    /**
     * 
    * @Title: getOrderDetailList
    * @Description: 查询报单明细
    * @param @param page
    * @param @param record
    * @param @return    参数
    * @return List<DaOrderDetail>    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月18日 下午4:23:28
     */
    public List<DaOrderDetail> getOrderDetailList(Page page,DaOrderDetail record);
}