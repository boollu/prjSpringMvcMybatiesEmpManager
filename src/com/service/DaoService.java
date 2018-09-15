package com.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.mapper.*;
@Service("DaoService")
public class DaoService {
	
	@Resource(name="DepDAO")
	private IDepMapper depMapper;
	
	@Resource(name="EmpDAO")
	private IEmpMapper empMapper;
	
	@Resource(name="EmpWelfareDAO")
	private IEmpWelfareMapper empWelfareMapper;
	
	@Resource(name="SalaryDAO")
	private ISalaryMapper salaryMapper;
	
	@Resource(name="WelfareDAO")
	private IWelfareMapper welfareMapper;
	
	
	public IDepMapper getDepMapper() {
		return depMapper;
	}
	public void setDepMapper(IDepMapper depMapper) {
		this.depMapper = depMapper;
	}
	public IEmpMapper getEmpMapper() {
		return empMapper;
	}
	public void setEmpMapper(IEmpMapper empMapper) {
		this.empMapper = empMapper;
	}
	public IEmpWelfareMapper getEmpWelfareMapper() {
		return empWelfareMapper;
	}
	public void setEmpWelfareMapper(IEmpWelfareMapper empWelfareMapper) {
		this.empWelfareMapper = empWelfareMapper;
	}
	public ISalaryMapper getSalaryMapper() {
		return salaryMapper;
	}
	public void setSalaryMapper(ISalaryMapper salaryMapper) {
		this.salaryMapper = salaryMapper;
	}
	public IWelfareMapper getWelfareMapper() {
		return welfareMapper;
	}
	public void setWelfareMapper(IWelfareMapper welfareMapper) {
		this.welfareMapper = welfareMapper;
	}
	
}
