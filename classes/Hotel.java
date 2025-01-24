import java.io.File;
import java.io.FileWriter; 
import java.io.FileNotFoundException; 
import java.util.*;
import java.io.IOException;

public class Hotel{
    public static void main (String [] args)throws IOException {
    //Creation of the hotel program using the Person class

	Person[] customers = new Person[8];
        customers[0] = new Person();
        customers[1] = new Person();
        customers[2] = new Person();
        customers[3] = new Person();
        customers[4] = new Person();
        customers[5] = new Person();
        customers[6] = new Person();
        customers[7] = new Person();
	//creating an array of Person objects

	Queue customersWaitingList = new Queue();
	//Circular queue class which holds the customer waiting list
	
	Scanner input= new Scanner(System.in);
	int roomNum=0;
	//initialising the roomNum value

	boolean menu=true;
	//boolean variable to access the menu
	
	initialise(customers);
	//initialise values in the hotel array to nobody. Better to initialise in a procedure

	while(menu){
	//loop to access menu options based on the input

	    System.out.println("Please select an options:");
	    System.out.println("'A' to Book A New Room.");
	    System.out.println("'E' to Display Empty Rooms.");
	    System.out.println("'V' to View all Rooms.");
	    System.out.println("'D' to Delete customer from room.");
	    System.out.println("'F' to Find room from customer name.");
	    System.out.println("'S' to Store program data in to file.");
	    System.out.println("'L' to Load program data from file.");
	    System.out.println("'O' to View rooms Ordered alphabetically by name.");
	    //options in the menu

	    String userInput=input.nextLine();
	    //getting a user input based on the options given

	    userInput=userInput.toUpperCase();
	    //convert input to uppercase to ignore the case
	
	
	
	    switch (userInput) {
		case "A":
		    addCustomer(roomNum,customers,customersWaitingList);
		    break;
		    //option to add a customer

		case "E":
		    displayEmpty(customers);
		    break;
		//option to display empty rooms
	
		case "V":
		    viewList(customers);
		    break;
		//option to view the rooms

		case "D":
		    deleteCustomer(roomNum,customers,customersWaitingList);
	            break;
		//option to delete a customer

		case "F":
		    findRoom(customers);
		    break;
		//option to find a customer based on the name given

		case "S":
		    storeData(customers);
		    break;
		//option to store data to a file

		case "L":
		    loadData(customers);
		    break;
		//option to load data from the file
	
		case "O":
		    orderCustomer(customers);
		    break;
		//option to order the customer names in alphabetical order

		default:
		    System.out.println("Wrong Selection");
		    break;
		//option when an invalid input is given

	   }

	    System.out.println("Would you like to return back to the menu selection(Yes/No):");
	    
	    boolean checkInput=true;
	    //boolean variable for the input based on the user's selection

	    while(checkInput){
	    //loop to check the customer input

		String selection=input.nextLine();
		//user selection

	    	if (selection.equalsIgnoreCase("Yes")) {
	    	    menu = true;
		    checkInput=false;
	    	}
		//condition which will access the menu selection again
 
		else if (selection.equalsIgnoreCase("No")){
	    	    menu = false;
		    checkInput=false;
	    	}
		//condition which will stop the program

		else if ((!selection.equalsIgnoreCase("Yes")) || (!selection.equalsIgnoreCase("No"))){
		    System.out.println("Unknown input. Would you like to return back to the menu selection(Yes/No):");
		    menu = false;
		    checkInput=true;
		}
		//condition to check for invalid inputs and run the loop once again					
            }
	}    
    }
  
    private static void initialise(Person []customers){
    //method to initialise the elements in the array

	for(int x=0; x<customers.length; x++){
	    customers[x].setLastName("nobody");
		
	}
    }

    public static void viewList(Person [] customers){
    //method to view all the rooms and their occupants	

	for (int i=0; i<customers.length; i++){
	//loop to traverse through all the elements in the array

	    System.out.println("Room " + (i+1) + " occupied by " + customers[i].getFirstName() + " " + customers[i].getLastName());
	}

    }

	

