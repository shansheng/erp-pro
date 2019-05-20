<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#mdsReplaceItemTable').bootstrapTable({
		 
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
               url: "${ctx}/mds/mdsReplaceItem/data",
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
                        jp.confirm('确认要删除该代料申请记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/mds/mdsrepalceitem/mdsReplaceItem/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#mdsReplaceItemTable').bootstrapTable('refresh');
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
		       
		    }
               ,{
   		        field: 'mdsProcurementPlanId',
   		        title: '采购订单Id',
   		        sortable: true,
   		        visible:false
   		    }
   			
   			,{
   		        field: 'beforeReplaceItemName',
   		        title: '替换前物料'
   		    }
   			,{
   		        field: 'classify',
   		        title: '分类'
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
   		        title: '物料编码'
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
		        field: 'requiredDate',
		        title: '需求时间'
		    }
   			,{
   		        field: 'purchaseAmount',
   		        title: '采购数量'
   		    }
   			,{
   		        field: 'itemName',
   		        title: '替换后物料'
   		    }
   			,{
   		        field: 'afterReplacePuraseNumber',
   		        title: '替换后采购数量'
   		    }
   			,{
   		        field: 'replaceState',
   		        title: '状态',
   		        formatter:function(value,row,index){
   		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('replace_item_state'))}, value, "-");
   		        }
   		    }
   			,{
   		        field: 'purchaseMode',
   		        title: '采购方式'
   		    },{
   		    	field: 'approveBy',
   		    	title: '审批人'
   		    },
   		     {
   		    	field: 'approveDate',
   		    	title: '审批时间'
   		    },
   		    {
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
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#mdsReplaceItemTable').bootstrapTable("toggleView");
		}
	  
	  $('#mdsReplaceItemTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', ! $('#mdsReplaceItemTable').bootstrapTable('getSelections').length);
            $('#edit').prop('disabled', $('#mdsReplaceItemTable').bootstrapTable('getSelections').length!=1);
        });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/mds/mdsrepalceitem/mdsReplaceItem/import/template';
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
		  $('#mdsReplaceItemTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#mdsReplaceItemTable').bootstrapTable('refresh');
		});
		
		
	});
		
  function getIdSelections() {
        return $.map($("#mdsReplaceItemTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){

		jp.confirm('确认要删除该代料申请记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/mds/mdsrepalceitem/mdsReplaceItem/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#mdsReplaceItemTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
   function add(){
	  jp.openDialog('新增代料申请', "${ctx}/mds/mdsrepalceitem/mdsReplaceItem/form",'800px', '500px', $('#mdsReplaceItemTable'));
  }
  function edit(id){//没有权限时，不显示确定按钮
  	  if(id == undefined){
			id = getIdSelections();
		}
	   <shiro:hasPermission name="mds:mdsrepalceitem:mdsReplaceItem:edit">
	  jp.openDialog('编辑代料申请', "${ctx}/mds/mdsrepalceitem/mdsReplaceItem/form?id=" + id,'800px', '500px', $('#mdsReplaceItemTable'));
	   </shiro:hasPermission>
	  <shiro:lacksPermission name="mds:mdsrepalceitem:mdsReplaceItem:edit">
	  jp.openDialogView('查看代料申请', "${ctx}/mds/mdsrepalceitem/mdsReplaceItem/form?id=" + id,'800px', '500px', $('#mdsReplaceItemTable'));
	  </shiro:lacksPermission>
  }
  //销售订单信息显示
  function project(id){
	   jp.openDialogView('查看销售订单信息', "${ctx}/mds/mdsProcurementPlan/project?id=" + id,'800px', '500px', $('#mdsProcurementPlanTable'));
  }
  
//临时申请页显示
  function temporary(id){
	   jp.openDialogView('查看临时计划信息', "${ctx}/mds/mdsProcurementPlan/temporary?id=" + id,'800px', '500px', $('#mdsProcurementPlanTable'));
  }
</script>