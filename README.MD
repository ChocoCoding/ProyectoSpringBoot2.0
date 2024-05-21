# Microservicio Usuarios

Este microservicio se encarga de gestionar las operaciones relacionadas con los usuarios, como creación, actualización, eliminación y validación. A continuación se describen los endpoints disponibles y cómo utilizarlos.

## Endpoints

### 1. Crear Usuario

**Descripción:** Este endpoint permite crear un nuevo usuario.

- **URL:** `/usuarios/registrar`
- **Método HTTP:** `POST`
- **Cuerpo de la solicitud:** `CrearUsuarioDTO`
- **Ejemplo solicitud POSTMAN:**
  ```json
  {
    "nombre": "paco",
    "correo_electronico": "paco@example.com",
    "direccion": "Direccion 1",
    "contrasena": "p1"
  }


### 2. Actualizar Usuario

**Descripción:** Este endpoint permite actualizar la información de un usuario existente.

- **URL:** `/usuarios/registrar`
- **Método HTTP:** `PUT`
- **Cuerpo de la solicitud:** `ActualizarUsuarioDTO`
- **Ejemplo solicitud POSTMAN:**
    ```json
  {
    "id":1,
    "nombre": "u1",
    "correo_electronico": "paco@example.com",
    "direccion": "Direccion 1",
    "contrasena": "p1"
   }
    
### 3.  Eliminar Usuario

**Descripción:** Este endpoint permite eliminar un usuario existente.

- **URL:** `/usuarios`
- **Método HTTP:** `DELETE`
- **Cuerpo de la solicitud:** `EliminarUsuarioDTO`
- **Ejemplo solicitud POSTMAN:**
    ```json
  {
    "nombre": "u1",
    "contrasena": "p1"
  }

### 4.  Validar Usuario

**Descripción:** Este endpoint permite eliminar un usuario existente.

- **URL:** `/usuarios/validar`
- **Método HTTP:** `POST`
- **Cuerpo de la solicitud:** `ValidarUsuarioDTO`
- **Ejemplo solicitud POSTMAN:**
    ```json
  {
    "nombre": "María López",
    "contrasena": "secreto456"
  }

### 5.  Obtener Información de Usuario por ID

**Descripción:** Este endpoint permite validar las credenciales de un usuario.

- **URL:** `/usuarios/info/id/{id}`
- **Método HTTP:** `GET`
- **Parámetro de ruta:** `id (int)`
- **Cuerpo de la solicitud:** `ValidarUsuarioDTO`
- **Ejemplo solicitud POSTMAN:**
    ```json
      /usuarios/info/id/2

### 6.  Obtener Información de Usuario por Nombre

**Descripción:** Este endpoint devuelve el ID del usuario basado en su nombre.

- **URL:** `/usuarios/info/nombre/{nombre}`
- **Método HTTP:** `GET`
- **Parámetro de ruta:** `nombre (string)`
- **Ejemplo solicitud POSTMAN:**
    ```json
      /usuarios/info/nombre/María López


### 7.  Obtener Información de Usuario por ID

**Descripción:** Este endpoint devuelve el nombre del usuario basado en su ID.

- **URL:** `/usuarios/checkIfExist/{id}`
- **Método HTTP:** `GET`
- **Parámetro de ruta:** `id (int)`
- **Ejemplo solicitud POSTMAN:**
    ```json
      /usuarios/checkIfExist/2

# Microservicio Reservas

Este servicio será el encargado de llevar el registro de los datos de los hoteles, las habitaciones que cada hotel tiene y las reservas hechas por los usuarios.
## Endpoints

### 1. Crear Habitación

**Descripción:** Se encargará de dar de alta una nueva habitación en un hotel.


- **URL:** `/reservas/habitacion`
- **Método HTTP:** `POST`
- **Cuerpo de la solicitud:** `CrearHabitacionDTO`
- **Ejemplo solicitud POSTMAN:**
  ```json
  {
  "nombre": "María López",
  "contrasena": "secreto456",
  "numero_habitacion":"111",
  "tipo": "individual",
  "precio": 5.4,
  "hotel_id": 2
  }

### 2. Actualizar Habitación

**Descripción:** Este endpoint permite actualizar la información de una habitación existente.

