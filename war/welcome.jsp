
<%
	int width = 200;
	int height = 200;
%>
<%
	for (Entry<String, Double> entry : pokemon.entrySet()) {
%>
<form action="addCart" method="get" class=pokemon>
	<figure> <img src="img/pokemons/<%=entry.getKey()%>.jpg"
		alt="Articuno" width="<%=width%>px" height="<%=height%>px" /> <figcaption><%=entry.getKey()%></figcaption>
	</figure>
	<p>
		<%=entry.getValue()%>
		&euro;
	</p>
	<input type="hidden" name="pokemon" value="<%=entry.getKey()%>" /> <input
		type="submit" value="Add to Cart">
</form>
<%
	}
%>
