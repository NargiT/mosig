package pokemon.model;

import java.util.LinkedList;

/**
 * 
 * Class for handling the pokemons in the cart
 */
public class Cart {

	/**
	 * The name of the owner of the cart
	 */
	private String owner;
	
	/**
	 * List of cartpokemon objects for determining 
	 * the kind and the number of pokemons in the cart
	 */
	private LinkedList<CartPokemon> cartpokemons;
	
	/**
	 * Constructor initializing the cart for a certain owner
	 * @param owner
	 * 					name of the owner the cart
	 */
	public Cart(String owner) {
		this.owner = owner;
		cartpokemons = new LinkedList<CartPokemon>();
	}
	
	/**
	 * 
	 * Adds a pokemon to the cart
	 * @param p
	 * 			Pokemon-object to be added to the cart
	 */
	public void addToCart(Pokemon p) {
		addToCart(p.getName(), p.getPrice());
	}
	
	/**
	 * Adds a pokemon to the cart
	 * @param name
	 * 				Name of the pokemon
	 * @param price
	 * 				Price of the pokemon
	 */
	public void addToCart(String name, double price) {
		for (CartPokemon cp : cartpokemons) {
			if (name.equals(cp.getName())) {
				cp.incUnits();
				return;
			}
		}
		CartPokemon cp = new CartPokemon(name, price);
		cp.incUnits();
		cartpokemons.add(cp);
	}
	
	/**
	 * Removes a pokemon from the cart
	 * @param name
	 * 			name of the pokemon
	 * @return
	 * 			true if the pokemon was removed, false otherwise
	 */
	public boolean removeFromCart(String name) {
		for (CartPokemon cp : cartpokemons) {
			if (cp.getName().equals(name)) {
				cp.decUnits();
				if (cp.getUnits() == 0) {
					cartpokemons.remove(cp);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Getter-Method for list of CartPokemons
	 * @return
	 * 			the list of CartPokemons
	 */
	public LinkedList<CartPokemon> getCartPokemons() {
		return cartpokemons;
	}
	
	/**
	 * Standard toString-Method
	 * Gives textual information about all the pokemons in the cart
	 */
	public String toString() {
		String s = new String();
		for (CartPokemon cp : cartpokemons) {
			s += cp.toString() + "\n";
		}
		return s;
	}
	
	/**
	 * Calculates total price of all the pokemons in the cart
	 * @return
	 * 			the total price
	 */
	public double getTotalPrice() {
		double price = 0;
		for (CartPokemon cp : cartpokemons) {
			price += cp.getTotalPrice();
		}
		return price;
	}
	
	/**
	 * Calculates total price without the taxes of all the pokemons in the cart
	 * @return
	 * 			the total price without the taxes
	 */
	public double getTotalPriceWithoutVAT() {
		double price = getTotalPrice();
		price -= price * 19.60 / 100.0;
		price *= 100;
		Math.round(price);
		price /= 100;
		return price;
	}
	
	/**
	 * Clears all the pokemons in the cart
	 */
	public void clear() {
		cartpokemons.clear();
	}

	/**
	 * Getter-Method for the owner
	 * @return
	 * 			name of the owner of the cart
	 */
	public String getOwner() {
		return owner;
	}
	
	/**
	 * Setter-Method for the owner of the cart
	 * @param owner
	 * 				name of the owner of the cart
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
}
