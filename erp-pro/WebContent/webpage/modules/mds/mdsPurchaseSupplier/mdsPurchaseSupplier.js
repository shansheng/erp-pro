<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	var id;
	var supplierName;
	var supplierId;
	/*$("#pageId").on('click','.pre,.next,.first,.last',jumpToPage);*/

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
               url: "${ctx}/mds/mdsPurchaseSupplier/data",
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
		        sortable: true,
		        visible:false,
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
		        formatter:function(value,row,index){
		        	if(row.state=="10" || row.state == "15"){
		        		return "<a href='javascript:edit(\""+row.id+"\")'>"+value+"</a>";
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
		    }
			,{
		        field: 'requiredDate',
		        title: '需求时间'
		    },{
		        field: 'buyer.name',
		        title: '采购人'
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
		        title: '采购方式'
		    },
			{
		        field: 'sourceType',
		        title: '来源类型'
		    }
			,{
		        field: 'particulars',
		        title: '详情',
		        visible:false,
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
		    },{
		    	field: 'supplierName',
		    	title: '供应商选择',
		    	class: 'supplier',
		    	formatter: function(value,row,index){
		    		if(value==null ){
		    			return "未选择";
		    			//return "<input id="+row.id+" type='button' onclick='getSupplier(this)' value='请选择'>";
		    		}else{
		    			return value;
		    			//return "<input id="+row.id+" type='button' onclick='getSupplier(this)' value='"+value+"'>";
		    		}
		    		
		    	}
                
		    }]
		    
		
		});
		

	
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#mdsProcurementPlanTable').bootstrapTable("toggleView");
		}

	  /**
	   * 判断勾选的数据中状态是否为未选择供应商或者驳回的
	   */
	  function submitButton(){
		  var date = $("#mdsProcurementPlanTable").bootstrapTable('getSelections');
		  var result = true;
		  for(var x = 0;x<date.length;x++){
			 if( date[x].state=='10'|| date[x].state=='15');
			 else{
				 result = false;
			 }
		  }
		  return result;
	  }
	  
	  $('#mdsProcurementPlanTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#edit').prop('disabled', !($('#mdsProcurementPlanTable').bootstrapTable('getSelections').length==1 && submitButton()));
            $('#select').prop('disabled',!($('#mdsProcurementPlanTable').bootstrapTable('getSelections').length>=1 && submitButton()));
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
		
  function getIdSelections() {
        return $.map($("#mdsProcurementPlanTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }

  function select(){
	  var ids = getIdSelections();
	  // jp.get("${ctx}/mds/mdsPurchaseSupplier/supplierSelectForm?ids="+ids")
	
			   top.layer.open({
					type : 2,
					area : [ '800px',
							'500px' ],
					title : "选择供应商",
					auto : true,
					name : 'friend',
					content : "${ctx}/tag/gridselect?url="
							+ encodeURIComponent("${ctx}/supplier/supplier/data")
							+ "&fieldLabels="
							+ encodeURIComponent("供应商编码|供应商名称|简称|行业|联系人|电话")
							+ "&fieldKeys="
							+ encodeURIComponent("supplierNo|suName|shortName|industry|linkman|phone")
							+ "&searchLabels="
							+ encodeURIComponent("供应商编码|供应商名称")
							+ "&searchKeys="
							+ encodeURIComponent("supplierNo|suName")
							+ "&isMultiSelected=false",
					btn : [ '确定', '关闭' ],
					yes : function(index,
							layero) {
						var iframeWin = layero
								.find('iframe')[0].contentWindow; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
						var items = iframeWin
								.getSelections();
						if (items == "") {
							jp.warning("必须选择一条数据!");
							return;
						}
						if(items.length > 1){
							jp.warning("只能选择一条数据!");
							return;
						}
						    console.log("ids:" + ids);
							console.log("item:" + items[0].id);
							console.log("item:" + items[0].suName);
						var supplierId = items[0].id;
						var supplierName = items[0].suName;
						jp.post("${ctx}/mds/mdsPurchaseSupplier/updateSupplier",{
							id:ids.toString(),
							supplierId: supplierId,
							supplierName: supplierName,
						},function(data){
							
							if(data.success){
		                    	$("#mdsProcurementPlanTable").bootstrapTable('refresh');
		                    	jp.success(data.msg);
		                    }else{
	            	  			jp.error(data.msg);
		                    }
						});
					


						top.layer.close(index);//关闭对话框。
					},
					cancel : function(index) {
					}
				});
  }
  function edit(id){//没有权限时，不显示确定按钮
  	  if(id == undefined){
			id = getIdSelections();
		} 
  	 jp.openDialog('供应商确认',"${ctx}/mds/mdsPurchaseSupplier/supplierForm?id="+id,'900px','600px',$('#mdsProcurementPlanTable'));
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