<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css"/>
<script type="text/javascript" src="easyui/jquery-1.9.1.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script>
	$(function(){
		$("#winempdetail").window('close');
		
		//生成复选框
		$.getJSON('doinit_Emp.do',function(map){
			var lswf=map.lswf;
			var lsdep=map.lsdep;
			for(var i=0;i<lswf.length;i++){
				var wf=lswf[i];
				$("#wf").append("<input type='checkbox' name='wids' value='"+wf.wid+"'/>"+wf.wname);
				
			}
			
			//给下拉列表赋值
			$('#depid').combobox({    
				data:lsdep,    
				panelHeight:80,
				valueField:'depid',    
				textField:'depname',
				value:1   
			});  
		});
		
		
		
	
	});
	
	/******************员工列表************************************/
	$(function(){
		$('#dg').datagrid({
			url : 'findPageAll_Emp.do?t='+new Date().getTime(),
			singleSelect:true,
			striped:true,
			width:'890',
			pagination:true,
			pageList:[5,10,15,20],
			pageSize:5,
			columns : [ [ {
				field : 'eid',
				title : '编号',
				width : 100,
				align:'center'
				
			}, {
				field : 'ename',
				title : '姓名',
				width : 100,
				align:'center'
			}, {
				field : 'sex',
				title : '性别',
				width : 100,
				align:'center'
			}, {
				field : 'address',
				title : '地址',
				width : 100,
				align:'center'
			}, {
				field : 'sdate',
				title : '生日',
				width : 100,
				align:'center'
			},{
				field : 'photo',
				title : '照片',
				width : 100,
				align:'center',
				formatter:function(value,row,index){
					return '<img src=uppic/'+row.photo+' width="40" height="50">';
				}
			}, {
				field : 'depname',
				title : '部门',
				width : 100,
				align:'center'
			}, {
				field : 'opt',
				title : '操作',
				width : 150,
				align:'center',
				formatter:function(value,row,index){
					var bt1='<input type="button" value="删除" onclick="dodelById('+row.eid+')">';
					var bt2='<input type="button" value="编辑" onclick="findById('+row.eid+')">';
					var bt3='<input type="button" value="详细" onclick="findDetail('+row.eid+')">';
					return bt1+'&nbsp;'+bt2+'&nbsp;'+bt3;
				}
			} ] ]
		});
	});
	/*************************************************************/
	/***************删除和查找*********************************/
	function dodelById(eid){
		//alert(eid);
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){    
		        $.get('delById_Emp.do?eid='+eid,function(code){
		        	if(code=='1'){
						$.messager.alert('提示','删除成功！');
						$('#dg').datagrid('reload');    // 重新载入当前页面数据  
					}else{
						$.messager.alert('提示','删除失败！');
					}
		        });
		    }    
		});  

	}
	
	//查找的函数
	function findById(eid){
		$.getJSON('findById_Emp.do?eid='+eid,function(emp){
			//清除复选框所有的选中
			$(":checkbox[name='wids']").each(function(){
				$(this).prop("checked",false);	//设置选中状态为false
			});
			
			//alert(emp);
			$('#ffemp').form('load',{
				'eid':emp.eid,
				'ename':emp.ename,
				'sex':emp.sex,
				'address':emp.address,
				'sdate':emp.sdate,
				'depid':emp.depid,
				'emoney':emp.emoney
			});

			$("#myphoto").attr('src','uppic/'+emp.photo);
			
			var wids=emp.wids;
			//alert(wids);
			//根据后台数据库中福利的编号，设置复选框的选中状态
			$(":checkbox[name='wids']").each(function(){				
				for(var i=0;i<wids.length;i++){
					if($(this).val()==wids[i]){//将当前员工的福利编号与福利复选框中的福利编号比较,相同，则选中
						$(this).prop("checked",true);//使用prop(...)设置选中状态
					}
				}
			});
		});
	}
	
	//查看详细的函数
	function findDetail(eid){
		$.getJSON('findDetail_Emp.do?eid='+eid,function(emp){
			//给员工详细信息中的文本设置值
			$("#enametxt").html(emp.ename);	
			$("#sextxt").html(emp.sex);	
			$("#addresstxt").html(emp.address);	
			$("#sdatetxt").html(emp.sdate);	
			$("#phototxt").html(emp.photo);	
			$("#depnametxt").html(emp.depname);	
			$("#emoneytxt").html(emp.emoney);
			
			/********获取当前员工的福利********/
			var lswf=emp.lswf;
			var wnames=[];//获取福利名称的数组
			
			for(var i=0;i<lswf.length;i++){
				var wf=lswf[i];//获取emp中的每个福利对象
				wnames.push(wf.wname);//加入到数组
			}
			var strwname=wnames.join(',');//使用,链接数组的元素值
			
			$("#wftxt").html(strwname);//显示在福利文本标签中
			
			$("#dtmyphoto").attr('src','uppic/'+emp.photo);
			
			$("#winempdetail").window("open");
		});
	}
	
	/********************************************************/
	
	/*************保存与修改操作************************************/
	$(function(){
		
		///////////////保存////////////////////////////////////
		$("#btsave").click(function(){
			$.messager.progress();	// 显示进度条
			$('#ffemp').form('submit', {//easyui中表单提交from表单提叫
				url:'save_Emp.do',
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					}
					return isValid;	// 返回false终止表单提交
				},
				success: function(code){
					if(code=='1'){
						$.messager.alert('提示','保存成功！');
						$('#dg').datagrid('reload');    // 重新载入当前页面数据  
					}else{
						$.messager.alert('提示','保存失败！');
					}
					$.messager.progress('close');	// 如果提交成功则隐藏进度条
				}
			});


		});
		/////////////////////////////////////////////////////////
		///////////////修改////////////////////////////////////
		$("#btupdate").click(function(){
			$.messager.progress();	// 显示进度条
			$('#ffemp').form('submit', {
				url:'update_Emp.do',
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					}
					return isValid;	// 返回false终止表单提交
				},
				success: function(code){
					if(code=='1'){
						$.messager.alert('提示','更新成功！');
						$('#dg').datagrid('reload');    // 重新载入当前页面数据  
					}else{
						$.messager.alert('提示','更新失败！');
					}
					$.messager.progress('close');	// 如果提交成功则隐藏进度条
				}
			});


		});
		/////////////////////////////////////////////////////////
	
		//点击取消按钮的事件
		$("#btreset").click(function(){
			$("#ffemp").form("reset");
		});
	});
	/*************************************************************/

