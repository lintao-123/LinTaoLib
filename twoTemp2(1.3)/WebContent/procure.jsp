<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>采购报损表</title>
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
	//显示datagrid中数据
	$("#dg").datagrid({
		idField:"bookid",
		fit:true,
		url:"ProcureServlet?method=findAll",
		fitColumns:true,
		rownumbers:true,
		striped:true,
		loadMsg:"正在加载数据，请稍候....",
		frozenColumns:[[
			{field:"checkbox",checkbox:true}
		]],
		columns:[[
			{field:"bookid",title:"编号",align:"center",width:100,hidden:true},
			{field:"ordernumber",title:"采购订单号",align:"center",width:100},
			{field:"bookname",title:"图书名称",align:"center",width:100},
			{field:"salesvolume",title:"图书数量",align:"center",width:100},
			{field:"bid",title:"图书进价",align:"center",width:100},
			{field:"total",title:"图书总额",align:"center",width:100},
			{field:"salesperiods",title:"报损日期",align:"center",width:100},
			{field:"operator",title:"操作员",align:"center",width:100}
		]],
		//分页工具栏
		pagination:true,
		pageSize:12,
		pageList:[12,16,20],
		pagePosition:"bottom",
		//datagrid操作菜单工具栏
		toolbar:[
			//增加按钮
			{
				text:"增加",
				iconCls:"icon-add",
				handler:function(){
					addStock();
					$("#tr1").attr("hidden","hidden");
				}
				
			},"-",{ //修改按钮
				text:"修改",
				iconCls:"icon-edit",
				handler:function(){
					updateStock();
					$("#tr1").attr("hidden","hidden");
				}
			},"-",{ //删除按钮
				text:"删除",
				iconCls:"icon-cancel",
				handler:function(){
					deleteStock();
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
function addStock(){
	var $form=$("#stockform");
	//重置
	$form.form("reset");
	//打开新增对话框，并添加按钮及事件
	$("#stockdialog").dialog({
		title:"添加数据",
		iconCls:"icon-add",
		buttons:[{
			//增加
			text:"增加",
			iconCls:"icon-add",
			handler:function(){
				//表单数据验证不通过
				if(!$form.form("validate")){
					$.messager.show({
						title:"提示",
						msg:"数据验证没有通过，不能保存数据"
					});
					return ;
				}
				//表单数据验证通过，则进行增加操作
				$.ajax({
					type:"post",
					url:"ProcureServlet?method=addProcure",
					cache:false,
					data:$form.serialize(),
					dataType:"text",
					success:function(message){
						if(message.indexOf("success")!=-1){
							$.messager.show({
								title:"提示",
								msg:"数据保存成功!"
							});
							$form.form("reset");
							$("#stockdialog").dialog("close");
							$("#dg").datagrid("reload");
						}else{
							$.messager.show({
								title:"提示",
								msg:"数据保存失败!"
							});
						}
					}
				});
			}
			
		},{
			//取消
			text:"取消",
			iconCls:"icon-no",
			handler:function(){
				//关闭窗口
				$("#stockdialog").dialog("close");
			}
		}]
	});
	
	$("#stockdialog").dialog("open");
}

//删除
function deleteStock(){
	var array=$("#dg").datagrid("getSelections");
	//判断行数进行操作
	if(array.length==0){
		$.messager.show({
			title:"提示",
			msg:"请至少选择一行数据进行删除"
		});
	}else{
		//删除前确认
		$.messager.confirm("确认","是否真的删除?",function(r){
			if(r){
				var indentStr="";
				for(var i=0;i<array.length;i++){
					indentStr+=array[i].bookid+",";
				}
				//去掉最后一个","号
				indentStr=indentStr.substring(0,indentStr.length-1);
				//post提交
				$.post("ProcureServlet?method=deleteProcure",
						{procureArray:indentStr},
						function(message){
							if(message.indexOf("success")!=-1){
								$.messager.show({
									title:"提示",
									msg:"删除成功!"
								});
								//取消选中行
								$("#dg").datagrid("clearSelections");
								//重新加载
								$("#dg").datagrid("reload");
							}else{
								$.messager.show({
									title:"提示",
									msg:"删除失败!"
								});
							}
						},"text");
			}
		});
	}
}

//修改
function updateStock(){
	var array=$("#dg").datagrid("getSelections");
	var $form=$("#stockform");
	//判断选择行数
	if(array.length==0){
		$.messager.show({
			title:"提示",
			msg:"请选择一行进行修改"
		});
		return ;
	}
	if(array.length>1){
		$.messager.show({
			title:"提示",
			msg:"选择的行数超过一行，只能选择一行"
		});
		return ;
	}
	//将数据加载到修改的表单中
	$("#stockform").form("load",{
		bookid:array[0].bookid,
		ordernumber:array[0].ordernumber,
		bookname:array[0].bookname,
		salesvolume:array[0].salesvolume,
		bid:array[0].bid,
		salesperiods:array[0].salesperiods,
		operator:array[0].operator,
	});
	//打开修改对话框,设置标题，图标,ajax操作等
	$("#stockdialog").dialog({
		title:"修改图书数据",
		iconCls:"icon-edit",
		//按钮
		buttons:[{
			text:"修改",
			iconCls:"icon-ok",
			handler:function(){//类似于添加
				//表单数据验证不通过
				if(!$form.form("validate")){
					$.messager.show({
						title:"提示",
						msg:"数据验证没有通过，不能保存数据"
					});
					return ;
				}
				//提交修改
				
				$.post("ProcureServlet?method=updateProcure",
					$form.serialize(),
					function(message){
						if(message.indexOf("success")!=-1){
						$.messager.show({
							title:"提示",
							msg:"修改成功!"
						});
						$("#stockform").form("reset");
						//关闭对话框
						$("#stockdialog").dialog("close");
						//取消选中行
						$("#dg").datagrid("clearSelections");
						//重新加载
						$("#dg").datagrid("reload");
					}else{
						$.messager.show({
							title:"提示",
							msg:"修改失败!"
						});
					}
				},"text");
			}
		},{
			//取消
			text:"取消",
			iconCls:"icon-no",
			handler:function(){
				//关闭窗口
				$("#stockdialog").dialog("close");
			}
		}]
	});
	//打开对话框
	$("#stockdialog").dialog("open");
}
</script>
</head>
<body class="easyui-layout" >      
	<div data-options="region:'center',title:'采购报损表'" style="padding:5px;background:#eee;">     
    <!-- datagrid -->
	<table id="dg">
	</table>
	<!-- 新增/修改窗口 -->
	<div id="stockdialog" class="easyui-dialog" title="增加" style="width:330px;height:280px;"
	data-options="iconCls:'icon-add',resizable:false,modal:true,closed:true">
		<!-- 表单 -->
		<form id="stockform">
			<!-- 采用表格布局 -->
			<table id="stocktable">
				<tr id="tr1">
					<td>编号</td>
					<td>
						<input type="text" name="bookid"  />
					</td>
				</tr>
				<tr>
					<td>采购订单号</td>
					<td>
						<input type="text" name="ordernumber" class="easyui-validatebox"
						data-options="required:true,missingMessage:'请输入采购订单号'"/>
					</td>
				</tr>
				<tr>
					<td>图书名称</td>
					<td>
						<input type="text" name="bookname" class="easyui-validatebox"
						data-options="required:true,missingMessage:'请输入图书名称'"/>
					</td>
				</tr>
				<tr>
					<td>图书数量</td>
					<td>
						<input type="text" name="salesvolume" 
						class="easyui-numberspinner"
						data-options="required:true,missingMessage:'请输入采购数量'"/></td>
					</td>
				</tr>
				<tr>
					<td>图书进价</td>
					<td>
						<input type="text" name="bid" class="easyui-validatebox"
						data-options="required:true,missingMessage:'请输入图书进价'"/></td>
					</td>
				</tr>
				<tr>
					<td>报损日期</td>
					<td>
						<input type="text" name="salesperiods" class="easyui-datebox"
						data-options="required:true,missingMessage:'请输入报损日期'"/>
					</td>
				</tr>
				<tr>
					<td>操作员</td>
					<td>
						<select class="easyui-combobox" name="operator" style="width:142px;">
						 	<option value="刘斌">刘斌</option>
						 	<option value="江流">江流</option>
						 	<option value="朱瑾涛">朱瑾涛</option>
						 	<option value="李定金">李定金</option>
						 	<option value="肖云飞">肖云飞</option>
						 </select>
					</td>
				</tr>
			 </table>
		</form>
	</div>
	</div>
</body>
</html>