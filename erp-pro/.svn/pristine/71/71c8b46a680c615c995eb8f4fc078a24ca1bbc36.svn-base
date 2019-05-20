<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	//zTree初始化
	$.getJSON("${ctx}/mds/mdsquota/mdsQuota/bootstrapTreeData?id="+$('#mdsQuotaId')[0].value,function(data){
		$('#mdsQuotaDetailTree').treeview({
			data: data,
			levels: 5,
            onNodeSelected: function(event, treeNode) {
            	var id = treeNode.id == '0' ? '' :treeNode.id;
				if(treeNode.level == 1){//level=0 代表公司
					$("#classify").val("");
				}else{
					$("#classify").val(treeNode.id);
				}
				
				$('#mdsQuotaDetailTable').bootstrapTable('refresh');
            },
         });
	});
	
	var mdsQuotaId = $('#mdsQuotaId')[0].value;
	var view = $('#view')[0].value;
	$('#mdsQuotaDetailTable').bootstrapTable({
		 
		  	   //请求方法
               method: 'get',
               //类型json
               dataType: "json",
               //数据
               /*data: {mdsQuotaId:}*/
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
               url: "${ctx}/mds/mdsquota/mdsQuota/detailData?mdsQuotaId="+mdsQuotaId,
               //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
               //queryParamsType:'',   
               ////查询参数,每次调用是会带上这个参数，可自定义                         
               queryParams : function(params) {
               	var searchParam = $("#searchForm").serializeJSON();
               	searchParam.pageNo = params.limit === undefined? "1" :params.offset/params.limit+1;
               	searchParam.pageSize = params.limit === undefined? -1 : params.limit;
               	if(params.sort=="itemCode"){
               		params.sort = "item_code";
               	}
               	if(params.sort=="itemName"){
               		params.sort = "item_name";
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
                   } else if($el.data("item") == "delete"){
                        jp.confirm('确认要删除该材料详情记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/mds/mdsquota/mdsQuotaDetail/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#mdsQuotaDetailTable').bootstrapTable('refresh');
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
		       
		    }/*
             ,{
   		        field: 'mdsQuota.id',
   		        title: '材料定额表ID',
   		        visible: false
   		    }*/
			,{
		        field: 'serial',
		        title: '序号',
		        sortable: true,
		        formatter:function(value, row , index){
		        		return row.serial;
		         }
		       
		    }
			/*,{
		        field: 'mdsItemId',
		        title: '物料id',
   		        visible: false
		       
		    }*/
			,{
		        field: 'itemCode',
		        title: '物料编码',
		        sortable: true,
		        formatter:function(value, row , index){
		        	if(row.isVersion == "0"){
		        		return "<a href='javascript:edit(\""+row.id+"\")'>"+value+"</a>";
		        	}else{
		        		return value;
		        	}
		         }
		       
		    }
			,{
		        field: 'itemName',
		        title: '物料名称',
		        sortable: true
		       
		    }
			,{
		        field: 'normalField',
		        title: '标准号',
		        sortable: false
		       
		    }
			,{
		        field: 'modelNum',
		        title: '材质',
		        sortable: false
		       
		    }
			,{
		        field: 'specs',
		        title: '规格/型号',
		        sortable: false
		       
		    }
			,{
		        field: 'quota',
		        title: '单台定额',
		        sortable: false
		       
		    }
			,{
		        field: 'mdsUnitId',
		        title: '单位',
		        sortable: false
		       
		    }
			,{
		        field: 'createBy.name',
		        title: '录入人',
		        sortable: false
		       
		    },{
		        field: 'createDate',
		        title: '录入时间',
		        sortable: false
		       
		    }
			,{
		        field: 'verifyBy',
		        title: '校验人',
		        sortable: false
		       
		    },{
		        field: 'remarks',
		        title: '备注',
		        sortable: false
		       
		    },{
		    	field: "isVersion",
		    	title: "定版状态",
		    	sortable: false,
		    	formatter:function(value, row , index){
		    		if(value == "0"){
		    			return "未定版";
		    		}else{
		    			return "已定版";
		    		}
		         }
		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#mdsQuotaDetailTable').bootstrapTable("toggleView");
		}
	/*	$('#add').prop('disabled',$('#mdsQuotaState')[0].value == '30');
		$('#btnImport').prop('disabled',$('#mdsQuotaState')[0].value == '30');*/
	  
	  /*
	   * 判断当前选择的物料记录中是否有状态为已定版的记录 
	   */
	  function version() {
		  var notVersion = 0;
		  var list = $("#mdsQuotaDetailTable").bootstrapTable('getSelections');
	       for(var i=0;i<list.length;i++){
	    	   if(list[i].isVersion == "1"){
	        		notVersion++;
	        	}
	       }
	       return notVersion >0 ? false : true;
	    }
	  
	  $('#mdsQuotaDetailTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () { 
            $('#remove').prop('disabled',!($('#mdsQuotaDetailTable').bootstrapTable('getSelections').length >0 && version()));
            $('#edit').prop('disabled', !($('#mdsQuotaDetailTable').bootstrapTable('getSelections').length ==1 && version()));
            $('#delconfirm').prop('disabled', !($('#mdsQuotaDetailTable').bootstrapTable('getSelections').length>0 && !version()));
        });
		  
	  $("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
				    	 var mdsQuotaId = document.getElementById("mdsQuotaId").value;
					  window.location='${ctx}/mds/mdsquota/mdsQuotaDetail/template?id='+mdsQuotaId;
				  },
			    btn2: function(index, layero){
				        var inputForm =top.$("#importForm");
				        var top_iframe = top.getActiveTab().attr("name");// 获取当前active的tab的iframe
				        inputForm.attr("target",top_iframe);// 表单提交成功后，从服务器返回的url在当前tab中展示
				        inputForm.onsubmit = function(){
				        	jp.loading('正在导入，请稍等...');
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
		  $('#mdsQuotaDetailTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#mdsQuotaDetailTable').bootstrapTable('refresh');
		});
		
		
	});
		
  function getIdSelections() {
        return $.map($("#mdsQuotaDetailTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function del(){

		jp.confirm('确认要删除该材料详情记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/mds/mdsquota/mdsQuotaDetail/delete?id=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#mdsQuotaDetailTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
  
  function deleteAll(){

		jp.confirm('确认要删除材料详情记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/mds/mdsquota/mdsQuotaDetail/deleteAll?ids=" + getIdSelections(), function(data){
       	  		if(data.success){
       	  			$('#mdsQuotaDetailTable').bootstrapTable('refresh');
       	  			jp.success(data.msg);
       	  		}else{
       	  			jp.error(data.msg);
       	  		}
       	  	})
        	   
		})
  }
  
  function delconfirm(){

		jp.confirm('确认要撤销定版吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/mds/mdsquota/mdsQuotaDetail/delconfirm?ids=" + getIdSelections(), function(data){
     	  		if(data.success){
     	  			$('#mdsQuotaDetailTable').bootstrapTable('refresh');
     	  			jp.success(data.msg);
     	  		}else{
     	  			jp.error(data.msg);
     	  		}
     	  	})
      	   
		})
}
   function add(){
	  jp.openDialog('新建材料', "${ctx}/mds/mdsquota/mdsQuotaDetail/detailEdit?mdsQuotaId="+$('#mdsQuotaId')[0].value+"&classify="+$('#classify')[0].value,'800px', '500px', $('#mdsQuotaDetailTable'));
  }
  function edit(id){
  	  if(id == undefined){
			id = getIdSelections();
		}
	  jp.openDialog('编辑材料', "${ctx}/mds/mdsquota/mdsQuotaDetail/detailEdit?id=" + id,'800px', '500px', $('#mdsQuotaDetailTable'));
  }
</script>