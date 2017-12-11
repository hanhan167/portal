
package com.hansy.portal.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hansy.portal.model.vo.SysLibrary;
import com.hansy.portal.service.ISysLibraryService;
import com.hansy.portal.service.base.BaseDao;

@Service
public class SysLibraryServiceImpl extends BaseDao implements ISysLibraryService {

	@Override
	public void insert(List<SysLibrary> paramLib) {
		try {
			getSqlMapClientTemplate().insert("sysLibrary.insert",paramLib);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

