<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>销售表</title>
<!-- 导入easyUI样式 -->
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<!-- 导入js库文件 -->
<script type="text/javascript" src="easyui/jquery.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="easyui/datagrid-filter.js"></script>
<script type="text/javascript">
$(function(){
	var i=2;
	//显示数据
	$("#dg").datagrid({
		idField:"bookno",//主键字段
		fit:true,//填充容器
		/* url:"PurchaseInfoServlet?method=findAll", */
		url:"SalestableServlet?method=findAll", 
		fitColumns:true,//自动填充列
		rownumbers:true,//增加行号列
		striped:true,
		loadMsg:"正在加载，请稍候。。。",
		frozenColumns:[[//冻结列，固定在表格的左边
			{field:"checkbox",checkbox:true},
		]],
		columns:[[
			{field:"bookno",title:"销售编号",align:"center",width:200,hidden:true},
			{field:"ordernumber",title:"销售订单号 ",align:"center",width:200},
			{field:"bookname",title:"图书名称",align:"center",width:200},
			{field:"salesnumber",title:"销售数量",align:"center",width:200},
			{field:"shouldprice",title:"应收价格",align:"center",width:200},
			{field:"realityprice",title:"实收价格",align:"center",width:200},
			{field:"salestime",title:"销售日期",align:"center",width:200},
			{field:"saleser",title:"操作员",align:"center",width:200},
 
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
	var $form=$("#salestableform");
	//清空表单
	$form.form("reset");
	//在对话框(表单)增加两个按钮，并设置相应的执行函数
	$("#salestabledialog").dialog({
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
					url:"SalestableServlet?method=salestableAdd",
					cache:false,
					data:$form.serialize(),//数据序列化
					dataType:"text",
					success:function(message){
						if(message.indexOf("success")!=-1){
							//给出添加操作成功的提示
							$.messager.show({
								title:"友情提示",
								msg:" 销售数据，添加成功！"
							});
							//添加成功后，清空数据
							$("#salestableform").form("reset"),
							//将对话框关闭
							$("#salestabledialog").dialog("close"),
							//重新加载
							$("#dg").datagrid("reload");
						}else{
							//给出添加失败操作提示
							$.messager.show({
								title:"友情提示",
								msg:" 销售数据，添加失败!"
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
				$("#salestabledialog").dialog("close");
			}
		}
		]
	});
	//打开窗口
	$("#salestabledialog").dialog("open");
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
				var booknoStr="";
				for(var i=0;i<array.length;i++){
					booknoStr+=array[i].bookno+",";
				}
				//将最后一个逗号去掉
				booknoStr=booknoStr.substring(0,booknoStr.length-1);
				//调用后台服务程序实现删除
				$.post("SalestableServlet?method=salestableDelete",{salestableArray:booknoStr},
						function(message){
					if(message.indexOf("success")!=-1){
						$.messager.show({
							title:"友情提示",
							msg:" 销售数据删除成功"
						});
						//重新加载
						$("#dg").datagrid("reload");
						//清除选择项
						$("#dg").datagrid("clearSelections");
					}else{
						$.messager.show({
							title:"友情提示",
							msg:" 销售数据删除失败"
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
	$("#salestableform").form("load",{
		bookno:array[0].bookno,
		ordernumber:array[0].ordernumber,
		bookname:array[0].bookname,
		salesnumber:array[0].salesnumber,
		shouldprice: array[0].shouldprice,
		realityprice:array[0].realityprice,
		salestime:array[0].salestime,
		saleser:array[0].saleser,
	});
	//设置修改对话框相关属性
	$("#salestabledialog").dialog({
		title:"修改销售数据",
		iconCls:"icon-edit",
		//增加两个按钮
		buttons:[{
			text:"修改",
			iconCls:"icon-ok",
			handler:function (){
				if(!$("#salestableform").form("validate")){
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
					url:"SalestableServlet?method=salestableUpdate",
					cecha:false,
					data:$("#salestableform").serialize(),
					dataType:"text",
					success:function(message){
						if(message.indexOf("success")!=-1){
							$.messager.show({
								title:"友情提示",
								msg:"销售数据修改成功!"
							});
							//数据添加成功后
							$("#salestableform").form("reset");
							//关闭对话框
							$("#salestabledialog").dialog("close");
							//重新加载
							$("#dg").datagrid("reload");
						}else{
							$.messager.show({
								title:"友情提示",
								msg:"销售数据修改失败!"
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
				$("#salestabledialog").dialog("close");
			}
		}
		]
		
	});
	//打开窗口
	$("#salestabledialog").dialog("open");
}
</script>
</head>
<body class="easyui-layout" id="layout">
	<!-- 显示数据的表格 -->
	<div data-options="region:'center',title:'销售表'" style="padding:5px;background:#eee;">
	<table id="dg">
	</table>
	</div>
	
	<!-- 增加/修改界面 -->
	<div id="salestabledialog" class="easyui-dialog" title="新增" 
	style="width:380px;height:300px;" 
	data-options="iconCls:'icon-add',resizable:false,modal:true,closed:true">
		<form id="salestableform">
			<!-- 采用表格布局 -->
			<table id="mytable">
				<tr id="tr1">
					<td class="tdalign">销售编号</td>
					<td><input type="text" name="bookno"/></td>
				</tr>
				<tr>
					<td class="tdalign">销售订单号</td>
					<td><input type="text" name="ordernumber" class="easyui-validatebox"
						data-options="required:true,missingMessage:'请输入销售订单号'"/></td>
				</tr>
				<tr>
					<td class="tdalign">图书名称</td>
					<td><input type="text" name="bookname" class="easyui-validatebox"
						data-options="required:true,missingMessage:'请输入图书名称'"/></td>
				</tr>
				<tr>
					<td class="tdalign">销售数量</td>
					<td><input type="text" name="salesnumber" 
						class="easyui-numberspinner"
						data-options="required:true,missingMessage:'请输入销售数量'"/></td>
				</tr>
				<tr>
					<td class="tdalign">应收价格</td>
					<td><input type="text" name="shouldprice" class="easyui-numberbox"
						data-options="required:true,missingMessage:'请输入应收价格'"/></td>
				</tr>
				<tr>
					<td class="tdalign">实收价格</td>
					<td><input type="text" name="realityprice" class="easyui-numberbox"
						data-options="required:true,missingMessage:'请输入实收价格'"/></td>
				</tr>
				<tr>
					<td class="tdalign">销售日期</td>
					<td><input type="text" name="salestime" class="easyui-datebox"
						data-options="required:true,missingMessage:'请输入销售日期'"/></td>
				</tr>
				<tr>
					<td class="tdalign">操作员</td>
					<td><select class="easyui-combobox" name="saleser" style="width:142px;">
						 	<option value="刘斌">刘斌</option>
						 	<option value="江流">江流</option>
						 	<option value="朱瑾涛">朱瑾涛</option>
						 	<option value="李定金">李定金</option>
						 	<option value="肖云飞">肖云飞</option>
						 </select>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>