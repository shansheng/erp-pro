<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#wmsMainInventoryTable').bootstrapTable({
		 
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
               url: "${ctx}/wms/wmsMainInventory/data",
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
               	if(params.sort == "modelNum"){
               		params.sort = "model_num"
               	}
               	if(params.sort == "currentOnhandQty"){
               		params.sort = "current_onhand_qty"
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
		        field: 'itemCode',
		        title: '物料编码',
		        sortable: true
		        ,formatter:function(value, row , index){
		        	return "<a href='javascript:view(\""+row.id+"\")'>"+value+"</a>";
		         }
		    }
			,{
		        field: 'itemName',
		        title: '物料名字',
		        sortable: true
		    }
			,{
		        field: 'normalField',
		        title: '标准号',
		        sortable: true
		    }
			,{
		        field: 'modelNum',
		        title: '材质',
		        sortable: true
		    }
			,{
		        field: 'specs',
		        title: '规格/型号',
		        sortable: true
		    }
			,{
		        field: 'currentOnhandQty',
		        title: '现在的库存',
		        sortable: true
		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#wmsMainInventoryTable').bootstrapTable("toggleView");
		}
	  
	  $('#wmsMainInventoryTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#view').prop('disabled', $('#wmsMainInventoryTable').bootstrapTable('getSelections').length!=1);
        });
		  
		/*$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/wms/maininventory/wmsMainInventory/import/template';
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
		});*/
		    
	  $("#search").click("click", function() {// 绑定查询按扭
		  $('#wmsMainInventoryTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		   $("#searchForm  .select-item").html("");
		  $('#wmsMainInventoryTable').bootstrapTable('refresh');
		});
		
		
	});
		
  function getIdSelections() {
        return $.map($("#wmsMainInventoryTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  /*function deleteAll(){

		jp.confirm('确认要删除该台账记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/wms/maininventory/wmsMainInventory/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#wmsMainInventoryTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }*/
  
  /*function add(){
	  jp.openDialog('新增台账', "${ctx}/wms/maininventory/wmsMainInventory/form",'800px', '500px', $('#wmsMainInventoryTable'));
  }*/
  
  function view(id){//没有权限时，不显示确定按钮
  	  if(id == undefined){
			id = getIdSelections();
		}
	  jp.openDialogView('查看台账', "${ctx}/wms/wmsMainInventory/form?id=" + id,'800px', '650px', $('#wmsMainInventoryTable'));
  }
  
  function detailFormatter(index, row) {
	  var htmltpl =  $("#wmsMainInventoryChildrenTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
	  var html = Mustache.render(htmltpl, {
			idx:row.id
		});
	  $.get("${ctx}/wms/wmsMainInventory/detail?id="+row.id, function(wmsMainInventory){
    	var wmsMainInventoryChild1RowIdx = 0, wmsMainInventoryChild1Tpl = $("#wmsMainInventoryChild1Tpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
		var data1 =  wmsMainInventory.wmsMainRecordList;
		for (var i=0; i<data1.length; i++){
			addRow('#wmsMainInventoryChild-'+row.id+'-1-List', wmsMainInventoryChild1RowIdx, wmsMainInventoryChild1Tpl, data1[i]);
			wmsMainInventoryChild1RowIdx = wmsMainInventoryChild1RowIdx + 1;
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
<script type="text/template" id="wmsMainInventoryChildrenTpl">//<!--
	<div class="tabs-container">
		<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-{{idx}}-1" aria-expanded="true">库房记录表</a></li>
		</ul>
		<div class="tab-content">
				 <div id="tab-{{idx}}-1" class="tab-pane fade in active">
						<table class="ani table">
						<thead>
							<tr>
								<th>物料编码</th>
								<th>物料名字</th>
								<th>操作数</th>
								<th>供应商</th>
								<th>创建人</th> 
								<th>创建时间</th>
								<th>状态</th>
							</tr>
						</thead>
						<tbody id="wmsMainInventoryChild-{{idx}}-1-List">
						</tbody>
					</table>
				</div>
		</div>//-->
	</script>
	<script type="text/template" id="wmsMainInventoryChild1Tpl">//<!--
				<tr>
					<td>
						{{row.itemCode}}
					</td>
					<td>
						{{row.itemName}}
					</td>
					<td>
						{{row.qty}}
					</td>
					<td>
					{{row.supplierName}}
					</td>
					<td>
						{{row.createBy.name}}
					</td>
					<td>
						{{row.createDate}}
					</td>
					<td>
						{{row.state}}
					</td>
				</tr>//-->
	</script>
