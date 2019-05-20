<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#mrpWoVTable').bootstrapTable({
		 
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
               url: "${ctx}/mrpwofeeding/mrpWoV/data",
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
                        jp.confirm('确认要删除该投料记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/mrpwofeeding/mrpWoV/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#mrpWoVTable').bootstrapTable('refresh');
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
   		        title: '操作',
   		        formatter:function( value,row , index){
		        	return "<a href='javascript:tlSubmit1(\""+row.id+"\")'>投料</a>";
		         }
   		    }
			,{
		        field: 'yearStr',
		        title: '年份'
		       
		    }
			,{
		        field: 'monthStr',
		        title: '月份'
		       
		    }
			,{
		        field: 'taskNo',
		        title: '任务编号'
		       
		    }
			,{
		        field: 'batchNo',
		        title: '批次'
		       
		    }
			,{
		        field: 'proMdsItemCode',
		        title: '产品'
		       
		    }
			,{
		        field: 'proMdsItemCode',
		        title: '零件编号'
		       
		    }
			,{
		        field: 'mdsItemName',
		        title: '零件名称'
		       
		    }
			,{
		        field: 'reqQty',
		        title: '需求数量'
		       
		    }
			,{
		        field: 'toEwoQty',
		        title: '已投料数'
		       
		    }
			,{
		        field: 'tlQty',
		        title: '投料数量',
		       
		        formatter:function(value, row , index){
					var tlqty=row.tlQty;
		        	return "<input id='"+row.id+"tlQty' name='sorts' type='text' value="+tlqty+" class='form-control' style='width:100px;margin:0;padding:0;text-align:center;'>"
		        }
		    }
			,{
		        field: 'planStartDate',
		        title: '计划开工日期',
		       
                formatter : crtTimeFtt
    		       
		       
		    }
			,{
		        field: 'planEndDate',
		        title: '计划完工日期',
		       
                formatter : crtTimeFtt
    		       
		       
		    }
			,{
		        field: 'mainOfficeName',
		        title: '部门名称'
		       
		    }
			,{
		        field: 'woStatusLable',
		        title: '状态'
		       
		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#mrpWoVTable').bootstrapTable("toggleView");
		}
	  
	  $('#mrpWoVTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#tlSubmit').prop('disabled', ! $('#mrpWoVTable').bootstrapTable('getSelections').length);
            
        });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/mrpwofeeding/mrpWoV/import/template';
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
		  $('#mrpWoVTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#mrpWoVTable').bootstrapTable('refresh');
		});
		
	});
		
  function getIdSelections() {
        return $.map($("#mrpWoVTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){
		jp.confirm('确认要删除该投料记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/mrpwofeeding/mrpWoV/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#mrpWoVTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})   
		})
  }
   function add(){
	  jp.openDialog('新增投料', "${ctx}/mrpwofeeding/mrpWoV/form",'800px', '500px', $('#mrpWoVTable'));
  }
  function edit(id){//没有权限时，不显示确定按钮
  	  if(id == undefined){
			id = getIdSelections();
		}
	   <shiro:hasPermission name="mrpwofeeding:mrpWoV:edit">
	  jp.openDialog('编辑投料', "${ctx}/mrpwofeeding/mrpWoV/form?id=" + id,'800px', '500px', $('#mrpWoVTable'));
	   </shiro:hasPermission>
	  <shiro:lacksPermission name="mrpwofeeding:mrpWoV:edit">
	  jp.openDialogView('查看投料', "${ctx}/mrpwofeeding/mrpWoV/form?id=" + id,'800px', '500px', $('#mrpWoVTable'));
	  </shiro:lacksPermission>
  }
	function tlSubmit(){
		id=getIdSelections();
         	$.ajax({
	 	        async: true,
	 	        url: "${ctx}/mrpwofeeding/mrpWoV/batchMesEwoInsert?id="+id,
	 	        data:  {"tlQty":$('#'+id+'tlQty').val()},
	 	        dataType: "json",
	 	        success: function (data) {
	 	        	console.log(data);
	 	        	if(data.success){
	 	        		$('#mrpWoVTable').bootstrapTable('refresh');
	 	    	    	top.layer.alert("执行成功！");
	 	    	    }else{
	 	    	    	top.layer.alert("执行失败，请联系管理员！");
	 	    	    }
	 	        },
	 	       error:function(){
	 	    	  $('#mrpWoVTable').bootstrapTable('refresh');
	 	    	  top.layer.alert("投料数不能大于库存数！");
               }
	 		});
	}
	
	function tlSubmit1(id){
			$.ajax({
	 	        async: true,
	 	        url: "${ctx}/mrpwofeeding/mrpWoV/batchMesEwoInsert?id="+id,
	 	        data:  {"tlQty":$('#'+id+'tlQty').val()},
	 	        dataType: "json",
	 	        success: function (data) {
	 	        	
	 	        	if(data.success){
	 	        		$('#mrpWoVTable').bootstrapTable('refresh');
	 	    	    	top.layer.alert("执行成功！");
	 	    	    }else{
	 	    	    	top.layer.alert("执行失败，请联系管理员！");
	 	    	    }
	 	        },
	 	       error:function(){
	 	    	  top.layer.alert("投料数不能大于库存数！");
	 	    	 $('#mrpWoVTable').bootstrapTable('refresh');
               }
	 		});
	}
	
	  function crtTimeFtt(val, row) {
		    if (val != null) {
		            var date = new Date(val);
		            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
		        }
		}
	
</script>