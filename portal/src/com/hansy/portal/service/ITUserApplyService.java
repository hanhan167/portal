
package com.hansy.portal.service;

import java.util.List;

import com.hansy.portal.model.vo.TFlowTaskInfoVo;
import com.hansy.portal.model.vo.TFlowTaskLogVo;
import com.hansy.portal.model.vo.TUserApplyVo;

public interface ITUserApplyService {

	/**
	 * 保存注册申请表
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月30日 
	 * @modifier:
	 * @modifiedDate:
	 * @param userApplyVo
	 */
	void insert(TUserApplyVo userApplyVo);

	/**
	 * 保存工作流表
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月30日 
	 * @modifier:
	 * @modifiedDate:
	 * @param fTaskInfoVo
	 */
	void insertFlowInfo(TFlowTaskInfoVo fTaskInfoVo);

	/**
	 * 保存工作流日志
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月30日 
	 * @modifier:
	 * @modifiedDate:
	 * @param param
	 */
	void insertFlowLog(List<TFlowTaskLogVo> param);

}

