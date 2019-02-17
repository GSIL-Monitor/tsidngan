package cn.dingan.tsdingan.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dingan.tsdingan.dao.InsuranceMealMapper;
import cn.dingan.tsdingan.model.InsuranceMeal;
import cn.dingan.tsdingan.service.InsuranceMealService;
import cn.trasen.BootComm.Contants;
import tk.mybatis.mapper.entity.Example;


@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
public class InsuranceMealServiceImpl implements InsuranceMealService{
	
	@Autowired
	private InsuranceMealMapper insuranceMealMapper;
	/**
	 * 根据套餐类型获取套餐
	 * @param mealType
	 * @return
	 */
	public InsuranceMeal getInsuranceMealByType(String mealType) {
		if(StringUtils.isNotBlank(mealType)) {
			Example example = new Example(InsuranceMeal.class);
			example.createCriteria().andEqualTo("isDeleted",Contants.IS_DELETED_FALSE).andEqualTo("mealType",mealType);
			return insuranceMealMapper.selectOneByExample(example);
		}
		return null;
	}
}
 