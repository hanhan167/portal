
package com.hansy.portal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hansy.portal.common.BusinessMap;
import com.hansy.portal.common.utils.Pager;
import com.hansy.portal.model.vo.TBusPamentVo;
import com.hansy.portal.model.vo.TUserAddressVo;
import com.hansy.portal.service.ITBusPamentService;
import com.hansy.portal.service.base.BaseDao;

@Service
public class TBusPamentImpl extends BaseDao implements ITBusPamentService{

	@Override
	public BusinessMap<Object> save(TBusPamentVo tBusPamentVo) {
		BusinessMap<Object> bMap=new BusinessMap<>();
		try {
			getSqlMapClientTemplate().update("tbusPament.save", tBusPamentVo);
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
	public BusinessMap<Object> delete(String orderNo) {
		BusinessMap<Object> bMap=new BusinessMap<Object>();
	    try {
	    	getSqlMapClientTemplate().delete("tbusPament.delete", orderNo);
		} catch (Exception e) {
			bMap.setIsSucc(false);
			bMap.setMsg("删除发票地址失败");
			return bMap;
		}
	    bMap.setIsSucc(true);
		return bMap;
	}

	@Override
	public BusinessMap<Object> update(TBusPamentVo tBusPamentVo) {
		BusinessMap<Object> bMap=new BusinessMap<Object>();
		try {
			getSqlMapClientTemplate().delete("tbusPament.update", tBusPamentVo);
		} catch (Exception e) {
			bMap.setIsSucc(false);
			bMap.setMsg("更新发票地址失败");
		}
		bMap.setIsSucc(true);
		return bMap;
	}

	@Override
	public BusinessMap<Object> getById(String id) {
		BusinessMap<Object> bMap=new BusinessMap<Object>();
		try {
			TBusPamentVo tBusPamentVo=(TBusPamentVo) getSqlMapClientTemplate().queryForObject("tbusPament.getBillByOrderNo", id);
			bMap.setInfoBody(tBusPamentVo);
		} catch (Exception e) {
			bMap.setIsSucc(false);
			bMap.setMsg("更新发票地址失败");
		}
	
		bMap.setIsSucc(true);
		return bMap;
	}

	@Override
	public Pager getPage(Map<String, String>map, Pager pager) {
		List<TUserAddressVo> list=new ArrayList<TUserAddressVo>();
		//获取pager后的list
		try {
			list=getSqlMapClientTemplate().queryForList("tbusPament.getBillByOrderNo", map, (pager.getPageNo()-1)*pager.getPageSize(), pager.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//获取总记录数
		int count=0;
		try {
			count=(int) getSqlMapClientTemplate().queryForObject("tbusPament.getBillByOrderNoTotal", map);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pager.setTotal(count);
		pager.setRows(list);
		return pager;
	}

	@Override
	public BusinessMap<Object> savePament(List<TBusPamentVo> orderList) {
		BusinessMap<Object> bMap=new BusinessMap<>();
		try {
			getSqlMapClientTemplate().insert("tbusPament.savePament", orderList);
		} catch (Exception e) {
			bMap.setIsSucc(false);
			e.printStackTrace();
			return bMap;
		}
		bMap.setIsSucc(true);
		return bMap;
	}
	
}

