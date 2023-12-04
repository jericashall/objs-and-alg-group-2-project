package OnlineOrderingQueue;

import java.util.ArrayList;

public class Order implements Comparable<Order> {
	public int pickupTime;
	public String name;
	public String[] products;
	public double[] prices;
	public Double price;
	boolean fulfilled;
	boolean active;
	
	public Order(int time, String name, String[] names, double[] prices) {
		pickupTime = time;
		this.name = name;
		products = names;
		this.prices = prices;
		price = getTotalPrice();
		fulfilled = false;
		active = true;
	}
	
	//get products
	String[] getProducts() {
		return products;
	}

	//get prices
	double[] getPrices() {
		return prices;
	}

	//get total price
	Double getTotalPrice() {
		Double sum = 0.0;
		for (Double price : this.prices) {
			sum = sum + price;
		}
		return sum;
	}

	// comparable interface implementation
	@Override
	public int compareTo(Order anotherOrder) {
		return (this.pickupTime > anotherOrder.pickupTime) ? 1 : ((this.pickupTime < anotherOrder.pickupTime) ? -1 : 0);
	}

	@Override
	public String toString() {
		return pickupTime + ", " + name + ", " + price;
	}
}
