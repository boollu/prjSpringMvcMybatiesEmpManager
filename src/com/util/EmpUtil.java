package com.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;



import com.alibaba.fastjson.serializer.PropertyFilter;

public class EmpUtil {
  /**
   * ����ַ���
   * */
	public static void print(HttpServletResponse response,String str){
		//���ַ���ת���ַ���ת��Ϊ
		
		response.setCharacterEncoding("utf-8");//�޸���Ӧ�����ʽ
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
	   * �������
	   * */
	//����ǰ̨�ж��Ƿ�ɹ���ɾ�Ĵ�ֻ
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
		   * ���Թ�����
		   * */
		public static PropertyFilter filterProperty(final String...propertNames){
			PropertyFilter pf=new PropertyFilter() {
				public boolean apply(Object arg0, String pname, Object arg2) {
					for (String propertName : propertNames) {
						if(propertName.equals(pname)){//�����Ƿ����Ҫ���˵�����
							return false;//���˴�����
						}
					}
					return true;//�����˴�����
				}
			};
			
			return pf;
			}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
