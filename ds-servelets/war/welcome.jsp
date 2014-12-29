<%@page import="pokemon.controller.Controller"%>
<%@	page import="java.util.Map.Entry"%>
<%
	int width = 200;
	int height = 200;
%>
<%
	//for (Entry<String, Double> entry : Controller.pokemons.entrySet()) {
		for (int i = 0; i < Controller.pokemons.size(); i ++) {
%>
<form action="controller" method="get" class=pokemon>
	<figure> <img src="img/pokemons/<%=Controller.pokemons.get(i).getName()%>.jpg"
		alt="Articuno" width="<%=width%>px" height="<%=height%>px" /> <figcaption><%=Controller.pokemons.get(i).getName()%></figcaption>
	</figure>
	<p>
		<%=Controller.pokemons.get(i).getPrice()%>
		&euro;
	</p>
	<input type="hidden" name="pokemonname" value="<%=Controller.pokemons.get(i).getName()%>" /> 
	<input type="hidden" name="pokemonprice" value="<%=Controller.pokemons.get(i).getPrice()%>" />
	<input type="submit" name="action" value="AddToCart">
</form>
<%
	}
%>
