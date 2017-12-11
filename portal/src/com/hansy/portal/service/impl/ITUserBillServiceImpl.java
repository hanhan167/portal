
package com.hansy.portal.service.impl;

import org.springframework.stereotype.Service;

import com.hansy.portal.common.BusinessMap;
import com.hansy.portal.model.vo.TUserBillVo;
import com.hansy.portal.service.ITUserBillService;
import com.hansy.portal.service.base.BaseDao;
@Service
public class ITUserBillServiceImpl extends BaseDao implements ITUserBillService{

	@Override
	public BusinessMap<Object> save(TUserBillVo tUserBill) {
		BusinessMap<Object> bMap=new BusinessMap<>();
		try {
			tUserBill.setBillMoney(0.0);
			getSqlMapClientTemplate().update("userBill.save", tUserBill);
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
	    	getSqlMapClientTemplate().delete("userBill.delete", orderNo);
		} catch (Exception e) {
			bMap.setIsSucc(false);
			bMap.setMsg("删除发票地址失败");
			return bMap;
		}
	    bMap.setIsSucc(true);
		return bMap;
	}

	@Override
	public BusinessMap<Object> update(TUserBillVo tUserBill) {
		BusinessMap<Object> bMap=new BusinessMap<Object>();
		try {
			getSqlMapClientTemplate().update("userBill.update", tUserBill);
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
			TUserBillVo tUserBill=(TUserBillVo) getSqlMapClientTemplate().queryForObject("userBill.getByTableKey", id);
			System.out.println(tUserBill.toString());
			bMap.setInfoBody(tUserBill);
		} catch (Exception e) {
			bMap.setIsSucc(false);
			bMap.setMsg("更新发票地址失败");
		}
		bMap.setIsSucc(true);
		return bMap;
	}
	}


