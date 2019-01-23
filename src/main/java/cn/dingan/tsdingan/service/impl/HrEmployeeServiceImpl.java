package cn.dingan.tsdingan.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dingan.tsdingan.dao.HrEmployeeMapper;
import cn.dingan.tsdingan.model.DataSet;
import cn.dingan.tsdingan.model.HrEmployee;
import cn.dingan.tsdingan.model.Page;
import cn.dingan.tsdingan.service.HrEmployeeService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 员工基础信息
 * 
 * @author jiangyq
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
public class HrEmployeeServiceImpl implements HrEmployeeService {


	 
	@Autowired
	private HrEmployeeMapper hrEmployeeMapper;

	public DataSet<HrEmployee> getDataSetList(Page page, HrEmployee record) {
        Example example = new Example(HrEmployee.class);
        Criteria c = example.createCriteria();
        page.setPageSize(1);
        
        if (StringUtils.isNotBlank(record.getPostName())) {
            c.andEqualTo("postId", record.getPostName());
        }

        // 性别查询
        if (StringUtils.isNotBlank(record.getSex())) {
            c.andEqualTo("sex", record.getSex());
        }
        // 是否重复入职
        if (null != record.getIsDuplicateEntry()) {
            c.andEqualTo("isDuplicateEntry", record.getIsDuplicateEntry());
        }
        
        // 离职状态查询
        if (StringUtils.isNotBlank(record.getStatus())) {
            c.andEqualTo("status", record.getStatus());
        }
        // 根据员工工号查询
        if (StringUtils.isNotBlank(record.getCode())) {
            c.andEqualTo("code", record.getCode());
        }
        
        

        List<HrEmployee> records = hrEmployeeMapper.selectByExampleAndRowBounds(example, page);

        return new DataSet<>(page.getPageNo(), page.getPageSize(), page.getTotalPages(), page.getTotalCount(),
                records);
    }

}
