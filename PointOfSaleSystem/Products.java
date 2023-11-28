package PointOfSaleSystem;

import java.util.ArrayList;

import Map.MapBase;

public class Products {
	MapBase<String, Double> prods;
	
	@SuppressWarnings("unchecked")
	public Products() {
		prods = new MapBase<String, Double>();
		
		addProduct("banana", 1.14);
		addProduct("apple", 1.39);
		addProduct("rice", 1.21);
		addProduct("cereal", 2.85);
		addProduct("milk", 4.85);
		addProduct("eggs", 4.39);
		addProduct("chicken", 4.35);
		addProduct("ham", 5.65);
		addProduct("bottled water", 9.99);
		addProduct("broccoli", 1.97);
		addProduct("bacon", 6.96);
		addProduct("cauliflower", 3.47);
		addProduct("sugar", 8.64);
		addProduct("flour", 5.59);
		addProduct("potatoes", 2.00);
	}

	
	public void addProduct(String name, double price) {
		prods.put(name, price);
	}
	
	public Double getPrice(String name) {
		return prods.get(name);
	}
	
	public ArrayList<MapBase<String,Double>.Record<String,Double>> getProducts() {
		return prods.getEntries();
	}
}
