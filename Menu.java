package cpsc4620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static cpsc4620.DBNinja.getCustomerList;

/*
 * This file is where the front end magic happens.
 * 
 * You will have to write the functionality of each of these menu options' respective functions.
 * 
 * This file should need to access your DB at all, it should make calls to the DBNinja that will do all the connections.
 * 
 * You can add and remove functions as you see necessary. But you MUST have all 8 menu functions (9 including exit)
 * 
 * Simply removing menu functions because you don't know how to implement it will result in a major error penalty (akin to your program crashing)
 * 
 * Speaking of crashing. Your program shouldn't do it. Use exceptions, or if statements, or whatever it is you need to do to keep your program from breaking.
 * 
 * 
 */

public class Menu {
	public static void main(String[] args) throws SQLException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Welcome to Taylor's Pizzeria!");
		
		int menu_option = 0;

		// present a menu of options and take their selection
		PrintMenu();
		String option = reader.readLine();
		menu_option = Integer.parseInt(option);

		while (menu_option != 9) {
			switch (menu_option) {
			case 1:// enter order
				EnterOrder();
				break;
			case 2:// view customers
				viewCustomers();
				break;
			case 3:// enter customer
				EnterCustomer();
				break;
			case 4:// view order
				// open/closed/date
				ViewOrders();
				break;
			case 5:// mark order as complete
				MarkOrderAsComplete();
				break;
			case 6:// view inventory levels
				ViewInventoryLevels();
				break;
			case 7:// add to inventory
				AddInventory();
				break;
			case 8:// view reports
				PrintReports();
				break;
			}
			PrintMenu();
			option = reader.readLine();
			menu_option = Integer.parseInt(option);
		}

	}

	public static void PrintMenu() {
		System.out.println("\n\nPlease enter a menu option:");
		System.out.println("1. Enter a new order");
		System.out.println("2. View Customers ");
		System.out.println("3. Enter a new Customer ");
		System.out.println("4. View orders");
		System.out.println("5. Mark an order as completed");
		System.out.println("6. View Inventory Levels");
		System.out.println("7. Add Inventory");
		System.out.println("8. View Reports");
		System.out.println("9. Exit\n\n");
		System.out.println("Enter your option: ");
	}

	// allow for a new order to be placed
	public static void EnterOrder() throws SQLException, IOException 
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		/*
		 * EnterOrder should do the following:
		 * Ask if the order is for an existing customer -> If yes, select the customer. If no -> create the customer (as if the menu option 2 was selected).
		 * 
		 * Ask if the order is delivery, pickup, or dinein (ask for orderType specific information when needed)
		 * 
		 * Build the pizza (there's a function for this)
		 * 
		 * ask if more pizzas should be be created. if yes, go back to building your pizza. 
		 * 
		 * Apply order discounts as needed (including to the DB)
		 * 
		 * apply the pizza to the order (including to the DB)
		 * 
		 * return to menu
		 */
		
		//TODO
		
		System.out.println("Finished adding order...Returning to menu...");
	}
	
	
	public static void viewCustomers()
	{
		/*
		 * Simply print out all of the customers from the database. 
		 */
		try
		{
			ArrayList<Customer> a = DBNinja.getCustomerList();
			for (int i = 0; i< a.size(); i++){

				System.out.println(a.get(i).toString());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	// Enter a new customer in the database
	public static void EnterCustomer() throws SQLException, IOException 
	{
		/*
		 * Ask what the name of the customer is. YOU MUST TELL ME (the grader) HOW TO FORMAT THE FIRST NAME, LAST NAME, AND PHONE NUMBER.
		 * If you ask for first and last name one at a time, tell me to insert First name <enter> Last Name (or separate them by different print statements)
		 * If you want them in the same line, tell me (First Name <space> Last Name).
		 * 
		 * same with phone number. If there's hyphens, tell me XXX-XXX-XXXX. For spaces, XXX XXX XXXX. For nothing XXXXXXXXXXXX.
		 * 
		 * I don't care what the format is as long as you tell me what it is, but if I have to guess what your input is I will not be a happy grader
		 * 
		 * Once you get the name and phone number (and anything else your design might have) add it to the DB
		 */
		try {
			String fName, lName, pNum;
			ArrayList<Customer> a = DBNinja.getCustomerList();
			Integer cusID = a.size() + 1;
			//reading in user data
			Scanner readIn = new Scanner(System.in);
			System.out.println("Please enter First name of Customer");
			fName = readIn.nextLine();
			System.out.println("Please enter Last name of Customer");
			lName = readIn.nextLine();
			System.out.println("Please enter Phone number(XXX-XXX-XXXX)");
			pNum = readIn.nextLine();
			//create customer
			Customer c = new Customer(cusID, fName, lName, pNum);
			//System.out.println(c.toString());
			DBNinja.addCustomer(c);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	// View any orders that are not marked as completed
	public static void ViewOrders() throws SQLException, IOException 
	{
	/*
	 * This should be subdivided into two options: print all orders (using simplified view) and print all orders (using simplified view) since a specific date.
	 * 
	 * Once you print the orders (using either sub option) you should then ask which order I want to see in detail
	 * 
	 * When I enter the order, print out all the information about that order, not just the simplified view.
	 * 
	 */

		try {
			char userChoice = 'x';
			boolean goodChoice = false;
			Scanner readIn = new Scanner(System.in);
			while(goodChoice != true) {
				//reading in user data
				System.out.println("Would you like to:");
				System.out.println("(a) display all orders");
				System.out.println("(b) display orders since a specific date");
				userChoice = readIn.next().charAt(0);
				if (userChoice == 'a' || userChoice == 'b') goodChoice = true;
			}

			switch(userChoice){
				case 'a':
					//print all orders
					ArrayList<Order> oList = DBNinja.getCurrentOrders();
					System.out.println(oList.toString());
					break;
				case 'b':
					System.out.println("What is the date you want to restrict by? (FORMAT = YYY-MM-DD)");
					String sortYear = readIn.nextLine();
					//print orders that occurred after the sortYear
					break;
			}

			System.out.println("Which order would you like to see in detail? Enter the number:");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	
	// When an order is completed, we need to make sure it is marked as complete
	public static void MarkOrderAsComplete() throws SQLException, IOException 
	{
		/*All orders that are created through java (part 3, not the 7 orders from part 2) should start as incomplete
		 * 
		 * When this function is called, you should print all of the orders marked as complete 
		 * and allow the user to choose which of the incomplete orders they wish to mark as complete
		 * 
		 */
		
		
		
		//TODO
		
		

	}

	// See the list of inventory and it's current level
	public static void ViewInventoryLevels() throws SQLException, IOException 
	{
		//print the inventory. I am really just concerned with the ID, the name, and the current inventory
		try
		{
			DBNinja.printInventory();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	// Select an inventory item and add more to the inventory level to re-stock the
	// inventory
	public static void AddInventory() throws SQLException, IOException 
	{
		/*
		 * This should print the current inventory and then ask the user which topping they want to add more to and how much to add
		 */

		try
		{
			Boolean validTop = false;
			Scanner readIn = new Scanner(System.in);
			Integer topID = 0;
			//loop until the user enters a topping with id between 1 and 17
			while(validTop != true) {
				DBNinja.printInventory();
				System.out.println("Which topping would you like to add more of? Enter Topping ID");
				topID = readIn.nextInt();
				if (topID >= 1 && topID <= 17) validTop = true;
			}
			System.out.println("How much inventory would you like to add?");
			double topAmt = readIn.nextDouble();
			//need to fix this to pass a topping not a topp id
			Topping t = DBNinja.getTopping(topID);
			//System.out.println(t.toString());
			DBNinja.AddToInventory(t, topAmt);
			System.out.println(t.toString());
			DBNinja.printInventory();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	// A function that builds a pizza. Used in our add new order function
	public static Pizza buildPizza(int orderID) throws SQLException, IOException 
	{
		
		/*
		 * This is a helper function for first menu option.
		 * 
		 * It should ask which size pizza the user wants and the crustType.
		 * 
		 * Once the pizza is created, it should be added to the DB.
		 * 
		 * We also need to add toppings to the pizza. (Which means we not only need to add toppings here, but also our bridge table)
		 * 
		 * We then need to add pizza discounts (again, to here and to the database)
		 * 
		 * Once the discounts are added, we can return the pizza
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Pizza ret = null;
		
		
		
		
		//TODO
		
		
		return ret;
	}
	
	private static int getTopIndexFromList(int TopID, ArrayList<Topping> tops)
	{
		/*
		 * This is a helper function I used to get a topping index from a list of toppings
		 * It's very possible you never need a function like this
		 * 
		 */
		int ret = -1;
		
		
		
		return ret;
	}
	
	
	public static void PrintReports() throws SQLException, NumberFormatException, IOException
	{
		/*
		 * This function calls the DBNinja functions to print the three reports.
		 * 
		 * You should ask the user which report to print
		 */

		try{
			Scanner readIn = new Scanner(System.in);
			boolean goodInput = false;
			Integer userChoice = 0;
			while (goodInput!=true){
				System.out.println("Which report do you wish to print?");
				System.out.println("1.) ToppingPopularity");
				System.out.println("2.) ProfitByPizza");
				System.out.println("3.) ProfitByOrderType");
				userChoice = readIn.nextInt();
				if (userChoice >= 1 && userChoice <= 3) goodInput = true;
			}

			System.out.println();
			switch(userChoice){
				case 1:
					DBNinja.printToppingPopReport();
					break;
				case 2:
					DBNinja.printProfitByPizzaReport();
					break;
				case 3:
					DBNinja.printProfitByOrderType();
					break;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
