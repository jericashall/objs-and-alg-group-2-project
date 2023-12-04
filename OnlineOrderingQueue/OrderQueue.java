package OnlineOrderingQueue;


public class OrderQueue {
	//priority queue for unfulfilled sorted by pick up time
    private Heap<Order> uncompletedOrders;
    private Orders completedOrders;

    public OrderQueue() {
        this.uncompletedOrders = new Heap<Order>();
        this.completedOrders = new Orders();
    }
		
	//add to queue
    public void addOrder(Order order) {
        this.uncompletedOrders.insert(order);
    }

	//print queue
    void printOrders() {
        System.out.println(this.uncompletedOrders.toString());
    }
    
    public Order findOrder(String name) {
    	return completedOrders.getOrder(name);
    }
	//remove from top of queue
    public Order removeNextOrder() {
    	
    	if(uncompletedOrders.size() == 0)
    		return null;
        //print order
        Order nextOrder = this.uncompletedOrders.deleteMin();

        //print statement about fulfilling order
        System.out.println("Fulfilled: " + nextOrder);

        //add to orders map
        this.completedOrders.addOrder(nextOrder);
        
        return nextOrder;


    }
}
