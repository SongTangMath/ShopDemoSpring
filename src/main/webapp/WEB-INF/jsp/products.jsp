<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
<%@ page import="com.zkdx.database.*"%>
<%@ page import="java.text.DateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户的页面</title>
</head>
<body>
<div style="text-align: center">
	欢迎你, ${sessionScope.user} </div>
<body>
	<form method="POST"
		action="${pageContext.request.contextPath}/UserQueryHandler">

		<div style="text-align: center">
			<input type="text" name="productcategory" /> <input type="submit"
				value="按关键字搜索商品" />
		</div>
	</form>

	<form method="POST"
		action="${pageContext.request.contextPath}/UserQueryHandler">

		<div style="text-align: center">
			<select id="level0" name="level0" onchange="level0CategoryChanged();">
				<option>===请选择大类===</option>
			</select> <select id="level1" name="level1"
				onchange="level1CategoryChanged();">
				<option>===请选择第二层分类===</option>
			</select> <select id="level2" name="level2"
				onchange="level2CategoryChanged();">
				<option>===请选择第三层分类===</option>
			</select> <input type="submit" value="按照类别搜索商品" />

		</div>
	</form>
	<form method="POST"
		action="">

		<table border="1">
			<tr>
				<th>商品名称</th>
				<th>商品图片</th>
				<th>单价</th>
				<th>库存数量</th>
				<th>当前文案</th>
				<th>购买数量(若超过库存则认为全部购买)</th>
				<th>额外属性选择</th>

			</tr>
			</table></form>
			
			<br><br><br>
		<a href="${pageContext.request.contextPath}/Quit">退出登录并返回首页</a>
</body>
</html>