- **URL:** `/reservas/habitacion`
- **Método HTTP:** `PATCH`
- **Cuerpo de la solicitud:** `ActualizarHabitacionDTO`
- **Ejemplo solicitud POSTMAN:**
  ```json
  {
  "nombre": "María López",
  "contrasena": "secreto456",
  "id": 1,
  "numero_habitacion":"111",
  "tipo": "individual",
  "precio": 5.4,
  "hotel_id": 2,
  "disponible":false
  }

### 3. Eliminar Habitación

**Descripción:** Este endpoint permite eliminar una habitación existente.

- **URL:** `/reservas/habitacion/{id}`
- **Método HTTP:** `DELETE`
- **Parámetro de ruta:** `id (int)`
- **Cuerpo de la solicitud:** `UsuarioContrasenhaDTO`
- **Ejemplo solicitud POSTMAN:**
  ```json
  {
  "nombre": "María López",
  "contrasena": "secreto456"
  }

### 4. Crear Hotel

**Descripción:** Este endpoint permite crear un nuevo hotel.

- **URL:** `/reservas/hotel`
- **Método HTTP:** `POST`
- **Cuerpo de la solicitud:** `CrearHotelDTO`
- **Ejemplo solicitud POSTMAN:**
  ```json
  {
  "nombreUsu": "María Ldsdópez",
  "contrasena": "secreto456",
  "nombre":"HotelD",
  "direccion":"Avenida de mi casa"
  }

### 5. Actualizar Hotel
**Descripción:** Este endpoint permite actualizar la información de un hotel existente.

- **URL:** `/reservas/hotel`
- **Método HTTP:** `PATCH`
- **Cuerpo de la solicitud:** `ActualizarHotelDTO`
- **Ejemplo solicitud POSTMAN:**
  ```json
  {
  "id":1,
  "nombreUsu": "María López",
  "contrasena": "secreto456",
  "nombre":"Hotel E",
  "direccion":"Avenida de mi casa"
  }

### 6. Eliminar Hotel
**Descripción:** Este endpoint permite eliminar un hotel existente.

- **URL:** `/reservas/hotel/{id}`
- **Método HTTP:** `DELETE`
- **Parámetro de ruta:** `id (int)`
- **Cuerpo de la solicitud:** `UsuarioContrasenhaDTO`
- **Ejemplo solicitud POSTMAN:**
  ```json
  {
  "nombre": "María López",
  "contrasena": "secreto456"
  }

### 7. Obtener ID a partir del Nombre del Hotel
**Descripción:** Este endpoint permite obtener el ID de un hotel a partir de su nombre.

- **URL:** `/reservas/hotel/id/{nombre}`
- **Método HTTP:** `POST`
- **Parámetro de ruta:** `nombre (string)`
- **Cuerpo de la solicitud:** `UsuarioContrasenhaDTO`
- **Ejemplo solicitud POSTMAN:**
  ```json
  {
  "nombre": "María López",
  "contrasena": "secreto456"
  }

### 8. Obtener Nombre a partir del ID del Hotel
**Descripción:** Este endpoint permite obtener el nombre de un hotel a partir de su ID.

- **URL:** `/reservas/hotel/nombre/{idHotel}`
- **Método HTTP:** `POST`
- **Parámetro de ruta:** `idHotel (int)`
- **Cuerpo de la solicitud:** `UsuarioContrasenhaDTO`
- **Ejemplo solicitud POSTMAN:**
  ```json
  {
  "nombre": "María López",
  "contrasena": "secreto456"
  }

### 9. Crear Reserva

**Descripción:** Este endpoint permite crear una nueva reserva.

- **URL:** `/reservas`
- **Método HTTP:** `POST`
- **Cuerpo de la solicitud:** `CrearReserva`
  ```json
  {
  "nombre": "María López",
  "contrasena": "secreto456",
  "fecha_inicio": "1999-11-23",
  "fecha_fin": "2000-12-24",
  "habitacion_id": 1
  }

### 10. Cambiar Estado de Reserva

**Descripción:**  Este endpoint permite cambiar el estado de una reserva existente.

- **URL:** `/reservas`
- **Método HTTP:** `PATCH`
- **Cuerpo de la solicitud:** `CambiarEstadoDTO`
  ```json
  {
  "nombre": "María López",
  "contrasena": "secreto456",
  "reserva_id": "1",
  "estado": "Cancelada"
  }

### 11. Listar Reservas de Usuario

**Descripción:**  Este endpoint permite listar todas las reservas de un usuario.

- **URL:** `/reservas`
- **Método HTTP:** `GET`
- **Cuerpo de la solicitud:** `UsuarioContrasenhaDTO`
  ```json
  {
  "nombre": "María López",
  "contrasena": "secreto456"
  }

### 12. Listar Reservas Según Estado
**Descripción:**  Este endpoint permite listar todas las reservas según su estado.

