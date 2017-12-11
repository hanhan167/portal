package com.hansy.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hansy.portal.common.BusinessMap;
import com.hansy.portal.common.utils.Pager;
import com.hansy.portal.model.bo.Fans;
import com.hansy.portal.model.bo.LoginUser;
import com.hansy.portal.model.bo.MyCustomer;
import com.hansy.portal.model.bo.TUserBaseInfoBo;
import com.hansy.portal.model.bo.UserBasic;
import com.hansy.portal.model.vo.GoodsVo;
import com.hansy.portal.model.vo.Order;
import com.hansy.portal.model.vo.TUserAddressVo;
import com.hansy.portal.model.vo.TUserBaseInfoVo;
import com.hansy.portal.model.vo.TUserSupplyInfoVo;
import com.hansy.portal.service.ITUserBaseInfoService;
import com.hansy.portal.service.base.BaseDao;

@Service
public class TUserBaseInfoServiceImpl extends BaseDao implements ITUserBaseInfoService{

	@Override
	public List<TUserBaseInfoVo> selectUserBaseInfo(String userNo) {
		@SuppressWarnings("unchecked")
		List<TUserBaseInfoVo> list = (List<TUserBaseInfoVo>)getSqlMapClientTemplate().queryForList("userBaseInfo.selectUserbaseInfo", null);
	 	return list;
	}

	@Override
	public BusinessMap<TUserBaseInfoBo> findIsExist(LoginUser user) {
		BusinessMap<TUserBaseInfoBo> bMap=new BusinessMap<>();
		TUserBaseInfoBo uBaseInfoVo=new TUserBaseInfoBo();
		try {
			uBaseInfoVo=(TUserBaseInfoBo) getSqlMapClientTemplate().queryForObject("userBaseInfo.findIsExist", user);
		} catch (Exception e) {
			bMap.setIsSucc(false);
			e.printStackTrace();
			return bMap;
		}
		bMap.setInfoBody(uBaseInfoVo);
		bMap.setIsSucc(true);
		return bMap;
	}

	
	
	
	
