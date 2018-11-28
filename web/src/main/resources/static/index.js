/**
 * 登录处理
 * 
 */
//$(function() {
//	$('#loginForm').validate();
//	$('#loginForm').ajaxForm(function(data) {
//		var rst = JSON.parse(data);
//		if (!rst.success) {
//			$("#infoMsg").html(rst.message).show();
//		}
//	});
//});

$(document).ready(function() {

	var options = {
		// target : '#output1', // target element(s) to be updated with server
		// response
		// beforeSubmit : showRequest, // pre-submit callback
		beforeSerialize : encryptPassword,
		success : showResponse
	// post-submit callback
	};

	$('#loginForm').ajaxForm(options);

});

function showRequest(formData, jqForm, options) {
	var queryString = $.param(formData);
	return true;
}

function showResponse(responseText, statusText, xhr, $form) {
	var rst = JSON.parse(responseText);
	if (!rst.success) {
		swal({
			title : "友情提示",
			text : rst.message,
			type : "error",
			showCancelButton : false,
			confirmButtonColor : "#18a689",
		},
		function(){
			$('#loginForm').clearForm();
		});
		
		
	}else{
		window.location.href= window.location.href + "home";
	}
}

function encryptPassword(form, options) {
	var jform = form[0];
	var password = jform.password.value;
	var encrytedPassword = sha1(password);
	$("#password").val(encrytedPassword);
	return true;
}

//$(document).ready(function() {
//	$(".demo1").click(function() {
//		swal({
//			title : "欢迎使用SweetAlert",
//			text : "Sweet Alert 是一个替代传统的 JavaScript Alert 的漂亮提示效果。"
//		})
//	});
//	$(".demo2").click(function() {
//		swal({
//			title : "太帅了",
//			text : "小手一抖就打开了一个框",
//			type : "success"
//		})
//	});
//	$(".demo3").click(function() {
//		swal({
//			title : "您确定要删除这条信息吗",
//			text : "删除后将无法恢复，请谨慎操作！",
//			type : "warning",
//			showCancelButton : true,
//			confirmButtonColor : "#DD6B55",
//			confirmButtonText : "删除",
//			closeOnConfirm : false
//		}, function() {
//			swal("删除成功！", "您已经永久删除了这条信息。", "success")
//		})
//	});
//	$(".demo4").click(function() {
//		swal({
//			title : "您确定要删除这条信息吗",
//			text : "删除后将无法恢复，请谨慎操作！",
//			type : "warning",
//			showCancelButton : true,
//			confirmButtonColor : "#DD6B55",
//			confirmButtonText : "是的，我要删除！",
//			cancelButtonText : "让我再考虑一下…",
//			closeOnConfirm : false,
//			closeOnCancel : false
//		}, function(isConfirm) {
//			if (isConfirm) {
//				swal("删除成功！", "您已经永久删除了这条信息。", "success")
//			} else {
//				swal("已取消", "您取消了删除操作！", "error")
//			}
//		})
//	})
//});