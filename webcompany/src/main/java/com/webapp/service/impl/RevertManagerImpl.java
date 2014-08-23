package com.webapp.service.impl;

import org.springframework.stereotype.Component;

import com.webapp.entity.Revert;
import com.webapp.service.RevertManager;

@Component("revertManager")
public class RevertManagerImpl implements RevertManager {

//	private RevertDao revertDao;
//	@Resource
//	public void setRevertDao(RevertDao revertDao) {
//		this.revertDao = revertDao;
//	}
	public int add(Revert revert) {
//		return revertDao.save(revert);
		return 1;
	}
}
