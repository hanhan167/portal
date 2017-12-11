
package com.hansy.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hansy.portal.common.BusinessMap;
import com.hansy.portal.common.constant.UserCosntans;
import com.hansy.portal.common.utils.Pager;
import com.hansy.portal.model.vo.TUserAddressVo;
import com.hansy.portal.model.vo.TUserBaseInfoVo;
import com.hansy.portal.service.ITUserAddressService;
import com.hansy.portal.service.base.BaseDao;

@Service
public class TUserAddressServiceImpl extends BaseDao implements ITUserAddressService {

	@Override
	public TUserAddressVo getByTableKey(String tableKey) {
		TUserAddressVo addressVo=(TUserAddressVo) getSqlMapClientTemplate().queryForObject("userAddress.getByTableKey", tableKey);
		return addressVo;
	}

	@Override
	public List<TUserAddressVo> getByUserNo(String userNo) {
		List<TUserAddressVo> addressList=getSqlMapClientTemplate().queryForList("userAddress.selectByUserNo", userNo);
		return addressList;
	}

	@Override
	public BusinessMap<Object> add(TUserAddressVo addressVo) {
		BusinessMap<Object> bMap=new BusinessMap<Object>();
		try {
			getSqlMapClientTemplate().insert("userAddress.insert", addressVo);
		} catch (Exception e) {
			bMap.setIsSucc(false);
			bMap.setMsg("新增收货地址失败");
			return bMap;
		}
		bMap.setIsSucc(true);
		return bMap;
	}

	@Override
	public BusinessMap<Object> delete(String tableKey) {
		BusinessMap<Object> bMap=new BusinessMap<Object>();
	    try {
	    	getSqlMapClientTemplate().delete("userAddress.delete", tableKey);
		} catch (Exception e) {
			bMap.setIsSucc(false);
			bMap.setMsg("删除收货地址失败");
			return bMap;
		}
	    bMap.setIsSucc(true);
		return bMap;
	}

	@Override
	public Pager getPage(String custNo, Pager pager) {
		List<TUserAddressVo> list=new ArrayList<TUserAddressVo>();
		//获取pager后的list
		try {
			list=getSqlMapClientTemplate().queryForList("userAddress.selectByUserNo", custNo, (pager.getPageNo()-1)*pager.getPageSize(), pager.getPageSize());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//获取总记录数
		int count=0;
		try {
			count=(int) getSqlMapClientTemplate().queryForObject("userAddress.selectCountByUserNo", custNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pager.setTotal(count);
		pager.setRows(list);
		return pager;
	}

	@Override
	public BusinessMap<Object> update(TUserAddressVo addressVo) {
		BusinessMap<Object> bMap=new BusinessMap<Object>();
		try {
			getSqlMapClientTemplate().update("userAddress.update", addressVo);
		} catch (Exception e) {
			bMap.setIsSucc(false);
			bMap.setMsg("更新收货地址失败");
		}
		bMap.setIsSucc(true);
		return bMap;
	}

	@Override
	public void updateStatus(String tableKey, String custNo) {
		Map<String, String> map1=new HashMap<String,String>();
		Map<String, String> map2=new HashMap<String,String>();
		map1.put("statusNo", UserCosntans.defaultAddressNo);
		map1.put("status", UserCosntans.defaultAddress);
		map1.put("custNo", custNo);
		map2.put("status", UserCosntans.defaultAddress);
		map2.put("tableKey", tableKey);
		//把原先默认地址改为非默认地址
		getSqlMapClientTemplate().update("userAddress.updateAddressNo",map1);
		//设置默认地址
		getSqlMapClientTemplate().update("userAddress.updateAddress", map2);
	}

}

