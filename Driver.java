import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

import java.awt.Dimension;
import java.awt.FlowLayout;

import PointOfSaleSystem.*;
import Map.*;
import OnlineOrderingQueue.Order;
import OnlineOrderingQueue.OrderQueue;
import Payroll.Employee;
import Payroll.Employees;

public class Driver {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// pre-add some employees, clock-in times, and hours worked
		Employees employeeTable = new Employees();
		Employee employeeZach = new Employee();
		employeeZach.setUsername("ZachIsMe");
		employeeZach.setEmployeeName("Zach Driggers");
		employeeZach.setPassword("Zach123!");
		employeeZach.setHourlyRate(15.00);
		employeeZach.setClockedIn(true);
		employeeTable.addEmployee(employeeZach.getUsername(), employeeZach);

		// pre-add some online orders
		OrderQueue orderQueue = new OrderQueue();
		String[] orderOneItems = new String[] { "banana", "apple", "chips" };
		double[] orderOnePrices = new double[] { 10.00, 3.00, 5.00 };
		Order orderOne = new Order(1300, "Steve's Order", orderOneItems, orderOnePrices);
		orderQueue.addOrder(orderOne);

		JFrame frame = new JFrame("TopLevelDemo");
		frame.setLayout(new FlowLayout());
		frame.setPreferredSize(new Dimension(500, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.addElement("Employee");
		listModel.addElement("Customer");

		JList<String> list = new JList<String>(listModel);
		frame.getContentPane().add(list);

		DefaultListModel<String> listModelTwo = new DefaultListModel<String>();
		listModelTwo.addElement("New employee registration");
		listModelTwo.addElement("Clock in");
		listModelTwo.addElement("back");

		JList<String> listTwo = new JList<String>(listModelTwo);

		DefaultListModel<String> listModelThree = new DefaultListModel<String>();
		listModelThree.addElement("Checkout");
		listModelThree.addElement("Pickup");
		listModelThree.addElement("back");

		JList<String> listThree = new JList<String>(listModelThree);

		DefaultListModel<String> listModelFour = new DefaultListModel<String>();
		listModelFour.addElement("Fulfill Next Order");
		listModelFour.addElement("back");

		JList<String> listFour = new JList<String>(listModelFour);

		DefaultListModel<String> listModelFive = new DefaultListModel<String>();
		listModelFive.addElement("Add Item to Cart");
		listModelFive.addElement("Cancel Order");
		listModelFive.addElement("Place Online Order");
		listModelFive.addElement("Checkout");
		listModelFive.addElement("back");

		JList<String> listFive = new JList<String>(listModelFive);

		Cart cart = new Cart();
		ArrayList<MapBase<String, Double>.Record<String, Double>> prods = cart.getAvailableProducts();
		DefaultListModel<String> listModelSix = new DefaultListModel<String>();
		for (MapBase<String, Double>.Record<String, Double> prod : prods) {
			listModelSix.addElement("" + prod.key + " $" + prod.value);
		}
		listModelSix.addElement("Banana");
		listModelSix.addElement("Milk");
		listModelSix.addElement("Orange Juice");
		listModelSix.addElement("Apples");
		listModelSix.addElement("Chicken");

		JList<String> listSix = new JList<String>(listModelSix);

		MouseListener eventListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = list.locationToIndex(e.getPoint());
					switch (index) {
					case (0):
						frame.getContentPane().remove(list);
						frame.getContentPane().add(listTwo);
						frame.setVisible(false);
						frame.setVisible(true);
						break;
					case (1):
						frame.getContentPane().remove(list);
						frame.getContentPane().add(listThree);
						frame.setVisible(false);
						frame.setVisible(true);
						break;
					default:
						break;

					}
				}
			}
		};

		list.addMouseListener(eventListener);

		MouseListener eventListenerTwo = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = listTwo.locationToIndex(e.getPoint());
					switch (index) {
					case (0):

						Employee newEmployee = new Employee();
						System.out.println("Please enter your name:");
						String name = scan.next();
						System.out.println("Please pick a username:");
						String user = scan.next();
						System.out.println("Please pick a password:");
						String pw = scan.next();
						System.out.println("Please enter your hourly payrate:");
						double rate = scan.nextDouble();

						// create a new user with this
						// add to employee table
						// confirm user was created
						newEmployee.setEmployeeName(name);
						newEmployee.setUsername(user);
						newEmployee.setPassword(pw);
						newEmployee.setHoursWorked(rate);
						employeeTable.addEmployee(user, newEmployee);

						if (employeeTable.getEmployee(user) != null)
							System.out.println("Employee " + name + " has successfully been added.");

						break;
					case (1):
						System.out.println("Please enter your username:");
						String un = scan.next();
						System.out.println("Please enter your password:");
						String pass = scan.next();
						System.out.println("Please enter your hours for this shift");
						double hours = scan.nextDouble();

						// validate login
						Employee existingEmployee = employeeTable.getEmployee(un);
						// state no username/pw match if no match
						if (existingEmployee == null) {
							System.out.println("Employee not found");
							return;
						}

						if (!existingEmployee.getPassword().equals(pass)) {
							System.out.println("Incorrect password");
							return;
						}

						existingEmployee.setClockedIn(true);
						existingEmployee.setHoursWorked(hours);

						// if successful
						// display hours worked & pay due this period
						System.out.println("Hours worked : " + existingEmployee.getHoursWorked());
						System.out.println("Pay due : " + existingEmployee.calculatePay());

						// button option fulfill next online order
						// remove from top of queue
						// display the order name, items, and total
						frame.getContentPane().remove(listTwo);
						frame.getContentPane().add(listFour);
						frame.setVisible(false);
						frame.setVisible(true);
						break;
					case (2):
						frame.getContentPane().remove(listTwo);
						frame.getContentPane().add(list);
						frame.setVisible(false);
						frame.setVisible(true);
						break;
					default:
						break;

					}
				}
			}
		};

		listTwo.addMouseListener(eventListenerTwo);

		MouseListener eventListenerThree = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = listThree.locationToIndex(e.getPoint());
					switch (index) {
					case (0):
						frame.getContentPane().remove(listThree);
						frame.getContentPane().add(listFive);
						frame.getContentPane().add(listSix);
						frame.setVisible(false);
						frame.setVisible(true);
						break;
					case (1):
						System.out.println("Please enter your name");
						String pickupName = scan.next();

						// find order from online order queue
						// Order ord =
						// use cart.setOrder(ord) to add order to cart
						// checkout with cart.checkout()

						break;
					case (2):
						frame.getContentPane().remove(listThree);
						frame.getContentPane().add(list);
						frame.setVisible(false);
						frame.setVisible(true);
						break;
					default:
						break;

					}
				}
			}
		};

		listThree.addMouseListener(eventListenerThree);

		MouseListener eventListenerFour = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = listFour.locationToIndex(e.getPoint());
					switch (index) {
					case (0):

						Order order = orderQueue.removeNextOrder();
						String orderName = order.name;
						int pickupTime = order.pickupTime;
						System.out.println("Order : " + orderName + " pickup time is " + pickupTime);
						
						System.out.println("Order contains the following items: ");
						for(int i =0; i< order.products.length; i++)
						{
							System.out.println("item: " + order.products[i] + " price: " + order.prices[i]);
						}
						
						System.out.println("Total cost is " + order.price);
						

						frame.setVisible(false);
						frame.setVisible(true);
						break;
					case (1):
						frame.getContentPane().remove(listFour);
						frame.getContentPane().add(listTwo);
						frame.setVisible(false);
						frame.setVisible(true);
						break;
					default:
						break;

					}
				}
			}
		};

		listFour.addMouseListener(eventListenerFour);

		MouseListener eventListenerFive = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = listFive.locationToIndex(e.getPoint());
					switch (index) {
					case (0):
						System.out.println(
								"Please enter the item one of the items from the list that you would like to add to your cart: ");
						String item = scan.next();
						cart.addProduct(item);
						break;
					case (1):
						cart.voidTransaction();
						break;
					case (2):
						cart.setOnline();
						System.out.println("Please enter your name");
						String customer = scan.next();
						System.out.println(
								"Please enter the time you would like to pickup your order in miltary time 1-24");
						Integer time = scan.nextInt();
						cart.placeOrder(customer, time);
						break;
					case (3):
						System.out.println("Enter the cash amount you are paying");
						Double cash = scan.nextDouble();
						cart.checkout(cash);
						break;
					case (4):
						frame.getContentPane().remove(listFive);
						frame.getContentPane().remove(listSix);
						frame.getContentPane().add(listThree);
						frame.setVisible(false);
						frame.setVisible(true);
						break;
					default:
						break;

					}
				}
			}
		};

		listFive.addMouseListener(eventListenerFive);

		// Display the window.
		frame.pack();
		frame.setVisible(true);

		// print all workers, their time worked time worked, and wages earned
		// print all orders still in the queue
	}
}
