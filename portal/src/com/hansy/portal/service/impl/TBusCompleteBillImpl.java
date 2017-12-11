
package com.hansy.portal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hansy.portal.common.BusinessMap;
import com.hansy.portal.common.utils.Pager;
import com.hansy.portal.model.vo.GoodsVo;
import com.hansy.portal.model.vo.TBusCompleteBillVo;
import com.hansy.portal.model.vo.TBusPamentVo;
import com.hansy.portal.service.ITBusCompleteBillService;
import com.hansy.portal.service.base.BaseDao;
@Service
public class TBusCompleteBillImpl extends BaseDao implements ITBusCompleteBillService{

	@Override
	public BusinessMap<Object> save(TBusCompleteBillVo tBusCompleteBillVo) {
		BusinessMap<Object> bMap=new BusinessMap<>();
		try {
			getSqlMapClientTemplate().update("tBusCompleteBill.save", tBusCompleteBillVo);
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
	public BusinessMap<Object> delete(String billNo) {
		BusinessMap<Object> bMap=new BusinessMap<Object>();
	    try {
	    	getSqlMapClientTemplate().delete("tBusCompleteBill.delete", billNo);
		} catch (Exception e) {
			bMap.setIsSucc(false);
			bMap.setMsg("删除发票地址失败");
			return bMap;
		}
	    bMap.setIsSucc(true);
		return bMap;
	}

	@Override
	public BusinessMap<Object> update(TBusCompleteBillVo tBusCompleteBillVo) {
		BusinessMap<Object> bMap=new BusinessMap<Object>();
		try {
			getSqlMapClientTemplate().update("tBusCompleteBill.update", tBusCompleteBillVo);
		} catch (Exception e) {
			bMap.setIsSucc(false);
			bMap.setMsg("更新发票地址失败");
			System.out.println(e.getStackTrace());
		}
		bMap.setIsSucc(true);
		return bMap;
	}

	@Override
	public BusinessMap<Object> getById(Map<String, String>map) {
		BusinessMap<Object> bMap=new BusinessMap<Object>();
		try {
			TBusCompleteBillVo tBusCompleteBillVo=(TBusCompleteBillVo) getSqlMapClientTemplate().queryForObject("tBusCompleteBill.getBillByOrderNo", map);
			bMap.setInfoBody(tBusCompleteBillVo);
		} catch (Exception e) {
			bMap.setIsSucc(false);
			bMap.setMsg("更新发票地址失败");
		}
	
		bMap.setIsSucc(true);
		return bMap;
	}

	@Override
	public Pager getContactOrder(Map<String, Object> map, Pager pager) {
		List<GoodsVo> list=new ArrayList<GoodsVo>();
		//获取pager后的list
		try {
			list=getSqlMapClientTemplate().queryForList("tBusCompleteBill.getContactOrder", map, (pager.getPageNo()-1)*pager.getPageSize(), pager.getPageSize());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//获取总记录数
		int count=0;
		try {
			count=(int) getSqlMapClientTemplate().queryForObject("tBusCompleteBill.getContactOrderTotal", map);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pager.setTotal(count);
		pager.setRows(list);
		return pager;
	}
	
}

