package com.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;



import com.alibaba.fastjson.serializer.PropertyFilter;

public class EmpUtil {
  /**
   * 输出字符串
   * */
	public static void print(HttpServletResponse response,String str){
		//将字符串转换字符串转化为
		
		response.setCharacterEncoding("utf-8");//修改响应编码格式
		PrintWriter out=null;
		 try {
			out=response.getWriter();
			out.print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
	}
	 /**
	   * 输出整数
	   * */
	//传给前台判断是否成功增删改传只
		public static void print(HttpServletResponse response,int code){
			response.setCharacterEncoding("utf-8");
			PrintWriter out=null;
			 try {
				out=response.getWriter();
				out.print(code);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				out.flush();
				out.close();
			}
		}
		 /**
		   * 属性过滤器
		   * */
		public static PropertyFilter filterProperty(final String...propertNames){
			PropertyFilter pf=new PropertyFilter() {
				public boolean apply(Object arg0, String pname, Object arg2) {
					for (String propertName : propertNames) {
						if(propertName.equals(pname)){//查找是否存在要过滤的属性
							return false;//过滤此属性
						}
					}
					return true;//不过滤此属性
				}
			};
			
			return pf;
			}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
