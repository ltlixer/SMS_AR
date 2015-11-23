function onSelectChange(){
	var pagePerNumber =$("#pagePerNumber").val();
	var pageNum =1;
	var searchmap={
			pagePerNumber : pagePerNumber,
			pageNum : pageNum
	};
	reflashTable(searchmap);
	refreshPagination(searchmap);
}

function pageTurn(pagePerNumber,pageNum){
	var searchmap={
			pagePerNumber : pagePerNumber,
			pageNum : pageNum
				
	};
	reflashTable(searchmap);
	refreshPagination(searchmap);
}
