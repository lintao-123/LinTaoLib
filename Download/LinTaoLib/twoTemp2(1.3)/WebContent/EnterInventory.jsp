<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图书进货表</title>
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
			idField:"bookNo",//主键字段
			fit:true,
			/* url:"json/Data.json",//数据源路径 */
			url:"EnterInventoryServlet?method=findAll",
			fitColumns:true,//自动填充列，计算列宽
			rownumbers:true,//增加一个行号列
			striped:true,
			loadMsg:"正在加载数据请稍后。。。。",//显示数据信息
			frozenColumns:[[
				{"field":"checkbox","checkbox":true}
			]],
			columns:[[
				{field:"bookNo",title:"图书编号",align:"center",width:180,hidden:true},
				{field:"bookName",title:"图书名称",align:"center",width:180},
				{field:"purchaseQuantity",title:"采购数量",align:"center",width:180},
				{field:"bookBid",title:"图书进价",align:"center",width:180},
				{field:"inventoryTime",title:"入库时间",align:"center",width:180},
				{field:"consoleOperator",title:"操作员",align:"center",width:180}
			]],
			pagination:true,
			pageSize:12,
			pageList:[12,16,20],//页面记录数选择列表
			pagePosition:"bottom",
			toolbar:[//菜单工具栏
				{
					text:"增加",
					iconCls:"icon-add",//按钮上方的图标
					handler:function(){//按钮点击时执行的函数
						addC();
						$("#tr1").attr("hidden","hidden");
					}
				},"-",{	
					text:"修改",
					iconCls:"icon-edit",//按钮上方的图标
					handler:function(){//按钮点击时执行的函数
						updateC();
						$("#tr1").attr("hidden","hidden");
					}
				},"-",{	
					text:"删除",
					iconCls:"icon-cancel",//按钮上方的图标
					handler:function(){//按钮点击时执行的函数
						deleteC();
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
	
	
	//增加新商品信息
	function addC(){
		//获取表单
		var $form=$("#cform");
		//重置表单数据（清空）
		$form.form("reset");
		//在对话框中增加两个按钮，并设置相应的执行函数
		$("#cdialog").dialog({
			title:"添加数据",
			iconCls:"icon-add",
			buttons:[{
				text:"增加",
				iconCls:"icon-ok",
				handler:function(){
					if(!$form.form("validate")){
						$.messager.show({
							title:"友情提示",
							msg:"数据验证没有通过，不能保存商品信息"
						});
						return;
					}
					$.ajax({
						type:"post",
						url:"EnterInventoryServlet?method=enterInventoryAdd",
						cache:false,
						data:$form.serialize(),
						dataType:"text",
						success:function(message){
							if(message.indexOf("success")!=-1){
								//给出操作成功提示
								$.messager.show({
									title:"提示",
									msg:"添加成功！"
								});
								//添加成功后：
								//清空添加界面数据
								$("#cform").form("reset");
								$("#cdialog").dialog("close");
								$("#dg").datagrid("reload");
							}else{
								$.messager.show({
									title:"提示",
									msg:"添加失败!"
									
								});
							}
						}
					});
				}
			},{
				text:"取消",
				iconCls:"icon-no",
				handler:function(){
					$("#cdialog").dialog("close");
				}
			}]
		});
		$("#cdialog").dialog("open");
	}
	
	
	//删除，可以删除多行
	function deleteC(){
		var array=$("#dg").datagrid("getSelections");
		if(array.length==0){
			$.messager.show({
				title:"提示",
				msg:"请至少执行一行数据进行删除。。。。"
			});
			return;
		}else{
			$.messager.confirm("确认","您真的想要删除数据吗？",function(r){
				if(r){
					var cidStr="";
					for(var i=0;i<array.length;i++){
						cidStr+=array[i].bookNo+",";
						
					}
					cidStr=cidStr.substring(0,cidStr.length-1);
					$.post("EnterInventoryServlet?method=enterInventoryDelete",{cArray:cidStr},
						function(message){
							if(message.indexOf("success")!=-1){
								$.messager.show({
									title:"提示",
									msg:"已经成功删除信息"
								});
								$("#dg").datagrid("reload");
								$("#dg").datagrid("clearSelections");
								
							}else{
								$.messager.show({
									title:"提示",
									msg:"删除失败！"
								});
							}															
						},"text");
				}
			});
		}
	}
	
	//修改操作
	function updateC(){
		var array=$("#dg").datagrid("getSelections");
		if(array.length==0){
			$.messager.show({
				title:"提示",
				msg:"请选择一行数据进行修改"
			});
			return;
		}
		//没有问题
		if(array.length>1){
			$.messager.show({
				title:"提示",
				msg:"请只选择一行数据进行修改"
			});
			return ;
		}
		//没有问题
		//将选择数据放置到对话框的表单中
		$("#cform").form("load",{
			bookNo:array[0].bookNo,
			bookName:array[0].bookName,
			purchaseQuantity:array[0].purchaseQuantity,
			bookBid:array[0].bookBid,
			inventoryTime:array[0].inventoryTime,
			consoleOperator:array[0].consoleOperator
		});
		
		//没有问题
		
		$("#cdialog").dialog({
			title:"修改数据",
			iconCls:"icon-edit",
			buttons:[{
				text:"修改",
				iconCls:"icon-ok",
				handler:function(){
					if(!$("#cform").form("validate")){
						$.messager.show({
							title:"提示",
							msg:"数据验证不通过，不能保存数据！"
						});
						return ;
					}
					$.ajax({
						type:"post",
						url:"EnterInventoryServlet?method=enterInventoryUpdate",
						cache:false,
						data:$("#cform").serialize(),
						dataType:"text",
						success:function(message){
							if(message.indexOf("success")!=-1){
								$.messager.show({
									title:"提示",
									msg:"信息修改成功！"
								});
								$("#cform").form("reset");
								$("#cdialog").dialog("close");
								$("#dg").datagrid("reload");
							}else{
								$.messager.show({
									title:"提示",
									msg:"信息修改失败！"
								});
							}
						}
					});
				}
			},{
				//取消按钮
				text:"取消",
				iconCls:"icon-no",
				handler:function(){
					$("#cdialog").dialog("close");
				}
			}]
		});
		//打开对话框
		$("#cdialog").dialog("open");
	}

</script>
</head>
<body class="easyui-layout" id="layout">   
	<!-- 显示数据的表格 -->
	<div data-options="region:'center',title:'图书进货表'" style="padding:5px;background:#eee;height:520px">
	<table id="dg">
	</table>
	</div>   
	<!-- 增加/修改界面 -->
	<div id="cdialog" class="easyui-dialog" title="新增" 
	style="width:'380px';height:'300px';" 
	data-options="iconCls:'icon-add',resizable:false,modal:true,closed:true">
		<form id="cform" >
			<!-- 采用表格布局 -->
			<table id="mytable">
				<tr id="tr1">
					<td class="tdalign" >图书编号</td>
					<td><input type="text" name="bookNo"  /></td>
				</tr>
				<tr>
					<td class="tdalign">图书名称</td>
					<td><input type="text" name="bookName" class="easyui-validatebox"
						data-options="required:true,missingMessage:'请输入图书名称'"/></td>
				</tr>
				<tr>
					<td class="tdalign">采购数量</td>
					<td><input type="text" name="purchaseQuantity" 
						class="easyui-numberspinner"
						data-options="required:true,missingMessage:'请输入采购数量'"/></td>
				</tr>
				<tr>
					<td class="tdalign">图书进价</td>
					<td><input type="text" name="bookBid" class="easyui-validatebox"
						data-options="required:true,missingMessage:'请输入图书进价'"/></td>
				</tr>
				<tr>
					<td class="tdalign">入库时间</td>
					<td><input type="text" name="inventoryTime" class="easyui-datebox"
						data-options="required:true,missingMessage:'请输入图书人库时间'"/></td>
				</tr>
				<tr>
					<td class="tdalign">操作员</td>
					<td><select class="easyui-combobox" name="consoleOperator" style="width:142px;">
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
</body>
</html>