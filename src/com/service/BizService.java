package com.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.biz.IDepBiz;
import com.biz.IEmpBiz;
import com.biz.IWelfareBiz;

@Service("BizService")
public class BizService {
	@Resource(name = "DepBiz")
	private IDepBiz depbiz;

	@Resource(name = "WelfareBiz")
	private IWelfareBiz welbiz;
	
	@Resource(name = "EmpBiz")
	private IEmpBiz empbiz;

	public IDepBiz getDepbiz() {
		return depbiz;
	}

	public void setDepbiz(IDepBiz depbiz) {
		this.depbiz = depbiz;
	}

	public IWelfareBiz getWelbiz() {
		return welbiz;
	}

	public void setWelbiz(IWelfareBiz welbiz) {
		this.welbiz = welbiz;
	}

	public IEmpBiz getEmpbiz() {
		return empbiz;
	}

	public void setEmpbiz(IEmpBiz empbiz) {
		this.empbiz = empbiz;
	}

}
