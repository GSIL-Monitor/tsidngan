package cn.dingan.tsdingan.service;

import cn.dingan.tsdingan.model.DataSet;
import cn.dingan.tsdingan.model.HrEmployee;
import cn.dingan.tsdingan.model.Page;

/**
 * 员工基础信息
 * 
 * @ClassName: HrEmployeeService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author jyq#trasen.cn
 * @date 2018年4月11日 下午2:19:24
 *
 */
public interface HrEmployeeService {
    
    public DataSet<HrEmployee> getDataSetList(Page page, HrEmployee record);
}
