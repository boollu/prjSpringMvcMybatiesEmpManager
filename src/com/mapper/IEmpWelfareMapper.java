package com.mapper;
import com.po.*;
import java.util.*;

import org.springframework.stereotype.Service;
@Service("EmpWelfareDAO")
public interface IEmpWelfareMapper {
	/**
	 * ����Ա����������
	 * */
	public int save(EmpWelfare ewf);
	
	/**
	 * ����Ա�����ɾ��Ա����������
	 * */
	public int delByEid(Integer eid);
	
	/**
	 * ����Ա����Ų���Ա������Ӧ�ĸ�������
	 * */
	public List<Welfare> findByEid(Integer eid);
	
}
