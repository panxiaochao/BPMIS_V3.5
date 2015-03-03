<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<c:out value="${webRoot}" />">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<!--easyui-->
<link rel="stylesheet" type="text/css" href="plugin/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="plugin/easyui/themes/icon.css">
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="plugin/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="plugin/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/jquery.exteasyui.js"></script>
<script type="text/javascript">
var datagrid ;
$(function() {
	datagrid = $('#tt').datagrid({  
		url:'salary/debitlist', 
		//border : false,	
		singleSelect: true, //单选	
		loadMsg:'数据加载中......',
		fit: true,
		nowrap: true,
		idField : 'id',
		striped:true, //斑马线
		pagination:true,//分页控件  
        rownumbers:true,//行号
		fitColumns:true,
		toolbar  : '#tb',
		pageSize: 15,
		pageList: [15,30,45],
		onBeforeLoad : function(param) {
			
		},		
		onLoadSuccess : function() {
			datagrid.datagrid('clearSelections');
		},
		onLoadError : function() {
			
		},
		   
	}); 
	//设置分页控件  
    var p = datagrid.datagrid('getPager');  
    $(p).pagination({  
        beforePageText: '第',
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
        onBeforeRefresh:function(){ 

        } 
    }); 
});

var addFun = function() {
	var dialog = parent.ext.modalDialog({
		title : '添加银行卡种类信息',
		url : 'system/to/commen_salary_debitadd',
		buttons : [{
			text : '&nbsp;添加&nbsp;',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, datagrid, parent.$);
			}
		}]
	});
}

var editFun = function(id) {
	var rows = datagrid.datagrid('getSelections');
	if (rows.length > 0){
		if(rows[0].debitstate == '1')		
			ext._showConfirm('注意', '在确定还款前，请先在账单中添加还款记录再进行此操作。', function() {
				$.post('salary/repayment', {id : rows[0].id}, function(result) {
					if (result.success) {
						ext._showAlert('提示', result.msg, 'info');
						datagrid.datagrid('reload');
					}else
						ext._showAlert('提示', result.msg, 'info');
				}, 'json');			
			});
		else
			ext._showAlert('消息', "本记录已还款，无需再操作！","warning");
	}else
		ext._showAlert('消息', "至少选择一条！");
};

function removeFun(){
	var rows = datagrid.datagrid('getSelections');
	if (rows.length > 0){
		$.messager.confirm('询问', '您是否要删除？', function(b) {
			if (b) {
				ext._showProcess(true, '数据处理中，请稍后....')
				var ids = new Array();
                for (var i = 0; i < rows.length; i++) {
                    ids.push(rows[i].id);
                } 
				$.post('salary/delete/debit', {id : ids.join(';')}, function(result) {
					if (result.success) {
						parent.ext._showAlert('提示', result.msg, 'info');
						datagrid.datagrid('reload');
					}else
						parent.ext._showAlert('提示', result.msg, 'info');
					ext._showProcess(false);
				}, 'json');
			}
		});
	}else
		ext._showAlert('消息', "至少选择一条！");
	
}

var debitstate = function(value,rowData,rowIndex) {
	if(value == '1')
		return "<span style=color:#ac2925><b>借款中</b></span>";		
	else
		return "<span style=color:#398439><b>已还款</b></span>";
}
</script>
</head>

<body id="index_layout" class="easyui-layout">
<!--header-->
<div id="navhead" data-options="region:'north'" border="false" style="height:40px;">
  <div class="place"> <span>位置：</span>
    <ul class="placeul">
      <li><a href="system/to/main_content">首页</a></li>
      <li>借款记录</li>
    </ul>
  </div>
</div>
<!--主体-->
<div data-options="region:'center'" border="false" style="padding:10px 0;" >
  <table id="tt" style=" border-bottom:none;">
    <thead>
      <tr>
        <th data-options="field:'serialnumber',width:160">流水号</th>
        <th data-options="field:'debitsummary',width:150">借款说明</th>
        <th data-options="field:'debituser',width:80">借款人</th>          
        <th data-options="field:'debitmoney',width:80">借款金额(元)</th>  
        <th data-options="field:'debitstate',width:80" formatter="debitstate">借款状态</th>
        <th data-options="field:'debittime',width:80">借款时间</th>                  
        <th data-options="field:'repaytime',width:100">还款时间</th>
        <th data-options="field:'salnum',width:160">账单关联</th>   
      </tr>
    </thead>
  </table>
  <div id="tb" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="addFun()">添加</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="editFun()">还款</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="removeFun()">删除</a> 
  </div>
  </div>
</div>
</body>
</html>
