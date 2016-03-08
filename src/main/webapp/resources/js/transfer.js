$(document).ready(function() {
	$("#transfer").on(ace.click_event,function() {
		var file = $("#id-input-file-2").val();
		if(file==null || file==""){
			alert("请选择要导入的Execl文件");
			return;
		}else {
			var file_Suffix =/\.[^\.]+/.exec(file);
			if(file_Suffix!=".xls"){
				alert("请选择后缀为.xls的文件！");
				return;
			}else{
				var pos1 = file.lastIndexOf("\\");
				var pos2 = file.lastIndexOf(".");
				var fileName = file.substring(pos1 + 1, pos2);
				if(fileName!="打分模型SMS_AR"){
					alert("文件名必须命名为：打分模型SMS_AR");
					return;
				} 
			}		
			
		}
		bootbox.confirm("提示：1.Execl字库必须命名为：“打分模型SMS_AR.xls” 2.Execl格式必须按照我们规定的标准格式",
			function(result) {
				if (result) {
					$("#upload").submit();
					//$( "#dataSourceTable tbody").empty(); 
					//$("#table-loader").css("display","block"); 
					//$("#table-loader").append("请耐心等待正在转换数据.......");
				}
		});
	});

	/*
	 * 清空word字库
	 */
	$("#emptyWordLibrary").on(ace.click_event, function() {
		bootbox.confirm("确定要清空字库吗?请慎重考虑！", function(result) {
			if (result) {
				// 确定
				var pagePerNumber = $("#pagePerNumber").val();
				var pageNum = 1;
				var searchmap = {
					pagePerNumber : pagePerNumber,
					pageNum : pageNum
				};
				$.ajax({
					type : "POST",
					url : "emptyWord",
					dataType : "json",
					contentType : "application/json",
					success : function(data) {
						if (data.success) {
							alert(data.successMsg);
							reflashTable(searchmap);
							refreshPagination(searchmap);
						} else {
							alert(data.errorMsg);
						}

					},
					error : function(msg) {
						alert("数据错误！" + msg);
					}

				});
			}
		});
	});

});