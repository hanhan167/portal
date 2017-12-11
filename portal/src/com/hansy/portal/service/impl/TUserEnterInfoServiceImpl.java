
package com.hansy.portal.service.impl;

import org.springframework.stereotype.Service;

import com.hansy.portal.model.vo.TUserEnterInfoVo;
import com.hansy.portal.service.ITUserEnterInfoService;
import com.hansy.portal.service.base.BaseDao;

@Service
public class TUserEnterInfoServiceImpl extends BaseDao implements ITUserEnterInfoService {

	@Override
	public void insert(TUserEnterInfoVo enterInfoVo) {
		getSqlMapClientTemplate().insert("userEnterInfo.insert", enterInfoVo);
	}

}

