<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#mdsArrivalCheckoutTable').bootstrapTable({
		 
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
               url: "${ctx}/mds/mdsarrivalcheckout/mdsArrivalCheckout/data",
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
               	if(params.sort == "supplierName"){
               		params.sort = "supplier_name"
               	}
               	
               	if(params.sort == "arrivalNum"){
               		params.sort = "arrival_num"
               	}
               	if(params.sort == "createBy.name"){
               		params.sort = "create_by"
               	}
               	if(params.sort == "createDate"){
               		params.sort = "create_date"
               	}
               	if(params.sort == "updateBy.name"){
               		params.sort = "update_by"
               	}
               	if(params.sort == "updateDate"){
               		params.sort = "update_date"
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
   		        title: '物料编号',
   		        sortable: true
   		       
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
    		     field: 'modelnm',
    		     title: '材质',
    		     sortable: true
    		       
    		 }
             ,{
    		     field: 'specs',
    		     title: '规格/型号',
    		     sortable: true
    		       
    		 }
             ,{
 		        field: 'supplierName',
 		        title: '供应商名称',
 		        sortable: true
 		       
 		    }
			,{
		        field: 'arrivalNum',
		        title: '到货数量',
		        sortable: true
		    }
			,{
		        field: 'createBy.name',
		        title: '到货确认者',
		        sortable: true
		       
		    }
			,{
		        field: 'createDate',
		        title: '到货确认日期',
		        sortable: true
		       
		    }
			,{
		        field: 'qualified',
		        title: '合格数'
		        ,formatter:function(value,row,index){
		        	if(row.inStoargeState=="0"){
			        	if(row.state=='0'){
			        		return "<input type='text' id='qualified"+row.id+"' style='width:80px' onchange='change("+index+")'>";
			        	}else{
			        		return "<input type='text' id='qualified"+row.id+"' style='width:80px' onchange='change("+index+")' value="+value+">";
			        	}
		        	}else{
		        		return value;
		        	}
		        }
		       
		    }
			,{
		        field: 'disqualification',
		        title: '不合格数'
		        ,formatter:function(value,row,index){
		        	if(row.inStoargeState=="0"){
			        	if(row.state=='0'&&row.inStoargeState=="0"){
			        		return "<input type='text' id='disqualification"+row.id+"' style='width:80px' onchange='change("+index+")'>";
			        	}else{
			        		return "<input type='text' id='disqualification"+row.id+"' style='width:80px' onchange='change("+index+")' value="+value+">";
			        	}
		        	}else{
		        		return value;
		        	}
		        }
		       
		    }
			,{
		        field: 'updateBy.name',
		        title: '检验人',
		        sortable: true
		       
		    }
			,{
		        field: 'updateDate',
		        title: '检验时间',
		        sortable: true
		       
		    }
			,{
		        field: 'state',
		        title: '状态',
		        sortable: true
		        ,formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('mds_arrival_checkout'))}, value, "-");
		        }
		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#mdsArrivalCheckoutTable').bootstrapTable("toggleView");
		}
	  
	  $('#mdsArrivalCheckoutTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', ! $('#mdsArrivalCheckoutTable').bootstrapTable('getSelections').length);
            $('#edit').prop('disabled', !$('#mdsArrivalCheckoutTable').bootstrapTable('getSelections').length);
        });
		  
		/*$("#btnImport").click(function(){
			jp.open({
			    type: 1, 
			    area: [500, 300],
			    title:"导入数据",
			    content:$("#importBox").html() ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					  window.location='${ctx}/mds/mdsarrivalcheckout/mdsArrivalCheckout/import/template';
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
		  $('#mdsArrivalCheckoutTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#mdsArrivalCheckoutTable').bootstrapTable('refresh');
		});
		
		
	});
	//当修改了到货数量时勾选
	function change(num){
		$("#mdsArrivalCheckoutTable").bootstrapTable('check', num);
	}
		
  function getIdSelections() {
        return $.map($("#mdsArrivalCheckoutTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
//返回所选信息的id,合格数量,不合格数量
  function getMessageSelections() {
	  var n = 0;
	  var x = 0;
      var date= $.map($("#mdsArrivalCheckoutTable").bootstrapTable('getSelections'), function (row) {
    	  var qualified=$("#qualified"+row.id).val();
    	  var disqualification=$("#disqualification"+row.id).val();
    	  if(qualified==''||disqualification==''||qualified<0||disqualification<0){
    		  return n++;
    	  }
    	  if(qualified>row.arrivalNum){
    		  return n++;
    	  }
    	  if(row.arrivalNum!=(parseInt(qualified)+parseInt(disqualification))){
    		  return x++;
    	  }
          return row.id+":"+qualified+":"+disqualification;
      });
      if(n==0&&x==0){
    	  return date;
      }else if(n!=0){
    	  return 1;
      }else{
    	  return -1;
      }
  }
  
  function edit(id){//没有权限时，不显示确定按钮
	  var date =  getMessageSelections();
	  if(date==1){
		  jp.error("合格数或不合格数合法!");
	  }else if(date==-1){
		  jp.error("合格数与不合格数相加不等于到货数!");
	  }else{
		  jp.confirm('确认要保存到检验数据吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/mds/mdsarrivalcheckout/mdsArrivalCheckout/save?date=" + date, function(data){
		         	if(data.success){
		         	  	$('#mdsArrivalCheckoutTable').bootstrapTable('refresh');
		         	  	jp.success(data.msg);
		         	  }else{
		         	  	jp.error(data.msg);
		         	  }
		         	 })
			})
	  }
  }

</script>