</script>

<title>员工管理</title>
</head>

<body>
<p align="center">员工列表</p>
<hr />
<div align="center">
<table id="dg"></table>
</div>
<hr />
<p>&nbsp;</p>
<p>
	<form action="" method="post" enctype="multipart/form-data" name="ffemp" id="ffemp">
	  <table width="550" border="1" align="center" cellpadding="1" cellspacing="0">
	    <tr>
	      <td colspan="3" align="center" bgcolor="#99FFCC">员工管理</td>
        </tr>
	    <tr>
	      <td width="104">姓名</td>
	      <td width="303"><label for="ename"></label>
          <input type="text" name="ename"  id="ename"  class="easyui-validatebox" data-options="required:true"/></td>
          <input type="hidden" name="eid"  id="eid"/></td>
	      <td width="129" rowspan="7"><img id="myphoto" src="uppic/default.jpg" width="129" height="150" /></td>
        </tr>
	    <tr>
	      <td>性别</td>
	      <td><input name="sex" type="radio" id="radio" value="男" checked="checked" />
	        男
          <label for="sex">
            <input type="radio" name="sex" id="radio2" value="女" />
          女</label></td>
        </tr>
	    <tr>
	      <td>地址</td>
	      <td><input type="text" name="address" id="address" class="easyui-validatebox" data-options="required:true" /></td>
        </tr>
	    <tr>
	      <td>生日</td>
	      <td><input name="sdate" type="date" id="sdate" value="1990-01-01" /></td>
        </tr>
	    <tr>
	      <td>照片选择</td>
	      <td><label for="pic"></label>
          <input type="file" name="pic" id="pic" /></td>
        </tr>
	    <tr>
	      <td>部门</td>
	      <td>
          <input type="text" name="depid" id="depid" /></td>
        </tr>
	    <tr>
	      <td>薪资</td>
	      <td>
          <input name="emoney" type="text" id="emoney" value="2000" class="easyui-validatebox" data-options="required:true"/></td>
        </tr>
	    <tr>
	      <td>福利</td>
	      <td colspan="2"><span id="wf"></span></td>
        </tr>
	    <tr>
	      <td colspan="3" align="center" bgcolor="#99FFCC"><input type="button" name="btsave" id="btsave" value="保存" />
          <input type="button" name="btupdate" id="btupdate" value="修改" />
          <input type="button" name="btreset" id="btreset" value="取消" /></td>
        </tr>
      </table>
</form>
</p>
<!-- 加入提示窗口,用于显示员工的详细信息 -->
<div id="winempdetail" class="easyui-window" title="员工详细信息" style="width:600px;height:400px"   
        data-options="iconCls:'icon-save',modal:true">   
	   <table width="550" border="1" align="center" cellpadding="1" cellspacing="0">
		    <tr>
		      <td colspan="3" align="center" bgcolor="#99FFCC">员工详细信息</td>
	        </tr>
		    <tr>
		      <td width="104">姓名</td>
		      <td width="303"><span id="enametxt"></span></td>
		      <td width="129" rowspan="7"><img id="dtmyphoto" src="uppic/default.jpg" width="129" height="150" /></td>
	        </tr>
		    <tr>
		      <td>性别</td>
		      <td><span id="sextxt"></span></td>
	        </tr>
		    <tr>
		      <td>地址</td>
		      <td><span id="addresstxt"></span></td>
	        </tr>
		    <tr>
		      <td>生日</td>
		      <td><span id="sdatetxt"></span></td>
	        </tr>
		    <tr>
		      <td>照片</td>
		      <td><span id="phototxt"></span></td>
	        </tr>
		    <tr>
		      <td>部门</td>
		      <td><span id="depnametxt"></span></td>
	        </tr>
		    <tr>
		      <td>薪资</td>
		      <td><span id="emoneytxt"></span></td>
	        </tr>
		    <tr>
		      <td>福利</td>
		      <td colspan="2"><span id="wftxt"></span></td>
	        </tr>
		   
	      </table>
</div>  

</body>
</html>