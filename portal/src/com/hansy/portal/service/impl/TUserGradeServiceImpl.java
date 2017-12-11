
package com.hansy.portal.service.impl;

import org.springframework.stereotype.Service;

import com.hansy.portal.model.vo.TUserGradeVo;
import com.hansy.portal.service.ITUserGradeService;
import com.hansy.portal.service.base.BaseDao;

@Service
public class TUserGradeServiceImpl extends BaseDao implements ITUserGradeService {

	@Override
	public void insert(TUserGradeVo userGradeVo) {
		try {
			getSqlMapClientTemplate().insert("userGrade.insert", userGradeVo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

