<%@	page import="java.util.HashMap"%>
<%@	page import="java.util.LinkedList"%>
<%@	page import="pokemon.model.CartPokemon"%>
<%@	page import="pokemon.model.Cart"%>
<%@ page import="java.text.DecimalFormat" %>

<table>
	<thead>
		<tr>
			<td>Name</td>
			<td>Price</td>
			<td>Units</td>
			<td>Total</td>
			
		</tr>
	</thead>
	<tbody>
		<%
			DecimalFormat dFormat = new DecimalFormat("0.00");
			Cart usercart = (Cart) request.getSession().getAttribute("cart");
			double total = 0.00;
			/**
			for (Entry<String, Integer> entry : cart.entrySet()) {
				total += entry.getValue() * pokemon.get(entry.getKey());
			**/
			LinkedList<CartPokemon> cartpokemons = usercart.getCartPokemons();
			for (CartPokemon cp : cartpokemons) {
			
		%>
		<tr>
			<td>
				<form action="controller" method="get">
					<input type="hidden" name="pokemonname" value=<%=cp.getName()%> />
					<input type="submit" name="action" value="RemoveFromCard" />
					<%=cp.getName()%>
				</form>
			</td>
			<td><%=cp.getPrice()%> &euro;</td>
			<td><%=cp.getUnits()%></td>
			<td><%=cp.getTotalPrice()%>&euro;</td>
		</tr>
		<%
			}
		%>
	</tbody>
	<tfoot>
		<tr>
			<td>Price (without VTA)</td>
			<td colspan="3"><%=dFormat.format(usercart.getTotalPriceWithoutVAT())%> &euro;</td>
		</tr>
		<tr>
			<td>V.T.A.</td>
			<td colspan="3"><%=dFormat.format(usercart.getTotalPrice() - usercart.getTotalPriceWithoutVAT())%> &euro;</td>
		</tr>
		<tr>
			<td>Total price</td>
			<td colspan="3"><%=usercart.getTotalPrice()%> &euro;</td>
		</tr>
	</tfoot>
</table>
<table style="width:150px; border-bottom:0px; margin-left:55px">
	<tr  style="border-bottom:0px">
		<td>
<form action="controller" method="get">
	<input type="hidden" name="price" value="<%=total%>" /> <input
		type="submit" name="action" value="Pay" />
</form>
		</td>
		<td>
<form action="controller" method="get">
	<input type="submit" name="action" value="Return"/>
</form>
		</td>
	</tr>
</table>
