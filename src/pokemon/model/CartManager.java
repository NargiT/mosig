package pokemon.model;

import java.util.LinkedList;

public class CartManager {
	
	private LinkedList<Cart> carts;
	
	public CartManager() {
		carts = new LinkedList<Cart>();
	}
	
	public void setCart(Cart cart) {
		for (Cart c : carts) {
			if (c.getOwner().equals(cart.getOwner())) {
				c = cart;
				return;
			}
		}
		carts.add(cart);
	}
	
	public Cart getCart(String owner) {
		for (Cart c : carts) {
			if (c.getOwner().equals(owner)) {
				return c;
			}
		}
		return null;
	}
	
}
