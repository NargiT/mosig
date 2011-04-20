<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="/style/default.css" />
<title>Buy-a-Pokemon.com</title>
</head>

<body>


	<div id="header">
		Headder
		<div id="login">
			<form action="login">
				<label for="nickname">Nickname : </label><input id="nickname"
					type="text" />
				<label for="password">Password : </label><input
					id="password" type="password" />
				<input type="submit">
			</form>
		</div>
	</div>

	<div id="menu">Menu</div>
	<div id="body">Body</div>
	<%@ include file="footer.jsp" %>
</body>
</html>
