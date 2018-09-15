package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.IEmpBiz;
import com.po.Emp;
import com.po.EmpWelfare;
import com.po.PageBean;
import com.po.Salary;
import com.po.Welfare;
import com.service.DaoService;

@Service("EmpBiz")
@Transactional
public class EmpBiz implements IEmpBiz {
	@Resource(name = "DaoService")
	private DaoService sdao;

	public DaoService getSdao() {
		return sdao;
	}

	public void setSdao(DaoService sdao) {
		this.sdao = sdao;
	}
	  /**
	    * 增加方法必须先添加员工后才可以添加和员工关联的字表
	    * 
	    * */
	public boolean save(Emp emp) {
		//数据库插入式成功后影响行数 失败为0或负数   
		int code = sdao.getEmpMapper().save(emp);
		System.out.println(code+"$$$$$$$$$$$$$$$$$$$$$$");
		if (code > 0) {
			//获取刚刚保存的员工编号
			int eid = sdao.getEmpMapper().findMaxEid();
			Float money = emp.getEmoney();
			Salary sal = new Salary(eid, money);
		int a=sdao.getSalaryMapper().save(sal);
			System.out.println("***************"+a);
			Integer[] wids = emp.getWids();//插入薪资员工
			if (wids.length > 0 && wids != null) {
				for (int i = 0; i < wids.length; i++) {
					EmpWelfare ewf = new EmpWelfare(eid, wids[i]);
					
					sdao.getEmpWelfareMapper().save(ewf);
				}
			}
			
			return true;
		}
		return false;
	}
    /**
      * 修改方法同增加数据库同样
      * 
      * */
	public boolean update(Emp emp) {
		int code = sdao.getEmpMapper().update(emp);
		if (code > 0) {
			Salary oldsal = sdao.getSalaryMapper()
					.findSalaryByEid(emp.getEid());
			if (oldsal != null && oldsal.getEmoney() != null) {
				oldsal.setEmoney(emp.getEmoney());// 修改薪资
				sdao.getSalaryMapper().updateByEid(oldsal);
			}
			List<Welfare> ew = sdao.getEmpWelfareMapper().findByEid(
					emp.getEid());// 先查询有没有福利
			if (ew.size() > 0 && ew != null) {
				sdao.getEmpWelfareMapper().delByEid(emp.getEid());
				// 删除旧福利
			}
			Integer[] wids = emp.getWids();// 获取新福利
			if (wids.length > 0 && wids != null) {
				for (int i = 0; i < wids.length; i++) {//获取前台传过来的复选框中的value数组值
					//插入员工福利
					EmpWelfare ewf = new EmpWelfare(emp.getEid(), wids[i]);
					// EmpWelfare ewf=new EmpWelfare(emp.getEid(), new
					// Integer(wids[i]));
					sdao.getEmpWelfareMapper().save(ewf);
				}
			}
			return true;
		}

		return false;
	}
         /**
          * 删除员工需要删除与员工关联的字表不然数据库无法删除员工
          * */
	public boolean delById(Integer eid) {
		sdao.getEmpWelfareMapper().delByEid(eid);//删除员工的先删福利
		sdao.getSalaryMapper().delByEid(eid);
         //先删除关联字表
		int code = sdao.getEmpMapper().delById(eid);
		if (code > 0) {
			return true;
		}

		return false;
	}
         /**
          * 通过员工的编号查找员工的信息福利薪资
          * 
          * */
	public Emp findById(Integer eid) {
		List<Welfare> ws=sdao.getEmpWelfareMapper().findByEid(eid);//查找福利
		Integer[] wids=new Integer[ws.size()];
		for (int i = 0; i < wids.length; i++) {
		  wids[i]=ws.get(i).getWid();//给前台查找到的福利转化为数组给前台
		}
		
		Salary sal=sdao.getSalaryMapper().findSalaryByEid(eid);
		Float emomey=sal.getEmoney();//通过员工的编号查找薪资传给前台
		Emp emp=sdao.getEmpMapper().findById(eid);//查找员工星系
		emp.setEmoney(emomey);//添加薪资属性
		emp.setWids(wids);//添加福利集合
		return emp;
	}

	public List<Emp> findPageAll(PageBean pb) {
		
		pb=pb==null?new PageBean():pb;
		return sdao.getEmpMapper().findPageAll(pb);//添加完战士展示的部分
	}

	public int findMaxRow() {
		int a=sdao.getEmpMapper().findMaxRow();
		return a;
	}

	

	

}
