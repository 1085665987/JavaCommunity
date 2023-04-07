function queryList(page){
	var request = new XMLHttpRequest();
	var url = "http://localhost:8080/china-java/login.do?page_count="+page;
	console.log(url);
	request.open("GET", url, true);
	request.onload=function () {
		var responseText=JSON.parse(this.responseText);
		console.log(responseText.msg);
	}
	request.send();	
}