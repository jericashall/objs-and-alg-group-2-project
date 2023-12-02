package OnlineOrderingQueue;

import java.util.HashMap;
public class Orders {
	//map with online orders ready for pick up
	//name as the key
    private HashMap<String, Order> orders;

    public Orders {
        this.orders = new HashMap<String, Order>;
    }
	
	//add order
    void addOrder(Order order) {
        this.orders.put(order.name, order);
    }

	//retrieve order
    Order getOrder(String name) {
        return this.orders.get(name);
    }

	//remove order
    void removeOrder(String name) {
        //soft delete with active boolean
        this.orders.remove(name);
    }

	//fulfill order
		//update to show ready for sale
	
	
}
