package cl.neerixx.junit.objects;

import java.util.UUID;

public class User {
	
	private UUID id;
	
	private String name;
	private String department;
	private String description;
	
	public User(String name, String department, String description) {
		id = UUID.randomUUID();
		
		this.setName(name);
		this.setDepartment(department);
		this.setDescription(description);
	}
	
	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + " | " +
				"NOMBRE: " + name + " | " +
				"DEPARTAMENTO: " + department + " | " +
				"DESCRIPCION: " + description;
	}
}
