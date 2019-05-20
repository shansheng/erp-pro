<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<script>
$(document).ready(function() {
	$('#demandDate').datetimepicker({
		 format: "YYYY-MM-DD"
	});
	var mdsItemId = $('#mdsItemId').val();
	console.log("aaaaaaa:"+mdsItemId);
	$('#mdsPurchaseListTable').bootstrapTable({
		 
		  //请求方法
               method: 'get',
               //类型json
               dataType: "json",
               //显示刷新按钮
               showRefresh: true,
               //显示切换手机试图按钮
               showToggle: true,
               //显示 内容列下拉框
    	       showColumns: true,
    	       //显示到处按钮
    	       showExport: true,
    	       //显示切换分页按钮
    	       showPaginationSwitch: true,
    	       //最低显示2行
    	       minimumCountColumns: 2,
               //是否显示行间隔色
               striped: true,
               //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）     
               cache: false,    
               //是否显示分页（*）  
               pagination: true,   
                //排序方式 
               sortOrder: "asc",  
               //初始化加载第一页，默认第一页
               pageNumber:1,   
               //每页的记录行数（*）   
               pageSize: 10,  
               //可供选择的每页的行数（*）    
               pageList: [10, 25, 50, 100],
               //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
               url: "${ctx}/mds/purchase/mdsPurchaseList/findByItemId?mdsItemId="+mdsItemId,
               //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
               //queryParamsType:'',   
               ////查询参数,每次调用是会带上这个参数，可自定义                         
               queryParams : function(params) {
               	var searchParam = $("#searchForm").serializeJSON();
               	searchParam.pageNo = params.limit === undefined? "1" :params.offset/params.limit+1;
               	searchParam.pageSize = params.limit === undefined? -1 : params.limit;
               	searchParam.orderBy = params.sort === undefined? "" : params.sort+ " "+  params.order;
                   return searchParam;
               },
               //分页方式：client客户端分页，server服务端分页（*）
            sidePagination: "server",
               contextMenuTrigger:"right",//pc端 按右键弹出菜单
               contextMenuTriggerMobile:"press",//手机端 弹出菜单，click：单击， press：长按。
               contextMenu: '#context-menu',
               onContextMenuItem: function(row, $el){
                   if($el.data("item") == "edit"){
                   	edit(row.id);
                   } else if($el.data("item") == "delete"){
                        jp.confirm('确认要删除该批量采购计划记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/mds/purchase/mdsPurchaseList/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#mdsPurchaseListTable').bootstrapTable('refresh');
                   	  			jp.success(data.msg);
                   	  		}else{
                   	  			jp.error(data.msg);
                   	  		}
                   	  	})
                   	   
                   	});
                      
                   } 
               },
              
               onClickRow: function(row, $el){
               },
              columns: [{
		        checkbox: true
		       
		    },
             {
               field: 'sourceType',
               title: '物料来源'
             },
             {
               field: 'summaryCode',
               title: '合同编号'
             }
             ,{
            	 field: 'mesName',
            	 title: '产品名'
             }
         	,{
		        field: 'itemCode',
		        title: '物料编码'
		    }
			,{
		        field: 'itemName',
		        title: '物料名称'
		    }
			,{
		        field: 'normalField',
		        title: '标准号'
		    }
			,{
		        field: 'modelNum',
		        title: '材质'
		    }
			,{
		        field: 'specs',
		        title: '规格/型号'
		    }
			,{
		        field: 'purchaseNumber',
		        title: '采购数量'
		    }
			,{
		        field: 'demandNumber',
		        title: '需求数量'
		    },{
		    	field: 'demandDate',
		    	title: '需求时间'
		    },{
		    	field: 'remarks',
		    	title: '版本号'
		    }
			,{
		        field: 'inventoryNumber',
		        title: '库存数量',
		        sortable: true,
		        visible:false
		       
		    },
		    {
		        field: 'purchaseState',
		        title: '采购方式',
		        sortable: true,
		        visible: false
		       
		    }
		    
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#mdsPurchaseListTable').bootstrapTable("toggleView");
		}
	  
	  $('#mdsPurchaseListTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', ! $('#mdsPurchaseListTable').bootstrapTable('getSelections').length);
            $('#edit').prop('disabled', $('#mdsPurchaseListTable').bootstrapTable('getSelections').length!=1);
        });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/mds/purchase/mdsPurchaseList/import/template';
				  },
			    btn2: function(index, layero){
				        var inputForm =top.$("#importForm");
				        var top_iframe = top.getActiveTab().attr("name");//获取当前active的tab的iframe 
				        inputForm.attr("target",top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示
				        inputForm.onsubmit = function(){
				        	jp.loading('  正在导入，请稍等...');
				        }
				        inputForm.submit();
					    jp.close(index);
				  },
				 
				  btn3: function(index){ 
					  jp.close(index);
	    	       }
			}); 
		});
		    
	  $("#search").click("click", function() {// 绑定查询按扭
		  $('#mdsPurchaseListTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#mdsPurchaseListTable').bootstrapTable('refresh');
		});
		
		$('#beginDemandDate').datetimepicker({
			 format: "YYYY-MM-DD"
		});
		$('#endDemandDate').datetimepicker({
			 format: "YYYY-MM-DD"
		});
	
	});
		
  function getIdSelections() {
        return $.map($("#mdsPurchaseListTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function addPurase(){
	  jp.confirm('确认添加到采购计划吗?',function(){
		  jp.loading();
		  jp.get("${ctx}/mds/purchase/mdsPurchaseList/addPurchase?ids="+getIdSelections(),function(data){
			  if(data.success){
				  $('#mdsPurchaseListTable').bootstrapTable('refresh');
				  jp.success(data.msg);
			  }else{
				  jp.error(data.msg);
			  }
		  })
	  })
  }
  
  
  function deleteAll(){

		jp.confirm('确认要删除该批量采购计划记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/mds/purchase/mdsPurchaseList/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#mdsPurchaseListTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
   function add(){
	  jp.openDialog('新增批量采购计划', "${ctx}/mds/purchase/mdsPurchaseList/form",'800px', '500px', $('#mdsPurchaseListTable'));
  }
  function edit(id){//没有权限时，不显示确定按钮
  	  if(id == undefined){
			id = getIdSelections();
		}
	   <shiro:hasPermission name="mds:purchase:mdsPurchaseList:edit">
	  jp.openDialog('编辑批量采购计划', "${ctx}/mds/purchase/mdsPurchaseList/form?id=" + id,'800px', '500px', $('#mdsPurchaseListTable'));
	   </shiro:hasPermission>
	  <shiro:lacksPermission name="mds:purchase:mdsPurchaseList:edit">
	  jp.openDialogView('查看批量采购计划', "${ctx}/mds/purchase/mdsPurchaseList/form?id=" + id,'800px', '500px', $('#mdsPurchaseListTable'));
	  </shiro:lacksPermission>
  }

</script>