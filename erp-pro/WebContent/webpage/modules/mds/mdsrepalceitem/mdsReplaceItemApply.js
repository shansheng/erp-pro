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
               url: "${ctx}/mds/mdsReplaceItem/applyData",
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
		        formatter:function(value, row , index){
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
		        formatter:function(value, row , index){
		        		if(row.replaceState=='20'){
		        			return "<a href='javascript:apply()'>"+value+"</a>";
		        		}else{
		        			return value;
		        		}
		        	
		         }
		       
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
		        field: 'modelnm',
		        title: '材质'
		    }
			,{
		        field: 'specs',
		        title: '规格/型号'
		    }
			,{
		        field: 'purchaseAmount',
		        title: '采购数量'
		        ,formatter:function(value,row,index){
		        	if(row.state==0 || row.state==5){
		        		onchange="change('purchaseEndDateStart')"
		        		return "<input type='text' id='purchaseAmount"+index+"' style='width:80px' value="+value+" onchange='change("+index+")'>";
		        	}else{
		        		return value;
		        	}
	        	 
		        }
		       
		    }
			,{
		        field: 'requiredDate',
		        title: '需求时间'
		    }
			,{
		        field: 'replaceState',
		        title: '状态',
		        sortable: true,
		        visible:false,
		        formatter:function(value,row,index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('replace_item_state'))}, value, "-");
		        }
		       
		    }
			,{
		        field: 'procurementWay',
		        title: '采购方式'
		    }
			,{
		        field: 'particulars',
		        title: '详情',
		        formatter:function(value,row,index){
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
		        sortable: true,
		        visible:false
		    }]
		
		});
		

	
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端
		  $('#mdsProcurementPlanTable').bootstrapTable("toggleView");
		}
	  function applying(){
		   var row =$('#mdsProcurementPlanTable').bootstrapTable('getSelections');
		   var result;
		   for(var i=0;i<row.length;i++){
			   if(row[i].replaceState=='30'|| row[i].replaceState=='40' || row[i].replaceState=='50'){
				   result = false;
			   }
			   else{
				   result=true;
			   }
		   }
		   return result;
	  }
	  /**
	   * 判断勾选的数据中状态是否为未提交或者驳回的
	   */
	  function submitButton(){
		  var date = $("#mdsProcurementPlanTable").bootstrapTable('getSelections');
		  var result = true;
		  for(var x = 0;x<date.length;x++){
			 if( date[x].state=='0'|| date[x].state=='5');
			 else{
				 result = false;
			 }
		  }
		  return result;
	  }
	  
	  /**
	   * 判断勾选的数据中状态是否为申请中的
	   */
	  function rejectButton(){
		  var date = $("#mdsProcurementPlanTable").bootstrapTable('getSelections');
		  var result =true;
		  for(var x = 0;x<date.length;x++){
			 if( date[x].state=='10');
			 else{
				 result =false;
			 }
		  }
		  return result;
	  }
	  
	  $('#mdsProcurementPlanTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
		  	$('#edit').prop('disabled', ! ($('#mdsProcurementPlanTable').bootstrapTable('getSelections').length>0 && submitButton()));
            $('#submit').prop('disabled', ! ($('#mdsProcurementPlanTable').bootstrapTable('getSelections').length>0 && submitButton()));
            $('#apply').prop('disabled',!($('#mdsProcurementPlanTable').bootstrapTable('getSelections').length==1 &&  applying()));
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
			 format: "YYYY-MM-DD"
		});
		 $('#endRequiredDate').datetimepicker({
			 format: "YYYY-MM-DD"
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
  
  //返回所选信息的id,需求数,需求时间
  function getMessageSelections() {
      return $.map($("#mdsProcurementPlanTable").bootstrapTable('getSelections'), function (row,index) {
    	  var requiredDate=$("#requiredDate"+index).val();
    	  var purchaseAmount=$("#purchaseAmount"+index).val();
          return row.id+":"+purchaseAmount+":"+requiredDate;
      });
  }

   //修改数量以及时间
  function edit(){//没有权限时，不显示确定按钮
	  jp.confirm('确认要保存采购计划吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/mds/mdsProcurementPlan/save?date=" + getMessageSelections(), function(data){
		         	if(data.success){
		         	  	$('#mdsProcurementPlanTable').bootstrapTable('refresh');
		         	  	jp.success(data.msg);
		         	  }else{
		         	  	jp.error(data.msg);
		         	  }
		         	 })
			})
  	  /*if(id == undefined){
			id = getIdSelections();
		} 
	  jp.openDialog('编辑采购计划申请', "${ctx}/mds/mdsProcurementPlan/form?id=" + id,'800px', '500px', $('#mdsProcurementPlanTable'));*/
  }

  function apply(id){
	  if(id == undefined){
			id = getIdSelections();
		}
	  jp.openDialog('申请代料',"${ctx}/mds/mdsReplaceItem/applyForm?id="+id,'800px','300px',$('#mdsProcurementPlanTable'));
  }
   //销售订单信息显示
   function project(id){
	   console.log("id:"+id);
	   jp.openDialogView('查看销售订单信息', "${ctx}/mds/mdsProcurementPlan/project?id=" + id,'800px', '500px', $('#mdsProcurementPlanTable'));
   }
   
 //临时申请页显示
   function temporary(id){
	   jp.openDialogView('查看临时计划信息', "${ctx}/mds/mdsProcurementPlan/temporary?id=" + id,'800px', '500px', $('#mdsProcurementPlanTable'));
   }

</script>