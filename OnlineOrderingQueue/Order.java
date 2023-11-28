package OnlineOrderingQueue;

import java.util.ArrayList;

public class Order {
	int pickupTime;
	String name;
	ArrayList<String> products;
	ArrayList<Double> prices;
	Double price;
	boolean fulfilled;
	boolean active;
	
	public Order(int time, String name, ArrayList<String> names, ArrayList<Double> prices, Double total) {
		pickupTime = time;
		this.name = name;
		products = names;
		this.prices = prices;
		price = total;
		fulfilled = false;
		active = true;
	}
	
	//get products
	//get prices
	//get total price
}
