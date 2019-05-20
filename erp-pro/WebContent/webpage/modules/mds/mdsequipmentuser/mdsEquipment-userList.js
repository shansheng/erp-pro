<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
		$('#userTable').bootstrapTable({
			  //请求方法
                method: 'get',
                dataType: "json",
                 //是否显示行间隔色
                striped: true,
                //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）     
                cache: false,    
                //是否显示分页（*）  
                pagination: true, 
                
                pageList: [10, 25, 50, 100],
                //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
                url:"${ctx}/mdsequipmentuser/mdsEquipment/assign",
                //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
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
                columns: [{
			        field: 'loginName',
			        title: '登录名'
			    }, {
			        field: 'sysUserName',
			        title: '姓名'
			    }, {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    events: {
        		        'click .del': function (e, value, row, index) {
        		        	
        		        	jp.confirm('确认要用户从角色中移除吗？',function(){
        		        		jp.loading();
        		        		$.get('${ctx}/mdsequipmentuser/mdsEquipment/deluser?id='+row.id, function(data){
  	                    	  		if(data.success){
  	                    	  			$('#userTable').bootstrapTable("refresh",{query:{"mdsEquipmentId":$("#mdsEquipmentId").val()}});
  	                    	  			jp.success(data.msg);
  	                    	  		}else{
  	                    	  			jp.error(data.msg);
  	                    	  		}
  	                    	  	})
        		        	});
        		        }
        		    },
                    formatter:  function operateFormatter(value, row, index) {
        		        return [
        		        	<shiro:hasPermission name="sys:role:assign">
    						'<a href="#" class="del" title="移除" >[移除] </a>'
    						</shiro:hasPermission>
        		        ].join('');
        		    }
                }]
			
			});
		
		  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端
			  $('#userTable').bootstrapTable("toggleView");
			}
		  
		  
		  
		  
			$("#assignButton").click(function(row){
				jp.openUserSelectDialog(true,function(ids){
					jp.loading();
			    	 $.ajax({
				 	        async: false,
				 	        url: "${ctx}/mdsequipmentuser/mdsEquipment/saveUsers",
				 	        data:{"mdsEquipmentId": $("#mdsEquipmentId").val(),ids:ids},
				 	        //data: param,
				 	        dataType: "json",
				 	        success: function (data) {
				 	    	    if(data.success){
				 	    	    	$('#userTable').bootstrapTable("refresh",{query:{"mdsEquipmentId":$("#mdsEquipmentId").val()}});
				 	    	    	top.layer.alert(data.msg);
				 	    	    	
				 	    	    }else{
				 	    	    	top.layer.alert(data.msg);
				 	    	    }
				 	        }
				 		 });
				})
			});
		  
		  });


</script>