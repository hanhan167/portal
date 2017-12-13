
package com.hansy.portal.service;

import java.util.Map;

import com.hansy.portal.common.BusinessMap;
import com.hansy.portal.common.utils.Pager;
import com.hansy.portal.model.vo.TBusBillVo;
import com.hansy.portal.model.vo.TBusCompleteBillVo;


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
	/**
	 *更改发票状态为已开发票 bus bill
	 */
	BusinessMap<Object> updateBillStatus(String status);
	/**
	 *更改发票状态为已开发票 bus order
	 */
	BusinessMap<Object> updateBillStatusOfOrder(String applyNo);
	/**
	 *从bus bill表获取需要存储的发票数据 
	 */
	TBusCompleteBillVo getInvoiceByApplyNo(String applyNo);
}

