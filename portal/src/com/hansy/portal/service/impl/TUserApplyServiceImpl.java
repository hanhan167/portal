
package com.hansy.portal.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hansy.portal.model.vo.TFlowTaskInfoVo;
import com.hansy.portal.model.vo.TFlowTaskLogVo;
import com.hansy.portal.model.vo.TUserApplyVo;
import com.hansy.portal.service.ITUserApplyService;
import com.hansy.portal.service.base.BaseDao;

@Service
public class TUserApplyServiceImpl extends BaseDao implements ITUserApplyService {

	@Override
	public void insert(TUserApplyVo userApplyVo) {
		try {
			getSqlMapClientTemplate().insert("userApply.insert", userApplyVo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void insertFlowInfo(TFlowTaskInfoVo fTaskInfoVo) {
		try {
			getSqlMapClientTemplate().insert("userApply.insertFlowInfo", fTaskInfoVo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void insertFlowLog(List<TFlowTaskLogVo> param) {
		try {
			getSqlMapClientTemplate().insert("userApply.insertFlowLog", param);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}

