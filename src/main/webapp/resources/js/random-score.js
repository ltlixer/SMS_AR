$(document).ready(function() {
	var startTime=new Date();
	$("#SUB").click(function() {
		var endTime=new Date();
		
		var labl = $("#labl").text();
		var articleTitle = $("#articleId").text();
		var inputs = document.getElementsByTagName("input");
		var adios = document.getElementsByClassName("adio");
		var number = 0;
		var flag = 0;
		var che=0;
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
					url : "randomScoreSubmit",
					data : JSON.stringify({
						"articleId":articleTitle,
						"word":word,
						"scoreVal":val,
						"startTime":startTime.getTime(),
						"endTime":endTime.getTime()
					}),
					dataType : "json",
					contentType : "application/json",
					success : function(data) {
						flag++;
						if(flag==number){
							 alert(data.msg);
							 location=location;
						}
											
					},
					error : function() {
						 alert("连接服务器失败！");
					}
					
				});
			}
		}
		
		}else{					
			alert("还有未选项！");	
			
			for(var m=0;m<adios.length/5;m++){
				for(var n=1;n<6;n++){
					if(adios[m*5+n-1].checked){
						break;
					}
					else if(adios[m*5+n].checked){
						break;
					}
					else if(adios[m*5+n+1].checked){
						break;
					}
					else if(adios[m*5+n+2].checked){
						break;
					}
					else if(adios[m*5+n+3].checked){
						break;
					}					
					else{		
							adios[m*5].focus();
							return;												
					}
				}
			}
												
			return;		
		}
	});
});
function scoreObj(newWord,word,value){
	this.newWord = newWord;
	this.word = word;
	this.value = value;
}
