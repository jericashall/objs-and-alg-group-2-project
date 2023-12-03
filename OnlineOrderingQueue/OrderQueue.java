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
    void addOrder(Order order) {
        this.uncompletedOrders.insert(order);
    }

	//print queue
    void printOrders() {
        System.out.println(this.uncompletedOrders.toString());
    }

	//remove from top of queue
    public Order removeNextOrder() {
        //print order
        Order nextOrder = this.uncompletedOrders.deleteMin();

        //print statement about fulfilling order
        System.out.println("Fulfilled: " + nextOrder);

        //add to orders map
        this.completedOrders.addOrder(nextOrder);
        
        return nextOrder;


    }
}
