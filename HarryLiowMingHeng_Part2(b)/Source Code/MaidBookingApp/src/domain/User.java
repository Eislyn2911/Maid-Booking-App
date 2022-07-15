package domain;
import java.util.ArrayList;

public abstract class User {
	
	protected String userName;
	protected String password;
	protected String name;
	protected String contactNo;
	protected String accountType;
	
	//getter setter
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getAccountType() {
		return accountType;
	}
	
	//abstract methods
	public abstract void viewAccount();
	public abstract void createAccount(ArrayList<User> userList);
	public abstract void viewBooking(ArrayList<Booking> bookingList);
	
	
}
