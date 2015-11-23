$(function() {
	var remember = $.cookie('remember');
	if (remember == 'true') {
		var username = $.cookie('username');
		var password = $.cookie('password');
		// autofill the fields
		$('#username').attr("value", username);
		$('#password').attr("value", password);
		$("#rememberMe").attr("checked", true);
	} else {
		$("#rememberMe").attr("checked", false);
	}

	// 监听【记住我】事件
	$("#rememberMe").click(function() {
		if ($('#rememberMe:checked').length > 0) {// 设置cookie
			$.cookie('username', $('#username').val(), {
				expires : 7
			});
			$.cookie('password', $('#password').val(), {
				expires : 7
			});
			$.cookie('remember', true, {
				expires : 7
			});
		} else {// 清除cookie
			$.cookie('username', null);
			$.cookie('password', null);
			$.cookie('remember', null);
		}
	});
});
$(document).ready(function() {
	//綁定button
	$("#onlogin").on("click", function(){
				var username = $.trim($("#username").val());
				var password = $("#password").val();
				if (username == "" && password == "") {
					alert("请填写用户名和密码");
					return false;
				} else if (username == "") {
					alert("请填写用户名");
					return false;
				} else if (password == "") {
					alert("请填写密码");
					return false;
				} else {
				}
				$.ajax({
					type : "POST",
					url : "loginIn",
					dataType : "json",
					contentType : "application/json",
					data : JSON.stringify({
						"username" : username,
						"password" : password				
					}),
					
					success : function(data) {
						if (data.success) {
							location.href = "/SMS_AR/homePage/chart-analysis.html";
						} else {
							alert(data.message);
						}
					},
					error : function() {
						alert("error");
					}
				});
			});		
		}
	);
$(document).keydown( function(e){
	if(e.keyCode == 13){
		$('#onlogin').trigger("click");
	}
});