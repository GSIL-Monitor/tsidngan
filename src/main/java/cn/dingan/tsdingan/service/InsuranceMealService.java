package cn.dingan.tsdingan.service;

import cn.dingan.tsdingan.model.InsuranceMeal;

public interface InsuranceMealService {
	
	/**
	 * 根据套餐类型获取套餐
	 * @param mealType
	 * @return
	 */
	InsuranceMeal getInsuranceMealByType(String mealType);
}
