<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<script>
$(document).ready(function() {
	$('#demandDate').datetimepicker({
		 format: "YYYY-MM-DD"
	});
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
               url: "${ctx}/mds/purchase/mdsPurchaseList/data",
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
		       
		    }
			,{
		        field: 'itemCode',
		        title: '物料编码',
		        formatter:function(value, row , index){
		        	return "<a href='javascript:edit(\""+row.mdsItemId+"\")'>"+value+"</a>";
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
		       /* ,formatter:function(value,row,index){
		        	 return "<input type='text' style='width:80px' value="+value+">";
		         }*/
		      /*  ,editable:{
		        	type:'text',
		        	title:'采购数量',
		        	validate:function(v){
		        		if(!v) return '采购数量不能为空';
		        	}
		        }*/
		    }
			,{
		        field: 'demandNumber',
		        title: '需求数量'
		    },
			/*,{
		        field: 'demandDate',
		        title: '需求时间',
		        sortable: true,
		        formatter:function(value,row,index){
		        	 return "<a href='#' id='demandDate'>"+value+"</a>";
		        },*/
		    /*   formatter:function(value,row,index){
		    	   console.log(eval(value));
		    	    var date = eval(value);
		        	//var date = eval('new'+eval(value).source);
		        	return date.format("yyyy-MM-dd");
		        },*/
		     /*   editable:{
		        	type:'date',
		        	title:'日期',
		        	placement: 'bottom',
		        	datepicker:{
		        		language:'zh-CN',
		        	}
		        }*/
		       /* formatter :function(value,row,index){
		        	return "<div class='input-group form_datetime' id='demandDate'>"+
		                    "<input type='text'  name='demandDate' class='form-control required'  value="+value+" aria-required='true' aria-invalid='false'>"+
		                    "<span class='input-group-addon'>"+
		                        "<span class='glyphicon glyphicon-calendar'></span>"+
		                    "</span>"+
		                   "</div>" ;
		        			
		        }*/
		         
		       
		    /*}*/
		/*	,{
		        field: 'inventoryNumber',
		        title: '库存数量',
		        sortable: true
		       
		    }*/{
		    	field: 'currentOnhandQty',
		    	title: '库存数'
		    },
			{
		        field: 'state',
		        title: '状态'
		    },
		    {
		        field: 'purchaseState',
		        title: '采购方式',
		        sortable: true,
		        visible: false
		       
		    },
		    {
		        field: 'sourceType',
		        title: '来源类型',
		        sortable: true,
		        visible: false
		       
		    },{
		        field: 'marketId',
		        title: '项目ID',
		        sortable: true,
		        visible: false
		       
		    },
		    {
		        field: 'temPlanId',
		        title: '临时计划ID',
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
            $('#addPurase').prop('disabled',!$('#mdsPurchaseListTable').bootstrapTable('getSelections').length);
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
            return row.mdsItemId
        });
    }
  
  function addPurase(){
	  console.log("id:"+getIdSelections());
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
	  window.location = "${ctx}/mds/purchase/mdsPurchaseList/findAll?mdsItemId="+id;
  	  /*if(id == undefined){
			id = getIdSelections();
		}*/
	  /* <shiro:hasPermission name="mds:purchase:mdsPurchaseList:edit">
	  jp.openDialog('编辑批量采购计划', "${ctx}/mds/purchase/mdsPurchaseList/form?id=" + id,'800px', '500px', $('#mdsPurchaseListTable'));
	   </shiro:hasPermission>
	  <shiro:lacksPermission name="mds:purchase:mdsPurchaseList:edit">
	  jp.openDialogView('查看批量采购计划', "${ctx}/mds/purchase/mdsPurchaseList/form?id=" + id,'800px', '500px', $('#mdsPurchaseListTable'));
	  </shiro:lacksPermission>*/
  }

</script>