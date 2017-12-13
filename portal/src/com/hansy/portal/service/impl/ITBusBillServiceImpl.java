
package com.hansy.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hansy.portal.common.BusinessMap;
import com.hansy.portal.common.utils.Pager;
import com.hansy.portal.model.vo.TBusBillVo;
import com.hansy.portal.model.vo.TBusCompleteBillVo;
import com.hansy.portal.service.ITBusBillService;
import com.hansy.portal.service.base.BaseDao;

@Service
public class ITBusBillServiceImpl extends BaseDao implements ITBusBillService{

	@Override
	public BusinessMap<Object> save(TBusBillVo tBusBillVo) {
		BusinessMap<Object> bMap=new BusinessMap<>();
		try {
			getSqlMapClientTemplate().update("busBill1.save", tBusBillVo);
		} catch (Exception e) {
			bMap.setIsSucc(false);
			bMap.setMsg("添加发票信息失败");
			e.printStackTrace();
			return bMap;
		}
		bMap.setIsSucc(true);
		return bMap;
	}

	@Override
	public Pager<TBusBillVo> getBillByOrderNo(Map<String, String> map, Pager<TBusBillVo> pager) {
		List<TBusBillVo>list = getSqlMapClientTemplate().queryForList("busBill1.getBillByOrderNo",map,(pager.getPageNo()-1)*pager.getPageSize(), pager.getPageSize());
		pager.setRows(list);
		int count = (int) getSqlMapClientTemplate().queryForObject("busBill1.getBillByOrderNoTotal",map);
		pager.setTotal(count);
		return pager;
	}

	@Override
	public BusinessMap<Object> updateBillStatus(String applyNo) {
		BusinessMap<Object> bMap=new BusinessMap<>();
		try {
			getSqlMapClientTemplate().update("busBill1.updateStatus", applyNo);
		} catch (Exception e) {
			bMap.setIsSucc(false);
			bMap.setMsg("修改发票状态失败（bus bill）");
			e.printStackTrace();
			return bMap;
		}
		bMap.setIsSucc(true);
		return bMap;
	}

	@Override
	public BusinessMap<Object> updateBillStatusOfOrder(String applyNo) {
		BusinessMap<Object> bMap=new BusinessMap<>();
		try {
			getSqlMapClientTemplate().update("busBill1.updateBillStatusOfOrder", applyNo);
		} catch (Exception e) {
			bMap.setIsSucc(false);
			bMap.setMsg("修改发票状态失败（bus order）");
			e.printStackTrace();
			return bMap;
		}
		bMap.setIsSucc(true);
		return bMap;
	}
	
	@Override
	public TBusCompleteBillVo getInvoiceByApplyNo(String applyNo) {
		
		TBusCompleteBillVo tBusCompleteBillVo;
		try {
			tBusCompleteBillVo = (TBusCompleteBillVo) getSqlMapClientTemplate()
					.queryForObject("busBill1.getInvoiceByApplyNo", applyNo);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return tBusCompleteBillVo;
	}

}


