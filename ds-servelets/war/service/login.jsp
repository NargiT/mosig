
<div id="login">
	<!-- Display if not logged -->
	
	<% if (request.getSession().getAttribute("loggedin") == null) {%>
	
		<form action="controller" method="post">
		<section> <label for="nickname">Nickname : </label> <input
			id="nickname" name="nickname" type="text" maxlength="64" size="20" /> </section>
		<section> <label for="password">Password:</label> <input
			id="password" name="password" type="password" maxlength="64" size="20" /> </section>
		<!-- 		<a href="subscribe">Subscribe</a> -->
		<input type="submit" name="action" value="Login" />
		</form>
		
	<%} else {%>
	
	<!--  Display if logged -->
	<form action="controller" method="post">
		<section>Hello <%out.println((String)request.getSession().getAttribute("nickname"));%></section>
		<input type="submit" name="action" value="Logout" />
	</form>
	<form action="controller?action=ViewCart" method="post">
		<input type="image" src="img/cart.gif" width="32px" height="32px"/>
		
		</div>
	</form>
	<% } %>

</div>
