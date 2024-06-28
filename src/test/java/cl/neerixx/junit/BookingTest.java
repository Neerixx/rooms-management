package cl.neerixx.junit;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import cl.neerixx.junit.objects.Booking;
import cl.neerixx.junit.objects.Room;
import cl.neerixx.junit.objects.User;
import cl.neerixx.junit.types.BookingStatus;

class BookingTest {
	
	private Booking booking;
	private BookingManager bookingManager;
	
	private static final String BOOKING_DETAIL = "Ocupada durante los jueves";

	@BeforeEach
	protected void setUp() throws Exception {
		// Default booking configuration
		Room room = new Room("Oficina", "Sector A2");
		User user = new User("John Doe", "Comercial", "Sr. ");
		
		Date defaultDate = new Date();
		
		room.setStatus(BookingStatus.RESERVED);
		booking = new Booking(room.getCode(), user.getId(), defaultDate);
		
		// Add default booking
		bookingManager = new BookingManager();
		
		bookingManager.getUsers().put(user.getId(), user);
		bookingManager.getRooms().put(room.getCode(), room);
		
		bookingManager.getBookings().add(booking);
		
		// More rooms and users
		Room roomSafe = new Room("Safe", "Sector B2");
		Room roomDanger = new Room("Danger", "Sector G6");
		
		User additionalUser = new User("Alex Milton", "Informatico", "DevOps");
		
		bookingManager.getRooms().put(roomSafe.getCode(), roomSafe);
		bookingManager.getRooms().put(roomDanger.getCode(), roomDanger);
		
		bookingManager.getUsers().put(additionalUser.getId(), additionalUser);
	}
	
	@Test
	public void testReserveAvailableRoom() {
		// Given
		User user = bookingManager.getUser("Alex Milton");
		Room room = bookingManager.getRoom("Safe");
		
		Date today = new Date();
		
		// When
		assertEquals(bookingManager.getBookings().size(), 1);
		boolean reserve = bookingManager.reserveRoom(room.getCode(), user.getId(), today, "");
		
		// Then
		assertTrue(reserve);
		assertEquals(bookingManager.getBookings().size(), 2);
	}
	
	@Test
	public void testNotAllowReserveSameRoom() {
		// Given
		User user = bookingManager.getUser("Alex Milton");
		Room room = bookingManager.getRoom("Safe");
		
		User additionalUser = new User("Stevan Gray", "Informatico", "Testing");
		Date today = new Date();
		
		// When
		assertEquals(bookingManager.getBookings().size(), 1);
		
		bookingManager.getUsers().put(additionalUser.getId(), additionalUser);
		boolean reserve = bookingManager.reserveRoom(room.getCode(), user.getId(), today, "");
		
		boolean anotherReserve = bookingManager.reserveRoom(room.getCode(), additionalUser.getId(), today, "");
		
		// Then
		assertTrue(reserve);
		assertFalse(anotherReserve);
		assertEquals(bookingManager.getBookings().size(), 2);
	}

	@Test
	public void testGetEmptyDetail() {
		assertEquals(booking.getDetail(), "");
	}
	
	@Test
	public void testGetDetail() {
		// Given
		
		// When
		booking = new Booking(booking.getRoomCode(), booking.getUserId(), booking.getDate(), BOOKING_DETAIL);
		
		// Then
		assertNotEquals(booking.getDetail(), "");
		
		assertEquals(booking.getDetail(), BOOKING_DETAIL);
	}

	@Test
	public void testSetDetail() {
		// Given
		String newDetail = "No se encuentra disponible";
		
		// When
		booking.setDetail(newDetail);
		
		// Then
		assertNotEquals("", newDetail);
		
		assertNotEquals(booking.getDetail(), "");
		assertEquals(booking.getDetail(), newDetail);
	}

	@Test
	public void testGetRoomCode() {
		assertNotNull(booking.getRoomCode());
	}

	@Test
	public void testGetUserId() {
		assertNotNull(booking.getUserId());
	}

}
