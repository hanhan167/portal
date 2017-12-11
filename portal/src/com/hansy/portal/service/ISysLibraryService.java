
package com.hansy.portal.service;

import java.util.List;

import com.hansy.portal.model.vo.SysLibrary;

public interface ISysLibraryService {

	/**
	 * 保存文件信息表
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年3月31日 
	 * @modifier:
	 * @modifiedDate:
	 * @param sysLibary
	 */
	void insert(List<SysLibrary> paramLib);

}

