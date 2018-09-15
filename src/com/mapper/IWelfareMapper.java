package com.mapper;
import com.po.*;
import java.util.*;

import org.springframework.stereotype.Service;
@Service("WelfareDAO")
public interface IWelfareMapper {
	public List<Welfare> findAll();
}
