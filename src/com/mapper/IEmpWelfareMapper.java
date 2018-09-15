package com.mapper;
import com.po.*;
import java.util.*;

import org.springframework.stereotype.Service;
@Service("EmpWelfareDAO")
public interface IEmpWelfareMapper {
	/**
	 * 保存员工福利数据
	 * */
	public int save(EmpWelfare ewf);
	
	/**
	 * 根据员工编号删除员工福利数据
	 * */
	public int delByEid(Integer eid);
	
	/**
	 * 根据员工编号查找员工所对应的福利集合
	 * */
	public List<Welfare> findByEid(Integer eid);
	
}
