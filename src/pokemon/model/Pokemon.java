package pokemon.model;

/**
 * Class containing basic information on a pokemon
 *
 */
public class Pokemon {
	
	/** 
	 * Name of the pokemon
	 */
	protected String name;
	
	/**
	 * Price of the pokemon
	 */
	protected double price;
	
	/**
	 * Constructor
	 * initializes the pokemon
	 * @param name
	 * 				name of the pokemon
	 * @param price
	 * 				price of the pokemon
	 */
	public Pokemon(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	/**
	 * Getter method for the name
	 * @return
	 * 			name of the pokemon
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter-Method for the name
	 * @param name
	 * 			name of the pokemon
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter-Method for the price
	 * @return
	 * 			price of the pokemon
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Seter-Method for the price
	 * @param price
	 * 			price of the pokemon
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
}
