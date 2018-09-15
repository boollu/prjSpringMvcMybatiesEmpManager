package com.mapper;
import com.po.*;
import java.util.*;

import org.springframework.stereotype.Service;
@Service("DepDAO")
public interface IDepMapper {
	public List<Dep> findAll();
}
