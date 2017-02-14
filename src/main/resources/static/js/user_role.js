
/** **权限树的处理************* */
var UITree = function() {

	
	// 跟据authorityId roleId 删除,

	var insertUser_Role = function(userNo, roleIds) {
		var url ='/role/insertByUserNoAndRoleIds';
		$.ajax({
			url : url,
			type : 'post',
			dataType:"json",
			data : {
				'roleIds' : roleIds,
				'userId' : userNo
			},
			success : function(result) {

				if (result.code == "success") {
					//comm.showMsg('success', '提示', '选择成功！');
				} else {
					//comm.showMsg('warning', '提示', '选择失败!');
					// 刷新树
					loadRoleTreeByUserNo(userNo);
				}

			},
			error : function(e) {
				// 取消选择
				comm.showMsg('warning', '提示', '请求失败!');
				// //刷新树
				//$.jstree.reference('#tree_2').uncheck_node(authorityIds);
				loadRoleTreeByUserNo(userNo);
			}

		});

	};

	// 跟据authorityId roleId 添加,

	var deleteUser_Role = function(userNo,roleIds) {
		var url = '/role/deleteByUserNoAndRoleIds';
		$.ajax({
			url : url,
			type : 'post',
			dataType:"json",
			data : {
				'roleIds' : roleIds,
				'userId' : userNo
			},
			success : function(result) {
				if (result.code == "success") {
					//comm.showMsg('success', '提示', '取消成功!');
				} else {
					// //刷新树
					//comm.showMsg('warning', '提示', '取消失败!');
					//$.jstree.reference('#tree_2').check_node(authorityIds);
					loadRoleTreeByUserNo(userNo);
				}

			},
			error : function(e) {
				//comm.showMsg('warning', '提示', '请求失败!');
				// //刷新树
				//$.jstree.reference('#tree_2').check_node(authorityIds);
				loadRoleTreeByUserNo(userNo);
			}
		});
	};
	
	var bind_check_un = function() {
		// 绑定uncheck check 事件
		$('#tree_2').on(
				'check_node.jstree',
				function(e, data) {
					// 给隐藏域set値
					// $("#authorityId").val(data.node.id);
					var roleIds = "";
					if (data.node.children_d.length > 0) {
						roleIds += data.node.id + ","
								+ data.node.children_d.toString();
					} else {

						roleIds = data.node.id;
					}
					//console.log(roleIds);
					insertUser_Role(_userNo,roleIds);

				});
		$('#tree_2').on(
				'uncheck_node.jstree',
				function(e, data) {
					// 给隐藏域set値
					// $("#authorityId").val(data.node.id);
					var roleIds = "";
					if (data.node.children_d.length > 0) {
						roleIds += data.node.id + ","
								+ data.node.children_d.toString();
					} else {

						roleIds = data.node.id;
					}
					//console.log(roleIds);
					deleteUser_Role(_userNo, roleIds);

				});

	};

	
	// 根据RoleId加载权限树
	var loadRoleTreeByUserNo = function(userNo) {
		var roleUrl = "/role/roleTreeByUserId";
		_userNo=userNo;		
		$.ajax({
			url : roleUrl,
			type : 'get',
			dataType:"json",
			data : {
				'userId' : userNo
			},
			success : function(result) {
				var roleData = result.data;
				// $('#tree_2').removeData();
				$('#tree_2').jstree('destroy');
				//console.log(roleData);
				// $(document).off('.jstree');
				$('#tree_2').jstree({
					'plugins' : [ "checkbox", "types" ],
					'core' : {
						"themes" : {
							"responsive" : false
						},
						'data' : roleData
					},
					"types" : {
						"default" : {
							"icon" : "fa fa-folder icon-state-warning icon-lg"
						},
						"file" : {
							"icon" : "fa fa-file icon-state-warning icon-lg"
						}
					},
					"checkbox" : {
						whole_node : false,
						tie_selection : false
					}
				});

				// 绑定check and uncheck事件
				bind_check_un();

			}

		});

	};
	

	// 根据RoleId加载权限树
	/*var loadAuthorityTreeByRoleId = function(roleId) {
		var authorityUrl ="/role/roleTreeByUserId";
		$.ajax({
			url : authorityUrl,
			type : 'get',
			dataType:"json",
			data : {
				'userId' : roleId
			},
			success : function(result) {
				var authorityData = result.data;
				// $('#tree_2').removeData();
				$('#tree_2').jstree('destroy');
				// $(document).off('.jstree');
				//console.log(authorityData);
				$('#tree_2').jstree({
					'plugins' : [ "checkbox", "types" ],
					'core' : {
						"themes" : {
							"responsive" : false
						},
						'data' : authorityData
					},
					"types" : {
						"default" : {
							"icon" : "fa fa-folder icon-state-warning icon-lg"
						},
						"file" : {
							"icon" : "fa fa-file icon-state-warning icon-lg"
						}
					},
					"checkbox" : {
						whole_node : false,
						tie_selection : false
					}
				});

				// 绑定check and uncheck事件
				bind_check_un();

			}

		});

	};
*/
	// 跟据authorityId roleId 删除,
/*
	var insertRole_Authority = function(roleId, authorityIds) {
		var url ='/role/insertByUserIdAndroleIds';
		$.ajax({
			url : url,
			type : 'post',
			dataType:"json",
			data : {
				'useId' : roleId,
				'roleIds' : authorityIds
			},
			success : function(result) {

				if (result.code == "success") {
					//comm.showMsg('success', '提示', '选择成功！');
				} else {
					comm.showMsg('warning', '提示', '选择失败!');
					// 刷新树
					loadAuthorityTreeByRoleId(roleId);
				}

			},
			error : function(e) {
				// 取消选择
				comm.showMsg('warning', '提示', '请求失败!');
				// //刷新树
				//$.jstree.reference('#tree_2').uncheck_node(authorityIds);
				loadAuthorityTreeByRoleId(roleId);
			}

		});

	};*/

	// 跟据authorityId roleId 添加,

	/*var deleteRole_Authority = function(roleId, authorityIds) {
		var url ='/role/deleteByRoleIdAndAuthIds';
		$.ajax({
			url : url,
			type : 'post',
			dataType:"json",
			data : {
				'roleId' : roleId,
				'authorityIds' : authorityIds
			},
			success : function(result) {
				if (result.code == "success") {
					//comm.showMsg('success', '提示', '取消成功!');
				} else {
					// //刷新树
					comm.showMsg('warning', '提示', '取消失败!');
					//$.jstree.reference('#tree_2').check_node(authorityIds);
					loadAuthorityTreeByRoleId(roleId);
				}

			},
			error : function(e) {
				comm.showMsg('warning', '提示', '请求失败!');
				// //刷新树
				//$.jstree.reference('#tree_2').check_node(authorityIds);
				loadAuthorityTreeByRoleId(roleId);
			}
		});
	};
*/
	// 绑定点击事件
/*	var click = function() {
		$('#tree_3').on('activate_node.jstree', function(e, data) {
			 给隐藏域set値 
			// $("#authorityId").val(data.node.id);
			loadAuthorityTreeByRoleId(data.node.id);
		});
	};

	var bind_check_un = function() {
		// 绑定uncheck check 事件
		$('#tree_2').on(
				'check_node.jstree',
				function(e, data) {
					// 给隐藏域set値
					// $("#authorityId").val(data.node.id);
					var authorityIds = "";
					if (data.node.children_d.length > 0) {
						authorityIds += data.node.id + ","
								+ data.node.children_d.toString();
					} else {

						authorityIds = data.node.id;
					}
				//	var roleId = UImultiSelect.getSelectedRoleId();
					var userId=$("input[name='id']").val();
					insertRole_Authority(userId, authorityIds);

				});
		$('#tree_2').on(
				'uncheck_node.jstree',
				function(e, data) {
					// 给隐藏域set値
					// $("#authorityId").val(data.node.id);
					var authorityIds = "";
					if (data.node.children_d.length > 0) {
						authorityIds += data.node.id + ","
								+ data.node.children_d.toString();
					} else {

						authorityIds = data.node.id;
					}
				//	var roleId = UImultiSelect.getSelectedRoleId();
					deleteRole_Authority(roleId, authorityIds);

				});

	};*/

	return {
		// main function to initiate the module
		init : function(url) {
			contextualMenuSample(url);
			$("#add").on("click",function(e){
				addRoleShow();
			});

		},
		loadRoleTreeByUserId:function(userId){
			loadRoleTreeByUserNo(userId);
		}
		,
		clicked : function() {
			click();
		}
	};

}();

/** **权限树的处理*** end********** */


/*jQuery(document).ready(function() {
	var renameUrl ="/role/update";
	var createUrl ="/role/create";
	var deleteUrl ="/role/delete";
	var moveUrl ="/role/update";
	var copyUrl ="/role/copy";
	var treeUrl ="/role/roleTree";

	// 绑定按钮
	UITree.copyNode(copyUrl);
	UITree.moveNode(moveUrl);
	UITree.reNameNode(renameUrl);
	UITree.createNode(createUrl);
	UITree.deleteNode(deleteUrl);
	UITree.init(treeUrl);
	UITree.clicked();

	
	 * //选中树 var nodeId=$("#authorityId").val(); console.log(nodeId);
	 * $.jstree.reference('#tree_3').select_node(nodeId);
	 

});*/
