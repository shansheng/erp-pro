<%@ page contentType="text/html;charset=UTF-8" %>
	<script>
		$(document).ready(function() {
			var to = false;
			$('#search_q').keyup(function () {
				if(to) { clearTimeout(to); }
				to = setTimeout(function () {
					var v = $('#search_q').val();
					$('#matBomDetailjsTree').jstree(true).search(v);
				}, 250);
			});
			$('#matBomDetailjsTree').jstree({
				'core' : {
					"multiple" : false,
					"animation" : 0,
					"themes" : { "variant" : "large", "icons":true , "stripes":true},
					'data' : {
						"url" : "${ctx}/matbom/matBomDetail/treeData?matBomId="+$("#matBomId").val(),
						"dataType" : "json" 
					}
				},
				"conditionalselect" : function (node, event) {
					return false;
				},
				'plugins': ["contextmenu", 'types', 'wholerow', "search"],
				"contextmenu": {
					"items": function (node) {
						var tmp = $.jstree.defaults.contextmenu.items();
						delete tmp.create.action;
						delete tmp.rename.action;
						tmp.rename = null;
						tmp.create = {
							"label": "添加下级Bom",
							"action": function (data) {
								var inst = jQuery.jstree.reference(data.reference),
									obj = inst.get_node(data.reference);
								
								jp.openDialog('添加下级Bom', '${ctx}/matbom/matBomDetail/form?parent.id=' + obj.id + "&parent.name=" + obj.text, '800px', '500px', $('#matBomDetailjsTree'));
							}
						};
						tmp.remove = {
							"label": "删除Bom",
							"action": function (data) {
								var inst = jQuery.jstree.reference(data.reference),
									obj = inst.get_node(data.reference);
								jp.confirm('确认要删除Bom吗？', function(){
									jp.loading();
									$.get("${ctx}/matbom/matBomDetail/delete?id="+obj.id, function(data){
										if(data.success){
											$('#matBomDetailjsTree').jstree("refresh");
											jp.success(data.msg);
										}else{
											jp.error(data.msg);
										}
									})

								});
							}
						}
						tmp.ccp = {
							"label": "编辑Bom",
							"action": function (data) {
								var inst = jQuery.jstree.reference(data.reference),
									obj = inst.get_node(data.reference);
								var parentId = inst.get_parent(data.reference);
								var parent = inst.get_node(parentId);
								$.get("${ctx}/matbom/matBomDetail/getDetail?id="+obj.id, function(data){
									
									if(data.success){
										 jp.openDialog('编辑Bom', '${ctx}/matbom/matBomDetail/form?id=' + obj.id, '800px', '500px', $('#matBomDetailjsTree'));
									}else{
										jp.error(data.msg);
									}
								});
						
						}
						}
						return tmp;
					
					}
				},
				"types":{
					'default' : { 'icon' : 'fa fa-file-text-o' },
					'1' : {'icon' : 'fa fa-home'},
					'2' : {'icon' : 'fa fa-umbrella' },
					'3' : { 'icon' : 'fa fa-group'},
					'4' : { 'icon' : 'fa fa-file-text-o' }
				}

			}).bind("activate_node.jstree", function (obj, e) {
				var node = $('#matBomDetailjsTree').jstree(true).get_selected(true)[0];
				var opt = {
					silent: true,
					query:{
						'matBomDetailId.id':node.id
					}
				};
				$("#matBomDetailIdId").val(node.id);
				$("#matBomDetailIdName").val(node.text);
				$('#matBomTableTable').bootstrapTable('refresh',opt);
			}).on('loaded.jstree', function() {
				$("#matBomDetailjsTree").jstree('open_all');
			});
		});
	</script>