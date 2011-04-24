<%@	page import="java.util.HashMap"%>


<table>
	<thead>
		<tr>
			<td>Name</td>
			<td>Price</td>
		</tr>
	</thead>
	<tbody>
		<%
			DecimalFormat dFormat = new DecimalFormat("0.00");
			cart.put("mew", 2);
			double total = 0.00;
			for (Entry<String, Integer> entry : cart.entrySet()) {
				total += entry.getValue() * pokemon.get(entry.getKey());
		%>
		<tr>
			<td><%=entry.getKey()%></td>
			<td><%=entry.getValue() * pokemon.get(entry.getKey())%> &euro;</td>
		</tr>
		<%
			}
		%>
	</tbody>
	<tfoot>
		<tr>
			<td>Price (without VTA)</td>
			<td><%=dFormat.format(total - (total * 19.60 / 100.00))%> &euro;</td>
		</tr>
		<tr>
			<td>V.T.A.</td>
			<td><%=dFormat.format(total * 19.60 / 100.00)%> &euro;</td>
		</tr>
		<tr>
			<td>Total price</td>
			<td><%=total%> &euro;</td>
		</tr>
	</tfoot>
</table>
<form action="payement.jsp" method="get">
	<input type="hidden" name="price" value="<%=total%>" /> <input
		type="submit" value="Pay" />
</form>
