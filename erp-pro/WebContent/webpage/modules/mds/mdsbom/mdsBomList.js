<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	refreshTree("a0c7e9b03c3a4366b51b1ac4aeca2336","a0c7e9b03c3a4366b51b1ac4aeca2336","p");
  
  
  $("#search").click("click", function() {// 绑定查询按扭
	  refreshTree("a0c7e9b03c3a4366b51b1ac4aeca2336","a0c7e9b03c3a4366b51b1ac4aeca2336","p");
  });
		 
	$("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  // $('#mdsBomTable').bootstrapTable('refresh');
		});
	});

  function refreshTree(mdsItemId,proMdsItemId,bomType){
	 //$.jstree.create($('#mdsBomTreeDiv'),);
	 $('#mdsBomTreeDiv').jstree({
		'core' : {
	        'data' : {
	          "url" : "${ctx}/mdsbom/mdsBom/treedata?mdsItemId=" + mdsItemId + "&proMdsItemId=" + proMdsItemId + "&bomType=" + bomType,
	          "dataType" : "json"
	        }
	    }
    });
  }
		
  function getIdSelections() {
        return $.map($("#mdsBomTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){

		jp.confirm('确认要删除该BOM记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/mdsbom/mdsBom/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#mdsBomTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
   function add(){
	  jp.openDialog('新增BOM', "${ctx}/mdsbom/mdsBom/form",'800px', '500px', $('#mdsBomTable'));
  }
  function edit(id){// 没有权限时，不显示确定按钮
  	  if(id == undefined){
			id = getIdSelections();
		}
	   <shiro:hasPermission name="mdsbom:mdsBom:edit">
	  jp.openDialog('编辑BOM', "${ctx}/mdsbom/mdsBom/form?id=" + id,'800px', '500px', $('#mdsBomTable'));
	   </shiro:hasPermission>
	  <shiro:lacksPermission name="mdsbom:mdsBom:edit">
	  jp.openDialogView('查看BOM', "${ctx}/mdsbom/mdsBom/form?id=" + id,'800px', '500px', $('#mdsBomTable'));
	  </shiro:lacksPermission>
  }

</script>