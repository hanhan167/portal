package com.hansy.portal.service;

import java.util.List;
import java.util.Map;

import com.hansy.portal.common.BusinessMap;
import com.hansy.portal.common.utils.Pager;
import com.hansy.portal.model.bo.LoginUser;
import com.hansy.portal.model.bo.MyCustomer;
import com.hansy.portal.model.bo.TUserBaseInfoBo;
import com.hansy.portal.model.bo.UserBasic;
import com.hansy.portal.model.vo.GoodsVo;
import com.hansy.portal.model.vo.Order;
import com.hansy.portal.model.vo.TUserBaseInfoVo;
import com.hansy.portal.model.vo.TUserSupplyInfoVo;

public interface ITUserBaseInfoService {

	public static final String BEAN_ID = "ITUserBaseInfoService";
	
	public List<TUserBaseInfoVo>  selectUserBaseInfo(String userNo);

	/**
	 * 登录判断
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月21日 
	 * @modifier:
	 * @modifiedDate:
	 * @param user
	 * @return
	 */
	public BusinessMap<TUserBaseInfoBo> findIsExist(LoginUser user);
	
	/**
	 * 获取登录用的订单总数
	 * @param custNo
	 * @return
	 */
	public int getLoginUserOrderCount(String custNo);

	/**
	 * 新增
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月23日 
	 * @modifier:
	 * @modifiedDate:
	 * @param userBaseInfoVo
	 */
	public void insert(TUserBaseInfoVo userBaseInfoVo);

	/**
	 * 注册时判断输入内容是否可用
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月23日 
	 * @modifier:
	 * @modifiedDate:
	 * @param baseInfoVo
	 */
	public Map<String, Integer> findIsRegister(TUserBaseInfoVo baseInfoVo);

	/**
	 * 更新用户基本表
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月28日 
	 * @modifier:
	 * @modifiedDate:
	 * @param baseInfoVo
	 * @return
	 */
	public BusinessMap<Object> update(TUserBaseInfoBo baseInfoVo);
	public BusinessMap<Object>update1(Map<String, String>map);

	/**
	 * 获取粉丝page
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月28日 
	 * @modifier:
	 * @modifiedDate:
	 * @param userNo
	 * @return
	 */
	public Pager getFansPage(Map<String, Object>map,Pager pager);

	/**
	 * 获取我的客户page
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月28日 
	 * @modifier:
	 * @modifiedDate:
	 * @param userNo
	 * @return
	 */
	public Pager getMyCustomerPage(String custNo,Pager pager);
	
	/**
	 * 判断推荐人是否存在
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月1日 
	 * @modifier:
	 * @modifiedDate:
	 * @param superUserNo
	 * @return
	 */
	public Boolean findUserIsExist(String superUserPhone);

	/**
	 * 获取个人基本信息
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月2日 
	 * @modifier:
	 * @modifiedDate:
	 * @param userNo
	 * @return
	 */
	public UserBasic getPersonBasic(String userNo);

	/**
	 * 获取企业基本信息
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月2日 
	 * @modifier:
	 * @modifiedDate:
	 * @param userNo
	 * @return
	 */
	public UserBasic getEnterBasic(String userNo);
	
	/**
	 * 获取供方基本信息
	 * @description: TODO
	 * @creator: wt
	 * @createDate: 2017年3月2日 
	 * @modifier:
	 * @modifiedDate:
	 * @param userNo
	 * @return
	 */
	public UserBasic getSupplyBasic(String userNo);

	/**
	 * 更新的时候判断不能和其他用户（email,phone,alias相同）
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月2日 
	 * @modifier:
	 * @modifiedDate:
	 * @param userBaseInfoVo
	 * @return
	 */
	public Map<String, Integer> findIsEdit(TUserBaseInfoBo userBaseInfoVo);

	/**
	 * 根据user_no判断是否存在，除了自己的No
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月3日 
	 * @modifier:
	 * @modifiedDate:
	 * @param superUserNo
	 * @return
	 */
	public Boolean findUserNoIsExistOther(String superUserNo,String userNo);
	
	/**
	 * 根据用户的真实姓名获取用户的基本信息
	 * @param custName
	 * @return
	 */
	public TUserBaseInfoBo getUserBaseInfoByCustName(String custName,String phone);
	
	/**
	 * 找回密码功能-根据用户的手机号码后六位重置密码
	 * @param param
	 * @return
	 */
	public BusinessMap<Object> updateUserPwd(Map<String, Object> param);
	/**
	 * 获取商户供方信息表
	 * @description: TODO
	 * @creator: 欧诗阳
	 * @createDate: 2017年7月17日 
	 * @modifier:
	 * @modifiedDate:
	 * @param custNo
	 * @return
	 */
	public List<TUserSupplyInfoVo> selectUserSupplyInfoQuery(Map<String, Object>map);
	/**
	 * 根据custno查询详细数据
	 * @description: TODO
	 * @creator: 欧诗阳
	 * @createDate: 2017年7月18日 
	 * @modifier:
	 * @modifiedDate:
	 * @param custNo
	 * @return
	 */
	public TUserSupplyInfoVo getUserSupplyDetail(String custNo);
	public Pager<Order>filterByMoneyOrNum(Map<String, String>map,Pager<Order>pager);
	public Pager<MyCustomer> getMyCustomerPage1(Map<String, String>map,Pager<MyCustomer> pager);
	
	public TUserBaseInfoBo getBySessionCustNo(String custNo);
	public Pager selectSale(Map<String, Object>map,Pager pager);
	public Pager consumeRank(Map<String, Object>map,Pager pager);
	public Pager productRank(Map<String, Object>map,Pager pager);
	public Pager getMyOrderNoBill(Map<String, Object>map,Pager pager);
	public Pager getMyOrderNoBillDetail(Map<String, Object>map,Pager pager);
	public Pager getOrderCustName(Map<String, Object>map, Pager pager);
	public List<GoodsVo> getMyOrderNoBill(Map<String, Object>map);
}
