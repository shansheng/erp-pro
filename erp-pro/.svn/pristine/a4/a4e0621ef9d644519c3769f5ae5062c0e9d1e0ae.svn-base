<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#wmsInvOutBillTable').bootstrapTable({
		 
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
               url: "${ctx}/wmsinvoutbill/wmsInvOutBill/data",
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
                        jp.confirm('确认要删除该出库单记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/wmsinvoutbill/wmsInvOutBill/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#wmsInvOutBillTable').bootstrapTable('refresh');
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
		        title: '出库类型',
		        sortable: true,
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('wms_out_bill_type'))}, value, "-");
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
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('wms_inv_out_bill_status'))}, value, "-");
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

		 
		  $('#wmsInvOutBillTable').bootstrapTable("toggleView");
		}
	  
	  $('#wmsInvOutBillTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', !($('#wmsInvOutBillTable').bootstrapTable('getSelections').length==1 && $('#wmsInvOutBillTable').bootstrapTable('getSelections')[0].status != '20'));
            $('#edit').prop('disabled', !($('#wmsInvOutBillTable').bootstrapTable('getSelections').length==1 && $('#wmsInvOutBillTable').bootstrapTable('getSelections')[0].status != '20'));
            $('#outBill').prop('disabled', !($('#wmsInvOutBillTable').bootstrapTable('getSelections').length==1 && $('#wmsInvOutBillTable').bootstrapTable('getSelections')[0].status != '20'));
        });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/wmsinvoutbill/wmsInvOutBill/import/template';
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
		  $('#wmsInvOutBillTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		   $("#searchForm  .select-item").html("");
		  $('#wmsInvOutBillTable').bootstrapTable('refresh');
		});
		
		
	});
		
  function getIdSelections() {
        return $.map($("#wmsInvOutBillTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){

		jp.confirm('确认要删除该出库单记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/wmsinvoutbill/wmsInvOutBill/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#wmsInvOutBillTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
  
  //出库事件
  function outBill(){
		jp.confirm('确认要出库吗？', function(){
			jp.loading();  	
			//jp.get("${ctx}/wmsinvinbill/wmsInvInBill/outBill?ids=" + getIdSelections(), function(data){
			jp.get("${ctx}/wmsinvoutbill/wmsInvOutBill/outBill?ids=" + getIdSelections(), function(data){
       	  		if(data.success){
       	  			$('#wmsInvOutBillTable').bootstrapTable('refresh');
       	  			jp.success(data.msg);
       	  		}else{
       	  			jp.error(data.msg);
       	  		}
       	  	})
        	   
		})
  }
  
  function add(){
	  jp.openDialog('新增出库单', "${ctx}/wmsinvoutbill/wmsInvOutBill/form",'800px', '500px', $('#wmsInvOutBillTable'));
  }
  
  function edit(id){//没有权限时，不显示确定按钮
  	  if(id == undefined){
			id = getIdSelections();
		}
	   <shiro:hasPermission name="wmsinvoutbill:wmsInvOutBill:edit">
	  jp.openDialog('编辑出库单', "${ctx}/wmsinvoutbill/wmsInvOutBill/form?id=" + id,'800px', '500px', $('#wmsInvOutBillTable'));
	   </shiro:hasPermission>
	  <shiro:lacksPermission name="wmsinvoutbill:wmsInvOutBill:edit">
	  jp.openDialogView('查看出库单', "${ctx}/wmsinvoutbill/wmsInvOutBill/form?id=" + id,'800px', '500px', $('#wmsInvOutBillTable'));
	  </shiro:lacksPermission>
  }
  
  
  
  
		   
  function detailFormatter(index, row) {
	  var htmltpl =  $("#wmsInvOutBillChildrenTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
	  var html = Mustache.render(htmltpl, {
			idx:row.id
		});
	  $.get("${ctx}/wmsinvoutbill/wmsInvOutBill/detail?id="+row.id, function(wmsInvOutBill){
    	var wmsInvOutBillChild1RowIdx = 0, wmsInvOutBillChild1Tpl = $("#wmsInvOutBillChild1Tpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
		var data1 =  wmsInvOutBill.wmsInvOutBillListList;
		for (var i=0; i<data1.length; i++){
			addRow('#wmsInvOutBillChild-'+row.id+'-1-List', wmsInvOutBillChild1RowIdx, wmsInvOutBillChild1Tpl, data1[i]);
			wmsInvOutBillChild1RowIdx = wmsInvOutBillChild1RowIdx + 1;
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
<script type="text/template" id="wmsInvOutBillChildrenTpl">//<!--
	<div class="tabs-container">
		<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-{{idx}}-1" aria-expanded="true">出库申请单子表</a></li>
		</ul>
		<div class="tab-content">
				 <div id="tab-{{idx}}-1" class="tab-pane fade in active">
						<table class="ani table">
						<thead>
							<tr>
								<th>库位</th>
								<th>物料</th>
								<th>质量编号</th>
								<th>供方合格证号</th>
								<th>申请数量</th>
								<th>出库人</th>
								<th>出库日期</th>
								<th>领用人</th>
								<th>更新时间</th>
								<th>备注信息</th>
							</tr>
						</thead>
						<tbody id="wmsInvOutBillChild-{{idx}}-1-List">
						</tbody>
					</table>
				</div>
		</div>//-->
	</script>
	<script type="text/template" id="wmsInvOutBillChild1Tpl">//<!--
				<tr>
					<td>
						{{row.wmsLocatorCode}}
					</td>
					<td>
						{{row.itemCode}}
					</td>
					<td>
						{{row.qualityNo}}
					</td>
					<td>
						{{row.supplyRegularNo}}
					</td>
					<td>
						{{row.applyQty}}
					</td>
					<td>
						{{row.outSysUserName}}
					</td>
					<td>
						{{row.outDate}}
					</td>
					<td>
						{{row.leaderSysUserName}}
					</td>
					<td>
						{{row.updateDate}}
					</td>
					<td>
						{{row.remarks}}
					</td>
				</tr>//-->
	</script>
