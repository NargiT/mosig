
<%
	int width = 200;
	int height = 200;
%>
<%
	for (Entry<String, Double> entry : pokemon.entrySet()) {
%>
<form action="controller" method="get" class=pokemon>
	<figure> <img src="img/pokemons/<%=entry.getKey()%>.jpg"
		alt="Articuno" width="<%=width%>px" height="<%=height%>px" /> <figcaption><%=entry.getKey()%></figcaption>
	</figure>
	<p>
		<%=entry.getValue()%>
		&euro;
	</p>
	<input type="hidden" name="pokemonname" value="<%=entry.getKey()%>" /> 
	<input type="hidden" name="pokemonprice" value="<%=entry.getValue()%>" />
	<input type="submit" name="action" value="AddToCart">
</form>
<%
	}
%>
