<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>操作员管理</title>
<!-- easyui样式 -->
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css" >
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<!-- js库文件-->

<script type="text/javascript" src="easyui/jquery.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="easyui/datagrid-filter.js"></script>
<script type="text/javascript">
$(function(){
	var i=2;
	//显示数据
	$("#dg").datagrid({
		idField:"userId",//主键字段
		fit:true,//填充容器
		/* url:"PurchaseInfoServlet?method=findAll", */
		url:" OperatorsServlet?method=findAll", 
		fitColumns:true,//自动填充列
		rownumbers:true,//增加行号列
		striped:true,
		loadMsg:"正在加载，请稍候。。。",
		frozenColumns:[[//冻结列，固定在表格的左边
			{field:"checkbox",checkbox:true},
		]],
		columns:[[
			{field:"userId",title:"用户编号",align:"center",width:200,hidden:true},
			{field:"name",title:"操作员姓名",align:"center",width:200},
			{field:"address",title:"地址",align:"center",width:200},
			{field:"telephoneNumber",title:"电话号码",align:"center",width:200},
			{field:"empType",title:"员工类型",align:"center",width:200},
			{field:"empMoney",title:"员工工资",align:"center",width:200}
 
		]],
		pagination:true,//如果为true,则显示底部菜单栏
		pageSize:12,
		pageList:[12,16,20],
		pagePosition:"bottom",
		toolbar:[
			{
			text:"添加",
			iconCls:"icon-add",
			handler:function(){
				addPurchaseInfo();
				$("#tr1").attr("hidden" ,"hidden");
			}
		},"-",{
			text:"修改",
			iconCls:"icon-edit",
			handler:function(){
				updatePurchaseInfo();
				$("#tr1").attr("hidden" ,"hidden");
			}
		},"-",{
			text:"删除",
			iconCls:"icon-cancel",
			handler:function(){
				deletePurchaseInfo();
			}
		},"-",{
			text:"查询",
			iconCls:"icon-search",
			handler:function(){
				 if(i/2==1){
	 					$("#dg").datagrid('enableFilter'); // 启用过滤	
	 					i=1;
					}else if(i/2!==1){
						$("#dg").datagrid('destroyFilter'); // 关闭过滤
						i=2;
						}
			}	
		}
		]
	});
});
 //添加
