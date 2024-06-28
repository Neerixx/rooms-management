package cl.neerixx.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cl.neerixx.junit.objects.User;

class UserTest {
	
	private User user;
	
	private static final String USER_NAME = "John Doe";
	private static final String USER_DEPARTMENT = "Informatica";
	private static final String USER_DESCRIPTION = "Estudiante de 5to";

	@BeforeEach
	protected void setUp() throws Exception {
		user = new User(USER_NAME, USER_DEPARTMENT, USER_DESCRIPTION);
	}
	
	@Test
	public void testUser() {
		assertNotNull(user);
	}
	
	@Test
	public void testCreateUser() {
		// Given
		String name = "Alex Smith";
		String department = "Informatica";
		String description = "Doctorado";
		
		// When
		User newUser = new User(name, department, description);
		
		// Then
		assertNotNull(newUser);
		
		assertNotNull(newUser.getId());
		
		assertEquals(newUser.getName(), name);
		assertEquals(newUser.getDepartment(), department);
		assertEquals(newUser.getDescription(), description);
	}

	@Test
	public void testGetName() {
		assertEquals(user.getName(), USER_NAME);
	}

	@Test
	public void testSetName() {
		// Given
		String newName = "Phillip Collins";
		
		// When
		user.setName(newName);
		
		// Then
		assertNotEquals(USER_NAME, newName);
		
		assertNotEquals(user.getName(), USER_NAME);
		assertEquals(user.getName(), newName);
	}

	@Test
	public void testGetDepartment() {
		assertEquals(user.getDepartment(), USER_DEPARTMENT);
	}

	@Test
	public void testSetDepartment() {
		// Given
		String newDepartment = "Telematica";
		
		// When
		user.setDepartment(newDepartment);
		
		// Then
		assertNotEquals(USER_DEPARTMENT, newDepartment);
		
		assertNotEquals(user.getDepartment(), USER_DEPARTMENT);
		assertEquals(user.getDepartment(), newDepartment);
	}

	@Test
	public void testGetDescription() {
		assertEquals(user.getDescription(), USER_DESCRIPTION);
	}

	@Test
	public void testSetDescription() {
		// Given
		String newDescription = "Doctorado";
		
		// When
		user.setDescription(newDescription);
		
		// Then
		assertNotEquals(USER_DESCRIPTION, newDescription);
		
		assertNotEquals(user.getDescription(), USER_DESCRIPTION);
		assertEquals(user.getDescription(), newDescription);
	}
	
	@Test
	public void testUniqueIdRoom() {
		// Given
		User testUser = new User("Phillip Collins", "Telematica", "Magister");
		
		// When
		UUID id = testUser.getId();
		
		// Then
		assertNotNull(user.getId());
		assertNotNull(id);
		
		assertNotEquals(user.getId().toString(), id.toString());
	}

}
