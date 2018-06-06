<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>库存表</title>
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
			/*url:"json/Stocking.json",//数据源路径*/
			url:"StockCheckServlet?method=findAll",
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
				{field:"stockNamber",title:"库存数量",align:"center",width:180},
			]],
			pagination:true,
			pageSize:12,
			pageList:[12,16,20],//页面记录数选择列表
			pagePosition:"bottom",
			toolbar:[//菜单工具栏
				{
					text:"查询",
					iconCls:"icon-search",//按钮上方的图标
					handler:function(){
						if(i/2==1){
		 					$("#dg").datagrid('enableFilter'); // 启用过滤	
		 					i=1;
						}else if(i/2!==1){
							$("#dg").datagrid('destroyFilter'); // 关闭过滤
							i=2;
						}
					}
				},"-",{
					text:"刷新",
					iconCls:"icon-reload",//按钮上方的图标
					handler:function(){
						$("#dg").datagrid("reload");
					}
				}
				
			]
		});
	});
</script>
</head>
<body class="easyui-layout" id="layout">   
	<!-- 显示数据的表格 -->
	<div data-options="region:'center',title:'库存表'" style="padding:5px;background:#eee;">
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
					<td class="tdalign">图书编号</td>
				</tr>
				<tr>
					<td class="tdalign">图书名称</td>
					<td><input type="text" name="bookName" class="easyui-validatebox"
						data-options="required:true,missingMessage:'请输入图书名称'"/></td>
				</tr>
				<tr>
					<td class="tdalign">库存数量</td>
					<td><input type="text" disabled="disabled" name="stockNamber"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>