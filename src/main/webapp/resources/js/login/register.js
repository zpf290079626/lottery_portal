var code="";
	function createCode() {
		code = "";
		var codeLength = 6; //验证码的长度
		var checkCode = document.getElementById("checkCode");
		var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'a', 'b', 'c',
				'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
				'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A',
				'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
				'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，当然也可以用中文的
		for (var i = 0; i < codeLength; i++) {
			var charNum = Math.floor(Math.random() * 52);
			code += codeChars[charNum];
		}
		if (checkCode) {
			checkCode.className = "code";
			checkCode.innerHTML = code;
		}
	}
	function validateCode() {
		var inputCode = document.getElementById("inputCode").value;
		if (inputCode.length <= 0) {
			alert("请输入验证码！");
			return false;
		} else if (inputCode.toUpperCase() != code.toUpperCase()) {
			alert("验证码输入有误！");
			createCode();
			return false ;
		}
		
		return true;
	}

	function register() {

		if (!validateCode()) {
			return;
		}
		
		if (!validCheck()) {
			return;
		}
		if (!validCodeCheck()) {
			return;
		}

		var form = document.forms['registerForm'];
		//执行SUBMIT
		form.submit();
	}
	
	// 校验必输项目及格式
	function validCheck(){
		// 校验录入的数据
		var userId = $("input[name='userId']").val();
		if (userId == '') {
			alert("请输入用户ID");
			return false;
		}
		var password1 = $("input[name='password']").val();
		if (password1 == '') {
			alert("请输入密码");
			return false;
		}
		var password2 = $("input[name='password2']").val();
		if (password1 != password2) {
			alert("输入的密码不一致");
			return false;
		}
		var email = $("input[name='email']").val();
		if (email == '') {
			alert("请输入邮箱地址");
			return false;
		}
		var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		if (!reg.test(email)) {
			alert("请输入正确的邮箱地址");
			return false;
		}
		
		return true;
	}
	
	// 校验邮箱验证码是否已录入
	function validCodeCheck() {
		var email = $("input[name='inputValiCode']").val();
		if (email == '') {
			alert("请输入邮箱验证码");
			return false;
		}
		
		return true;
	}
	
	function obtainValiCode() {
		
		if (!validateCode()) {
			return;
		}
		
		if (!validCheck()) {
			return;
		}
		
		$.ajax({
			type: "POST",
			url: "/loginController/obtainValiCode",
			data: "email=" + $("input[name='email']").val(),
			success: function(msg){
			//返回回来的值
			alert(msg);
			}
			});
	}