function reflashTable(searchmap) {
	$("#dataSourceTable tbody").empty();
	//$("#JobListDiv").empty();
	//$("#page").empty();
	$("#table-loader").css("display","block"); 
	var searchUrl =$("#searchUrl").val();
	$.ajax({
		url : ace.vars['base']+"/SearchData/"+searchUrl,
		type : "POST",
		data : JSON.stringify(searchmap),
		dataType : "html",
		contentType : "application/json",
		success : function(data) {
			$("#table-loader").css("display","none");
			$("#dataSourceTable tbody").html(data);	
			/*$("#JobListDiv").html(data);*/	
		},
		error : function(msg) {
			 alert("显示列表失败！"+msg);
		}
	});
	
}




function refreshPagination(searchmap) {
	//alert($("#searchSql").val());
	var searchPagination =$("#searchPagination").val();
	$.ajax({
		url : ace.vars['base']+"/SearchData/"+searchPagination,
		type : "POST",
		data : JSON.stringify(searchmap),
		dataType : "html",
		contentType : "application/json",
		success : function(data) {
			//alert(data);
			var carNum=document.getElementById("page"); 
			carNum.innerHTML=data;			
		},
		error : function(msg) {
			 alert("分页失败！"+msg);
		}
	});
	
}

function tablePageTurn(searchmap) {
	reflashTable(searchmap);
	refreshPagination(searchmap);	
}

//$(function() {
//	
//$("#dataSourceTable th")
//.click(
//		function(event) {
//			if ($("#dataSourceTable tbody").html() =="") {
//				return ;
//			}
//			var colName = $(this).attr("id");
//			if (colName == null || colName =="") {
//				return ;
//			}
//			var sortSlq = $("#baseSql").val();
//			if(sortSlq == null || sortSlq ==""){
//				return ;
//			}
//
//			var pagePeNumber = $("#pagePerNumber").val();
//			$("#dataSourceTable th").each(function(index) {
//				if ($(this).attr("id") != colName) {
////					$(this).children("i.sort").remove();
//					$(this).children("i.sort").attr("class",
//					"sort ace-icon fa fa-sort pull-right");
//				}
//			});
//
//			var currentClass = null;
//			if ($(this).children("i.sort") != null) {
//				currentClass = $(this).children("i.sort").attr("class");
//			}
//			if (currentClass.indexOf("fa-sort-desc") != -1) {
//				$(this).children("i.sort").attr("class",
//						"sort ace-icon fa fa-sort-asc pull-right");
//				// 升序
//				// sortTable(colName,"asc",pagePeNumber,1);
//				// ace.vars['sortCol'] = colName;
//				sortSql(colName, "asc");
//			} else if (currentClass.indexOf("fa-sort-asc") != -1) {
////				$(this).children("i.sort").remove();
//				$(this).children("i.sort").attr("class",
//				"sort ace-icon fa fa-sort pull-right");
//				// 无序
//				// sortTable(colName,"",pagePeNumber,1);
//				sortSql(colName, "");
//			}else if (currentClass.indexOf("fa-sort") != -1) {
////				$(this)
////				.append(
////						"<i class='sort ace-icon fa fa-sort-desc pull-right'></i>");
//				$(this).children("i.sort").attr("class",
//				"sort ace-icon fa fa-sort-desc pull-right");
//				// 降序
//				// sortTable(colName,"desc",pagePeNumber,1);
//				// ace.vars['sortType'] = "desc";
//				sortSql(colName, "desc");
//			}
//
//			pageTurn($("#pagePerNumber").val(), 1);
//		});
//});
//
//function sortSql(colName, type) {
//	var sortSlq = $("#baseSql").val();
//	if (type != "") {
//		$("#searchSql").val(sortSlq + " order by " + colName + " " + type);
//	} else {
//		$("#searchSql").val(sortSlq);
//	}
//
//}