package cl.neerixx.junit.objects;

import java.util.Date;
import java.util.UUID;

public class Booking {
	
	private UUID id;
	
	private UUID roomCode;
	private UUID userId;
	
	private Date date;
	
	private String detail;
	
	public Booking(UUID roomCode, UUID userId, Date date, String detail) {
		this.id = UUID.randomUUID();
		
		this.roomCode = roomCode;
		this.userId = userId;
		this.setDate(date);
		this.setDetail(detail);
	}
	
	public Booking(UUID roomCode, UUID userId, Date date) {
		this(roomCode, userId, date, "");
	}
	
	public UUID getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public UUID getRoomCode() {
		return roomCode;
	}

	public UUID getUserId() {
		return userId;
	}
	
	@Override
	public String toString() {
		return "FECHA: " + date.toString() + " | " +
				"USUARIO: " + userId + " | " +
				"SALA: " + roomCode + " | " +
				"DETALLE: " + detail;
	}
}
