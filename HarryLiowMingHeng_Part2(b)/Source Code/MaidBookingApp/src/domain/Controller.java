package domain;

import java.util.ArrayList;


public class Controller {
	Client client = new Client();
	Admin admin = new Admin();
	
	public void addMaid(ArrayList<Maid> maidList, int id, String name, String contact){
		Maid maid = new Maid(id, name, contact);
		admin.addMaid(maidList, maid);
	}
	
	public void deleteMaid(ArrayList<Maid> maidList, int id) {
		admin.deleteMaid(maidList, id);
	}
	
	public Booking searchBooking(ArrayList<Booking> bookingList, int id) {
		return admin.searchBooking(bookingList, id);
	}
	
	
	
	
}
