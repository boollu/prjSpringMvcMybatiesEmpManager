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
	    * ���ӷ������������Ա����ſ�����Ӻ�Ա���������ֱ�
	    * 
	    * */
	public boolean save(Emp emp) {
		//���ݿ����ʽ�ɹ���Ӱ������ ʧ��Ϊ0����   
		int code = sdao.getEmpMapper().save(emp);
		System.out.println(code+"$$$$$$$$$$$$$$$$$$$$$$");
		if (code > 0) {
			//��ȡ�ոձ����Ա�����
			int eid = sdao.getEmpMapper().findMaxEid();
			Float money = emp.getEmoney();
			Salary sal = new Salary(eid, money);
		int a=sdao.getSalaryMapper().save(sal);
			System.out.println("***************"+a);
			Integer[] wids = emp.getWids();//����н��Ա��
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
      * �޸ķ���ͬ�������ݿ�ͬ��
      * 
      * */
	public boolean update(Emp emp) {
		int code = sdao.getEmpMapper().update(emp);
		if (code > 0) {
			Salary oldsal = sdao.getSalaryMapper()
					.findSalaryByEid(emp.getEid());
			if (oldsal != null && oldsal.getEmoney() != null) {
				oldsal.setEmoney(emp.getEmoney());// �޸�н��
				sdao.getSalaryMapper().updateByEid(oldsal);
			}
			List<Welfare> ew = sdao.getEmpWelfareMapper().findByEid(
					emp.getEid());// �Ȳ�ѯ��û�и���
			if (ew.size() > 0 && ew != null) {
				sdao.getEmpWelfareMapper().delByEid(emp.getEid());
				// ɾ���ɸ���
			}
			Integer[] wids = emp.getWids();// ��ȡ�¸���
			if (wids.length > 0 && wids != null) {
				for (int i = 0; i < wids.length; i++) {//��ȡǰ̨�������ĸ�ѡ���е�value����ֵ
					//����Ա������
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
          * ɾ��Ա����Ҫɾ����Ա���������ֱ�Ȼ���ݿ��޷�ɾ��Ա��
          * */
	public boolean delById(Integer eid) {
		sdao.getEmpWelfareMapper().delByEid(eid);//ɾ��Ա������ɾ����
		sdao.getSalaryMapper().delByEid(eid);
         //��ɾ�������ֱ�
		int code = sdao.getEmpMapper().delById(eid);
		if (code > 0) {
			return true;
		}

		return false;
	}
         /**
          * ͨ��Ա���ı�Ų���Ա������Ϣ����н��
          * 
          * */
	public Emp findById(Integer eid) {
		List<Welfare> ws=sdao.getEmpWelfareMapper().findByEid(eid);//���Ҹ���
		Integer[] wids=new Integer[ws.size()];
		for (int i = 0; i < wids.length; i++) {
		  wids[i]=ws.get(i).getWid();//��ǰ̨���ҵ��ĸ���ת��Ϊ�����ǰ̨
		}
		
		Salary sal=sdao.getSalaryMapper().findSalaryByEid(eid);
		Float emomey=sal.getEmoney();//ͨ��Ա���ı�Ų���н�ʴ���ǰ̨
		Emp emp=sdao.getEmpMapper().findById(eid);//����Ա����ϵ
		emp.setEmoney(emomey);//���н������
		emp.setWids(wids);//��Ӹ�������
		return emp;
	}

	public List<Emp> findPageAll(PageBean pb) {
		
		pb=pb==null?new PageBean():pb;
		return sdao.getEmpMapper().findPageAll(pb);//�����սʿչʾ�Ĳ���
	}

	public int findMaxRow() {
		int a=sdao.getEmpMapper().findMaxRow();
		return a;
	}

	

	

}
