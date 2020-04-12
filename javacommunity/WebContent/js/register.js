var account = document.getElementById("account");
var password = document.getElementById("password");
var rePassword = document.getElementById("rePassword");
var identifying_code = document.getElementById("identifying_code");

var submit = document.getElementById("submit");
submit.onclick = function() {
	if (account.value.length < 10) {
		alert("手机号出错");
		return;
	}
	if (password.value.length < 6) {
		alert("密码不得少于6位");
		return;
	}
	if (rePassword.value.length == 0) {
		alert("用户名不能为空");
		return;
	}
	if (rePassword.value != password.value) {
		alert("两次密码不一致");
		return;
	}
	var request = new XMLHttpRequest();
	var url = "http://localhost:8080/china-java/register.do?account="+ account.value + "&password=" + password.value;
	console.log(url);
	request.open("GET", url, true);
 
	request.onload=function () {
        console.log("success");
    }
	request.send(); 
}