- **URL:** `/reservas/{estado}`
- **Método HTTP:** `GET`
- **Parámetro de ruta:** `estado (string)`
- **Cuerpo de la solicitud:** `UsuarioContrasenhaDTO`
  ```json
  {
  "nombre": "María López",
  "contrasena": "secreto456"
  }

### 13. Verificar Reserva
**Descripción:**  Este endpoint permite verificar si una reserva existe.

- **URL:** `/reservas/check/{idUsuario}-{idHotel}-{idReserva}`
- **Método HTTP:** `GET`
- **Parámetro de ruta:** `idUsuario(int)`,`idHotel(int)`,`idReserva (int)` 
- **Cuerpo de la solicitud:** `UsuarioContrasenhaDTO`


# Microservicio de  Comentarios
Este microservicio maneja las operaciones relacionadas con los comentarios, como crear, listar y eliminar.
También incluye la validación de usuario antes de realizar ciertas operaciones.

### 1. Crear Comentario

**Descripción:** Este endpoint permite crear un nuevo comentario.

- **Método:** `Mutation`
- **Ejemplo de Solicitud:**
  ```graphql
  mutation {
    crearComentario(crearComentarioDTO: {
      nombre: "María López",
      contrasena: "secreto456",
      nombreHotel: "Hotel A",
      reservaId: 2,
      puntuacion: 4.5,
      comentario: "Excelente servicio"
    }) {
      nombre
      contrasena
      nombreHotel
      reservaId
      puntuacion
      comentario
    }
  }

### 2. Eliminar Comentarios

**Descripción:** Este endpoint permite eliminar todos los comentarios.

- **Método:** `Mutation`
- **Ejemplo de Solicitud:**
  ```graphql
  mutation {
  eliminarComentarios
  }

### 3. Eliminar Comentario de Usuario

**Descripción:** Este endpoint permite eliminar un comentario específico de un usuario.

- **Método:** `Mutation`
- **Ejemplo de Solicitud:**
  ```graphql
  mutation {
  eliminarComentarioDeUsuario(eliminarComentarioUsuarioDTO: {
    nombre: "María López",
    contrasena: "secreto456",
    _id: "664cd4978c9ca832b23233b2"
  })
  }

### 4. Listar Comentarios de Hotel

**Descripción:** Este endpoint permite listar todos los comentarios de un hotel.

- **Método:** `Query`
- **Ejemplo de Solicitud:**
  ```graphql
   query {
  listarComentariosHotel(listarComentariosHotelDTO: {
    nombre: "María López",
    contrasena: "secreto456",
    nombreHotel: "Hotel C"
  }) {
    nombreHotel
    reservaId
    puntuacion
    comentario
  }
  }

### 5. Listar Comentarios de Usuario

**Descripción:** Este endpoint permite listar todos los comentarios de un usuario.

- **Método:** `Query`
- **Ejemplo de Solicitud:**
  ```graphql
  query {
  listarComentariosUsuario(usuarioContrasenhaDTO: {
    nombre: "María López",
    contrasena: "secreto456"
  }) {
    nombreHotel
    reservaId
    puntuacion
    comentario
  }
  }

### 6. Mostrar Comentario de Usuario por Reserva

**Descripción:** Este endpoint permite mostrar un comentario de un usuario para una reserva específica.

- **Método:** `Query`
- **Ejemplo de Solicitud:**
  ```graphql
  query {
  mostrarComentarioUsuarioReserva(mostrarComentarioReservaDTO: {
    nombre: "Juan Pérez",
    contrasena: "clave123",
    reservaId: 4
  }) {
    nombreHotel
    reservaId
    puntuacion
    comentario
  }
  }

### 7. Puntuación Media del Hotel

**Descripción:** Este endpoint permite obtener la puntuación media de un hotel.

- **Método:** `Query`
- **Ejemplo de Solicitud:**
  ```graphql
  query {
  puntuacionMediaHotel(obtenerHotelDTO: {
    nombreHotel: "Hotel A",
    nombre: "Carlos Rodriguez",
    contrasena: "password789"
  })
  }

### 8. Puntuación Media de un Usuario

**Descripción:** Este endpoint permite obtener las puntuaciones medias de un usuario.

- **Método:** `Query`
- **Ejemplo de Solicitud:**
  ```graphql
  query {
  puntuacionesMediasUsuario(usuarioContrasenhaDTO: {
    nombre: "Carlos Rodriguez",
    contrasena: "password789"
  })
    }
  
