<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#mdsArrivalAffirmTable').bootstrapTable({
		 
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
               url: "${ctx}/mds/mdsarrivalaffirm/mdsArrivalAffirm/data",
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
               	if(params.sort == "purchaseAmount"){
               		params.sort = "purchase_amount"
               	}
               	if(params.sort == "requiredDate"){
               		params.sort = "required_date"
               	}
               	if(params.sort == "procurementWay"){
               		params.sort = "procurement_way"
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
                   	edit();
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
		       
		    }
			,{
		        field: 'itemName',
		        title: '物料名称',
		        sortable: true,
		        formatter:function(value, row , index){
			       return "<a href='javascript:view(\""+row.id+"\")'>"+value+"</a>";
			    }
		       
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
		        title: '供应商名字',
		        sortable: true
		       
		    }
			,{
		        field: 'purchaseAmount',
		        title: '采购数量',
		        sortable: true
		       
		    }
			,{
		        field: 'arrivalnum',
		        title: '已到货数量',
		        sortable: true
		       
		    }
			,{
		        field: 'arrivalnumNew',
		        title: '到货数量'
		        ,formatter:function(value,row,index){
		        	return "<input type='text' id='arrivalnumNew"+row.id+"' value ="+value+" style='width:80px' onchange='change("+index+")'>";
		        }
		    }
			,{
		        field: 'requiredDate',
		        title: '需求时间',
		        sortable: true
		       
		    }
			,{
		        field: 'procurementWay',
		        title: '采购方式',
		        sortable: true
		    }
			,{
		        field: 'particulars',
		        title: '详情',
		        sortable: true
		        ,formatter:function(value,row,index){
		        	if(row.sourceType=="项目录入"){
		        		return "<a href='javascript:project(\""+row.fromId+"\")'>"+value+"</a>";
		        	}else if(row.sourceType=="手动录入"){
		        		return value;
		        	}else{
		        		return "<a href='javascript:temporary(\""+row.fromId+"\")'>"+value+"</a>";
		        	}
		        }
		    }/*,{
   		        field: 'index',
   		        title: '序号',
   		         visible: false,
   		        switchable: false,//禁用可切换的列项
   		    	formatter: function(value,row,index){
					value = index;
		        	return value;
		        }
   		    }*/
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#mdsArrivalAffirmTable').bootstrapTable("toggleView");
		}
	  
	  $('#mdsArrivalAffirmTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#edit').prop('disabled', !$('#mdsArrivalAffirmTable').bootstrapTable('getSelections').length);
        });
		  
		    
	  $("#search").click("click", function() {// 绑定查询按扭
		  $('#mdsArrivalAffirmTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#mdsArrivalAffirmTable').bootstrapTable('refresh');
		});
		
		 $('#beginRequiredDate').datetimepicker({
			 format: "YYYY-MM-DD"
		});
		 $('#endRequiredDate').datetimepicker({
			 format: "YYYY-MM-DD"
		});
	});

	//当修改了到货数量时勾选
	function change(num){
		$("#mdsArrivalAffirmTable").bootstrapTable('check', num);
	}
		
  function getIdSelections() {
        return $.map($("#mdsArrivalAffirmTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  //返回所选信息的id,到货数量
  function getMessageSelections() {
	  var n = 0;
      var date= $.map($("#mdsArrivalAffirmTable").bootstrapTable('getSelections'), function (row) {
    	  var arrivalnumNew=$("#arrivalnumNew"+row.id).val();
    	  console.log(parseInt(arrivalnumNew));
    	  if(arrivalnumNew=='0'||arrivalnumNew==''||arrivalnumNew<=0 || isNaN(parseInt(arrivalnumNew))){
    		  
    		  return n++;
    	  }else if(row.purchaseAmount<(parseInt(row.arrivalnum)+parseInt(arrivalnumNew))){
    		  $("#arrivalnumNew"+row.id).val(row.purchaseAmount-row.arrivalnum);
    		  return  n++;
    	  }
          return row.id+":"+arrivalnumNew;
      });
      if(n==0){
    	  return date;
      }else{
    	  return null;
      }
  }

  function edit(id){//没有权限时，不显示确定按钮
	  var date = getMessageSelections();
	  
	  //修改对到货数的判断
	  
	  if(date==null){
		  jp.error("到货数不合法!");
	  }else{
		  jp.confirm('确认要保存到货数量吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/mds/mdsarrivalaffirm/mdsArrivalAffirm/save?date=" + date, function(data){
		         	if(data.success){
		         	  	$('#mdsArrivalAffirmTable').bootstrapTable('refresh');
		         	  	jp.success(data.msg);
		         	  }else{
		         	  	jp.error(data.msg);
		         	  }
		         	 })
			})
	  }
  }
  
  function view(id){
	  jp.openDialogView('查看到货详情', "${ctx}/mds/mdsarrivalaffirm/mdsArrivalAffirm/form?id=" + id,'800px', '500px', $('#mdsArrivalAffirmTable'));
  }

  //销售订单信息显示
  function project(id){
	   jp.openDialogView('查看销售订单信息', "${ctx}/mds/mdspcmplanprocurement/project?id=" + id,'800px', '500px', $('#mdsArrivalAffirmTable'));
  }
  
  //临时申请页显示
  function temporary(id){
	   jp.openDialogView('查看临时计划信息', "${ctx}/mds/mdspcmplanprocurement/temporary?id=" + id,'800px', '500px', $('#mdsArrivalAffirmTable'));
  }
</script>