    public static void addCustomer(int n,Person [] customers, Queue customersWaitingList){
    //method to add customers to the rooms
	
	try{
	    Scanner input= new Scanner(System.in);
	    Scanner in= new Scanner(System.in);
	    in.useDelimiter("\n");
	    //spilt string
	    
	    while (n<customers.length){
	    //loop to access each room 
		if ((!customers[0].getLastName().equals("nobody")) && (!customers[1].getLastName().equals("nobody")) && (!customers[2].getLastName().equals("nobody")) && (!customers[3].getLastName().equals("nobody")) && (!customers[4].getLastName().equals("nobody")) && (!customers[5].getLastName().equals("nobody")) && (!customers[6].getLastName().equals("nobody")) && (!customers[7].getLastName().equals("nobody"))){
		//condition to check whether all the rooms are empty

		    System.out.println("All the rooms are booked. The customer will be added to a waiting list");
		
				
		    System.out.println("Please enter the last name of the customer:");
		    String waitingListCustomerName=input.next();
		    //variable to store the customer's last name in the waiting list
		    
		    customersWaitingList.enQueue(waitingListCustomerName);
		    //add customer's last name to the waiting list queue
		    
		    break;
				
		}else{	
		//condition when all roomes are not empty
	            System.out.println("Enter room number(1-8) or 9 to stop adding customers:");

		    n=input.nextInt()-1;
		    //room number entered by the user

		    
		    if (n+1==9){
		    	break;
		    }
		    //not adding customers for room number 9 as there are only 8 rooms available
	
			
		    if (n+1==0 || n+1>9){
		    	System.out.println("No such room");
		    	break;
		    }
		    //not adding customers for invalid room numbers

		    
		    if (customers[n].getLastName().equals("nobody")){
	    	        System.out.println("Enter first name of registered customer in room " + (n+1)+ ":");
		    	String firstname=input.next();
	    	        customers[n].setFirstName(firstname);
			//add customer's first name to the given room number

			System.out.println("Enter last name of registered customer in room " + (n+1) + ":");
		    	String lastname=input.next();
	    	        customers[n].setLastName(lastname);
			//add customer's last name to the given room number

			System.out.println("Enter credit card number of the registered customer in room " + (n+1)+ ":");
		    	while(!input.hasNextLong()){
			    System.out.println("Please enter a valid credit card number:");
			    input.next();
			}
			long creditcard=input.nextLong();
			customers[n].setCreditCard(creditcard);
		        //add customer's credit card number to the data    	

				
			System.out.println("Enter the number of guests in room " + (n+1) + ":");
			while(!input.hasNextInt()){
			    System.out.println("Please enter a valid number:"); 
		    	    input.next();
			}
			int guests=input.nextInt();
	    	        customers[n].setNoOfGuests(guests);
			//number of customers
		        continue;
		    }
		    else if(!customers[n].getLastName().equals("nobody")){
			System.out.println("Room was already booked.");
	       	        continue;
		    }
		}
		
	    }
	}catch(Exception er){
		System.out.println("Wrong input type");
		addCustomer(n,customers,customersWaitingList);
	}
	    
    }

    public static void displayEmpty(Person []customers){
    //method to display all the empty rooms in the hotel

	for (int i=0; i<customers.length;i++){
	    if (customers[i].getLastName().equals("nobody")){
	    //checking if the room is empty

		System.out.println("Room " + (i+1) + " is empty");
	    }
	}
    }

