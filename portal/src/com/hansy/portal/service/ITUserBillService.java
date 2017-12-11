
package com.hansy.portal.service;

import com.hansy.portal.common.BusinessMap;
import com.hansy.portal.model.vo.TUserBillVo;

public interface ITUserBillService {
	BusinessMap<Object> save(TUserBillVo tUserBill);
	BusinessMap<Object> delete(String orderNo);
	BusinessMap<Object> update(TUserBillVo tUserBill);
	BusinessMap<Object> getById(String id);
}

