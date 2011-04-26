package pokemon.model;

import java.util.LinkedList;

public class Cart {

	private String owner;
	private LinkedList<CartPokemon> cartpokemons;
	
	public Cart(String name) {
		this.owner = name;
		cartpokemons = new LinkedList<CartPokemon>();
	}
	
	public void addToCart(Pokemon p) {
		addToCart(p.getName(), p.getPrice());
	}
	
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
	
	public LinkedList<CartPokemon> getCartPokemons() {
		return cartpokemons;
	}
	
	public String toString() {
		String s = new String();
		for (CartPokemon cp : cartpokemons) {
			s += cp.toString() + "\n";
		}
		return s;
	}
	
	public double getTotalPrice() {
		double price = 0;
		for (CartPokemon cp : cartpokemons) {
			price += cp.getTotalPrice();
		}
		return price;
	}
	
	public double getTotalPriceWithoutVAT() {
		double price = getTotalPrice();
		price -= price * 19.60 / 100.0;
		price *= 100;
		Math.round(price);
		price /= 100;
		return price;
	}
	
	public void clear() {
		cartpokemons.clear();
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
}
