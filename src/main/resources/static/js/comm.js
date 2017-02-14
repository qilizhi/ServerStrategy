var comm = {
	initDel : function() {
		var $this = this;
		$('body').find('.list-del').on('click', function(e) {
			// console.log(e);
			var url = $(this).attr("href");
			e.preventDefault();
			$this.confirm(operation.tip, operation.deletMsgConfirm, function() {
				// console.log("删除"+url);
				$.post(url, function(result) {
					// console.log(result);
					if (result.code == "success") {
						// $this.alert("提示","删除操作成功");
						setTimeout(function() {
							window.location.href = window.location.href;
						}, 100);
					} else if (result.code == "fail") {
						$this.alert(operation.tip, operation.failMsg+result.msg);
					}
				});
			});

		});

	},
	initAdd : function() {
		// var $this = this;
		$('body').find('.list-add').on('click', function(e) {
			var url = $(this).attr("href");
			window.location.href=url;
		});

	},
	initEdit:function(){
		//alert("edit");
		$('body').find('.list-edit').on('click', function(e) {
			var url = $(this).attr("href");
			window.location.href=url;
		});
	},
	initAllCheck:function(){
		//console.log($("body").find("thead tr th input"));
		$("body").find("thead tr th input").on("click",function(e){
			if(e.currentTarget['checked']){
				$("body").find("tbody tr td input").each(function(index,object){
					$(object)['0'].checked=true;
				});
			}else{
				$("body").find("tbody tr td input").each(function(index,object){
					$(object)['0'].checked=false;
				
				});
			}
		});
	}
	,
	initSelect:function(){
			var selects=$(".selectInit");
			selects.each(function(index,data){
				//console.log(data);
		     var select=$(data);
			var url=select.attr("action");
			var value=select.attr("value");
			var options="<option>"+operation.pleaseSelect+"</option>";
			if(url){
			$.get(url,function(result){
				if(result.code=="success"){
					select.empty();
					var selected="' selected='selected";
				$.each(result.data,function(index,obj){
				//	console.log(obj);
				//	options=options+"<option id="+obj.id +" value='"+obj.name+"'  > "+obj.name+"</option>";
					if(obj.name==value){
						options=options+"<option id="+obj.id +" code='"+obj.code+"' value='"+obj.name+selected+"'  > "+obj.name+"</option>";

					}else{
						
						options=options+"<option id="+obj.id +" code='"+obj.code+"' value='"+obj.name+"'  > "+obj.name+"</option>";
					}
				});
				select.append(options);
				select.trigger("chosen:updated");
				}
			});
			}
			});
	},
	initServer:function(){
		var selects=$(".selectServer");
		selects.each(function(index,data){
			//console.log(data);
	     var select=$(data);
		var url=select.attr("action");
		var value=select.attr("value");
		var options=" ";
/*		var options="<option>"+operation.pleaseSelect+"</option>";
*/		if(url){
		$.get(url,function(result){
			if(result.code=="success"){
				select.empty();
			var selected="' selected='selected";
			$.each(result.data,function(index,obj){
			//	console.log(obj);
			//	options=options+"<option id="+obj.id +" value='"+obj.name+"'  > "+obj.name+"</option>";
				if(obj.name==value){
					options=options+"<option id="+obj.id +" code='"+obj.code+"' value='"+obj.name+selected+"'  > "+obj.name+"</option>";

				}else{
					
					options=options+"<option id="+obj.id +" code='"+obj.code+"' value='"+obj.name+"'  > "+obj.name+"</option>";
				}
			});
			select.append(options);
			select.trigger("chosen:updated");
			}
		});
		}
		});
},
	confirm : function(title, content, func) {
		var $modal = $("#basic-modals");
		// console.log($modal);
		func = func || function(e) {
			$modal.modal("close");
		};
		$modal.modal("open");
		$modal.find(".am-modal-hd").html(title ||operation.tip);
		$modal.find(".am-modal-bd").html(content || operation.isContinue);
		$modal.find(".close").off("click").on("click", function(e) {
			$modal.modal("close");
			func();
		});
	},
	alert : function(title, content) {
		// alert("conetent");
		var $modal = $("#base-alert");
		$modal.modal("open");
		$modal.find(".am-modal-hd").html(title ||operation.tip);
		$modal.find(".am-modal-bd").html(content);
		$modal.find(".am-modal-btn").off("click").on("click", function(e) {
			$modal.modal("close");
		});
	},
	showMsg : function(content) {
		// alert("conetent");
		var $modal = $("#base-alert");
		$modal.modal("open");
		$modal.find(".am-modal-hd").html(operation.tip);
		$modal.find(".am-modal-bd").html(content);
		$modal.find(".am-modal-btn").off("click").on("click", function(e) {
			$modal.modal("close");
		});
	},
	del:function(url,id){
		var $this = this;
		$this.confirm(operation.tip, operation.deletMsgConfirm, function() {
			// console.log("删除"+url);
			$.post(url,{id:id}, function(result) {
				// console.log(result);
				if (result.code == "success") {
					// $this.alert("提示","删除操作成功");
					setTimeout(function() {
						window.location.href = window.location.href;
					}, 100);
				} else if (result.code == "fail") {
					$this.alert(operation.tip,operation.failMsg+result.msg);
				}
			});
		});
		
	},
	initChosenSelect:function(){
		 $('.chosen-select').chosen({
			  disable_search_threshold: 10,
			  no_results_text: '没有选项/ops,nothing found!',
			  allow_single_deselect:true,
			  width: '95%'
			});
	},
	/*initSelect2:function(){
		$('.select2').select2(
		{
			placeholder: "Select a state"
		}		
		);
	},*/
	 countryInit:function(){
		 var $this=this;
		var select=$(".selectCountry");
		var url=select.attr("action");
		var value=select.attr("value");
		var options="<option value=''>"+operation.pleaseSelect+"</option>";
		if(url){
		$.get(url,function(result){
			if(result.code=="success"){
				select.empty();
				var selected="' selected='selected";
			$.each(result.data,function(index,obj){
			//	console.log(obj);
				if(obj.name==value){
					options=options+"<option id="+obj.id +" code='"+obj.code+"' value='"+obj.name+selected+"'  > "+obj.name+"</option>";

				}else{
					
					options=options+"<option id="+obj.id +" code='"+obj.code+"' value='"+obj.name+"'  > "+obj.name+"</option>";
				}
			});
			select.append(options);
			select.trigger("chosen:updated");
			}
		});
		
		}
		//绑定省事件
		select.on("change", function(e, params) {
			if(params.selected){
			  $this.regionInit(params.selected.id);
			  $this.ispInit(params.selected.id);
			}
			});
		select.on("open:opening",function(e){
			 alert("dd");
			 console.log("ddd");
		 });
		
	},
	
	 regionInit:function(countryId){
		 var $this=this;
		var select=$(".selectRegion");
		var url=select.attr("action");
		var value=select.attr("value");
		var options="<option value='' >"+operation.pleaseSelect+"</option>";
		if(url){
		url+="?countryId="+countryId;
			$.get(url,function(result){
			if(result.code=="success"){
				select.empty();
				var selected="' selected='selected";
			$.each(result.data,function(index,obj){
			//	console.log(obj);
				if(obj.name==value){
					options=options+"<option id="+obj.id +" code='"+obj.code+"' value='"+obj.name+selected+"'  > "+obj.name+"</option>";

				}else{
					
					options=options+"<option id="+obj.id +" code='"+obj.code+"' value='"+obj.name+"'  > "+obj.name+"</option>";
				}
				//options=options+"<option id="+obj.id +" value="+obj.name+"  > "+obj.name+"</option>";
			});
			select.append(options);
			select.trigger("chosen:updated");
			}
		});
		
		}
		//绑定省事件
		select.on("change", function(e, params) {
			$this.cityInit(params.selected.id);
			});	
	},
	cityInit:function (regionId){
		var select=$(".selectCity");
		var url=select.attr("action");
		var value=select.attr("value");
		var options="<option value=''>"+operation.pleaseSelect+"</option>";
		if(url){
		url+="?regionId="+regionId;
			$.get(url,function(result){
			if(result.code=="success"){
				var selected="' selected='selected";
				select.empty();
			$.each(result.data,function(index,obj){
			//	console.log(obj);
				if(obj.name==value){
					options=options+"<option id="+obj.id +" code='"+obj.code+"' value='"+obj.name+selected+"'  > "+obj.name+"</option>";

				}else{
					
					options=options+"<option id="+obj.id +" code='"+obj.code+"' value="+obj.name+"  > "+obj.name+"</option>";
				}
				//options=options+"<option id="+obj.id +" value="+obj.name+"  > "+obj.name+"</option>";
			});
			select.append(options);
			select.trigger("chosen:updated");
			}
		});
		
		}
	},
	ispInit:function(countryId){
		var select=$(".selectIsp");
		var url=select.attr("action");
		var value=select.attr("value");
		var options=" ";
/*		var options="<option value='' >"+operation.pleaseSelect+"</option>";
*/		if(url){
		url+="?countryId="+countryId;
			$.get(url,function(result){
			if(result.code=="success"){
				select.empty();
				var selected=" selected=selected";
			$.each(result.data,function(index,obj){
				//console.log(obj);
				if(obj.name==value){
					options=options+"<option id="+obj.id +" code='"+obj.code+"' value='"+obj.name+selected+"'  > "+obj.name+"</option>";

				}else{
					
					options=options+"<option id="+obj.id +" code='"+obj.code+"' value='"+obj.name+"'  > "+obj.name+"</option>";
				}
				//options=options+"<option id="+obj.id +" value="+obj.name+"  > "+obj.name+"</option>";
			});
			select.append(options);
			select.trigger("chosen:updated");
			}
		});
		
		}
	},
	init : function() {
		// this.initTableDetail();
		this.initSelect();
		this.initDel();
		this.initAdd();
		this.initEdit();
		this.initAllCheck();
		this.countryInit();
		this.initChosenSelect();
		this.initServer();
		//this.initSelect2();
		// this.initSort();
		// this.initAjaxForm();
	}

};
comm.init();
// 设置jQuery Ajax全局的参数
$.ajaxSetup({
	beforeSend : function(xhr) {
		//var header = $("meta[name='_csrf_header']").attr("content");
		//var token = $("meta[name='_csrf']").attr("content");
		//xhr.setRequestHeader(header, token);
	},
	error : function(jqXHR, textStatus, errorThrown) {
		// console.log(textStatus);
		// console.log(jqXHR);
		switch (jqXHR.status) {
		case (500):
			comm.showMsg(operation.serverErr);
			break;
		case (401):
			comm.showMsg(operation.noLogin);
			break;
		case (302):
			// alert("无权限执行此操作");
			comm.showMsg(operation.hasNoAuthority);
		window.location.reload; 
		  break;
		case (403):
			// alert("无权限执行此操作");
			comm.showMsg(operation.hasNoAuthority);
			break;
		case (408):
			comm.showMsg(operation.Timeout);
			break;
		case (404):
		
			comm.showMsg(operation.pathInvalid+jqXHR.responseJSON.path);
			break;
		default:
			comm.showMsg(operation.unknowErr);
		}
	}
});
