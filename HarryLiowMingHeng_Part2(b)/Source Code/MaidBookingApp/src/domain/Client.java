package domain;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Client extends User{
	private Booking booking = null;
	private String accountType = "Client";
	Scanner scanner = new Scanner(System.in);
	
	public Client() {
		
	}
	
	public Client(String userName, String password, String name, String contactNo) {
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.contactNo = contactNo;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public void createBooking(ArrayList<Booking> bookingList, ArrayList<Maid> maidList)
	{
		boolean anyMaidAvailable = false;
		for(int i=0; i<maidList.size(); i++) {
			if(maidList.get(i).getIsAvailable()==true) {
				anyMaidAvailable = true;
				break;
			}
		}
		if(booking != null) {
			System.out.println("You has already created a booking, here is your booking.");
			this.viewBooking(bookingList);
		}else if(maidList.isEmpty()){
			System.out.println("No maid added in this app, please contact admin for further inquiry.");
		}else if(anyMaidAvailable == false){		//still need to check if all maid available==false, if yes then stop create booking.
			System.out.println("No maid currently available, please come again next time.");
		}else {
			Maid maid=null;
			int maidId;
			System.out.println("\nEnter maid id to book a maid");
			System.out.println("\nAll available maids");
			System.out.println("=======================================================================================");
			System.out.printf("%1$-6s %2$-12s %3$-30s %4$-18s \n", "Index", "Maid ID", "Maid name" , "Maid contact number");
			System.out.println("=======================================================================================");
			for(int i=0; i<maidList.size(); i++) {
				if(maidList.get(i).getIsAvailable()==true) {
				System.out.printf("%1$-6d %2$-12d %3$-30s %4$-16s \n",(i+1) , maidList.get(i).getId(), 
						maidList.get(i).getName(), maidList.get(i).getContactNo());
				}
			}
			System.out.println("---------------------------------------------------------------------------------------");
			System.out.println();
			
			
			int bookingId = 0;
			
			do {
				maidId = getUserInput2nd("Choose maid by ID: ","Invalid input",0);
				for(int i=0; i<maidList.size(); i++) {
					if(maidId == maidList.get(i).getId() && maidList.get(i).getIsAvailable()==true) {
						System.out.println("Maid successfully choosen.\n");
						maid = maidList.get(i);
						maid.setIsAvailable(false);
						bookingId = i+1;
						break;
					}
				}
				if(maid == null) {
					System.out.println("Maid not found, please enter again.\n");
				}
			}while(maid == null);
			
			
			System.out.print("Enter your booking date: "); 
			String bookingDate = scanner.nextLine();
			
			System.out.print("Enter your booking time: "); 
			String bookingTime = scanner.nextLine();
			
			System.out.println("");
			
			booking = new Booking(this, maid, bookingDate, bookingTime);
			booking.setBookingId(bookingId);
			
			bookingList.add(booking);
			System.out.print("Booking created successfully."); 
		}
	}
	
	
	public void updateBooking(ArrayList<Booking> bookingList)
	{

		for(int i=0; i<bookingList.size(); i++) {
			if(this.userName.equals(bookingList.get(i).getClient().getUserName())) {
				booking = bookingList.get(i);
				break;
			}
		}
		
		if(booking != null) {
			System.out.println("Pick a booking detail to update");
			System.out.println("1. Update booking date");
			System.out.println("2. Update booking time");
			int choice = 0;
			choice = getUserInput("Please enter your choice (1-2): ","Please enter integer value 1 or 2 only.\n",1,2); 
			
			switch(choice) {
			case 1 :
				System.out.print("Enter new booking date: ");
				String newBookingDate = scanner.nextLine();
				booking.setBookingDate(newBookingDate);
				for(int i=0; i<bookingList.size(); i++) {
					if(bookingList.get(i).getClient() == this) {
						bookingList.get(i).setBookingDate(newBookingDate);
					}
				}
				System.out.print("Booking updated successfully.");
				break;
				
			case 2 : 
				System.out.print("Enter new booking time: ");
				String newBookingTime = scanner.nextLine();
				booking.setBookingTime(newBookingTime);
				for(int i=0; i<bookingList.size(); i++) {
					if(bookingList.get(i).getClient() == this) {
						bookingList.get(i).setBookingTime(newBookingTime);
					}
				}
				System.out.print("Booking updated successfully.");
				break;
			}
			
		}else {
			System.out.println("No booking created! Please create a booking to continue.");
		}
	}
	
	
	public void cancelBooking(ArrayList<Booking> bookingList, ArrayList<Maid>maidList)
	{
		if(booking != null) {
			this.viewBooking(bookingList);
			String confirm;
			do {
				System.out.print("Are you sure you want to cancel this booking? (y/n): ");
				confirm = scanner.nextLine();
				confirm = confirm.toLowerCase();
				if(confirm.equals("y")) {
					for(int i=0; i<bookingList.size(); i++) {
						if(bookingList.get(i).getClient() == this) {
							Maid maid = bookingList.get(i).getMaid();
							maid.setIsAvailable(true);
							bookingList.remove(i);
							booking = null;
							System.out.println("Booking cancelled successfully!");
						}
					}
				}else if(confirm.equals("n")) {
					System.out.print("Booking remains.");
				}else {
					System.out.print("Invalid input. Please type y or n.\n");
				}
			}while(confirm.equals("y")==false && confirm.equals("n")==false);
		}else {
			System.out.println("No booking created! Please create a booking to continue.");
		}
	}
	
	@Override
	public void viewAccount() {
		System.out.println("===========================================================================================");
		System.out.printf("%1$-14s %2$-14s %3$-30s %4$-10s \n", "Username", "Password", "Name", "ContactNo");
		System.out.println("===========================================================================================");
		System.out.printf("%1$-14s %2$-14s %3$-30s %4$-10s \n", this.userName, this.password, this.name, this.contactNo);
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.println();
	}
	
	@Override
	public void createAccount(ArrayList<User> userList) 
	{

		String userName;
		do {
			System.out.print("Enter username: ");
			userName = scanner.nextLine();
			for(int i=0; i<userList.size(); i++) {
				if(userName.equals(userList.get(i).getUserName())) {
					System.out.print("This username has already taken by another user, please enter another username.\n");
					userName = "null";
				}
			}
		}while(userName.equals("null"));
		
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
		System.out.print("Enter name: ");
		String name = scanner.nextLine();
		System.out.print("Enter Contact No: ");
		String contactNo = scanner.nextLine();

		this.userName = userName;
		this.password = password;
		this.name = name;
		this.contactNo = contactNo;

		System.out.println();
		System.out.println("Account created successfully.");
		System.out.println("======================================================================================================");
		System.out.printf("%1$-14s %2$-14s %3$-30s %4$-14s %5$-13s \n", "Username", "Password", "Name", "ContactNo", "AccountType");
		System.out.println("======================================================================================================");
		System.out.printf("%1$-14s %2$-14s %3$-30s %4$-14s %5$-13s \n", this.userName, this.password, this.name, this.contactNo, this.accountType);
		System.out.println("------------------------------------------------------------------------------------------------------");
		System.out.println();
		
		userList.add(this);
	}
	
	@Override
	public void viewBooking(ArrayList<Booking> bookingList) {
		if(booking != null) {
			System.out.println("================================================================================================================");
			System.out.printf("%1$-6s %2$-12s %3$-30s %4$-30s %5$-14s %6$-14s \n", "Index", "Booking Id", "Maid name" , "Client name", "Booking Date", "Booking Time");
			System.out.println("================================================================================================================");
			for(int i=0; i<bookingList.size();i++)
			{
				if(bookingList.get(i).getClient() == this)
				{
					
					System.out.printf("%1$-6d %2$-12d %3$-30s %4$-30s %5$-14s %6$-14s \n",(i+1) , bookingList.get(i).getBookingId(), bookingList.get(i).getMaid().getName(), 
							bookingList.get(i).getClient().getName(), bookingList.get(i).getBookingDate(), bookingList.get(i).getBookingTime());
					
				}
			}
			System.out.println("----------------------------------------------------------------------------------------------------------------");
		}else {
			System.out.println("No booking created! Please create a booking to continue.");
		}
	}
	
	
	
	//-------------------------------other methods-------------------------------------------
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
}
