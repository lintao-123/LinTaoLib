<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图书操作表</title>
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
	//显示datagrid中数据
	$("#dg").datagrid({
		idField:"bookno",
		fit:true,
		url:"BookoperationServlet?method=findAll",
		fitColumns:true,
		rownumbers:true,
		striped:true,
		loadMsg:"正在加载数据，请稍候....",
		frozenColumns:[[
			{field:"checkbox",checkbox:true}
		]],
		columns:[[
			{field:"bookno",title:"图书编号",align:"center",width:100,hidden:true},
			{field:"bookname",title:"图书名称",align:"center",width:100},
			{field:"booktype",title:"图书种类",align:"center",width:100},
			{field:"bookauthor",title:"图书作者",align:"center",width:100},
			{field:"supplier",title:"供应商",align:"center",width:100},
			{field:"bookprice",title:"图书价格",align:"center",width:100},
			{field:"bookunit",title:"图书单位",align:"center",width:100}
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
					url:"BookoperationServlet?method=addbookoperation",
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
					indentStr+=array[i].bookno+",";
				}
				//去掉最后一个","号
				indentStr=indentStr.substring(0,indentStr.length-1);
				//post提交
				$.post("BookoperationServlet?method=deleteBookoperation",
						{bookoperationArray:indentStr},
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
		bookno:array[0].bookno,
		bookname:array[0].bookname,
		booktype:array[0].booktype,
		bookauthor:array[0].bookauthor,
		supplier:array[0].supplier,
		bookprice:array[0].bookprice,
		bookunit:array[0].bookunit
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
				
				$.post("BookoperationServlet?method=updateBookoperation",
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
	<div data-options="region:'center',title:'图书操作表'" style="padding:5px;background:#eee;">    
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
					<td>图书编号</td>
					<td>
						<input type="text" name="bookno">
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
					<td>图书种类</td>
					<td>
						<select class="easyui-combobox" name="booktype" style="width:142px;">
							<option value="哲学">哲学</option>
							<option value="社会科学总论">社会科学总论</option>
							<option value="政治">政治</option>
							<option value="军事">军事</option>
							<option value="经济">经济</option>
							<option value="文学">文学</option>
							<option value="语言">语言</option>
							<option value="艺术">艺术</option>
							<option value="历史">历史</option>
							<option value="名著">名著</option>
							<option value="诗选">诗选</option>
							<option value="医学">医学</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>图书作者</td>
					<td>
						<input type="text" name="bookauthor" class="easyui-validatebox"
						data-options="required:true,missingMessage:'请输入图书作者'"/>
					</td>
				</tr>
				<tr>
					<td>供应商</td>
					<td>
						<select class="easyui-combobox" name="supplier" style="width:142px;">
							<option value="中国书店出版社">中国书店出版社</option>
							<option value="河北人民出版社">河北人民出版社</option>
							<option value="当代世界出版社">当代世界出版社</option>
							<option value="人民文学出版社">人民文学出版社</option>
							<option value="湖南出版社">湖南出版社</option>
							<option value="青岛出版社">青岛出版社</option>
							<option value="北京大学出版社">北京大学出版社</option>
							<option value="人民大学出版社">人民大学出版社</option>
							<option value="吉林文史出版社">吉林文史出版社</option>
							<option value="天津大学出版社">天津大学出版社</option>
							<option value="天津教育出版社">天津教育出版社</option>
							<option value="中国时代经济出版社">中国时代经济出版社</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>图书价格</td>
					<td>
						<input type="text" name="bookprice" class="easyui-validatebox"
						data-options="required:true,missingMessage:'请输入图书价格'"/>
					</td>
				</tr>
				<tr>
					<td>图书单位</td>
					<td>
						<input type="text" name="bookunit" class="easyui-validatebox"
						data-options="required:true,missingMessage:'请输入图书单位'"/>
					</td>
				</tr>
			 </table>
		</form>
	</div>
	</div>
</body>
</html>