<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form method="post"
			action="http://localhost:8080/ShopDemo/RegisterHandler">
			<div style="text-align: center" id=usernamediv>
				用户名:<input type="text" name="username" id="username"
					onBlur="testIfUserNameIsUsed();" />
			</div>
			<div id="testIfUsed" style="text-align: center"></div>
			<div style="text-align: center">
				密码 :<input type="password" name="password" id="password" />
			</div>

			<div style="text-align: center">
				手机 :<input type="text" name="phone" id="phone" />
			</div>

			<div style="text-align: center">
				收货地址 :<input type="text" name="address" id="address" />
			</div>

			<div style="text-align: center">
				<input type="submit" value="Register" />
			</div>

		</form>
	</div>
</body>

<script type="text/javascript">
	console.log("hello world");
	var nameTag = document.getElementById("username");
	console.log(nameTag);

	//nameTag.onBlur=
	function testIfUserNameIsUsed() {

		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET",
				"http://localhost:8080/ShopDemo/RegisterHandler?username="
						+ nameTag.value, true);

		console.log(nameTag.value);
		xmlhttp.send(null);
		xmlhttp.onreadystatechange = function() {
			console.log("xmlhttp.readyState:" + xmlhttp.readyState);
			console.log("xmlhttp.status:" + xmlhttp.status);
			if (xmlhttp.readyState == 4) {
				if (xmlhttp.status == 200) {
					console.log(xmlhttp.responseText);
					var testIfUsed = document.getElementById("testIfUsed");
					if (xmlhttp.responseText == "true") {
						console.log("used already");
						testIfUsed.innerHTML = "已经被占用";
					} else
						testIfUsed.innerHTML = "";

				} else
					console.log("error");
			}
		}

	}
</script></html>