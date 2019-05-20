<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#matRequisitionTable').bootstrapTable({
		 
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
               url: "${ctx}/matdepartment/matDepartment/data",
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
                        jp.confirm('确认要删除该领料单流程记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/matdepartment/matDepartment/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#matRequisitionTable').bootstrapTable('refresh');
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
		       
		    },{
		        field: 'code',
		        title: '申请单号',
		        sortable: true ,
		        formatter:function(value, row , index){
		        	if(row.state == 20 || row.state == 21){
		        		return "<a href='javascript:edit(\""+row.id+"\")'>"+value+"</a>";
		        	}else{
		        		return value;
		        	}
		         }
		       
		    }
			,{
		        field: 'userName',
		        title: '申请人'
		       
		    }
			,{
		        field: 'createDate',
		        title: '申请时间',
		       
                formatter : crtTimeFtt
		       
		    }
			,{
		        field: 'remarks',
		        title: '申请原因'
		       
		    },{
		        field: 'state',
		        title: '状态',
		        sortable: true,
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('requisition_state'))}, value, "-");
		        }
		       
		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#matRequisitionTable').bootstrapTable("toggleView");
		}
	  
	  $('#matRequisitionTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', ! $('#matRequisitionTable').bootstrapTable('getSelections').length);
            $('#edit').prop('disabled', $('#matRequisitionTable').bootstrapTable('getSelections').length!=1);
        });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/matdepartment/matDepartment/import/template';
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
		  $('#matRequisitionTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		   $("#searchForm  .select-item").html("");
		  $('#matRequisitionTable').bootstrapTable('refresh');
		});
		
				$('#beginCreateDate').datetimepicker({
					 format: "YYYY-MM-DD"
				});
				$('#endCreateDate').datetimepicker({
					 format: "YYYY-MM-DD"
				});
				$('#createDate').datetimepicker({
					 format: "YYYY-MM-DD"
				});
				$('#demandDate').datetimepicker({
					 format: "YYYY-MM-DD"
				});
				
	});
		
  function getIdSelections() {
        return $.map($("#matRequisitionTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){

		jp.confirm('确认要删除该领料单流程记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/matdepartment/matDepartment/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#matRequisitionTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
  
  function add(){
	  jp.openDialog('新增领料单流程', "${ctx}/matdepartment/matDepartment/form",'800px', '500px', $('#matRequisitionTable'));
  }
  
  function edit(id){//没有权限时，不显示确定按钮
  	  if(id == undefined){
			id = getIdSelections();
		}
	   <shiro:hasPermission name="matdepartment:matDepartment:edit">
	  jp.openDialog('编辑领料单流程', "${ctx}/matdepartment/matDepartment/form?id=" + id,'800px', '500px', $('#matRequisitionTable'));
	   </shiro:hasPermission>
	  <shiro:lacksPermission name="matdepartment:matDepartment:edit">
	  jp.openDialogView('查看领料单流程', "${ctx}/matdepartment/matDepartment/form?id=" + id,'800px', '500px', $('#matRequisitionTable'));
	  </shiro:lacksPermission>
  }
  
  
  
  
		   
  function detailFormatter(index, row) {
	  var htmltpl =  $("#matRequisitionChildrenTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
	  var html = Mustache.render(htmltpl, {
			idx:row.id
		});
	  $.get("${ctx}/matdepartment/matDepartment/detail?id="+row.id, function(matRequisition){
    	var matRequisitionChild1RowIdx = 0, matRequisitionChild1Tpl = $("#matRequisitionChild1Tpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
		var data1 =  matRequisition.matRequisitionListList;
		for (var i=0; i<data1.length; i++){
			addRow('#matRequisitionChild-'+row.id+'-1-List', matRequisitionChild1RowIdx, matRequisitionChild1Tpl, data1[i]);
			matRequisitionChild1RowIdx = matRequisitionChild1RowIdx + 1;
		}
				
      	  			
      })
     
        return html;
    }
  function crtTimeFtt(val, row) {
	    if (val != null) {
	            var date = new Date(val);
	            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
	        }
	}
	function addRow(list, idx, tpl, row){
		$(list).append(Mustache.render(tpl, {
			idx: idx, delBtn: true, row: row
		}));
	}
	 function doReject() {
		  
		  jp.confirm('确认要驳回该部门领导审核记录吗？', function(){
				jp.loading();  	
				jp.get("${ctx}/matdepartment/matDepartment/doreject?id=" + getIdSelections(), function(data){
	       	  		if(data.success){
	       	  			$('#matRequisitionTable').bootstrapTable('refresh');
	       	  			jp.success(data.msg);
	       	  		}else{
	       	  			jp.error(data.msg);
	       	  		}
	       	  	})
	        	   
			})
		  
		};

			 function doAssessing() {
				 jp.confirm('确认要同意该部门领导审核记录吗？', function(){
						jp.loading();  	
						jp.get("${ctx}/matdepartment/matDepartment/save?id=" + getIdSelections(), function(data){
			       	  		if(data.success){
			       	  			$('#matRequisitionTable').bootstrapTable('refresh');
			       	  			jp.success(data.msg);
			       	  		}else{
			       	  			jp.error(data.msg);
			       	  		}
			       	  	})
			        	   
					})
			};	
</script>
<script type="text/template" id="matRequisitionChildrenTpl">//<!--
	<div class="tabs-container">
		<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-{{idx}}-1" aria-expanded="true">物料申请表</a></li>
		</ul>
		<div class="tab-content">
				 <div id="tab-{{idx}}-1" class="tab-pane fade in active">
						<table class="ani table">
						<thead>
							<tr>
							<th>物料编号</th>
							<th>物料名字</th>
							<th>申请数量</th>
							<th>审批数量</th>
							<th>需求日期</th>
							<th>备注</th>
								
							</tr>
						</thead>
						<tbody id="matRequisitionChild-{{idx}}-1-List">
						</tbody>
					</table>
				</div>
		</div>//-->
	</script>
	<script type="text/template" id="matRequisitionChild1Tpl">//<!--
	<tr>
	<td>
		{{row.itemCode}}
	</td>
	<td>
		{{row.itemName}}
	</td>
	<td>
		{{row.applyNumber}}
	</td>
	<td>
		{{row.applyovalNumber}}
	</td>
	<td>
		{{row.demandDate}}
	</td>
	<td>
		{{row.remarks}}
	</td>
</tr>//-->
	</script>