    public static void deleteCustomer(int n,Person [] customers, Queue customersWaitingList){
    //method to delete a customer based on the room number given

	try{
	    Scanner input= new Scanner(System.in);		
	    System.out.println("Enter the room you would like to cancel:");
	    n=input.nextInt()-1;
		
	    if (customersWaitingList.checkIfEmpty()==true){
	    //incase waiting list is empty set room as occupied by nobody
		    customers[n].setLastName("nobody");
		    customers[n].setFirstName("");
		    customers[n].setNoOfGuests(0);
		    customers[n].setCreditCard(0);
		    System.out.println("Successfully deleted customer from the room");
	    }

		
	    else if (!customers[n].getLastName().equalsIgnoreCase("nobody")){
	    //check if room occupies a customer

	    	

		if (customersWaitingList.checkIfEmpty()==false){
		//checking if there are customers in the waiting list

		    customers[n].setLastName(customersWaitingList.deQueue());
		    //remove customer from the waiting list

		    System.out.println("Added customer to Room number " + (n + 1));
		    System.out.println("Please Enter this customers' first name:");
		    String firstname=input.next();
	    	    customers[n].setFirstName(firstname);
		    //setting the customers's first name and adding to the room

		    System.out.println("Enter credit card number of the registered customer in room " + (n+1)+ ":");
		    while(!input.hasNextLong()){
		        System.out.println("Please enter a valid credit card number:");
		        input.next();
		    }
		    long creditcard=input.nextLong();
		    customers[n].setCreditCard(creditcard);
		    //add customer's credit card number to the data 
		            	

		    System.out.println("Enter the number of guests in room " + (n+1) + ":");
		    while(!input.hasNextInt()){
		        System.out.println("Please enter a valid number:"); 
		        input.next();
		    }
		    int guests=input.nextInt();
	    	    customers[n].setNoOfGuests(guests);
		    //adding number of customers
		    
	            System.out.println("Successfully deleted customer from the waiting list and added to a room");
		}
		
	    }if (customers[n].getLastName().equalsIgnoreCase("nobody")){
		System.out.println("This room is already empty");
	    }
	}catch(Exception er){
	    System.out.println("Invalid input. Please try again");
	    deleteCustomer(n,customers,customersWaitingList);
	    //get an input again to delete a customer when the initial input was incorrect
	}
    }

    public static void findRoom(Person []customers){
    //method that finds the room number of the customer whose name is given
	
	Scanner input= new Scanner(System.in);
	System.out.print("Enter the name you want to search:"); 
	String name=input.nextLine();
	int temp=1;
	//variable to use when checking whether a customer is there in a room or not

	for(int i=0;i<customers.length;i++){ 
	    if (name.equalsIgnoreCase(customers[i].getFirstName()) || name.equalsIgnoreCase(customers[i].getLastName())){
		System.out.println("This customer is in room " + (i+1));
		temp=0;
		//condition when a customer is found
	    }
	 }


	for(int i=0;i<customers.length;i++){ 
	    if ((!name.equalsIgnoreCase(customers[i].getFirstName()) || (!name.equalsIgnoreCase(customers[i].getLastName()))) && (temp!=0)){
		System.out.println("There is no such customer");
		//condition when a customer is not found
		break;
	    }
	}
	    
    }

    public static void storeData(Person []customers){
    //method to store the data collected into a text file called GuestData

	FileWriter storeData=null;
	try{
	    try{
		storeData=new FileWriter("GuestData.txt");
		for (int i=0;i<customers.length;i++){
		    storeData.write("Room " + (i+1) + " is occupied by " + customers[i].getFirstName() + " " + customers[i].getLastName() );
		    storeData.write("\n");
		    storeData.write("Credit Card number: " + customers[i].getCreditCard());
		    storeData.write("\n");
		    storeData.write("The number of guests in this room are " + customers[i].getNoOfGuests());
		    storeData.write("\n" + "\n");
		//write to file

		}
		System.out.println("Saved to file ");

	    }finally{
		storeData.close();
	    }

	}catch(IOException e){
	    System.out.println("An error has occured");
	    e.printStackTrace();
	    //handle exceptions and errors
	}
    }

    public static void loadData(Person []customers){
    //method to load the data store in the GuestData textfile

	try {
      	    File storeData = new File("GuestData.txt");
      	    Scanner load = new Scanner(storeData);
      	    while (load.hasNextLine()) {
        	String data = load.nextLine();
        	System.out.println(data);
		//loading data from file
            }

      		load.close();
    	 } catch (FileNotFoundException e) {
      	    System.out.println("An error occurred.");
      	    e.printStackTrace();
	    //handle exceptions and errors
    	 }
  	     
	
    }
	
