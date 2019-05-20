<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#mdsQuotaTable').bootstrapTable({
		 
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
    	      // detailView: true,
    	       	//显示详细内容函数
	           //detailFormatter: "detailFormatter",
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
               url: "${ctx}/mds/mdsquota/mdsQuota/data",
               //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
               //queryParamsType:'',   
               ////查询参数,每次调用是会带上这个参数，可自定义                         
               queryParams : function(params) {
               	var searchParam = $("#searchForm").serializeJSON();
               	searchParam.pageNo = params.limit === undefined? "1" :params.offset/params.limit+1;
               	searchParam.pageSize = params.limit === undefined? -1 : params.limit;
               	if(params.sort=="clientName"){
               		params.sort = "client_name";
               	}
               	if(params.sort=="mdsItemCode"){
               		params.sort = "item_code";
               	}
               	if(params.sort=="mdsItemName"){
               		params.sort = "item_name";
               	}
               	if(params.sort=="mdsItemspecs"){
               		params.sort = "specs";
               	}
               	if(params.sort=="mdsItemmodelNum"){
               		params.sort = "model_num";
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
                  /* if($el.data("item") == "edit"){
                   	window.location = "${ctx}/mds/mdsquota/mdsQuota/form?id=" + row.id;
                   } else if($el.data("item") == "delete"){
                        jp.confirm('确认要删除该材料定额记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/mds/mdsquota/mdsQuota/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#mdsQuotaTable').bootstrapTable('refresh');
                   	  			jp.success(data.msg);
                   	  		}else{
                   	  			jp.error(data.msg);
                   	  		}
                   	  	})
                   	   
                   	});
                      
                   } */
               },
              
               onClickRow: function(row, $el){
               },
               columns: [{
		        checkbox: true
		       
		    }
             ,{
   		        field: 'clientName',
   		        title: '客户名称',
   		        sortable: true,
   		        formatter:function(value, row , index){
	   		    	 if(row.state!="30" && row.state != 40){
			        	return "<a href='javascript:edit(\""+row.id+"\",\""+row.state+"\")'>"+value+"</a>";
	   		    	 }else{
	   		    		 return row.clientName;
	   		    	 }
		         }
   		       
   		    }
			,{
		        field: 'mdsItemCode',
		        title: '产品编号',
		        sortable: true
		       
		    }
			,{
		        field: 'mdsItemName',
		        title: '产品名称',
		        sortable: true
		       
		    }
			,{
		        field: 'mdsItemspecs',
		        title: '规格/型号',
		        sortable: true
		       
		    }
			,{
		        field: 'mdsItemmodelNum',
		        title: '材质',
		        sortable: true
		       
		    }
			,{
		        field: 'num',
		        title: '数量',
		        sortable: true
		       
		    }
			,{
		        field: 'state',
		        title: '订单状态',
		        sortable: true,
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('mds_quota_state'))}, value, "-");
		        }
		       
		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#mdsQuotaTable').bootstrapTable("toggleView");
		}
	  
	  /*
	   * 判断当前记录状态是否可以编制
	   */
	  function isCompile() {
		  var notVersion = 0;
		  var list = $("#mdsQuotaTable").bootstrapTable('getSelections');
	       for(var i=0;i<list.length;i++){
	    	  /* if(list[i].state != "20" && list[i].state != "10" && list[i].state != "40"){
	        		notVersion++;
	        	}*/
	    	   if (list[i].state == 30 || list[i].state == 40) {
	    		   notVersion++;
	    	   }
	       }
	       return notVersion >0 ? false : true;
	    }
	  
	  /*
	   * 判断当前记录状态是否可以编制完成
	   */
	  function isCompileOver() {
		  var notVersion = 0;
		  var list = $("#mdsQuotaTable").bootstrapTable('getSelections');
	       for(var i=0;i<list.length;i++){
	    	   if(list[i].state != "20"){
	        		notVersion++;
	        	}
	       }
	       return notVersion >0 ? false : true;
	    }
	  
	  /*
	   * 判断当前记录状态是否可以撤销编制
	   */
	  function isCompileBack() {
		  var notVersion = 0;
		  var list = $("#mdsQuotaTable").bootstrapTable('getSelections');
	       for(var i=0;i<list.length;i++){
	    	   if(list[i].state != "30"){
	        		notVersion++;
	        	}
	       }
	       return notVersion >0 ? false : true;
	    }
	  
	  /*
	   * 判断当前记录状态是否为编制完成
	   */
	  function isVersion() {
		  var notVersion = 0;  
		  var list = $("#mdsQuotaTable").bootstrapTable('getSelections');
	       for(var i=0;i<list.length;i++){
	    	   if(list[i].state != "30"){
	        		notVersion++;
	        	}
	       }
	       return notVersion >0 ? false : true;
	    }
	  
	  /*
	   * 判断当前记录状态是否为已定版
	   */
	  function isConfirm() {
		  var notVersion = 0;
		  var list = $("#mdsQuotaTable").bootstrapTable('getSelections');
	       for(var i=0;i<list.length;i++){
	    	   if(list[i].state != "40"){
	        		notVersion++;
	        	}
	       }
	       return notVersion >0 ? false : true;
	    }
	  
	  $('#mdsQuotaTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
		  	$('#show').prop('disabled', $('#mdsQuotaTable').bootstrapTable('getSelections').length!=1);
            $('#edit').prop('disabled', !($('#mdsQuotaTable').bootstrapTable('getSelections').length==1 && isCompile()));
            $('#compile').prop('disabled', !($('#mdsQuotaTable').bootstrapTable('getSelections').length==1 && isCompileOver()));
            $('#unCompile').prop('disabled', !($('#mdsQuotaTable').bootstrapTable('getSelections').length==1 && isCompileBack()));
            $('#confirm').prop('disabled', !($('#mdsQuotaTable').bootstrapTable('getSelections').length==1 && isVersion()));
           /* $('#delconfirm').prop('disabled', !($('#mdsQuotaTable').bootstrapTable('getSelections').length==1 && isConfirm()));*/
        });
		  
		    
	  $("#search").click("click", function() {// 绑定查询按扭
		  $('#mdsQuotaTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		   $("#searchForm  .select-item").html("");
		  $('#mdsQuotaTable').bootstrapTable('refresh');
		});
		
		
	});
		
  function getIdSelections() {
        return $.map($("#mdsQuotaTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  function getStateSelections() {
      return $.map($("#mdsQuotaTable").bootstrapTable('getSelections'), function (row) {
          return row.state
      });
  }
  
  function compile(){

		jp.confirm('确认要编制完成吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/mds/mdsquota/mdsQuota/compile?id=" + getIdSelections(), function(data){
       	  		if(data.success){
       	  			$('#mdsQuotaTable').bootstrapTable('refresh');
       	  			jp.success(data.msg);
       	  		}else{
       	  			jp.error(data.msg);
       	  		}
       	  	})
        	   
		})
}
  
  function unCompile(){

		jp.confirm('确认要撤销编制吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/mds/mdsquota/mdsQuota/unCompile?id=" + getIdSelections(), function(data){
       	  		if(data.success){
       	  			$('#mdsQuotaTable').bootstrapTable('refresh');
       	  			jp.success(data.msg);
       	  		}else{
       	  			jp.error(data.msg);
       	  		}
       	  	})
        	   
		})
}
  
  function confirm(){

		jp.confirm('确认要定版吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/mds/mdsquota/mdsQuota/confirm?id=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#mdsQuotaTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
  /*function delconfirm(){

		jp.confirm('确认要撤销定版吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/mds/mdsquota/mdsQuota/delconfirm?id=" + getIdSelections(), function(data){
       	  		if(data.success){
       	  			$('#mdsQuotaTable').bootstrapTable('refresh');
       	  			jp.success(data.msg);
       	  		}else{
       	  			jp.error(data.msg);
       	  		}
       	  	})
        	   
		})
}*/
  function edit(id,state){
	  var searchParam = $("#searchForm").serializeJSON();
	  if(id == undefined && state == undefined){
		  window.location = "${ctx}/mds/mdsquota/mdsQuota/edit?id=" + getIdSelections()+"&column2=" + getStateSelections()+"&clientId="+searchParam.clientId+"&mdsItemCode="+searchParam.mdsItemCode+"&mdsItemName="+searchParam.mdsItemName+"&state="+searchParam.state;
	  }else{
		  window.location = "${ctx}/mds/mdsquota/mdsQuota/edit?id=" + id+"&column2="+state+"&clientId="+searchParam.clientId+"&mdsItemCode="+searchParam.mdsItemCode+"&mdsItemName="+searchParam.mdsItemName+"&state="+searchParam.state;
	  }
	  
  }
  function view(){
	  window.location = "${ctx}/mds/mdsquota/mdsQuota/show?id=" + getIdSelections();
  }
</script>
