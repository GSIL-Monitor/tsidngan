package cn.dingan.tsdingan.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dingan.tsdingan.dao.DaInsureMapper;
import cn.dingan.tsdingan.model.DaInsure;
import cn.dingan.tsdingan.model.DriverSchool;
import cn.dingan.tsdingan.service.DaInsureService;
import cn.dingan.tsdingan.utils.UserUtil;
import cn.trasen.BootComm.Contants;
import cn.trasen.BootComm.model.DataSet;
import cn.trasen.commons.util.ApplicationUtils;
import cn.trasen.core.feature.orm.mybatis.Page;
import tk.mybatis.mapper.entity.Example;


/**
 * 
* @ClassName: DaInsureServiceImpl
* @Description: 投保人
* @author jyq#trasen.cn
* @date 2019年2月12日 下午4:18:17
*
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
public class DaInsureServiceImpl implements DaInsureService {
    
    
    @Autowired
    private DaInsureMapper  daInsureMapper;
    
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
    @Transactional(readOnly=false)
    public int insert(DaInsure record) {
    	DriverSchool school = UserUtil.getUser();
    	if(null==school) {
    		return 0;
    	}
    	record.setDriverSchoolId(school.getDriverSchoolId());
        record.setInsureId(ApplicationUtils.GUID32());
        record.setCreateDate(new Date());
        record.setIsDeleted(Contants.IS_DELETED_FALSE);
        record.setIsInsure("1");
        List<DaInsure> list = checkIdNumber(record.getIdcard());
        if(null!=list && list.size()>0) {
        	return 2;
        }
        
        return daInsureMapper.insert(record);
    }
    
    /**
     * 修改投保人
     * @param record
     * @return
     */
    @Transactional(readOnly = false)
    public int update(DaInsure record) {
        Assert.hasText(record.getInsureId(), "insureId must be not null.");
        
        return daInsureMapper.updateByPrimaryKeySelective(record);
    }
    
    /**
     * 根据id删除投保人
     * @param postId
     * @return
     */
    @Transactional(readOnly = false)
    public int deleteById(String insureId) {
        DaInsure record = daInsureMapper.selectByPrimaryKey(insureId);
        if (null != record) {
            record.setIsDeleted(Contants.IS_DELETED_TURE);
        }
        return daInsureMapper.updateByPrimaryKeySelective(record); 
    }
    
    /**
     * 根据id查询投保人
     * @param postId
     * @return
     */
    public DaInsure findById(String insureId) {
        Assert.hasText(insureId, "insureId must be not null.");
        DaInsure record = new DaInsure();
        record.setInsureId(insureId);
        return daInsureMapper.selectByPrimaryKey(record);
    }
    
    /**
     * 分页查询投保人
     * @param page
     * @param record
     * @return
     */
    public DataSet<DaInsure> getDataSetList(Page page, DaInsure record) {
    	DriverSchool school = UserUtil.getUser();
    	if(null==school) {
    		return null;
    	}
    	
        Example example = new Example(DaInsure.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(Contants.IS_DELETED_FIELD, Contants.IS_DELETED_FALSE);
        if(StringUtils.isBlank(record.getType())) {
        	record.setType("0");
        }
        example.and().andEqualTo("driverSchoolId",school.getDriverSchoolId());
        example.and().andEqualTo("type",record.getType());
        example.and().andEqualTo("isInsure","1");
        
        List<DaInsure> records = daInsureMapper.selectByExampleAndRowBounds(example, page);
         
        return new DataSet<>(page.getPageNo(), page.getPageSize(), page.getTotalPages(),
                page.getTotalCount(), records);
    }
    
    
    
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
    public String importDaInsure(List<DaInsure> list) {
        
        return "";
    }
    
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
    public List<DaInsure> checkIdNumber(String idcard) {
       Example example = new Example(DaInsure.class);
       example.createCriteria().andEqualTo("idcard",idcard).andEqualTo("isDeleted",Contants.IS_DELETED_FALSE)
       .andEqualTo("isInsure","1");
       List<DaInsure> list = daInsureMapper.selectByExample(example);
       
       return list;
    }
    
    /**
     * 
    * @Title: getFinishInsureList
    * @Description: 已投保查询
    * @param @param page
    * @param @param record
    * @param @return    参数
    * @return DataSet<DaInsure>    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月17日 上午10:10:09
     */
    public DataSet<DaInsure> getFinishInsureList(Page page, DaInsure record){
        DriverSchool school = UserUtil.getUser();
        if(null==school) {
            return null;
        }
        
        Example example = new Example(DaInsure.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(Contants.IS_DELETED_FIELD, Contants.IS_DELETED_FALSE);
        if(StringUtils.isBlank(record.getType())) {
            record.setType("0");
        }
        
        if(StringUtils.isNotBlank(record.getName())) {
            example.and().andLike("name", "%"+record.getName()+"%");
        }
        
        if(StringUtils.isNotBlank(record.getIdcard())) {
            example.and().andLike("idcard", "%"+record.getIdcard()+"%");
        }
        
        example.and().andEqualTo("driverSchoolId",school.getDriverSchoolId());
        example.and().andEqualTo("type",record.getType());
        example.and().andEqualTo("isInsure","2");
        
        List<DaInsure> records = daInsureMapper.selectByExampleAndRowBounds(example, page);
         
        return new DataSet<>(page.getPageNo(), page.getPageSize(), page.getTotalPages(),
                page.getTotalCount(), records);
    }
}
