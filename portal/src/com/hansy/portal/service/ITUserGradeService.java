
package com.hansy.portal.service;

import com.hansy.portal.model.vo.TUserGradeVo;

public interface ITUserGradeService {
	public static final String BEAN_ID = "ITUserGradeService";

	/**
	 * 保存等级信息
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月1日 
	 * @modifier:
	 * @modifiedDate:
	 * @param userGradeVo
	 */
	void insert(TUserGradeVo userGradeVo);
}

