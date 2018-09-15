package com.mapper;
import com.po.*;
import java.util.*;

import org.springframework.stereotype.Service;
@Service("EmpDAO")
public interface IEmpMapper {
	public int save(Emp emp);
	public int update(Emp emp);
	public int delById(Integer eid);
	public Emp findById(Integer eid);
	/**
	 * ��ҳ��ѯ
	 * */
	public List<Emp> findPageAll(PageBean pb);
	/**
	 * ��ȡ�ܼ�¼��
	 * */
	public int findMaxRow();
	//
	public int findMaxEid();
}
