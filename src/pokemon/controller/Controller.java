package pokemon.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pokemon.model.Cart;
import pokemon.model.CartManager;
import pokemon.model.Pokemon;
import pokemon.model.User;
import pokemon.model.UserManager;

public class Controller extends HttpServlet {

	private static final Logger log = Logger.getLogger(Controller.class.getName());
	
	public static LinkedList<Pokemon> pokemons;
	public static UserManager usermanager;
	public static CartManager cartmanager;
	
	public static boolean initialized = false;
	
	public static void initialize() {
		log.info("Initialization");
		
		//Usermanager
		usermanager = new UserManager();
		//Add users
		usermanager.addUser("Laurent", "abc");
		usermanager.addUser("Tigran", "abc");
		
		cartmanager = new CartManager();
		
		//Pokemons in the system
		pokemons = new LinkedList<Pokemon>();
		pokemons.add(new Pokemon("articuno", 150.00));
		pokemons.add(new Pokemon("blastoise", 130.00));
		pokemons.add(new Pokemon("bulbasaur", 30.00));
		pokemons.add(new Pokemon("charizard", 130.00));
		pokemons.add(new Pokemon("charmander", 35.00));
		pokemons.add(new Pokemon("charmeleon", 75.00));
		pokemons.add(new Pokemon("dragonair", 100.00));
		pokemons.add(new Pokemon("dragonite", 200.00));
		pokemons.add(new Pokemon("dratini", 45.00));
		pokemons.add(new Pokemon("ivysaur", 75.00));
		pokemons.add(new Pokemon("mew", 350.00));
		pokemons.add(new Pokemon("mewtwo", 300.00));
		pokemons.add(new Pokemon("moltres", 150.00));
		pokemons.add(new Pokemon("squirtle", 30.00));
		pokemons.add(new Pokemon("venusaur", 130.00));
		pokemons.add(new Pokemon("wartortle", 75.00));
		pokemons.add(new Pokemon("zapdos", 150.00));
		
		initialized = true;
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		
		if (!initialized) {
			initialize();
		}
		
		String action = request.getParameter("action");
		log.info("ACTION: " + action);
		if (action != null) {
			if (action.equals("Login")) {
				String nickname = request.getParameter("nickname");
				String password = request.getParameter("password");
				log.info("LOGIN-DATA: USER=" + nickname + ", PW=" + password);
				if (usermanager.verifyLogin(nickname, password)) {
					log.info("LOGIN-DATA correct");
					request.getSession().setAttribute("nickname", nickname);
					request.getSession().setAttribute("loggedin", true);
					Cart cart = cartmanager.getCart(nickname);
					if  (cart == null) {
						cart = new Cart(nickname);
					}
					request.getSession().setAttribute("cart",cart);
				} else {
					log.info("LOGIN-DATA incorrect");
				}
			} else if (action.equals("Logout")) {
				Cart cart = (Cart)request.getSession().getAttribute("cart");
				cartmanager.setCart(cart);
				request.getSession().removeAttribute("nickname");
				request.getSession().removeAttribute("cart");
				request.getSession().removeAttribute("loggedin");
				log.info("LOGOUT performed");
			} else if (action.equals("AddToCart")) {
				if (request.getSession().getAttribute("loggedin") != null) {
					String pokemonname = request.getParameter("pokemonname");
					double pokemonprice = Double.parseDouble(request.getParameter("pokemonprice"));
					log.info("pokemon-name=" + pokemonname + ", pokemon-price=" + pokemonprice);
					Cart cart = (Cart)request.getSession().getAttribute("cart");
					cart.addToCart(pokemonname, pokemonprice);
					request.getSession().setAttribute("cart", cart);
					log.info("pokemon added to cart");
					log.info("New basket:\n" + cart.toString());
				} else {
					log.info("Cannot add pokemon to cart, user not logged in");
				}
			} else if (action.equals("ViewCart")) {
				request.setAttribute("site","cart");
			} else if (action.equals("RemoveFromCard")) {
				String pokemonname = request.getParameter("pokemonname");
				Cart cart = (Cart)request.getSession().getAttribute("cart"); 
				cart.removeFromCart(pokemonname);
				log.info(pokemonname + " removed from cart");
				log.info("New Basket:\n" + cart.toString());
				
				request.getSession().setAttribute("cart", cart);
				request.setAttribute("site","cart");
			} else if(action.equals("Pay")) {
				Cart cart = (Cart)request.getSession().getAttribute("cart");
				cart.clear();
				request.getSession().setAttribute("cart", cart);
				request.setAttribute("site","pay");
			}
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
	
}