	@Override
	public int getLoginUserOrderCount(String custNo) {

		Integer count = 0;
		try {
			Map<String,String> params=new HashMap<String,String>();
			params.put("custNo", custNo);
			Integer buyCount = (Integer)getSqlMapClientTemplate().queryForObject("userBaseInfo.selectBuyerOrdersCount", params);
			Integer supplyCount = (Integer)getSqlMapClientTemplate().queryForObject("userBaseInfo.selectSupplyOrdersCount", params);
			count=buyCount+supplyCount;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public void insert(TUserBaseInfoVo userBaseInfoVo) {
		try {
			getSqlMapClientTemplate().insert("userBaseInfo.insert", userBaseInfoVo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Map<String, Integer> findIsRegister(TUserBaseInfoVo baseInfoVo) {
		Map<String, Integer> result=new HashMap<String,Integer>();
		
		if (baseInfoVo.getUserPhone()!=null) {
			//判断电话号码是否存在，存在返回1，不存在返回0
			int phoneIsExist=(int) getSqlMapClientTemplate().queryForObject("userBaseInfo.phoneIsExist", baseInfoVo);
			result.put("phoneIsExist", phoneIsExist);
		}
		if (baseInfoVo.getUserEmail()!=null) {
			//判断email，结果同上
			int emailIsExist=(int) getSqlMapClientTemplate().queryForObject("userBaseInfo.emailIsExist", baseInfoVo);
			result.put("emailIsExist", emailIsExist);
		}
		if (baseInfoVo.getUserAlias()!=null) {
			//判断登录名，结果同上
			int userAliasIsExist=(int) getSqlMapClientTemplate().queryForObject("userBaseInfo.userAliasIsExist", baseInfoVo);
			result.put("userAliasIsExist", userAliasIsExist);
		}
		return result;
	}

	@Override
	public BusinessMap<Object> update(TUserBaseInfoBo baseInfoVo) {
		BusinessMap<Object> bMap=new BusinessMap<Object>();
		try {
			getSqlMapClientTemplate().update("userBaseInfo.update", baseInfoVo);
		} catch (Exception e) {
			bMap.setIsSucc(false);
			bMap.setMsg("修改失败");
		}
		
		
		bMap.setIsSucc(true);
		return bMap;
	}

	@Override
	public Pager getFansPage(Map<String, Object>map,Pager pager) {
		List<Fans> list=new ArrayList<Fans>();
		//获取pager后的list
		try {
			list=getSqlMapClientTemplate().queryForList("userBaseInfo.selectFans", map, (pager.getPageNo()-1)*pager.getPageSize(), pager.getPageSize());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//获取总记录数
		int count=0;
		try {
			count=(int) getSqlMapClientTemplate().queryForObject("userBaseInfo.selectCountFans", map);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pager.setTotal(count);
		pager.setRows(list);
		return pager;
	}
	
	
	

	@Override
	public Pager getMyCustomerPage(String custNo, Pager pager) {
		List<Fans> list=new ArrayList<Fans>();
		//获取pager后的list
		try {
			list=getSqlMapClientTemplate().queryForList("userBaseInfo.getMyCustomer", custNo, (pager.getPageNo()-1)*pager.getPageSize(), pager.getPageSize());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//获取总记录数
		int count=0;
		try {
			count=(int) getSqlMapClientTemplate().queryForObject("userBaseInfo.getMyCustomerCount", custNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pager.setTotal(count);
		pager.setRows(list);
		return pager;
	}

	@Override
	public Boolean findUserIsExist(String superUserPhone) {
		int result=(int) getSqlMapClientTemplate().queryForObject("userBaseInfo.userIsExist", superUserPhone);
		return result==1?true:false;
	}

	@Override
	public UserBasic getPersonBasic(String userNo) {
		UserBasic uBasic=(UserBasic) getSqlMapClientTemplate().queryForObject("userBaseInfo.getPersonBasic", userNo);
		return uBasic;
	}

	@Override
	public UserBasic getEnterBasic(String userNo) {
		UserBasic uBasic=(UserBasic) getSqlMapClientTemplate().queryForObject("userBaseInfo.getEnterBasic", userNo);
		return uBasic;
	}
	
	

	@Override
	public UserBasic getSupplyBasic(String userNo) {
		UserBasic uBasic=(UserBasic) getSqlMapClientTemplate().queryForObject("userBaseInfo.getSupplyBasic", userNo);
		return uBasic;
	}

	@Override
	public Map<String, Integer> findIsEdit(TUserBaseInfoBo baseInfoVo) {
		Map<String, Integer> result=new HashMap<String,Integer>();
		//判断电话号码是否和其他用户冲突，冲突返回1，不存在返回0
		try {
			int phoneIsExist=(int) getSqlMapClientTemplate().queryForObject("userBaseInfo.phoneIsExistOther", baseInfoVo);
			result.put("phoneIsExist", phoneIsExist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//判断email，结果同上
		int emailIsExist=(int) getSqlMapClientTemplate().queryForObject("userBaseInfo.emailIsExistOther", baseInfoVo);
		result.put("emailIsExist", emailIsExist);
		//判断登录名，结果同上
		int userAliasIsExist=(int) getSqlMapClientTemplate().queryForObject("userBaseInfo.userAliasIsExistOther", baseInfoVo);
		result.put("userAliasIsExist", userAliasIsExist);
		return result;
	}

	@Override
	public Boolean findUserNoIsExistOther(String superUserNo, String userNo) {
		Map<String, String> map=new HashMap<String,String>();
		map.put("superUserNo", superUserNo);
		map.put("userNo", userNo);
		int result=(int) getSqlMapClientTemplate().queryForObject("userBaseInfo.userNoIsExistOther", map);
		return result>0?true:false;
	}

	@Override
	public TUserBaseInfoBo getUserBaseInfoByCustName(String custName,
			String phone) {
		TUserBaseInfoBo baseInfoVo = null;
		List<TUserBaseInfoBo> list = (List<TUserBaseInfoBo>)getSqlMapClientTemplate().queryForList("userBaseInfo.getUserBaseInfoByCustName",custName);
		if(list.size() > 1){
			for (TUserBaseInfoBo vo : list) {
				if(phone.equals(vo.getUserPhone())){
					baseInfoVo = vo;
				}
			}
			if(baseInfoVo == null){
				baseInfoVo = list.get(0);
			}
		}else{
			if(list.size() == 1){
				baseInfoVo = list.get(0);
			}
		}
		return baseInfoVo;
	}

	@Override
	public BusinessMap<Object> updateUserPwd(Map<String, Object> param) {
		BusinessMap<Object> bMap=new BusinessMap<Object>();
		try {
			getSqlMapClientTemplate().update("userBaseInfo.updateUserPwd", param);
		} catch (DataAccessException e) {
			bMap.setIsSucc(false);
			bMap.setMsg("找回密码重置失败！");
			e.printStackTrace();
		}
		bMap.setIsSucc(true);
		return bMap;
	}

	@Override
	public List<TUserSupplyInfoVo> selectUserSupplyInfoQuery(Map<String, Object>map) {
	List<TUserSupplyInfoVo> list = (List<TUserSupplyInfoVo>)getSqlMapClientTemplate().queryForList("userBaseInfo.SelectUserSupplyInfoQuery",map);
		return list;
	}

	@Override
	public TUserSupplyInfoVo getUserSupplyDetail(String custNo) {
		List<TUserSupplyInfoVo> list = (List<TUserSupplyInfoVo>)getSqlMapClientTemplate().queryForList("userBaseInfo.getUserSupplyDetail",custNo);
		
		return list.size()>0?list.get(0):null;
	}

	@Override
	public Pager<Order> filterByMoneyOrNum(Map<String, String> map,Pager<Order>pager) {
		List<Order>list = getSqlMapClientTemplate().queryForList("userBaseInfo.filterByMoneyOrNum",map,(pager.getPageNo()-1)*pager.getPageSize(), pager.getPageSize());
		pager.setRows(list);
		int count = (int) getSqlMapClientTemplate().queryForObject("userBaseInfo.filterByMoneyOrNumTotal",map);
		pager.setTotal(count);
		return pager;
	}

	@Override
	public BusinessMap<Object> update1(Map<String, String> map) {
		BusinessMap<Object> bMap=new BusinessMap<Object>();
		try {
			getSqlMapClientTemplate().update("userBaseInfo.update1", map);
		} catch (Exception e) {
			bMap.setIsSucc(false);
			bMap.setMsg("修改失败");
		}
		bMap.setIsSucc(true);
		return bMap;
	}

	@Override
	public Pager<MyCustomer> getMyCustomerPage1(Map<String, String> map, Pager<MyCustomer> pager) {
		List<MyCustomer> list=new ArrayList<MyCustomer>();
		//获取pager后的list
		try {
			list=getSqlMapClientTemplate().queryForList("userBaseInfo.getMyCustmer1", map, (pager.getPageNo()-1)*pager.getPageSize(), pager.getPageSize());
			pager.setRows(list);
		} catch (Exception e) {
		}
		//获取总记录数
		int count=0;
		try {
			count=(int) getSqlMapClientTemplate().queryForObject("userBaseInfo.getMyCustmer1count", map);
		} catch (Exception e) {
		}
		pager.setTotal(count);
		pager.setRows(list);
		return pager;
	}

	@Override
	public TUserBaseInfoBo getBySessionCustNo(String custNo) {
		TUserBaseInfoBo uBasic=(TUserBaseInfoBo) getSqlMapClientTemplate().queryForObject("userBaseInfo.getBySessionCustNo", custNo);
		return uBasic;
	}

	@Override
	public Pager selectSale(Map<String, Object> map, Pager pager) {
		List<Fans> list=new ArrayList<Fans>();
		//获取pager后的list
		try {
			list=getSqlMapClientTemplate().queryForList("userBaseInfo.selectSale", map, (pager.getPageNo()-1)*pager.getPageSize(), pager.getPageSize());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//获取总记录数
		int count=0;
		try {
			count=(int) getSqlMapClientTemplate().queryForObject("userBaseInfo.selectSaleTotal", map);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pager.setTotal(count);
		pager.setRows(list);
		return pager;
	}

	@Override
	public Pager consumeRank(Map<String, Object> map, Pager pager) {
		List<Fans> list=new ArrayList<Fans>();
		//获取pager后的list
		try {
			list=getSqlMapClientTemplate().queryForList("userBaseInfo.consumeRank", map, (pager.getPageNo()-1)*pager.getPageSize(), pager.getPageSize());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//获取总记录数
		int count=0;
		try {
			count=(int) getSqlMapClientTemplate().queryForObject("userBaseInfo.consumeRankTotal", map);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pager.setTotal(count);
		pager.setRows(list);
		return pager;
	}

	@Override
	public Pager productRank(Map<String, Object> map, Pager pager) {
		List<GoodsVo> list=new ArrayList<GoodsVo>();
		//获取pager后的list
		try {
			list=getSqlMapClientTemplate().queryForList("userBaseInfo.productRank", map, (pager.getPageNo()-1)*pager.getPageSize(), pager.getPageSize());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//获取总记录数
		int count=0;
		try {
			count=(int) getSqlMapClientTemplate().queryForObject("userBaseInfo.productRankTotal", map);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pager.setTotal(count);
		pager.setRows(list);
		return pager;
	}

	@Override
	public Pager getMyOrderNoBill(Map<String, Object> map, Pager pager) {
		List<GoodsVo> list=new ArrayList<GoodsVo>();
		//获取pager后的list
		try {
			list=getSqlMapClientTemplate().queryForList("userBaseInfo.getMyOrderNoBill", map, (pager.getPageNo()-1)*pager.getPageSize(), pager.getPageSize());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//获取总记录数
		int count=0;
		try {
			count=(int) getSqlMapClientTemplate().queryForObject("userBaseInfo.getMyOrderNoBillTotal", map);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pager.setTotal(count);
		pager.setRows(list);
		return pager;
	}

	@Override
	public Pager getMyOrderNoBillDetail(Map<String, Object> map, Pager pager) {
		List<GoodsVo> list=new ArrayList<GoodsVo>();
		//获取pager后的list
		try {
			list=getSqlMapClientTemplate().queryForList("userBaseInfo.getMyOrderNoBillDetail", map, (pager.getPageNo()-1)*pager.getPageSize(), pager.getPageSize());
			System.out.println(list.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//获取总记录数
		int count=0;
		try {
			count=(int) getSqlMapClientTemplate().queryForObject("userBaseInfo.getMyOrderNoBillDetailTotal", map);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pager.setTotal(count);
		pager.setRows(list);
		return pager;
	}

	@Override
	public Pager getOrderCustName(Map<String, Object> map, Pager pager) {
		List<GoodsVo> list=new ArrayList<GoodsVo>();
		//获取pager后的list
		try {
			list=getSqlMapClientTemplate().queryForList("userBaseInfo.getOrderCustName", map, (pager.getPageNo()-1)*pager.getPageSize(), pager.getPageSize());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//获取总记录数
				int count=0;
				try {
					count=(int) getSqlMapClientTemplate().queryForObject("userBaseInfo.getOrderCustNameTotal", map);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				pager.setTotal(count);
				pager.setRows(list);
				return pager;
	}

	@Override
	public List<GoodsVo> getMyOrderNoBill(Map<String, Object> map) {
		List<GoodsVo> list=new ArrayList<GoodsVo>();
		//获取pager后的list
		try {
			list=getSqlMapClientTemplate().queryForList("userBaseInfo.getMyOrderNoBill", map);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//获取总记录数
		return list;
	}
}
