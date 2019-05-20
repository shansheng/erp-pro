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
				'plugins': [ 'types', 'wholerow', "search"],
		
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