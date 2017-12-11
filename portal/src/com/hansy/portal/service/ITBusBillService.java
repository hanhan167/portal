
package com.hansy.portal.service;

import java.util.Map;

import com.hansy.portal.common.BusinessMap;
import com.hansy.portal.common.utils.Pager;
import com.hansy.portal.model.vo.TBusBillVo;


public interface ITBusBillService {
	/**
	 * 单个保存订单备注信息
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月23日 
	 * @modifier:
	 * @modifiedDate:
	 * @param orderDetlVo
	 * @return
	 */
	BusinessMap<Object> save(TBusBillVo tBusBillVo);
	public Pager<TBusBillVo>getBillByOrderNo(Map<String, String>map,Pager<TBusBillVo>pager);
}

