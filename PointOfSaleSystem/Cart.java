package PointOfSaleSystem;

import java.util.ArrayList;
import OnlineOrderingQueue.*;

import Map.MapBase;
import OnlineOrderingQueue.Order;

public class Cart {
	ArrayList<String> prodNames;
	ArrayList<Double> prodPrices;
	boolean online = false;
	Products prodMap = new Products();
	OrderQueue queue = new OrderQueue();
	Double total = 0.0;
	
	public Cart() {
		prodNames = new ArrayList<>();
		prodPrices = new ArrayList<>();
	}
					
	public void addProduct(String name) {
		Double price = prodMap.getPrice(name);
		name.toLowerCase();
		prodNames.add(name);
		prodPrices.add(price);
		total += price;
	}
	
	public void setOnline() {
		online = true;
	}
	
	public void setOrder(Order ord) {
		prodNames = ord.getProducts();
		prodPrices = ord.getPrices();
		total = ord.getTotalPrice();
	}
	
	public void printCart() {
		for(int i = 0; i < prodNames.size(); i++) {
			System.out.println("" + prodNames.get(i) + ": " + prodPrices.get(i));
		}
	}
	
	public void printTotal() {
		System.out.println("Your total: " + total);
	}
	
	public void voidTransaction() {
		System.out.println("Transaction voided");
		cartReset();
	}
	
	private void cartReset() {
		prodNames = new ArrayList<>();
		prodPrices = new ArrayList<>();
		total = 0.0;
	}
	
	public ArrayList<MapBase<String,Double>.Record<String,Double>> getAvailableProducts() {
		return prodMap.getProducts();
	}

	public void placeOrder(String name, int time) {
		Order ord = new Order(time, name, prodNames, prodPrices);
		queue.addOrder(ord);
		
		//add to map
		System.out.println("Thank you for shopping with us, your order will be ready at " + time + ".");
	}
	
	public void pickupOrder(String name) {
		Order ord = queue.findOrder(name);
		setOrder(ord);
		System.out.println("Thank you for shopping with us! You have been charged " + total);
	}
	
	public Order nextOrder() {
		return queue.removeNextOrder();
	}
	
	public void checkout(Double cash) {
		Double amt = cash - total;
		if(amt > 0) {
			System.out.println("Your change is " + amt);
		} else if (amt < 0) {
			System.out.println("Balance remaining is: " + amt);
		} else {
			System.out.println("Thank you for shopping with us today!");
		}
		
		printCart();
		printTotal();
		cartReset();
	}		
}
