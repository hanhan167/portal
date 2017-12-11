
package com.hansy.portal.service.impl;

import org.springframework.stereotype.Service;

import com.hansy.portal.common.BusinessMap;
import com.hansy.portal.model.vo.TUserLoginLog;
import com.hansy.portal.service.ITUserLoginLogService;
import com.hansy.portal.service.base.BaseDao;
@Service
public class TUserLoginLogImpl extends BaseDao implements ITUserLoginLogService{

	@Override
	public BusinessMap<Object> save(TUserLoginLog tUserLoginLog) {
		BusinessMap<Object> bMap=new BusinessMap<>();
		try {
			getSqlMapClientTemplate().update("userLoginLog.save", tUserLoginLog);
		} catch (Exception e) {
			bMap.setIsSucc(false);
			bMap.setMsg("添加日志记录失败");
			e.printStackTrace();
			return bMap;
		}
		bMap.setIsSucc(true);
		return bMap;
	}

}

