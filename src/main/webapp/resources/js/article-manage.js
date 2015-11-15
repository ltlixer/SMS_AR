function FirstClassSelect() {
	var code = $("#FirstClass option:selected").val();
	$.ajax({
		type : "POST",
		url : "secondClass",
		data : JSON.stringify({
			"code":code	
		}),
		dataType : "html",
		contentType : "application/json",
		success : function(data) {
			$("#select").empty();
			$("#select").html(data);		
		},
		error : function(msg) {
			 alert("数据错误！"+msg);
		}
		
	});
}
$(document).ready(function() {
	$("#articleImport").click(function() {
		$("#upload")
		.submit();
	});
});