    public static void orderCustomer(Person []customers){
    //method to sort the names of the customers in alphabetical order

	Person customersCopy[]=customers.clone();
	//copy of the original array of customers
	    
	for (int i=0;i<customersCopy.length-1;i++){
	    for (int j=i+1;j<customersCopy.length;j++){
		if (customersCopy[i].getLastName().compareTo((customersCopy[j].getLastName()))>0) {
		    Person temp = customersCopy[i];
               	    customersCopy[i]= customersCopy[j];
               	    customersCopy[j] = temp;
			
		}
		    
	    }
		
	}
	//bubble sort to sort the names of the customers
		
	for (int i=0;i<customersCopy.length;i++){
	    if ((!customersCopy[i].getLastName().equals("nobody")) && (!customersCopy[i].getFirstName().equals(""))){
		System.out.println(customersCopy[i].getLastName() + " " + customersCopy[i].getFirstName());
	    }
	    else{
		break;
	    }
	//display customers in alphabetical order
	}

    
    }		

public static class Room{
    
    public String lastName;
    //customer's last name

    public Room(){
	this.lastName="nobody";
    }
    //constructor

    public void setLastName(String lastName){
	this.lastName=lastName;
    }
    //setter to set customer's last name

    public String getLastName(){
	return lastName;
    }
    //setter to get customer's last name

}

private static class Person extends Room{

    private String firstName;
    //customer's first name

    private long creditCard;
    //customer's credit card number

    public int noOfGuests;
    //number of guests in a room

    public Person(){
	this.creditCard=0;
	this.firstName="";
	this.lastName="nobody";
	this.noOfGuests=0;
    }

    public void setFirstName(String firstName){
	this.firstName=firstName;
    }
    //setter to set customer's first name

    public void setCreditCard(long creditCard){
	this.creditCard=creditCard; 
    }
    //setter to set customer's credit card number

    public void setNoOfGuests(int noOfGuests){
	this.noOfGuests=noOfGuests;
    }
    //setter to set number of guests in a room

    public String getFirstName(){
	return firstName;
    }
    //getter to get customer's first name
    
    public long getCreditCard(){
	return creditCard;
    }
    //getter to get customer's credit card number

    public int getNoOfGuests(){
	return noOfGuests;
    }
    //getter to get number of guests in a room

}



private static class Queue extends Room{

	
    private static int front, rear;
    //front and rear variables initialisation

    private static int size=4;
    //the size of the waiting list is 4

    private static String waitingList[] = new String[size];
    //customer waiting list array of size 4

    public Queue(){
	
	this.size=size;
	this.front=this.rear=-1;
	//initialise values to -1
    }
    //constructor
    
    
    public static boolean checkIfFull(){
    //method to check if the waiting list is full

	if ((rear-front==size-1) || (front-rear==1) || ((front == 0) && (rear == size - 1)) || (front == rear + 1)){
	    return true;
	}
	//the waiting list is full
	
	
	return false;	
	
    }
    public static boolean checkIfEmpty(){
    //method to check if the waiting list is empty

	if (front==-1){
	    return true;
	}
	//the waiting list is empty
	else{
	
	    return false;	
	}
    }

    public static void enQueue(String customer){
	//add customers name

	if (checkIfFull()){
	    System.out.println("The waiting list is full. ");
	    
	}
	else if ((front==-1) && (rear==-1)){
	    front=0;
	    rear=0;
	    waitingList[rear]=customer;
	    System.out.println("Added first customer to the waiting list");
	    //first entry to the waiting list
	}
	else{
	    rear=(rear+1)%size;
	    waitingList[rear]=customer;
	    System.out.println("Added customer to the waiting list");
	    //entry to the waiting list
	}

    }

    public static String deQueue(){
	//remove customer from the waiting list
	
	if (checkIfEmpty()){
	    System.out.println("The waiting list is empty");
	    return "";
	    //check if empty
	}
	else if (front==rear){
	    String temp=waitingList[rear];
	    rear=-1;
	    front=-1;
	    System.out.println("Last customer in waiting list removed");
	    return temp;
	    //last customer to remove from the waiting list
	}
	else{
	    String temp=waitingList[front];
	    front=(front +1)%size; 
	    System.out.println("Deleted customer from waiting list");
	    return temp;
	    //remove customer from the list
	}
	    
	
    }
	
}
}