<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>销售退货表 </title>
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
		url:"ReturnInquiryServlet?method=findAll", 
		/*url:"json/returnInquiry.json", */
		fitColumns:true,//自动填充列
		rownumbers:true,//增加行号列
		striped:true,
		loadMsg:"正在加载，请稍候。。。",
		frozenColumns:[[//冻结列，固定在表格的左边
			{field:"checkbox",checkbox:true},
		]],
		columns:[[
			{field:"tempId",title:"编号",align:"center",width:200,hidden:true},
			{field:"orderNumber",title:"订单号",align:"center",width:200},
			{field:"bookName",title:"图书名称",align:"center",width:200},
			{field:"bookPrice",title:"图书价格",align:"center",width:200},
			{field:"salesVolume",title:"销售数量",align:"center",width:200},
			{field:"salesPeriods",title:"退货时间",align:"center",width:200},
			{field:"consoleOperator",title:"操作员",align:"center",width:200},
			{field:"total",title:"总价",align:"center",width:200}
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
				addSeturnInquiry();
				$("#tr1").attr("hidden","hidden");
			}
		},"-",{
			text:"修改",
			iconCls:"icon-edit",
			handler:function(){
				updateSeturnInquiry();
				$("#tr1").attr("hidden","hidden")
			}
		},"-",{
			text:"删除",
			iconCls:"icon-cancel",
			handler:function(){
				deleteSeturnInquiry();
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
function addSeturnInquiry(){
	//获取表单
	var $form=$("#returnInquiryfrom");
	//清空表单
	$form.form("reset");
	//在对话框(表单)增加两个按钮，并设置相应的执行函数
	$("#returnInquirydialog").dialog({
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
					msg:" 退货数据验证未通过,保存失败!"
				});
				return;
				}
				//验证通过执行添加操作(提交方式)
				$.ajax({
					type:"post",
					url:"ReturnInquiryServlet?method=addReturnInquiry",
					cache:false,
					data:$form.serialize(),//数据序列化
					dataType:"text",
					success:function(message){
						if(message.indexOf("success")!=-1){
							//给出添加操作成功的提示
							$.messager.show({
								title:"友情提示",
								msg:" 退货数据，添加成功！"
							});
							//添加成功后，清空数据
							$("#returnInquiryfrom").form("reset"),
							//将对话框关闭
							$("#returnInquirydialog").dialog("close"),
							//重新加载
							$("#dg").datagrid("reload");
						}else{
							//给出添加失败操作提示
							$.messager.show({
								title:"友情提示",
								msg:" 退货数据，添加失败!"
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
				$("#returnInquirydialog").dialog("close");
			}
		}
		]
	});
	//打开窗口
	$("#returnInquirydialog").dialog("open");
} 
//删除
function deleteSeturnInquiry(){
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
				$.post("ReturnInquiryServlet?method=deleteReturnInquiry",{returnInquiryArray:uidStr},
						function(message){
					if(message.indexOf("success")!=-1){
						$.messager.show({
							title:"友情提示",
							msg:" 利润删除成功"
						});
						//重新加载
						$("#dg").datagrid("reload");
						
					}else{
						$.messager.show({
							title:"友情提示",
							msg:" 利润删除失败"
						});
					}
					},"text");
			}
		});
	}
}
//修改
function updateSeturnInquiry(){
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
	$("#returnInquiryfrom").form("load",{
		tempId:array[0].tempId,
		orderNumber:array[0].orderNumber,
		bookName:array[0].bookName,
		 
		 
		bookPrice:array[0].bookPrice,
		salesVolume:array[0].salesVolume,
		 
		salesPeriods:array[0].salesPeriods,
		consoleOperator:array[0].consoleOperator
	});
	//设置修改对话框相关属性
	$("#returnInquirydialog").dialog({
		title:"修改 利润数据",
		iconCls:"icon-edit",
		//增加两个按钮
		buttons:[{
			text:"修改",
			iconCls:"icon-ok",
			handler:function (){
				if(!$("#returnInquiryfrom").form("validate")){
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
					url:"ReturnInquiryServlet?method=updateReturnInquiry",
					cecha:false,
					data:$("#returnInquiryfrom").serialize(),
					dataType:"text",
					success:function(message){
						if(message.indexOf("success")!=-1){
							$.messager.show({
								title:"友情提示",
								msg:"数据修改成功!"
							});
							//数据添加成功后
							$("#returnInquiryfrom").form("reset");
							//关闭对话框
							$("#returnInquirydialog").dialog("close");
							//重新加载
							$("#dg").datagrid("reload");
						}else{
							$.messager.show({
								title:"友情提示",
								msg:"数据修改失败!"
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
				$("#returnInquirydialog").dialog("close");
			}
		}
		]
		
	});
	//打开窗口
	$("#returnInquirydialog").dialog("open");
}
</script>

<body class="easyui-layout" id="layout">
	   
    <div data-options="region:'center',title:'销售退货'" style="padding:5px;background:#eee;">
	    <table id="dg">
	    </table>
	    <div id="returnInquirydialog" class="easyui-dialog" title="新增数据"
	    style="width:380px ;height:380px;"
	    data-options="iconCls:'icon-add',resizable:false,modal:true,closed:true" >
		    <form id="returnInquiryfrom">
		    	<!--  采用表格布局-->
		    	<table id="mytable">
		    		<tr id="tr1">
		    			<td> 编号</td>
		    			<td>
		    			<input type="text"   name="tempId"  />
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>订单号</td>
		    			<td>
		    			<input type="text" name="orderNumber" class="easyui-validatebox"
		    			 data-options="required	:true,missingMessage:'请输入订单号'"/>
		    			<td>
		    		</tr> 
		    		<tr>
		    			<td>图书名称</td>
		    			<td>
		    			<input type="text" name="bookName" class="easyui-validatebox"
		    			data-options="required:true,missingMessage:'请输入图书名称'"/>
		    			</td>
		    		</tr>
		    		
		    		 
		    		<tr>
		    			<td>图书价格</td>
		    			<td>
		    			<input type="text"   name="bookPrice" class="easyui-numberbox"
		    			data-options="required:true,missingMessage:'请输入图书价格'"/>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>销售数量</td>
		    			<td>
		    			<input type="text" name="salesVolume" 
						class="easyui-numberspinner"
						data-options="required:true,missingMessage:'请输入销售数量'"/></td>
		    			</td>
		    		</tr>
		    		 
		    		<tr>
		    			<td>退货时间</td>
		    			<td>
		    			<input type="text"   name="salesPeriods" class="easyui-datebox"
		    			data-options="required:true,missingMessage:'请输入退货时间'"/>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>操作员</td>
		    			<td>
		    			<select class="easyui-combobox" name="consoleOperator" style="width:142px;">
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
    </div>
</body>
</html>