package app;
import domain.*;


import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class MaidBookingApp {
	static Scanner scanner = new Scanner(System.in);
	private static Controller controller = new Controller();
    private static ArrayList<User> userList = new ArrayList<User>();
	private static ArrayList<Booking> bookingList = new ArrayList<Booking>();
	private static ArrayList<Maid> maidList = new ArrayList<Maid>();

	public static void main(String[] args) {
		importAdmin();
		importClient();
		importMaid();
		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		boolean isLogin = false;
		User user;
		
		do {
			System.out.println("===================================================\n");
			System.out.println("	Welcome to Maid Booking App!	^_^\n");
			System.out.println("===================================================\n\n");
			System.out.println("Please login to your account before create a booking.\n");
			System.out.println("Please create a new account if you don't have an account.\n");
			System.out.println("1. Login");
			System.out.println("2. Create new account");
			System.out.println("3. Exit\n");
			choice = getUserInput("Please enter your choice (1-3): ","Please enter integer value between 1 to 3 only.\n",1,3);	
				
			switch(choice) {
				case 1: 
					user = loginAccount();
					isLogin = true;
					if(isLogin == true && user != null) {
						displayMenu(user);
					}
					break; 
				case 2: createAccount(); break; 
				case 3: System.out.print("Exit successfully. Thanks for using our app!"); break;
			}
			
		}while (choice != 3);
		

	}
	
	
	//-----------------------------------------------static methods in switch-----------------------------------------------------
	

	
	public static User loginAccount() {
		User user = null;
	
		do{
			System.out.print("Enter username: ");
			String username = scanner.nextLine();
			System.out.print("Enter password: ");
			String password = scanner.nextLine();
			
			for(int i=0; i<userList.size(); i++) {
				if(username.equals(userList.get(i).getUserName()) && password.equals(userList.get(i).getPassword())) {
					user = userList.get(i);
					System.out.println();
					System.out.println("Login successfull. Welcome " + user.getUserName());
					return userList.get(i);
				}
			}
			if(user == null) {
				System.out.println("Incorrect username or password, please try again.");
				System.out.println();
				break;
			}
		}while(user == null);
		
		return user;
	}
	
	public static void createAccount() {
		int choice = 0;
		User user;
		do {
			System.out.println("Select account type");
			System.out.println("1. Admin");
			System.out.println("2. Client");
			System.out.println();
			choice = getUserInput("Enter choice: ","Please enter 1 or 2 only",1,2);

			switch (choice) {
			case 1:
				user = new Admin();
				user.createAccount(userList);
				break;
			case 2:
				user = new Client();
				user.createAccount(userList);
			}
		}while(choice!=1 && choice!=2);
	}
	
	
	public static void displayMenu(User user) {
		int choice;
		if(user.getAccountType() == "Admin") {	//Admin menu
			do {
				System.out.println("\nWhat do you want to do?:"); 
				System.out.println("1. Add maid"); 
				System.out.println("2. View all maid"); 
				System.out.println("3. Delete maid"); 
				System.out.println("4. View all booking");
				System.out.println("5. Search booking");
				System.out.println("6. View account");
				System.out.println("7. View ALL account");
				System.out.println("8. Logout\n");
				
				choice = getUserInput("Please enter your choice (1-8): ","Please enter integer value between 1 to 8 only.\n",1,8); 
				
				switch(choice) {
					case 1: addMaid(); break; 
					case 2: ((Admin)user).viewAllMaid(maidList); break;
					case 3: deleteMaid(); break; 
					case 4: ((Admin)user).viewBooking(bookingList); break;
					case 5: searchBooking(); break;
					case 6: ((Admin)user).viewAccount(); break;
					case 7: ((Admin)user).viewAllAccount(userList); break;
					case 8: System.out.print("Logged out successfully.\n\n"); break;
				}
			}while(choice != 8);
		}
		else {	//Client Menu
			do {
				System.out.println("\nWhat do you want to do?:"); 
				System.out.println("1. Create booking"); 
				System.out.println("2. View booking"); 
				System.out.println("3. Update booking");
				System.out.println("4. Cancel booking");
				System.out.println("5. View account");
				System.out.println("6. Logout\n");
			
				choice = getUserInput("Please enter your choice (1-6): ","Please enter integer value between 1 to 6 only.\n",1,6); 

		
				switch(choice) {
					case 1: ((Client)user).createBooking(bookingList, maidList); break; 
					case 2: ((Client)user).viewBooking(bookingList); break; 
					case 3: ((Client)user).updateBooking(bookingList); break;
					case 4: ((Client)user).cancelBooking(bookingList, maidList); break;
					case 5: ((Client)user).viewAccount(); break;
					case 6: System.out.print("Logged out successfully.\n\n"); break;
				}
				System.out.println();
			} while (choice != 6);
		}
	}

	
	public static void searchBooking() {
		int id = getUserInput2nd("Please enter the booking ID you want to search: ","Please enter integer value only.\n",0); 
		Booking booking = controller.searchBooking(bookingList, id);
		if(booking == null) {
			System.out.println("Invalid booking ID. Please try again.");
		}else {
			System.out.println("=================================================================================================================");
			System.out.printf("%1$-12s %2$-30s %3$-30s %4$-14s %5$-14s \n", "Booking Id", "Maid name" , "Client name", "Booking Date", "Booking Time");
			System.out.println("=================================================================================================================");
			System.out.printf("%1$-12d %2$-30s %3$-30s %4$-14s %5$-14s \n",booking.getBookingId(), booking.getMaid().getName(), 
					booking.getClient().getName(), booking.getBookingDate(), booking.getBookingTime());
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
			System.out.println();
		}
	}
	
	
	public static void deleteMaid() {
		int id = getUserInput2nd("Enter maid id to delete: ","Invalid input",0);
		controller.deleteMaid(maidList, id);
	}
	
	public static void addMaid(){
		int id;
		do {
			id = getUserInput2nd("Enter maid's id: ","Invalid input",0);
			for(int i=0; i<maidList.size(); i++) {
				if(id == maidList.get(i).getId()) {
					System.out.print("This maid ID already existed, please enter another maid ID.\n");
					id = -1;
				}
			}
		}while(id == -1);
		
//		for(int i=0; i<maidList.size(); i++) {
//			if(maidList.get(i).getId()!=id) {
//				id = i+1;
//			}
//		}
		
		System.out.print("Enter maid's name: "); 
		String name = scanner.nextLine();
		
		System.out.print("Enter maid's contact number: "); 
		String contactNo = scanner.nextLine();
		
		controller.addMaid(maidList, id, name, contactNo); 
		System.out.println("New maid added."); 
	}
	
	
	
	//------------------------------------other methods-------------------------------------------------
	
	
	public static int getUserInput(String var, String invalid, int min, int max) {
        Scanner sc = new Scanner(System.in);
        int number;
        while(true) {
            try {
                System.out.print(var);
                number = sc.nextInt();
                if(number < min || number > max) {
                    throw new InputMismatchException();
                }
                break;  
            }
            catch(InputMismatchException e) {
                System.out.println(invalid);
                sc.nextLine();
            }
        } 
        return number;
    }
	
	public static int getUserInput2nd(String var, String invalid, int min) {
        Scanner sc = new Scanner(System.in);
        int number;
        while(true) {
            try {
                System.out.print(var);
                number = sc.nextInt();
                if(number < min) {
                    throw new InputMismatchException();
                }
                break;  
            }
            catch(InputMismatchException e) {
                System.out.println(invalid);
                sc.nextLine();
            }
        } 
        return number;
    }

	
	public static void importClient() {
		try {
			File clientFile = new File("clients.txt");
			Scanner sc = new Scanner(clientFile);	
			
			sc.useDelimiter(";");
		    while(sc.hasNextLine()) {
		    	String clientData = sc.nextLine();
		    	String[] clientDataArray = clientData.split(";");
		    	int i=0;
		    	String uname = clientDataArray[i];
		    	String pass = clientDataArray[i+1];
		    	String name = clientDataArray[i+2];
		    	String contactNo = clientDataArray[i+3];
		    	
		    	Client client = new Client(uname,pass,name,contactNo);
		    	userList.add(client);
		    }
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public static void importAdmin() {
		try {
			File adminFile = new File("admins.txt");
			Scanner sc = new Scanner(adminFile);	
			
			sc.useDelimiter(";");
		    while(sc.hasNextLine()) {
		    	String adminData = sc.nextLine();
		    	String[] adminDataArray = adminData.split(";");
		    	int i=0;
		    	String uname = adminDataArray[i];
		    	String pass = adminDataArray[i+1];
		    	String name = adminDataArray[i+2];
		    	String contactNo = adminDataArray[i+3];
		    	
		    	Admin admin = new Admin(uname,pass,name,contactNo);
		    	userList.add(admin);
		    }
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public static void importMaid() {
		try {
			File maidFile = new File("maids.txt");
			Scanner sc = new Scanner(maidFile);	
			
			sc.useDelimiter(";");
		    while(sc.hasNextLine()) {
		    	String maidData = sc.nextLine();
		    	String[] maidDataArray = maidData.split(";");
		    	int i=0;
		    	String sid = maidDataArray[i];
		    	int id = Integer.parseInt(sid);
		    	String name = maidDataArray[i+1];
		    	String contactNo = maidDataArray[i+2];
		    	
		    	Maid maid = new Maid(id,name,contactNo);
		    	maidList.add(maid);
		    }
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
}