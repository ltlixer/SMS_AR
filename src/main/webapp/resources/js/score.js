$(document).ready(function() {
	
	$("#SUB").click(function() {
		var labl = $("#labl").text();
		var articleTitle = $("#articleId").text();
		var inputs = document.getElementsByTagName("input");
		var number = 0;
		var flag = 0;
		for(var i=0;i<inputs.length;i++){
			if(inputs[i].checked){
				number++;
			}
		}
		if(number==labl){
	
		for ( var j = 0; j < inputs.length; j++) {
			if (inputs[j].checked) {
				var val = inputs[j].value;
				var  word = inputs[j].name;
				
				$.ajax({
					type : "POST",
					url : "scoreSubmit",
					data : JSON.stringify({
						"articleId":articleTitle,
						"word":word,
						"scoreVal":val
					}),
					dataType : "json",
					contentType : "application/json",
					success : function(data) {
						flag++;
						if(flag==number){
							 alert("提交完成");
							 location=location;
						}
											
					},
					error : function(msg) {
						 alert("数据错误！"+msg);
					}
					
				});
			}
		}
		
		}else{
			alert("还有未选项！");
			return;
		}
	});
});
function scoreObj(newWord,word,value){
	this.newWord = newWord;
	this.word = word;
	this.value = value;
}