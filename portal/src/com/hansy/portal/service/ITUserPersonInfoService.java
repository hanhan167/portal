
package com.hansy.portal.service;

import com.hansy.portal.model.vo.TUserPersonInfoVo;

public interface ITUserPersonInfoService {
	public static final String BEAN_ID = "ITUserPersonInfoService";

	/**
	 * 新增
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月23日 
	 * @modifier:
	 * @modifiedDate:
	 * @param userPersonInfoVo
	 */
	void insert(TUserPersonInfoVo userPersonInfoVo);
}

