
package com.hansy.portal.service;

import java.util.List;
import java.util.Map;

import com.hansy.portal.common.BusinessMap;
import com.hansy.portal.common.utils.Pager;
import com.hansy.portal.model.vo.TBusPamentVo;

public interface ITBusPamentService {
	BusinessMap<Object> save(TBusPamentVo tBusPamentVo);
	BusinessMap<Object> delete(String orderNo );
	BusinessMap<Object> update(TBusPamentVo tBusPamentVo);
	BusinessMap<Object> getById(String id);
	Pager getPage(Map<String, String>map, Pager pager);
	BusinessMap<Object> savePament(List<TBusPamentVo> orderList);

}

