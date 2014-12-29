<div id="content">
	<!-- If index page -->
	
	<%
		String site = (String) request.getAttribute("site");
		int state = 1;
		if (site != null) {
			if (site.equals("cart")) {
				state = 2;
			} else if (site.equals("pay")) {
				state = 3;
			}
		}
		
		if (state == 1) {
	%>
	
	<%@ include file="welcome.jsp"%>
	<!-- If cart page -->
	
	<%
		} else if (state == 2) {
	%>
	
	<%@ include file="cart.jsp" %>
	
	<%
		} else if (state == 3) {
	%>
	<%@ include file="pay.jsp" %>	
	<%
		}
	%>
	<div style="clear: both;"></div>
</div>