function addPurchaseInfo(){
	//获取表单
	var $form=$("#operatorsform");
	//清空表单
	$form.form("reset");
	//在对话框(表单)增加两个按钮，并设置相应的执行函数
	$("#operatorsdialog").dialog({
		title:"添加新数据",
		iconCls:"icon-add",
		//增加按钮
		buttons:[{
			text:"添加",
			iconCls:"icon-ok",
			handler:function (){
				//先判断验证是否通过
				if(!$form.form("validate")){//未通过
				//显示提示
				$.messager.show({
					title:"友情提示",
					msg:" 数据验证未通过,保存失败!"
				});
				return;
				}
				//验证通过执行添加操作(提交方式)
				$.ajax({
					type:"post",
					url:"OperatorsServlet?method=addOperators",
					cache:false,
					data:$form.serialize(),//数据序列化
					dataType:"text",
					success:function(message){
						if(message.indexOf("success")!=-1){
							//给出添加操作成功的提示
							$.messager.show({
								title:"友情提示",
								msg:" 员工数据，添加成功！"
							});
							//添加成功后，清空数据
							$("#operatorsform").form("reset"),
							//将对话框关闭
							$("#operatorsdialog").dialog("close"),
							//重新加载
							$("#dg").datagrid("reload");
						}else{
							//给出添加失败操作提示
							$.messager.show({
								title:"友情提示",
								msg:" 员工数据，添加失败!"
							});
						}
					}
				});
			}
		},{
			text:"取消",
			iconCls:"icon-no",
			handler:function (){
				//关闭窗口
				$("#operatorsdialog").dialog("close");
			}
		}
		]
	});
	//打开窗口
	$("#operatorsdialog").dialog("open");
} 
//删除
function deletePurchaseInfo(){
	//获取选中的行
	var array=$("#dg").datagrid("getSelections");
	//判断选择的行
	if(array==0){
		//如果选择行数等于零
		$.messager.show({
			title:"友情提示",
			msg:"请至少选择一行"
		});
		return;
	}else{
		//删除前，确认是否真的删除？
		$.messager.confirm("确认","是否真的删除?",function(r){
			if(r){
				//删除多行前，需将编号拼接（以逗号分开）
				var uidStr="";
				for(var i=0;i<array.length;i++){
					uidStr+=array[i].userId+",";
				}
				//将最后一个逗号去掉
				uidStr=uidStr.substring(0,uidStr.length-1);
				//调用后台服务程序实现删除
				$.post("OperatorsServlet?method=deleteOperators",{operatorsArray:uidStr},
						function(message){
					if(message.indexOf("success")!=-1){
						$.messager.show({
							title:"友情提示",
							msg:" 员工数据删除成功"
						});
						//重新加载
						$("#dg").datagrid("reload");
						//清除选择项
						$("#dg").datagrid("clearSelections");
					}else{
						$.messager.show({
							title:"友情提示",
							msg:" 员工数据删除失败"
						});
					}
					},"text");
			}
		});
	}
}
//修改
function updatePurchaseInfo(){
	//获取选中的行
	var array=$("#dg").datagrid("getSelections");
	if(array.length==0){
		$.messager.show({
			title:"友情提示",
			msg:"请至少选择一行进行修改！"
		});
		return;
	}
	if(array.length>1){
		$.messager.show({
			title:"友情提示",
			msg:"只能选择一行进行修改"
		});
		return;
	}
	//对当前行数进行修改
	//将选择行数据放置在表单中
	$("#operatorsform").form("load",{
		userId:array[0].userId,
		  
		name:array[0].name,
		address:array[0].address,
		telephoneNumber:array[0].telephoneNumber,
		empType : array[0].empType,
		empMoney:array[0].empMoney
		 
		
	});
	//设置修改对话框相关属性
	$("#operatorsdialog").dialog({
		title:"修改员工数据",
		iconCls:"icon-edit",
		//增加两个按钮
		buttons:[{
			text:"修改",
			iconCls:"icon-ok",
			handler:function (){
				if(!$("#operatorsform").form("validate")){
					//验证未通过
					$.messager.show({
						tilte:"友情提示",
						msg:"数据验证未通过,不能进行修改"
					});
					return;
				}
				//验证通过则进行修改操作
				$.ajax({
					type:"post",
					url:"OperatorsServlet?method=updateOperators",
					cecha:false,
					data:$("#operatorsform").serialize(),
					dataType:"text",
					success:function(message){
						if(message.indexOf("success")!=-1){
							$.messager.show({
								title:"友情提示",
								msg:"员工数据修改成功!"
							});
							//数据添加成功后
							$("#operatorsform").form("reset");
							//关闭对话框
							$("#operatorsdialog").dialog("close");
							//重新加载
							$("#dg").datagrid("reload");
						}else{
							$.messager.show({
								title:"友情提示",
								msg:"员工数据修改失败!"
							});
						}
						
					}
				});
				
			}
		},{
			//取消按钮
			text:"取消",
			iconCls:"icon-no",
			handler:function (){
				//关闭窗口
				$("#operatorsdialog").dialog("close");
			}
		}
		]
		
	});
	//打开窗口
	$("#operatorsdialog").dialog("open");
}
 
</script>

</head>
<body class="easyui-layout" id="layout">
   
 <div data-options="region:'center',title:'操作员管理'" style="padding:5px;background:#eee;height:520px">
	    <table id="dg">
	    </table>
	    <div id="operatorsdialog" class="easyui-dialog" title="新增数据"
	    style="width:380px ;height:380px;"
	    data-options="iconCls:'icon-add',resizable:false,modal:true,closed:true" >
		    <form id="operatorsform">
		    	<!--  采用表格布局-->
		    	<table id="mytable">
		    		<tr id="tr1" >
 		    			<td>用户编号</td>
		    			<td>
		    			<input type="text" name="userId"  
		    			 data-options="required	:true,missingMessage:'请输入用户编号'"/>
		    			<td>
		    		</tr> 
		    		 
		    		<tr>
		    			<td> 操作员姓名</td>
		    			<td>
		    			<input type="text" name="name" class="easyui-validatebox"
		    			data-options="required:true,missingMessage:'请输入操作员姓名'"/>
		    			</td>
		    		</tr>
		    		 
		    		<tr>
		    			<td>地址</td>
		    			<td>
		    			<input type="text"   name="address" class="easyui-validatebox"
		    			data-options="required:true,missingMessage:'请输入地址'"/>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>电话号码</td>
		    			<td>
		    			<input type="text"   name="telephoneNumber" class="easyui-validatebox"
		    			data-options="required:true,missingMessage:'请输入电话号码'"/>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>员工类型</td>
		    			<td>
		    			<input type="text"   name="empType" class="easyui-validatebox"
		    			data-options="required:true,missingMessage:'请输入员工类型'"/>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>员工工资</td>
		    			<td>
		    			<input type="text"   name="empMoney" class="easyui-validatebox"
		    			data-options="required:true,missingMessage:'请输入员工工资'"/>
		    			</td>
		    		</tr>
		    	</table>
		    </form>
	    </div>
    </div>
</body>
</html>