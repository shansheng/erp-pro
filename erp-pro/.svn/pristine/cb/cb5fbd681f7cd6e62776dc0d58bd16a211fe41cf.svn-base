<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#workOrderSonTable').bootstrapTable({
		 
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
               url: "${ctx}/WorkOrderSynthesize/WorkOrderSynthesize/data",
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
                        jp.confirm('确认要删除该工作计划记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/workorderson/workOrderSon/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#workOrderSonTable').bootstrapTable('refresh');
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
     		        field: 'contractNo',
     		        title: '合同编号',
     		       formatter:function(value, row , index){
     		        	var url= "<a href='javascript:contract(\""+row.sysMarketId+"\")'>"+value+"</a>";
     		        	return url;
     		         }
     		    },{
       		        field: 'contractDate',
       		        title: '合同签订时间',
       		       
                    formatter : crtTimeFtt
       		       
       		    }
       			,{
       		        field: 'deliveryDate',
       		        title: '交货日期',
       		       
                    formatter : crtTimeFtt
       		       
       		    },{
       		        field: 'contractStatus',
       		        title: '合同状态',
       		        
       		        formatter:function(value, row , index){
       		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('contract_status'))}, value, "-");
       		        }
       		       
       		    }
               ,{
      		        field: 'clientName',
      		        title: '客户名称'
      		    }
                
         			,{
         		        field: 'mdsItemName',
         		        title: '产品名称'
         		    }
         			
         			
         			,
         		    {
   		        field: 'orderState',
   		        title: '工作订单下达状态',
   		       
      		        formatter:function(value, row , index){
      		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('order_state'))}, value, "-");
      		        }
   		       
   		    }
         			,{
        		        field: 'mdsQuotaState',
        		        title: '材料定额状态',
        		        sortable: true,
        		        formatter:function(value, row , index){
        		        	var valu= jp.getDictLabel(${fns:toJson(fns:getDictList('mds_quota_state'))}, value, "-");
        		       return "<a href='javascript:mdsQuota(\""+row.id+"\")'>"+valu+"</a>";
        		        
        		        
        		        }
        		       
        		    }
         			,{
           		        field: 'quotaDate',
           		        title: '材料定额定版时间',
                        formatter : crtTimeFtt
           		       
           		    },{
        		        field: 'bomState',
        		        title: 'bom状态',
        		        sortable: true,
        		        formatter:function(value, row , index){
        		        	var valu= jp.getDictLabel(${fns:toJson(fns:getDictList('mat_bom_state'))}, value, "-");
        		       return "<a href='javascript:matBom(\""+row.id+"\")'>"+valu+"</a>";
        		        
        		        
        		        }
        		       
        		    }
         			,{
           		        field: 'bomDate',
           		        title: 'bom定版时间',
                        formatter : crtTimeFtt
           		       
           		    },{
        		        field: 'mdsProcurementPlanState',
        		        title: '采购状态',
        		        formatter:function(value,row,index){
        		        	var valu= jp.getDictLabel(${fns:toJson(fns:getDictList('mds_procurement_plan_state'))}, value, "-");
        		        	return "<a href='javascript:mdsPlan(\""+row.id+"\")'>"+valu+"</a>";
        		        }
        		       
        		    },{
           		        field: 'mdsProcurementPlanDate',
           		        title: '采购下达时间',
                        formatter : crtTimeFtt
           		       
           		    },
         			{
           		    	field: 'arrivalState',
           		    	title: '到货情况',
           		    	formatter: function(value,row,index){
           		    	console.log("asdf:"+row.purchaseAmount)
           		    	  if(row.purchaseAmount > 0){
           		    		 if((row.purchaseAmount-row.arrivalnum) > 0 ){
             		    		   return "<a href='javascript:arrival()'>未全部到货</a>"
             		    	   }else{
             		    		   return "已全部到货";
             		    	   }
           		    	  }else{
           		    		   return "未开始采购";
           		    	  }
           		    }
         			},
         			{
           		        field: 'finishSche',
           		        title: '生产状况'
           		    },{
           		        field: 'shipments',
           		        title: '是否发货',
           		        formatter:function(value, row , index){
           		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('shipments'))}, value, "-");
           		        }
           		       
           		    },{
           		        field: 'shipmentsDate',
           		        title: '发货时间',
                        formatter : crtTimeFtt
           		        
           		    },{
        		        field: 'arrivalState',
        		        title: '检验状态',
        		        formatter:function(value,row,index){
        		        	var valu= jp.getDictLabel(${fns:toJson(fns:getDictList('mds_arrival_checkout'))}, value, "-");
        		        	return "<a href='javascript:arrival(\""+row.id+"\")'>"+valu+"</a>";
        		        }
        		       
        		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#workOrderSonTable').bootstrapTable("toggleView");
		}
	  
	  $('#workOrderSonTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#view').prop('disabled', $('#workOrderSonTable').bootstrapTable('getSelections').length!=1);
            $('#edit').prop('disabled', $('#workOrderSonTable').bootstrapTable('getSelections').length!=1||$('#workOrderSonTable').bootstrapTable('getSelections')[0].orderState=='1');
           
	  });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/workorderson/workOrderSon/import/template';
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
		  $('#workOrderSonTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#clientId").val("");
		  $("#searchForm  .select-item").html("");
		  $('#workOrderSonTable').bootstrapTable('refresh');
		});
		
		
	});
function crtTimeFtt(val, row) {
    if (val != null) {
            var date = new Date(val);
            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
        }
}
  function getIdSelections() {
        return $.map($("#workOrderSonTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  function arrival(){
	   window.location = "${ctx}/mds/mdsProcurementPlan/missProjectQuery";
  }
  function mdsQuota(id){
	  var searchParam = $("#searchForm").serializeJSON();
	  window.location="${ctx}/WorkOrderSynthesize/WorkOrderSynthesize/show?id=" + id+"&clientId="+searchParam.clientId;
  }
  function matBom(id){
	  var searchParam = $("#searchForm").serializeJSON();
	  window.location="${ctx}/WorkOrderSynthesize/WorkOrderSynthesize/matBom?id=" + id+"&clientId="+searchParam.clientId;
  }
  function contract(id){
	  if(id == undefined){
			id = getIdSelections();
		}
	  jp.openDialogView('查看销售', "${ctx}/sysmarketview/sysMarket/view?id=" + id,'800px', '500px', $('#workOrderSonTable'));
  }
  function mdsPlan(id){
	  if(id == undefined){
			id = getIdSelections();
		}
	  jp.openDialogView('查看采购计划', "${ctx}/WorkOrderSynthesize/WorkOrderSynthesize/mdsPlan?id=" + id,'800px', '500px', $('#workOrderSonTable'));
  }

</script>