package cl.neerixx.junit;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import cl.neerixx.junit.objects.Booking;
import cl.neerixx.junit.objects.Room;
import cl.neerixx.junit.objects.User;
import cl.neerixx.junit.types.BookingStatus;

public class BookingManager {
	
	private Map<UUID, Room> rooms;
	private Map<UUID, User> users;
	
	private List<Booking> bookings;
	
	public BookingManager() {
		this.rooms = new HashMap<>();
		this.users = new HashMap<>();
		this.bookings = new ArrayList<>();
	}
	
	public boolean reserveRoon(UUID roomCode, UUID userId, Date date) {
		return reserveRoom(roomCode, userId, date, "");
	}
	
	public boolean reserveRoom(UUID roomCode, UUID userId, Date date, String details) {
		Room room = rooms.get(roomCode);
		
		if (room == null || room.getStatus() == BookingStatus.RESERVED) {
			return false;
		}
		
		User user = users.get(userId);
		
		if (user == null) {
			return false;
		}
		
		room.setStatus(BookingStatus.RESERVED);
		
		Booking booking = new Booking(roomCode, userId, date, details);
		bookings.add(booking);
		
		return true;
	}
	
	public Room getRoom(String name) {
		Room room = null;
		
		for (Map.Entry<UUID, Room> entry : rooms.entrySet()) {
			room = entry.getValue();
			
			if (room.getName().equalsIgnoreCase(name)) {
				break;
			}
		}
		
		return room;
	}
	
	public Map<UUID, Room> getRooms() {
		return rooms;
	}
	
	public void deleteRoom(UUID roomCode) {
		rooms.remove(roomCode);
		
		if (roomIsInBooking(roomCode)) {
			Iterator<Booking> iterator = bookings.iterator();
			
			while (iterator.hasNext()) {
				Booking booking = iterator.next();
				
				if (booking.getRoomCode().equals(roomCode)) {
					iterator.remove();
				}
			}
		}
	}
	
	public void deleteUser(UUID userId) {
		users.remove(userId);
		
		if (userIsInBooking(userId)) {
			Iterator<Booking> iterator = bookings.iterator();
			
			while (iterator.hasNext()) {
				Booking booking = iterator.next();
				
				if (booking.getUserId().equals(userId)) {
					iterator.remove();
				}
			}
		}
	}
	
	public User getUser(String name) {
		User user = null;
		
		for (Map.Entry<UUID, User> entry : users.entrySet()) {
			user = entry.getValue();
			
			if (user.getName().equalsIgnoreCase(name)) {
				break;
			}
		}
		
		return user;
	}
	
	public Map<UUID, User> getUsers() {
		return users;
	}
	
	public List<Booking> getBookings() {
		return bookings;
	}
	
	public boolean userIsInBooking(UUID userId) {
		for (Booking booking : bookings) {
			if (booking.getUserId().equals(userId)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean roomIsInBooking(UUID roomCode) {
		for (Booking booking : bookings) {
			if (booking.getRoomCode().equals(roomCode)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean hasBooking(UUID id) {
		for (Booking booking : bookings) {
			if (booking.getId().equals(id)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void deleteBooking(UUID id) {
		Iterator<Booking> iterator = bookings.iterator();
		
		while (iterator.hasNext()) {
			Booking booking = iterator.next();
			
			if (booking.getId().equals(id)) {
				iterator.remove();
			}
		}
	}
}
