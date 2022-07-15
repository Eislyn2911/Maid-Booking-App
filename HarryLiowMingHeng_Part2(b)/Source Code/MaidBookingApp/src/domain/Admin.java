package domain;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User{
	Scanner scanner = new Scanner(System.in);
	private String accountType = "Admin";
	
	public Admin() {
		
	}
	
	public Admin(String userName, String password, String name, String contactNo) {
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.contactNo = contactNo;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public void addMaid(ArrayList<Maid> maidList, Maid maid) {
		maidList.add(maid);
	}
	
	public void deleteMaid(ArrayList<Maid> maidList, int id) {
		boolean found = false;
		for(int i=0; i<maidList.size(); i++) {
			if(id == maidList.get(i).getId()) {
				found = true;
				if(maidList.get(i).getIsAvailable()==false) {
					System.out.println("Maid currently unable to delete, please try again later when the maid is available.");
					break;
				}
				maidList.remove(i);
				System.out.println("Maid removed successfully!");
				break;
			}
		}
		if(found == false) {
			System.out.println("No such maid.");
		}
	}
	
	public void viewAllMaid(ArrayList<Maid> maidList) {
		System.out.println("View All Maid");
		System.out.println("===========================================================================");
		System.out.printf("%1$-6s %2$-5s %3$-30s %4$-13s %5$-11s \n", "Index", "Id", "Name" , "ContactNo", "Available");
		System.out.println("===========================================================================");
		for(int i=0; i<maidList.size(); i++) {
			System.out.printf("%1$-6d %2$-5d %3$-30s %4$-13s %5$-11b \n", (i+1), maidList.get(i).getId(), maidList.get(i).getName() , maidList.get(i).getContactNo(), maidList.get(i).getIsAvailable());
			
		}
		System.out.println("---------------------------------------------------------------------------");
		System.out.println();
	}
	
	@Override
	public void createAccount(ArrayList<User> userList) {

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
			System.out.println("=========================================================================================");
			System.out.printf("%1$-14s %2$-14s %3$-30s %4$-14s %5$-13s \n", "Username", "Password", "Name", "ContactNo", "AccountType");
			System.out.println("=========================================================================================");
			System.out.printf("%1$-14s %2$-14s %3$-30s %4$-14s %5$-13s \n", this.userName, this.password, this.name, this.contactNo, this.accountType);
			System.out.println("-----------------------------------------------------------------------------------------");
			System.out.println();
			
			userList.add(this);
	}

	@Override
	public void viewAccount() {
		System.out.println("===================================================================================");
		System.out.printf("%1$-14s %2$-14s %3$-30s %4$-10s \n", "Username", "Password", "Name", "ContactNo");
		System.out.println("===================================================================================");
		System.out.printf("%1$-14s %2$-14s %3$-30s %4$-10s \n", this.userName, this.password, this.name, this.contactNo);
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println();
	}
	
	public void viewAllAccount(ArrayList<User> userList) {
		System.out.println("View All Accounts");
		System.out.println("======================================================================================================");
		System.out.printf("%1$-6s %2$-14s %3$-14s %4$-30s %5$-14s %6$-14s \n", "Index", "Username", "Password" , "Name", "Contact No", "Account Type");
		System.out.println("======================================================================================================");
		for(int i=0; i<userList.size(); i++) {
			System.out.printf("%1$-6d %2$-14s %3$-14s %4$-30s %5$-14s %6$-14s \n",(i+1) , userList.get(i).userName, userList.get(i).password, userList.get(i).getName(), 
					userList.get(i).getContactNo(), userList.get(i).getAccountType());
		}
		System.out.println("------------------------------------------------------------------------------------------------------");
		System.out.println();
	}

	@Override
	public void viewBooking(ArrayList<Booking> bookingList) {
		System.out.println("View All Bookings");
		System.out.println("================================================================================================================");
		System.out.printf("%1$-6s %2$-12s %3$-30s %4$-30s %5$-14s %6$-14s \n", "Index", "Booking Id", "Maid name" , "Client name", "Booking Date", "Booking Time");
		System.out.println("================================================================================================================");
		for(int i=0; i<bookingList.size(); i++) {
			System.out.printf("%1$-6d %2$-12d %3$-30s %4$-30s %5$-14s %6$-14s \n",(i+1) , bookingList.get(i).getBookingId(), bookingList.get(i).getMaid().getName(), 
					bookingList.get(i).getClient().getName(), bookingList.get(i).getBookingDate(), bookingList.get(i).getBookingTime());
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		System.out.println();
	}

	public Booking searchBooking(ArrayList<Booking> bookingList, int bookingId) {
		Booking booking = null;
		for(int i=0; i<bookingList.size(); i++) {
			if(bookingId == bookingList.get(i).getBookingId()) {
				booking = bookingList.get(i);
			}
		}
		return booking;
		
	}

}
