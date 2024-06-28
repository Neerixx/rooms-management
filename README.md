# Tarea 3 - Gestión de salas | JUnit

## DESCRIPCIÓN DEL PROYECTO

Este programa de línea de comandos permite gestionar la reserva de salas de reuniones en una organización con sus respectivos usuarios.

## INSTALACIÓN

### REQUISITOS PREVIOS

- Java 8 o superior.
- Maven.

### INSTRUCCIONES PARA LA EJECUCIÓN

1. Clona el repositorio:
    ```sh
    git clone https://github.com/Neerixx/rooms-management
    cd rooms-management
    ```

2. Compila y empaqueta el programa usando Maven:
    ```sh
    mvn clean package
    ```

3. Ejecuta el programa:
    ```sh
    java -cp target/room-managment-0.0.1-SNAPSHOT.jar Main
    ```

## DISEÑO DEL PROGRAMA

Se realizaron algunos supuestos extras para poder tener el programa funcionando:

- Solo una persona pueda reservar una sala, es decir, si es que ya se encuentra reservada, entonces otra persona no la podrá solicitar a menos que la reserva se elimine.

- La reserva no se podrá modificar, por lo que la sala que se encuentra asociada no cambiará su estado a menos que se elimine.

- Si es que el usuario o la sala que se encuentran en una reserva son eliminados, entonces la reserva también es eliminada.

Por otro lado, para el diseño de la solución se tomaron los atributos anteriormente mencionados, pero se añadió el `id` a la reserva para hacer un mejor manera a la hora de querer borrarlo.

### CLASES PRINCIPALES

1. **ROOM**
    - `code`: Identificador único de la sala.
    - `name`: Nombre de la sala.
    - `location`: Ubicación de la sala dentro de la organización.
    - `status`: Estado de reserva (reservada o disponible).

2. **USER**
    - `id`: Identificador único del usuario.
    - `name`: Nombre del usuario.
    - `department`: Departamento al que pertenece el usuario.
    - `description`: Descripción adicional del usuario.

3. **BOOKING**
    - `id`: Identificador único de la reserva.
    - `roomCode`: Código de la sala a reservar.
    - `userId`: Identificador del usuario que realiza la reserva.
    - `date`: Fecha de la reserva.
    - `details`: Información adicional asociada a la reserva. (opcional)

### FUNCIONALIDADES

- **Gestión de Salas**
    - Crear, listar, actualizar y eliminar salas.
  
- **Gestión de Usuarios**
    - Crear, listar, actualizar y eliminar usuarios.
  
- **Gestión de Reservas**
    - Crear, listar y eliminar reservas.

### PRUEBAS UNITARIAS

Las pruebas unitarias están diseñadas para verificar las principales funcionalides de usuarios y reservas. Las pruebas están implementadas utilizando JUnit y cubren los siguientes escenarios:

- Creación de usuarios, salas y reservas.
- Lectura de datos.
- Actualización de información.

Para ejecutar las pruebas unitarias, utiliza el siguiente comando:

```sh
mvn test
