
function selectArticle(){
	var articleId = $("#articleTitle option:selected").val();
	if(articleId!=null && articleId!=""){
	$.ajax({
		type : "POST",
		url : "articleSelect",
		data : JSON.stringify({
			"articleId":articleId
		}),
		dataType : "html",
		contentType : "application/json",
		success : function(data) {
			 //alert("成功！"+msg);
				$("#article").empty();
				$("#article").html(data);					
		},
		error : function(msg) {
			 alert("数据错误！"+msg);
		}
		
	});
}
};
