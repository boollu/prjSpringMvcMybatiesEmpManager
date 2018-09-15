package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.biz.IWelfareBiz;
import com.po.Welfare;
import com.service.DaoService;
@Service("WelfareBiz")
public class WelfareBiz implements IWelfareBiz {
     @Resource(name="DaoService")
	private DaoService sdao;
     
	
	public DaoService getSdao() {
		return sdao;
	}


	public void setSdao(DaoService sdao) {
		this.sdao = sdao;
	}


	public List<Welfare> findAll() {
		//¸£Àû¸´Ñ¡¿ò
		return sdao.getWelfareMapper().findAll();
	}

}
