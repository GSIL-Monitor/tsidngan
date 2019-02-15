package cn.dingan.tsdingan.service;

import java.util.List;

import cn.dingan.tsdingan.model.DaInsure;
import cn.trasen.BootComm.model.DataSet;
import cn.trasen.core.feature.orm.mybatis.Page;

/**
 * 
* @ClassName: DaInsureService
* @Description: 新增投保人信息
* @author jyq#trasen.cn
* @date 2019年2月12日 下午4:19:13
*
 */
public interface DaInsureService {
    
    /**
     * 
    * @Title: insert
    * @Description: 新增投保人信息
    * @param @param record
    * @param @return    参数
    * @return int    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月12日 下午4:19:05
     */
    int insert(DaInsure record);
    
    /**
     * 修改投保人
     * @param record
     * @return
     */
    public int update(DaInsure record);
    
    /**
     * 根据id删除投保人
     * @param postId
     * @return
     */
    public int deleteById(String postId);
    
    /**
     * 根据id查询投保人
     * @param postId
     * @return
     */
    public DaInsure findById(String postId);
    
    /**
     * 分页查询投保人
     * @param page
     * @param record
     * @return
     */
    public DataSet<DaInsure> getDataSetList(Page page, DaInsure record);
    
    
    
    /**
     * 
    * @Title: importPost
    * @Description: 导入投保人信息
    * @param @param list
    * @param @return    参数
    * @return String    返回类型 记录没有导出进去的数据信息
    * @throws
    * @author jyq#trasen.cn
    * @date 2018年4月26日 上午10:05:09
     */
    public String importDaInsure(List<DaInsure> list);
    
    /**
     * 
    * @Title: checkIdNumber
    * @Description: 校验身份证号
    * @param @param idNumber
    * @param @return    参数
    * @return DaInsure    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月15日 下午5:53:53
     */
    DaInsure  checkIdNumber(String idNumber);
    
}
