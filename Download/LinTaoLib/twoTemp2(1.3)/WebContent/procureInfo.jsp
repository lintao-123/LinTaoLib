<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>采购订单表</title>
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
	//显示数据
	var i=2;
	$("#dg").datagrid({
		idField:"tempId",//主键字段
		fit:true,//填充容器
		/* url:"PurchaseInfoServlet?method=findAll", */
		url:"ProcureInfoServlet?method=findAll", 
		fitColumns:true,//自动填充列
		rownumbers:true,//增加行号列
		selectOnCheck:true,
		striped:true,
		loadMsg:"正在加载，请稍候。。。",
		loadFilter: function(data){
			if (data.d){
				return data.d;
			} else {
				return data;
			}
		},

		onDblClickRow: function(index,row){
			window.location="procurementStatement.jsp";
		},
		frozenColumns:[[//冻结列，固定在表格的左边
			{field:"checkbox",checkbox:true},
		]],
		columns:[[
			{field:"tempId",title:" 编号",align:"center",width:200,hidden:true},
			{field:"procurementNo",title:"采购单号",align:"center",width:200},
			{field:"supplier",title:"供应商名称",align:"center",width:200},
			{field:"libraryTime",title:"采购日期	",align:"center",width:200}
			
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
	var $form=$("#purchaseInfoform");
	//清空表单
	$form.form("reset");
	//在对话框(表单)增加两个按钮，并设置相应的执行函数
	$("#purchaseInfodialog").dialog({
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
					url:"ProcureInfoServlet?method=addProcureInfo",
					cache:false,
					data:$form.serialize(),//数据序列化
					dataType:"text",
					success:function(message){
						if(message.indexOf("success")!=-1){
							//给出添加操作成功的提示
							$.messager.show({
								title:"友情提示",
								msg:" 采购数据，添加成功！"
							});
							//添加成功后，清空数据
							$("#purchaseInfoform").form("reset"),
							//将对话框关闭
							$("#purchaseInfodialog").dialog("close"),
							//重新加载
							$("#dg").datagrid("reload");
						}else{
							//给出添加失败操作提示
							$.messager.show({
								title:"友情提示",
								msg:" 采购数据，添加失败!"
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
				$("#purchaseInfodialog").dialog("close");
			}
		}
		]
	});
	//打开窗口
	$("#purchaseInfodialog").dialog("open");
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
					uidStr+=array[i].tempId+",";
					
				}
				//将最后一个逗号去掉
				uidStr=uidStr.substring(0,uidStr.length-1);
				//调用后台服务程序实现删除
				$.post("ProcureInfoServlet?method=deleteProcureInfo",{procurementArray:uidStr},
						function(message){
					if(message.indexOf("success")!=-1){
						$.messager.show({
							title:"友情提示",
							msg:" 采购数据删除成功"
						});
						//重新加载
						$("#dg").datagrid("reload");
						//清除选择项
						$("#dg").datagrid("clearSelections");
						
					}else{
						$.messager.show({
							title:"友情提示",
							msg:" 采购数据删除失败"
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
	$("#purchaseInfoform").form("load",{
		 
		tempId:array[0].tempId,
		procurementNo:array[0].procurementNo,
		supplier:array[0].supplier,
		libraryTime:array[0].libraryTime
		 
		
		
	});
	//设置修改对话框相关属性
	$("#purchaseInfodialog").dialog({
		title:"修改采购数据",
		iconCls:"icon-edit",
		//增加两个按钮
		buttons:[{
			text:"修改",
			iconCls:"icon-ok",
			handler:function (){
				if(!$("#purchaseInfoform").form("validate")){
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
					url:"ProcureInfoServlet?method=updateProcureInfo",
					cecha:false,
					data:$("#purchaseInfoform").serialize(),
					dataType:"text",
					success:function(message){
						if(message.indexOf("success")!=-1){
							$.messager.show({
								title:"友情提示",
								msg:"采购数据修改成功!"
							});
							//数据添加成功后
							$("#purchaseInfoform").form("reset");
							//关闭对话框
							$("#purchaseInfodialog").dialog("close");
							//重新加载
							$("#dg").datagrid("reload");
						}else{
							$.messager.show({
								title:"友情提示",
								msg:"采购数据修改失败!"
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
				$("#purchaseInfodialog").dialog("close");
			}
		}
		]
		
	});
	//打开窗口
	$("#purchaseInfodialog").dialog("open");
}
 
</script>
</head>
<body class="easyui-layout" id="layout">
   
    <div data-options="region:'center',title:'采购订单表'" style="padding:5px;background:#eee;">
	    <table id="dg">
	    </table>
	    <div id="purchaseInfodialog" class="easyui-dialog" title="新增数据"
	    style="width:380px ;height:380px;"
	    data-options="iconCls:'icon-add',resizable:false,modal:
	    true,closed:true" >
		    <form id="purchaseInfoform">
		    	<!--  采用表格布局-->	
		    	<table id="mytable">
		    		<tr id="tr1">
		    			<td> 编号</td>
		    			<td>
		    			<input type="text" name="tempId"  />
		    			</td>
		    		</tr> 
		    		<tr>
		    			<td>采购单号</td>
		    			<td>
		    			<input type="text" name="procurementNo" class="easyui-validatebox"
		    			 data-options="required	:true,missingMessage:'请输入采购单号'"/>
		    			<td>
		    		</tr> 
		    		<tr>
		    			<td>供应商名称</td>
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
		    			<td>采购日期</td>
		    			<td>
		    			<input type="text"   name="libraryTime" class="easyui-datebox"
		    			data-options="required:true,missingMessage:'请输入采购日期'"/>
		    			</td>
		    		</tr>
		    	</table>
		    </form>
	    </div>
    </div>
</body>
</html>