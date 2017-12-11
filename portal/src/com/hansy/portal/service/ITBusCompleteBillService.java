
package com.hansy.portal.service;

import java.util.Map;

import com.hansy.portal.common.BusinessMap;
import com.hansy.portal.common.utils.Pager;
import com.hansy.portal.model.vo.TBusCompleteBillVo;

public interface ITBusCompleteBillService {
	BusinessMap<Object> save(TBusCompleteBillVo tBusCompleteBillVo);
	BusinessMap<Object> delete(String billNo);
	BusinessMap<Object> update(TBusCompleteBillVo tBusCompleteBillVo);
	BusinessMap<Object> getById(Map<String, String>map);
	public Pager getContactOrder(Map<String, Object>map,Pager pager);
}

