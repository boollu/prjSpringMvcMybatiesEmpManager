package com.biz.imp;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.*;
import com.po.*;
import com.service.*;
@Service("DepBiz")
@Transactional
public class DepBiz implements IDepBiz {
	@Resource(name="DaoService")
	private DaoService daoService;
	
	public DaoService getDaoService() {
		return daoService;
	}

	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}

	public List<Dep> findAll() {
		
		return daoService.getDepMapper().findAll();
	}

}
