package pokemon.model;

import java.util.LinkedList;

/**
 * Class for managing the carts of different users
 *
 */
public class CartManager {
	
	/**
	 * List of all the carts
	 */
	private LinkedList<Cart> carts;
	
	/**
	 * Constructor initializes list of carts
	 */
	public CartManager() {
		carts = new LinkedList<Cart>();
	}
	
	/**
	 * Replaces the cart of an owner in the list, 
	 * if there is no cart of the owner in the list,
	 * the cart gets added to the list
	 * @param cart
	 * 				cart to be set
	 */
	public void setCart(Cart cart) {
		for (Cart c : carts) {
			if (c.getOwner().equals(cart.getOwner())) {
				c = cart;
				return;
			}
		}
		carts.add(cart);
	}
	
	/**
	 * Function retrieves the corresponding cart of an owner
	 * @param owner
	 * 				name of the owner of the cart
	 * @return
	 * 				the cart of the owner, null if no cart was found
	 */
	public Cart getCart(String owner) {
		for (Cart c : carts) {
			if (c.getOwner().equals(owner)) {
				return c;
			}
		}
		return null;
	}
	
}
