
package com.hansy.portal.service;

import com.hansy.portal.common.BusinessMap;
import com.hansy.portal.model.vo.TUserLoginLog;

public interface ITUserLoginLogService {
	BusinessMap<Object> save(TUserLoginLog tUserLoginLog);
}

