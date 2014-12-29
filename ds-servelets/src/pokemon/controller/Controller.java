package pokemon.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pokemon.model.Cart;
import pokemon.model.CartManager;
import pokemon.model.SessionConstants;
import pokemon.model.Pokemon;
import pokemon.model.UserManager;

/**
 * 
 * The class controller is responsible for handling the incoming requests from
 * the user It handles login, logout, buying pokemons and is also responsible
 * for the session management
 * 
 */
public class Controller extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6276391437596079172L;

	/**
	 * Logging object used for logmessages
	 */
	private static final Logger log = Logger.getLogger(Controller.class
			.getName());

	/**
	 * List of pokemons that the system knows
	 */
	public static LinkedList<Pokemon> pokemons;
	/**
	 * Usermanager object for verifying the login-information
	 */
	public static UserManager usermanager;
	/**
	 * Cartmanager for saving and restoring a cart if the user logs in or out
	 */
	public static CartManager cartmanager;

	/**
	 * Variable indicating if the system is initialized or not
	 */
	public static boolean initialized = false;

	/**
	 * Initialization method Initializes the list of pokemons, the usermanger
	 * and the cartmanager
	 */
	public static void initialize() {
		log.info("Initialization");

		// Usermanager
		usermanager = new UserManager();
		// Add users
		usermanager.addUser("Laurent", "abc");
		usermanager.addUser("Tigran", "abc");
		usermanager.addUser("Sarah", "teacher");

		cartmanager = new CartManager();

		// Pokemons in the system
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

	/**
	 * Service method for handling the POST and GET-requests This actually is
	 * the main method of the system. Depending on the action, appropriate
	 * measures are taken. If the action is login, the usermanager verifies the
	 * login information and if it is correct opens a session for the user and
	 * loads also a cart from the cartmanager if there is a previous one stored.
	 * If the action is logout, the cart is saved into the cartmanager and the
	 * session objects get deleted. Action remove from card removes a pokemon
	 * from the cart and action addtocart adds one to the cart. If the action is
	 * pay or viewCart which leads to another content, another parameter for
	 * site is set to the request, so the correct code in the jsp content.jsp is
	 * shown.
	 */
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
					//Open session
					// do we have an old session?
					HttpSession session = request.getSession(false);
					if (session != null) {
					   // prevent session fixation
					   session.invalidate();
					}
					// create a new session and initialize it
					session = request.getSession(true);
					session.setAttribute(SessionConstants.NICKNAME_TOKEN, nickname);
					session.setAttribute(SessionConstants.ISLOGGEDIN_TOKEN, true);
					//Do we have an old card?
					Cart cart = cartmanager.getCart(nickname);
					if (cart == null) {
						//Add new card
						cart = new Cart(nickname);
					}
					session.setAttribute(SessionConstants.CART_TOKEN, cart);
				} else {
					log.info("LOGIN-DATA incorrect");
				}
			} else if (action.equals("Logout")) {
				Cart cart = (Cart) request.getSession().getAttribute("cart");
				cartmanager.setCart(cart);
				request.getSession().invalidate();
				log.info("LOGOUT performed");
			} else if (action.equals("AddToCart")) {
				HttpSession session = request.getSession();
				if (session.getAttribute(SessionConstants.ISLOGGEDIN_TOKEN) != null) {
					String pokemonname = request.getParameter("pokemonname");
					double pokemonprice = Double.parseDouble(request
							.getParameter("pokemonprice"));
					log.info("pokemon-name=" + pokemonname + ", pokemon-price="
							+ pokemonprice);
					Cart cart = (Cart) session.getAttribute(SessionConstants.CART_TOKEN);
					cart.addToCart(pokemonname, pokemonprice);
					session.setAttribute(SessionConstants.CART_TOKEN, cart);
					log.info("pokemon added to cart");
					log.info("New basket:\n" + cart.toString());
				} else {
					log.info("Cannot add pokemon to cart, user not logged in");
				}
			} else if (action.equals("ViewCart")) {
				request.setAttribute("site", "cart");
			} else if (action.equals("RemoveFromCard")) {
				String pokemonname = request.getParameter("pokemonname");
				HttpSession session = request.getSession();
				Cart cart = (Cart) session.getAttribute(SessionConstants.CART_TOKEN);
				cart.removeFromCart(pokemonname);
				log.info(pokemonname + " removed from cart");
				log.info("New Basket:\n" + cart.toString());
				session.setAttribute(SessionConstants.CART_TOKEN, cart);
				request.setAttribute("site", "cart");
			} else if (action.equals("Pay")) {
				HttpSession session = request.getSession();
				Cart cart = (Cart) session.getAttribute(SessionConstants.CART_TOKEN);
				cart.clear();
				session.setAttribute(SessionConstants.CART_TOKEN, cart);
				request.setAttribute("site", "pay");
			}
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}
