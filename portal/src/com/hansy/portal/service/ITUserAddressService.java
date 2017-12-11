
package com.hansy.portal.service;

import java.util.List;

import com.hansy.portal.common.BusinessMap;
import com.hansy.portal.common.utils.Pager;
import com.hansy.portal.model.vo.TUserAddressVo;

public interface ITUserAddressService {
	public static final String BEAN_ID = "ITUserAddressService";

	/**
	 * 根据主键获取收货地址
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月27日 
	 * @modifier:
	 * @modifiedDate:
	 * @param tableKey
	 * @return
	 */
	TUserAddressVo getByTableKey(String tableKey);

	/**
	 * 根据用户编号获取所有收货地址
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月27日 
	 * @modifier:
	 * @modifiedDate:
	 * @param custNo
	 * @return
	 */
	List<TUserAddressVo> getByUserNo(String userNo);

	/**
	 * 新增收货地址
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月27日 
	 * @modifier:
	 * @modifiedDate:
	 * @param addressVo
	 * @return 
	 */
	BusinessMap<Object> add(TUserAddressVo addressVo);

	/**
	 * 删除收货地址
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月27日 
	 * @modifier:
	 * @modifiedDate:
	 * @param tableKey
	 * @return
	 */
	BusinessMap<Object> delete(String tableKey);

	/**
	 * 获取分页对象
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月28日 
	 * @modifier:
	 * @modifiedDate:
	 * @param userNo
	 * @param pager
	 * @return
	 */
	Pager getPage(String custNo, Pager pager);

	/**
	 * 更新收货地址
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月28日 
	 * @modifier:
	 * @modifiedDate:
	 * @param addressVo
	 * @return
	 */
	BusinessMap<Object> update(TUserAddressVo addressVo);

	/**
	 * 修改默认地址
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月6日 
	 * @modifier:
	 * @modifiedDate:
	 * @param tableKey 需要修改的默认地址的主键
	 * @param userNo   需要修改默认地址的用户编号
	 */
	void updateStatus(String tableKey, String custNo);
}

