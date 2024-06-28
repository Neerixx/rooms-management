package cl.neerixx.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cl.neerixx.junit.objects.Room;
import cl.neerixx.junit.types.BookingStatus;

class RoomTest {
	
	private Room room;
	
	private static final String ROOM_NAME = "Sala de estar";
	private static final String ROOM_LOCATION = "UTFSM";

	@BeforeEach
	protected void setUp() throws Exception {
		room = new Room(ROOM_NAME, ROOM_LOCATION);
	}
	
	@Test
	public void testRoom() {
		assertNotNull(room);
	}

	@Test
	public void testGetName() {
		assertEquals(room.getName(), ROOM_NAME);
	}

	@Test
	public void testGetCode() {
		assertNotNull(room.getCode());
	}

	@Test
	public void testGetLocation() {
		assertEquals(room.getLocation(), ROOM_LOCATION);
	}

	@Test
	public void testGetDefaultStatus() {
		assertEquals(room.getStatus(), BookingStatus.AVAILABLE);
	}
	
	@Test
	public void testCreateRoom() {
		// Given
		String name = "Sala de ocio";
		String location = "Casa central";
		
		// When
		Room newRoom = new Room(name, location);
		
		// Then
		assertNotNull(newRoom);
		
		assertEquals(newRoom.getName(), name);
		assertEquals(newRoom.getLocation(), location);
		
		assertEquals(newRoom.getStatus(), BookingStatus.AVAILABLE);
	}
	
	@Test
	public void testChangeRoomName() {
		// Given
		String newName = "Casino";
		
		// When
		room.setName(newName);
		
		// Then
		assertNotEquals(ROOM_NAME, newName);
		
		assertNotEquals(room.getName(), ROOM_NAME);
		assertEquals(room.getName(), newName);
	}
	
	@Test
	public void testChangeRoomLocation() {
		// Given
		String newLocation = "Valparaiso";
		
		// When
		room.setLocation(newLocation);
		
		// Then
		assertNotEquals(ROOM_LOCATION, newLocation);
		
		assertNotEquals(room.getLocation(), ROOM_LOCATION);
		assertEquals(room.getLocation(), newLocation);
	}
	
	@Test
	public void testChangeRoomStatus() {
		// Given
		BookingStatus reserved = BookingStatus.RESERVED;
		
		// When
		room.setStatus(reserved);
		
		// Then
		assertNotEquals(BookingStatus.AVAILABLE, reserved);
		
		assertNotEquals(room.getStatus(), BookingStatus.AVAILABLE);
		assertEquals(room.getStatus(), reserved);
	}
	
	@Test
	public void testUniqueCodeRoom() {
		// Given
		Room testRoom = new Room("Prueba", "UTFSM");
		
		// When
		UUID code = testRoom.getCode();
		
		// Then
		assertNotNull(room.getCode());
		assertNotNull(code);
		
		assertNotEquals(room.getCode().toString(), code.toString());
	}
}
