<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	var valId;
	
	$('#mdsProcurementPlanTable').bootstrapTable({
		 
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
               url: "${ctx}/mds/mdspurchaseconfirmation/data",
               //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
               //queryParamsType:'',   
               ////查询参数,每次调用是会带上这个参数，可自定义                         
               queryParams : function(params) {
               	var searchParam = $("#searchForm").serializeJSON();
               	searchParam.pageNo = params.limit === undefined? "1" :params.offset/params.limit+1;
               	searchParam.pageSize = params.limit === undefined? -1 : params.limit;
               	if(params.sort == "itemCode"){
               		params.sort = "item_code"
               	}
               	if(params.sort == "itemName"){
               		params.sort = "item_name"
               	}
               	if(params.sort == "normalField"){
               		params.sort = "normal_field"
               	}
               	if(params.sort == "purchaseAmount"){
               		params.sort = "purchase_amount"
               	}
               	if(params.sort == "requiredDate"){
               		params.sort = "required_date"
               	}
               	if(params.sort == "procurementWay"){
               		params.sort = "procurement_way"
               	}
               	if(params.sort == "sourceType"){
               		params.sort = "source_type"
               	}
               	if(params.sort == "updateDate"){
               		params.sort = "update_date"
               	}
               	if(params.sort == "supplierName"){
               		params.sort = "supplier_name"
               	}
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
                   }
               },
              
               onClickRow: function(row, $el){
               },
               columns: [{
		        checkbox: true
		       
		    }
			,{
		        field: 'classify',
		        title: '分类',
		        sortable: true
		        ,formatter:function(value, row , index){
		        	if(row.classify=="0"){
		        		return "未分类";
		        	}else{
		        		return jp.getDictLabel(${fns:toJson(fns:getDictList('mds_quota_classify'))}, value, "-");
		        	}
		         }
		       
		    }
			,{
		        field: 'itemCode',
		        title: '物料编码',
		        sortable: true
		       
		    }
			,{
		        field: 'itemName',
		        title: '物料名称',
		        sortable: true
		       
		    }
			,{
		        field: 'normalField',
		        title: '标准号',
		        sortable: true
		       
		    }
			,{
		        field: 'modelnm',
		        title: '材质',
		        sortable: true
		       
		    }
			,{
		        field: 'specs',
		        title: '规格/型号',
		        sortable: true
		       
		    }
			,{
		        field: 'purchaseAmount',
		        title: '采购数量',
		        sortable: true
		        /*,formatter:function(value,row,index){
		        	return "<input type='text' id='purchaseAmount"+row.id+"' style='width:80px' value="+value+" onchange='change("+index+")'>";
		        }*/
		       
		    }
			,{
		        field: 'requiredDate',
		        title: '需求时间',
		        sortable: true
		        /*,formatter :function(value,row,index){
		        	return "<input type='date' name='requiredDate' id='requiredDate"+row.id+"' class='form-control' value='"+value+"' onchange='change("+index+")'>"; 
		        }*/
		    }
			,{
		        field: 'buyer.name',
		        title: '采购人'
		    }
			,{
		        field: 'supplierName',
		        title: '供应商名称',
		        sortable: true
		    }
			,{
		        field: 'state',
		        title: '状态',
		        sortable: true,
		        formatter:function(value,row,index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('mds_procurement_plan_state'))}, value, "-");
		        }
		       
		    }
			,{
		        field: 'procurementWay',
		        title: '采购方式',
		        sortable: true
		       
		    },
			{
		        field: 'sourceType',
		        title: '来源类型',
		        sortable: true
		       
		    }
			,{
		        field: 'particulars',
		        title: '详情'
		        ,formatter:function(value,row,index){
		        	if(row.sourceType=="项目录入"){
		        		return "<a href='javascript:project(\""+row.fromId+"\")'>"+value+"</a>";
		        	}else if(row.sourceType=="手动录入"){
		        		return value;
		        	}else{
		        		return "<a href='javascript:temporary(\""+row.fromId+"\")'>"+value+"</a>";
		        	}
		        }
		    }
			,{
		        field: 'updateDate',
		        title: '修改日期',
		        sortable: true
		       
		    }]
		
		});
		

	
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端
		  $('#mdsProcurementPlanTable').bootstrapTable("toggleView");
		}
	  
	  $('#mdsProcurementPlanTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#addIndent').prop('disabled', ! ($('#mdsProcurementPlanTable').bootstrapTable('getSelections').length>0));
            $('#reject').prop('disabled', ! ($('#mdsProcurementPlanTable').bootstrapTable('getSelections').length>0));
            
        });
		  
		    
	  $("#search").click("click", function() {// 绑定查询按扭
		  $('#mdsProcurementPlanTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#mdsProcurementPlanTable').bootstrapTable('refresh');
		});
		 $('#beginRequiredDate').datetimepicker({
			 format: "YYYY-MM-DD",
			 locale: 'zh-cn'
		});
		 $('#endRequiredDate').datetimepicker({
			 format: "YYYY-MM-DD",
			 locale: 'zh-cn'
		});
		 
	});
	//当修改之后选中行
	function change(num){
		$("#mdsProcurementPlanTable").bootstrapTable('check', num);
	}
		
  function getIdSelections() {
        return $.map($("#mdsProcurementPlanTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  //汇总显示
  function collectAll(){
	  var date = $("#searchForm").serialize();
	  jp.openDialogView('查看采购计划申请汇总', "${ctx}/mds/mdspcmplanprocurement/collectAll?" + date,'900px', '500px', $('#mdsProcurementPlanTable'));
  }
   //驳回
   function reject(){
		jp.confirm('确认要物资部重新选择供应商吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/mds/mdspurchaseconfirmation/reject?ids=" + getIdSelections(), function(data){
		         	if(data.success){
		         	  	$('#mdsProcurementPlanTable').bootstrapTable('refresh');
		         	  	jp.success(data.msg);
		         	  }else{
		         	  	jp.error(data.msg);
		         	  }
		         	 })
			})
	  }
   //下达采购订单
   function addIndent(){
		jp.confirm('确认要选择该供应商发货吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/mds/mdspurchaseconfirmation/addIndent?ids=" + getIdSelections(), function(data){
		         	if(data.success){
		         	  	$('#mdsProcurementPlanTable').bootstrapTable('refresh');
		         	  	jp.success(data.msg);
		         	  }else{
		         	  	jp.error(data.msg);
		         	  }
		         	 })
			})
	  }
   //销售订单信息显示
   function project(id){
	   jp.openDialogView('查看销售订单信息', "${ctx}/mds/mdspcmplanprocurement/project?id=" + id,'800px', '500px', $('#mdsProcurementPlanTable'));
   }
   
 //临时申请页显示
   function temporary(id){
	   jp.openDialogView('查看临时计划信息', "${ctx}/mds/mdspcmplanprocurement/temporary?id=" + id,'800px', '500px', $('#mdsProcurementPlanTable'));
   }

</script>