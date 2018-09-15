package com.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.po.Dep;
import com.po.Emp;
import com.po.PageBean;
import com.po.Welfare;
import com.service.BizService;
import com.util.*;

@Controller
public class EmpAction {
	@Resource(name="BizService")
	private BizService sbiz;

	public BizService getSbiz() {
		return sbiz;
	}

	public void setSbiz(BizService sbiz) {
		this.sbiz = sbiz;
	}
	@RequestMapping(value="save_Emp.do")
	public String save(HttpServletRequest request,
			HttpServletResponse response, Emp emp){//�����ļ��ϴ�����
			String realpath=request.getRealPath("/");
			
					MultipartFile multipartFile = emp.getPic();
			if (multipartFile != null && !multipartFile.isEmpty()) {
				// ��ȡ�ϴ����ļ�����
				String fname = multipartFile.getOriginalFilename();
				// ����
				if (fname.lastIndexOf(".") != -1) {// ���ں�׺
					// ��ȡ��׺��
					String ext = fname.substring(fname.lastIndexOf("."));

					// �жϺ�׺�Ƿ�Ϊjpg��ʽ
					if (ext.equalsIgnoreCase(".jpg")) {
						// ����
						String newfname = new Date().getTime() + ext;
						// �����ļ�����ָ���ϴ��ļ���·��
						File destFile = new File(realpath + "/uppic/" + newfname);
						// �ϴ�
						try {
							FileUtils.copyInputStreamToFile(
									multipartFile.getInputStream(), destFile);
							emp.setPhoto(newfname);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			System.out.println("emp========="+emp);
		boolean flag=sbiz.getEmpbiz().save(emp);
		if(flag){
			EmpUtil.print(response, 1);
		}else{
			EmpUtil.print(response, 0);
		}
		
		return null;
	}
	@RequestMapping(value="update_Emp.do")
	public String update(HttpServletRequest request,
			HttpServletResponse response, Emp emp){
				String oldphon=sbiz.getEmpbiz().findById(emp.getEid()).getPhoto();
				String realpath=request.getRealPath("/");	
				MultipartFile multipartFile = emp.getPic();
				if (multipartFile != null && !multipartFile.isEmpty()) {
					// ��ȡ�ϴ����ļ�����
					String fname = multipartFile.getOriginalFilename();
					// ����
					if (fname.lastIndexOf(".") != -1) {// ���ں�׺
						// ��ȡ��׺��
						String ext = fname.substring(fname.lastIndexOf("."));

						// �жϺ�׺�Ƿ�Ϊjpg��ʽ
						if (ext.equalsIgnoreCase(".jpg")) {
							// ����
							String newfname = new Date().getTime() + ext;
							// �����ļ�����ָ���ϴ��ļ���·��
							File destFile = new File(realpath + "/uppic/" + newfname);
							// �ϴ�
							try {
								FileUtils.copyInputStreamToFile(
										multipartFile.getInputStream(), destFile);
								emp.setPhoto(newfname);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}else{
					emp.setPhoto(oldphon);
				}
		boolean flag=sbiz.getEmpbiz().update(emp);
		if(flag){
			EmpUtil.print(response, 1);
		}else{
			EmpUtil.print(response, 0);
		}
		
		
		return null;
	}
	@RequestMapping(value="delById_Emp.do")
	public String delById(HttpServletRequest request,
			HttpServletResponse response,Integer eid){
		String oldphon=sbiz.getEmpbiz().findById(eid).getPhoto();
		String realpath=request.getRealPath("/");		
		boolean flag=sbiz.getEmpbiz().delById(eid);
		if(flag){
			File oldfile=new File(realpath+"/uppic/"+oldphon);
			if(oldfile.exists()&&!oldphon.equalsIgnoreCase("default.jpg")){
				
				oldfile.delete();
			}
			EmpUtil.print(response, 1);
			
		}else{
			EmpUtil.print(response, 0);
		}
		
		
		return null;
	}
	/**
	 * �΂�����
	 * 
	 * */
	
	@RequestMapping(value="findById_Emp.do")
	public String findById(HttpServletRequest request,
			HttpServletResponse response,Integer eid){
		Emp oldemp=sbiz.getEmpbiz().findById(eid);
		
		PropertyFilter propertyFilter=EmpUtil.filterProperty("birthday","pic");
		String jsonstr=JSONObject.toJSONString(oldemp, propertyFilter, SerializerFeature.DisableCircularReferenceDetect);
		EmpUtil.print(response, jsonstr);
		System.out.println("jstr--->"+jsonstr);
		return null;
		
	}
	/**
	 * �鿴����
	 * 
	 * */
	@RequestMapping(value="findDetail_Emp.do")
	public String findDetail(HttpServletRequest request,
			HttpServletResponse response,Integer eid){
		Emp oldemp=sbiz.getEmpbiz().findById(eid);
		
		PropertyFilter propertyFilter=EmpUtil.filterProperty("birthday","pic");
		String jsonstr=JSONObject.toJSONString(oldemp, propertyFilter, SerializerFeature.DisableCircularReferenceDetect);
		EmpUtil.print(response, jsonstr);
		System.out.println("jstr1111--->"+jsonstr);
		return null;
		
	}
	
	/**
	 * ��ҳ��
	 * 
	 * */
	
	@RequestMapping(value="findPageAll_Emp.do")
	public String findPageAll(HttpServletRequest request,
			HttpServletResponse response,Integer page,Integer rows){
Map<String,Object> map=new HashMap<String, Object>();
		
		PageBean pb=new PageBean();
		
		page=page==null||page<1?pb.getPage():page;
		rows=rows==null||rows<1?pb.getRows():rows;
		
		if(rows>10)rows=10;
		pb.setPage(page);
		pb.setRows(rows);
		//��ȡ��ǰҳ�ļ�¼����
		List<Emp> lsemp=sbiz.getEmpbiz().findPageAll(pb);
		
		//��ȡ�ܼ�¼��
		int maxrow=sbiz.getEmpbiz().findMaxRow();
		
		map.put("page", page);
		map.put("rows", lsemp);//ǰ̨easyui��ҳ�����õ�lsemp��total
		map.put("total", maxrow);
		
		PropertyFilter propertyFilter=EmpUtil.filterProperty("birthday","pic");
		String jsonstr=JSONObject.toJSONString(map, propertyFilter, SerializerFeature.DisableCircularReferenceDetect);
		EmpUtil.print(response, jsonstr);
				
		
		
		return null;
		
	}
	/***
	 * 
	 * ��ʼ��
	 * 
	 * 
	 * */
	
	@RequestMapping(value="doinit_Emp.do")
	public String doinit(HttpServletRequest request,
			HttpServletResponse response){
			Map<String,Object>	map=new HashMap<String, Object>();
			List<Dep> lsdep=sbiz.getDepbiz().findAll();
			List<Welfare> lswf=sbiz.getWelbiz().findAll();
			map.put("lswf", lswf);
			map.put("lsdep", lsdep);	
			PropertyFilter propertyFilter=EmpUtil.filterProperty("birthday","pic");
			String jsonstr=JSONObject.toJSONString(map, propertyFilter, SerializerFeature.DisableCircularReferenceDetect);
			EmpUtil.print(response, jsonstr);
		return null;
	}
	
	

}
