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
	 * 分页查询
	 * */
	public List<Emp> findPageAll(PageBean pb);
	/**
	 * 获取总记录数
	 * */
	public int findMaxRow();
	//
	public int findMaxEid();
}
