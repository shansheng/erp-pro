<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	//默认查询未入库
	$("#status").val('0');
	
	$('#wmsInvInBillTable').bootstrapTable({
		 
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
    	       //显示详情按钮
    	       detailView: true,
    	       	//显示详细内容函数
	           detailFormatter: "detailFormatter",
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
               url: "${ctx}/wmsinvinbill/wmsInvInBill/data",
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
                        jp.confirm('确认要删除该入库单记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/wmsinvinbill/wmsInvInBill/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#wmsInvInBillTable').bootstrapTable('refresh');
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
		        field: 'billNo',
		        title: '申请单号',
		        sortable: true
		        ,formatter:function(value, row , index){
		        	if(row.status == 20){
		        		return value;
		        	}else{
		        		return "<a href='javascript:edit(\""+row.id+"\")'>"+value+"</a>";
		        	}
		         }
		       
		    }
			,{
		        field: 'billType',
		        title: '入库类型',
		        sortable: true,
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('wms_in_bill_type'))}, value, "-");
		        }
		       
		    }
			,{
		        field: 'applySysUserName',
		        title: '申请人',
		        sortable: true
		    }
			,{
		        field: 'applyDate',
		        title: '申请时间',
		        sortable: true
		       
		    }
			,{
		        field: 'wmsInventory.inventoryName',
		        title: '库房',
		        sortable: true
		       
		    }
			,{
		        field: 'status',
		        title: '状态',
		        sortable: true,
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('wms_inv_in_bill_status'))}, value, "-");
		        }
		       
		    }
			,{
		        field: 'updateDate',
		        title: '更新时间',
		        sortable: true
		       
		    }
			,{
		        field: 'remarks',
		        title: '备注信息',
		        sortable: true
		       
		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#wmsInvInBillTable').bootstrapTable("toggleView");
		}
	  
	  $('#wmsInvInBillTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', !($('#wmsInvInBillTable').bootstrapTable('getSelections').length==1 && $('#wmsInvInBillTable').bootstrapTable('getSelections')[0].status != '20'));
            $('#edit').prop('disabled', !($('#wmsInvInBillTable').bootstrapTable('getSelections').length==1 && $('#wmsInvInBillTable').bootstrapTable('getSelections')[0].status != '20'));
            $('#inBill').prop('disabled', !($('#wmsInvInBillTable').bootstrapTable('getSelections').length==1 && $('#wmsInvInBillTable').bootstrapTable('getSelections')[0].status != '20'));
        });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/wmsinvinbill/wmsInvInBill/import/template';
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
		  $('#wmsInvInBillTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		   $("#searchForm  .select-item").html("");
		  $('#wmsInvInBillTable').bootstrapTable('refresh');
		});
	});
		
  function getIdSelections() {
        return $.map($("#wmsInvInBillTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){

		jp.confirm('确认要删除该入库单记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/wmsinvinbill/wmsInvInBill/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#wmsInvInBillTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
  //入库事件
  function inBill(){

		jp.confirm('确认要入库吗？', function(){
			jp.loading();  	
			//jp.get("${ctx}/wmsinvinbill/wmsInvInBill/inBill?ids=" + getIdSelections(), function(data){
			jp.get("${ctx}/wmsinvinbill/wmsInvInBill/inBill?ids=" + getIdSelections(), function(data){
       	  		if(data.success){
       	  			$('#wmsInvInBillTable').bootstrapTable('refresh');
       	  			jp.success(data.msg);
       	  		}else{
       	  			jp.error(data.msg);
       	  		}
       	  	})
        	   
		})
  }
  
  function add(){
	  jp.openDialog('新增入库单', "${ctx}/wmsinvinbill/wmsInvInBill/form",'800px', '500px', $('#wmsInvInBillTable'));
  }
  
  function edit(id){//没有权限时，不显示确定按钮
  	  if(id == undefined){
			id = getIdSelections();
		}
	   <shiro:hasPermission name="wmsinvinbill:wmsInvInBill:edit">
	  jp.openDialog('编辑入库单', "${ctx}/wmsinvinbill/wmsInvInBill/form?id=" + id,'800px', '500px', $('#wmsInvInBillTable'));
	   </shiro:hasPermission>
	  <shiro:lacksPermission name="wmsinvinbill:wmsInvInBill:edit">
	  jp.openDialogView('查看入库单', "${ctx}/wmsinvinbill/wmsInvInBill/form?id=" + id,'800px', '500px', $('#wmsInvInBillTable'));
	  </shiro:lacksPermission>
  }
  
		   
  function detailFormatter(index, row) {
	  var htmltpl =  $("#wmsInvInBillChildrenTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
	  var html = Mustache.render(htmltpl, {
			idx:row.id
		});
	  $.get("${ctx}/wmsinvinbill/wmsInvInBill/detail?id="+row.id, function(wmsInvInBill){
    	var wmsInvInBillChild1RowIdx = 0, wmsInvInBillChild1Tpl = $("#wmsInvInBillChild1Tpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
		var data1 =  wmsInvInBill.wmsInvInBillListList;
		for (var i=0; i<data1.length; i++){
			addRow('#wmsInvInBillChild-'+row.id+'-1-List', wmsInvInBillChild1RowIdx, wmsInvInBillChild1Tpl, data1[i]);
			wmsInvInBillChild1RowIdx = wmsInvInBillChild1RowIdx + 1;
		}
				
      	  			
      })
     
        return html;
    }
  
	function addRow(list, idx, tpl, row){
		$(list).append(Mustache.render(tpl, {
			idx: idx, delBtn: true, row: row
		}));
	}
			
</script>
<script type="text/template" id="wmsInvInBillChildrenTpl">//<!--
	<div class="tabs-container">
		<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-{{idx}}-1" aria-expanded="true">入库申请单子表</a></li>
		</ul>
		<div class="tab-content">
				 <div id="tab-{{idx}}-1" class="tab-pane fade in active">
						<table class="ani table">
						<thead>
							<tr>
								<th>库位</th>
								<th>物料号</th>
								<th>物料名称</th>
								<th>牌号</th>
								<th>规格</th>
								<th>质量编号</th>
								<th>申请数量</th>
								<th>入库人</th>
								<th>入库日期</th>
								<th>状态</th>
								<th>更新时间</th>
								<th>备注信息</th>
							</tr>
						</thead>
						<tbody id="wmsInvInBillChild-{{idx}}-1-List">
						</tbody>
					</table>
				</div>
		</div>//-->
	</script>
	<script type="text/template" id="wmsInvInBillChild1Tpl">//<!--
				<tr>
					<td>
						{{row.wmsLocatorCode}}
					</td>
					<td>
						{{row.itemCode}}
					</td>
					<td>
						{{row.itemName}}
					</td>
					<td>
						{{row.itemMarque}}
					</td>
					<td>
						{{row.itemSpecs}}
					</td>
					<td>
						{{row.qualityNo}}
					</td>
					<td>
						{{row.applyQty}}
					</td>
					<td>
						{{row.inSysUserName}}
					</td>
					<td>
						{{row.inDate}}
					</td>
					<td>
						{{row.statusLabel}}
					</td>
					<td>
						{{row.updateDate}}
					</td>
					<td>
						{{row.remarks}}
					</td>
				</tr>//-->
	</script>
