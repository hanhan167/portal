
package com.hansy.portal.service.impl;

import org.springframework.stereotype.Service;

import com.hansy.portal.model.vo.TUserPersonInfoVo;
import com.hansy.portal.service.ITUserPersonInfoService;
import com.hansy.portal.service.base.BaseDao;

@Service
public class TUserPersonInfoServiceImpl extends BaseDao implements ITUserPersonInfoService {

	@Override
	public void insert(TUserPersonInfoVo userPersonInfoVo) {
		try {
			getSqlMapClientTemplate().insert("userPersonInfo.insert", userPersonInfoVo);
		} catch (Exception e) {
			System.out.println("person"+e.getMessage());
		}
	}

}

