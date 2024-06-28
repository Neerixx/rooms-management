package cl.neerixx.junit.objects;

import java.util.UUID;

import cl.neerixx.junit.types.BookingStatus;

public class Room {
	
	private UUID code;
	private String name;
	private String location;
	
	private BookingStatus status;
	
	public Room(String name, String location) {
		code = UUID.randomUUID();
		
		this.setName(name);
		this.setLocation(location);
		
		setStatus(BookingStatus.AVAILABLE);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getCode() {
		return code;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "CODE: " + code + " | " +
				"NOMBRE: " + name + " | " +
				"UBICACION: " + location + " | " +
				"ESTADO: " + status;
	}
}
