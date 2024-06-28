package cl.neerixx.junit;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.Date;

import cl.neerixx.junit.objects.Room;
import cl.neerixx.junit.objects.User;
import cl.neerixx.junit.objects.Booking;

public class Main {
	
	private enum MenuState {
		GENERAL,
		USERS,
		ROOMS,
		BOOKINGS
	}
	
	private static final Scanner scanner = new Scanner(System.in);
	private static BookingManager bookingManager;
	
	private static MenuState menuState = MenuState.GENERAL;
	
	public static void main(String[] args) {
		bookingManager = new BookingManager();
		
		while (menuState == MenuState.GENERAL) {
			System.out.println("MENU DE OPCIONES:");
            System.out.println("1. Gestion de usuarios");
            System.out.println("2. Gestion de salas");
            System.out.println("3. Gestion de reservas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            int option = scanner.nextInt();
            scanner.nextLine();
            
            switch (option) {
	            case 1:
	            	menuState = MenuState.USERS;
	            	usersOptions();
	            	break;
	            case 2:
	            	menuState = MenuState.ROOMS;
	            	roomsOptions();
	            	break;
	            case 3:
	            	menuState = MenuState.BOOKINGS;
	            	bookingsOptions();
	            	break;
	            case 4:
	            	System.exit(0);
	            default:
	            	System.out.println("Opción no válida. Inténtelo de nuevo.");
	            	break;
            }
		}
	}
	
	public static void usersOptions() {
		while (menuState == MenuState.USERS) {
			System.out.println("GESTION DE USUARIOS:");
	        System.out.println("1. Agregar usuario");
	        System.out.println("2. Listar usuarios");
	        System.out.println("3. Actualizar usuario");
	        System.out.println("4. Eliminar usuario");
	        System.out.println("5. Volver");
	        System.out.print("Seleccione una opción: ");
	        
	        int option = scanner.nextInt();
	        scanner.nextLine();

	        switch (option) {
	            case 1:
	            	System.out.print("Ingrese el nombre del usuario: ");
	                String name = scanner.nextLine();
	                System.out.print("Ingrese el departamento del usuario: ");
	                String department = scanner.nextLine();
	                System.out.print("Ingrese la descripción del usuario: ");
	                String description = scanner.nextLine();

	                User user = new User(name, department, description);
	                bookingManager.getUsers().put(user.getId(), user);
	                System.out.println("Usuario agregado exitosamente.");
	                
	                break;
	            case 2:
	                if (bookingManager.getUsers().size() == 0) {
	                	System.out.println("No hay usuarios registrados");
	                	break;
	                }
	                
	                for (Map.Entry<UUID, User> entry : bookingManager.getUsers().entrySet()) {
	                	System.out.println(entry.getValue());
	                }
	                break;
	            case 3:
	            	System.out.print("Ingrese el ID del usuario a actualizar: ");
	            	UUID id = null;
	            	
	            	try {
	            		id = UUID.fromString(scanner.nextLine());
	            	} catch (Exception e) {
	            		System.out.println("El ID no es válido");
	            		break;
	            	}
	            		
	                if (!bookingManager.getUsers().containsKey(id)) {
	                    System.out.println("Usuario no encontrado.");
	                    break;
	                }
	                User currentUser = bookingManager.getUsers().get(id);

	                System.out.print("Ingrese el nuevo nombre del usuario: ");
	                currentUser.setName(scanner.nextLine());
	                System.out.print("Ingrese el nuevo departamento del usuario: ");
	                currentUser.setDepartment(scanner.nextLine());
	                System.out.print("Ingrese la nueva descripción del usuario: ");
	                currentUser.setDepartment(scanner.nextLine());

	                System.out.println("Usuario actualizado exitosamente.");
	                break;
	            case 4:
	            	System.out.print("Ingrese el ID del usuario a eliminar: ");
	                UUID idRemove = null;
	                
	                try {
	            		idRemove = UUID.fromString(scanner.nextLine());
	            	} catch (Exception e) {
	            		System.out.println("El ID no es válido");
	            		break;
	            	}
	                
	                if (!bookingManager.getUsers().containsKey(idRemove)) {
	                    System.out.println("Usuario no encontrado.");
	                    break;
	                }
	                
	                bookingManager.deleteUser(idRemove);
	                System.out.println("Usuario eliminado exitosamente.");
	                break;
	            case 5:
	            	menuState = MenuState.GENERAL;
	            	break;
	            default:
	                System.out.println("Opción no válida. Inténtelo de nuevo.");
	                break;
	        }
		}
	}
	
	public static void roomsOptions() {
		while (menuState == MenuState.ROOMS) {
			System.out.println("GESTION DE SALAS:");
	        System.out.println("1. Agregar sala");
	        System.out.println("2. Listar salas");
	        System.out.println("3. Actualizar sala");
	        System.out.println("4. Eliminar sala");
	        System.out.println("5. Volver");
	        System.out.print("Seleccione una opción: ");
	        
	        int option = scanner.nextInt();
	        scanner.nextLine();

	        switch (option) {
	            case 1:
	            	System.out.print("Ingrese el nombre de la sala: ");
	                String name = scanner.nextLine();
	                System.out.print("Ingrese la ubicacion de la sala: ");
	                String location = scanner.nextLine();

	                Room room = new Room(name, location);
	                bookingManager.getRooms().put(room.getCode(), room);
	                System.out.println("Sala agregada exitosamente.");
	                
	                break;
	            case 2:
	                if (bookingManager.getRooms().size() == 0) {
	                	System.out.println("No hay salas registradas.");
	                	break;
	                }
	                
	                for (Map.Entry<UUID, Room> entry : bookingManager.getRooms().entrySet()) {
	                	System.out.println(entry.getValue());
	                }
	                break;
	            case 3:
	            	System.out.print("Ingrese el ID de la sala a actualizar: ");
	            	UUID id = null;
	            	
	            	try {
	            		id = UUID.fromString(scanner.nextLine());
	            	} catch (Exception e) {
	            		System.out.println("El ID no es válido");
	            		break;
	            	}
	            		
	                if (!bookingManager.getRooms().containsKey(id)) {
	                    System.out.println("Sala no encontrada.");
	                    break;
	                }
	                Room currentRoom = bookingManager.getRooms().get(id);

	                System.out.print("Ingrese el nuevo nombre de la sala: ");
	                currentRoom.setName(scanner.nextLine());
	                System.out.print("Ingrese la nueva ubicacion de la sala: ");
	                currentRoom.setLocation(scanner.nextLine());

	                System.out.println("Sala actualizado exitosamente.");
	                break;
	            case 4:
	            	System.out.print("Ingrese el ID de la sala a eliminar: ");
	                UUID idRemove = null;
	                
	                try {
	            		idRemove = UUID.fromString(scanner.nextLine());
	            	} catch (Exception e) {
	            		System.out.println("El ID no es válido");
	            		break;
	            	}
	                
	                if (!bookingManager.getRooms().containsKey(idRemove)) {
	                    System.out.println("Sala no encontrada.");
	                    break;
	                }
	                
	                bookingManager.deleteRoom(idRemove);
	                System.out.println("Sala eliminada exitosamente.");
	                break;
	            case 5:
	            	menuState = MenuState.GENERAL;
	            	break;
	            default:
	                System.out.println("Opción no válida. Inténtelo de nuevo.");
	                break;
	        }
		}
	}
	
	public static void bookingsOptions() {
		while (menuState == MenuState.BOOKINGS) {
			System.out.println("GESTION DE SALAS:");
	        System.out.println("1. Crear reserva");
	        System.out.println("2. Listar reservas");
	        System.out.println("3. Eliminar reserva");
	        System.out.println("4. Volver");
	        System.out.print("Seleccione una opción: ");
	        
	        int option = scanner.nextInt();
	        scanner.nextLine();

	        switch (option) {
	            case 1:
	            	System.out.print("Ingrese el ID del usuario que reserva: ");
	                UUID userId = null;
	                
	                try {
	                	userId = UUID.fromString(scanner.nextLine());;
	                } catch (Exception e) {
	                	System.out.println("ID no válida.");
	                	break;
	                }
	                
	                if (!bookingManager.getUsers().containsKey(userId)) {
	                    System.out.println("Usuario no encontrado.");
	                    break;
	                }

	                System.out.print("Ingrese el código de la sala a reservar: ");
	                UUID roomCode = null;
	                
	                try {
	                	roomCode = UUID.fromString(scanner.nextLine());
	                } catch (Exception e) {
	                	System.out.println("ID no válida.");
	                	break;
	                }
	                
	                if (!bookingManager.getRooms().containsKey(roomCode)) {
	                    System.out.println("Sala no encontrada.");
	                    break;
	                }

	                System.out.print("Ingrese la fecha de la reserva (YYYY-MM-DD): ");
	                LocalDate localDate = null;
	                
	                try {
	                	localDate = LocalDate.parse(scanner.nextLine());
	                } catch (Exception e) {
	                	System.out.println("El formato de fecha es inválido.");
	                	break;
	                }
	                
	                Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

	                System.out.print("Ingrese detalles de la reserva (pulsa enter para vacío): ");
	                String details = scanner.nextLine();
	                
	                boolean reserve = bookingManager.reserveRoom(roomCode, userId, date, details);
	                
	                if (!reserve) {
	                	System.out.println("Está sala ya se encuentra reservada.");
	                } else {
	                	System.out.println("Reserva registrada exitosamente");
	                }
	                
	                break;
	            case 2:
	                if (bookingManager.getBookings().size() == 0) {
	                	System.out.println("No hay reservas registradas.");
	                	break;
	                }
	                
	                for (Booking booking : bookingManager.getBookings()) {
	                	System.out.println(booking);
	                }
	                break;
	            case 3:
	            	System.out.print("Ingrese el ID de la reserva a eliminar: ");
	                UUID idRemove = null;
	                
	                try {
	            		idRemove = UUID.fromString(scanner.nextLine());
	            	} catch (Exception e) {
	            		System.out.println("El ID no es válido");
	            		break;
	            	}
	                
	                if (!bookingManager.hasBooking(idRemove)) {
	                    System.out.println("Reserva no encontrada.");
	                    break;
	                }
	                
	                bookingManager.deleteBooking(idRemove);
	                System.out.println("Reserva eliminada exitosamente.");
	                break;
	            case 4:
	            	menuState = MenuState.GENERAL;
	            	break;
	            default:
	                System.out.println("Opción no válida. Inténtelo de nuevo.");
	                break;
	        }
		}
	}
}
