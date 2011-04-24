<div id="login">
	<!-- Display if not logged -->
	<form action="login" method="post">
		<section> <label for="nickname">Nickname : </label> <input
			id="nickname" type="text" maxlength="64" size="20" /> </section>
		<section> <label for="password">Password:</label> <input
			id="password" type="password" maxlength="64" size="20" /> </section>
		<!-- 		<a href="subscribe">Subscribe</a> -->
		<input type="submit" value="Login" />
	</form>

	<!--  Display if logged -->
	<form action="logout" method="post">
		<section>Hello {nickname}</section>
		<a href="basket"><img src="img/cart.gif" alt="basket" width="32px"
			height="32px" />
		</a> <input type="submit" value="Logout" />
	</form>

</div>
