package pokemon.model;

import java.io.Serializable;

/**
 * Class which is derived from the pokemon-class and
 * extends it by further features for handling pokemons in
 * an onlineshop-cart
 *
 */
public class CartPokemon extends Pokemon implements Serializable {

	/**
	 * Default serial version
	 */
	
	private static final long serialVersionUID = 1L;
	/**
	 * Units of the pokemon
	 */
	private int units;
	
	/**
	 * Constructor initializes the pokemon with name and price
	 * @param name
	 * 				name of the pokemon
	 * @param price
	 * 				price of the pokemon
	 */
	public CartPokemon(String name, double price) {
		super(name, price);
		units = 0;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Getter-Method for the units
	 * @return
	 * 			number of units of the pokemons
	 */
	public int getUnits() {
		return units;
	}

	/**
	 * Setter-Method for the pokemon
	 * @param units
	 * 			number of units
	 */
	public void setUnits(int units) {
		this.units = units;
	}
	
	/**
	 * Increases the number of units by 1
	 */
	public void incUnits() {
		units++;
	}
	
	/**
	 * Decreases the number of units by 1
	 */
	public void decUnits() {
		units--;
	}
	
	/**
	 * Calculates the total price according to the number of units
	 * @return
	 * 			the total price
	 */
	public double getTotalPrice() {
		return this.getPrice() * this.getUnits();
	}
	
	/**
	 * Standard-toString() method for the textual representation
	 * Contains basic information about the cart-pokemon
	 */
	public String toString() {
		String s = new String();
		s += "name: " + this.getName();
		s += ", price: " + this.getPrice();
		s += ", units: " + this.getUnits();
		return s;
	}

	
	
}
