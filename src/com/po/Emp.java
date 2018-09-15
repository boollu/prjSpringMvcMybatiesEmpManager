package com.po;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;
public class Emp implements Serializable {
	private Integer eid;
	private String ename;
	private String sex;
	private String address;
	private Date birthday;
	private String photo="default.jpg";
	private Integer depid;
	//与界面关联的属性
	private String depname;
	private Float emoney;//薪资
	
	//与福利关联的属性s
	private Integer[] wids;//福利的编号数组，接受界面的复选框值待定
	private List<Welfare> lswf;//当前员工的福利集合
	
	//与照片上传关联的属性
	private MultipartFile pic;
	
	//生日日期的转换属性
	private String sdate;

	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Emp(Integer eid, String ename, String sex, String address,
			Date birthday, String photo, Integer depid, String depname) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.sex = sex;
		this.address = address;
		this.birthday = birthday;
		this.photo = photo;
		this.depid = depid;
		this.depname = depname;
	}

	public Emp(String ename, String sex, String address, Date birthday,
			String photo, Integer depid) {
		super();
		this.ename = ename;
		this.sex = sex;
		this.address = address;
		this.birthday = birthday;
		this.photo = photo;
		this.depid = depid;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getDepid() {
		return depid;
	}

	public void setDepid(Integer depid) {
		this.depid = depid;
	}

	public String getDepname() {
		return depname;
	}

	public void setDepname(String depname) {
		this.depname = depname;
	}

	public Float getEmoney() {
		return emoney;
	}

	public void setEmoney(Float emoney) {
		this.emoney = emoney;
	}

	public Integer[] getWids() {
		return wids;
	}

	public void setWids(Integer[] wids) {
		this.wids = wids;
	}

	public List<Welfare> getLswf() {
		return lswf;
	}

	public void setLswf(List<Welfare> lswf) {
		this.lswf = lswf;
	}

	public MultipartFile getPic() {
		return pic;
	}

	public void setPic(MultipartFile pic) {
		this.pic = pic;
	}

	public String getSdate() {
		if(birthday!=null){
			sdate=new SimpleDateFormat("yyyy-MM-dd").format(birthday);
		}
		return sdate;
	}

	public void setSdate(String sdate) {
		if(sdate!=null&&!sdate.trim().equals("")){
			try {
				birthday=new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		this.sdate = sdate;
	}

	@Override
	public String toString() {
		return "Emp [ename=" + ename + ", sex=" + sex + ", address=" + address
				+ ", birthday=" + birthday + ", photo=" + photo + ", depid="
				+ depid + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
