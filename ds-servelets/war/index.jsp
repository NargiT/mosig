<%@ page language="java"%>
<%@	page import="java.util.HashMap"%>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="java.text.DecimalFormat"%>
<%@page import="pokemon.controller.Controller"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%
	if (!Controller.initialized)
		Controller.initialize();

	// Initialize the list of pokemons
	// Database<key, price>
// 	HashMap<String, Double> pokemon = new HashMap<String, Double>();
// 	pokemon.put("articuno", 150.00);
// 	pokemon.put("blastoise", 130.00);
// 	pokemon.put("bulbasaur", 30.00);
// 	pokemon.put("charizard", 130.00);
// 	pokemon.put("charmander", 35.00);
// 	pokemon.put("charmeleon", 75.00);
// 	pokemon.put("dragonair", 100.00);
// 	pokemon.put("dragonite", 200.00);
// 	pokemon.put("dratini", 45.00);
// 	pokemon.put("ivysaur", 75.00);
// 	pokemon.put("mew", 350.00);
// 	pokemon.put("mewtwo", 300.00);
// 	pokemon.put("moltres", 150.00);
// 	pokemon.put("squirtle", 30.00);
// 	pokemon.put("venusaur", 130.00);
// 	pokemon.put("wartortle", 75.00);
// 	pokemon.put("zapdos", 150.00);

	// Initialize the list of items in the cart
	// Cart<key, how many>)
	HashMap<String, Integer> cart = new HashMap<String, Integer>();

%>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="/style/default.css" />
<title>Buy-a-Pokemon.com</title>
</head>

<body>
	<%@ include file="header.jsp"%>
	<%@ include file="content.jsp"%>
	<%@ include file="footer.jsp"%>
</body>
</html>