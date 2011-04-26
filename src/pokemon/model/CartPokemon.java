package pokemon.model;

public class CartPokemon extends Pokemon {

	private int units;
	
	public CartPokemon(String name, double price) {
		super(name, price);
		units = 0;
		// TODO Auto-generated constructor stub
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}
	
	public void incUnits() {
		units++;
	}
	
	public void decUnits() {
		units--;
	}
	
	public double getTotalPrice() {
		return this.getPrice() * this.getUnits();
	}
	
	public String toString() {
		String s = new String();
		s += "name: " + this.getName();
		s += ", price: " + this.getPrice();
		s += ", units: " + this.getUnits();
		return s;
	}

	
	
}
