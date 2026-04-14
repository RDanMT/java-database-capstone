# Diseño de la Base de Datos MySQL (Clínica Inteligente)

## 1. Tabla: `app_users`
* `id` (INT, PRIMARY KEY, AUTO_INCREMENT)
* `email` (VARCHAR(255), UNIQUE)
* `password` (VARCHAR(255))
* `role` (ENUM('ADMIN', 'DOCTOR', 'PATIENT'))

## 2. Tabla: `doctor`
* `id` (INT, PRIMARY KEY, FOREIGN KEY referenciando a `app_users(id)`)
* `name` (VARCHAR(100))
* `specialty` (VARCHAR(100))
* `available_times` (VARCHAR(255)) - *(Almacena horarios disponibles)*

## 3. Tabla: `patient`
* `id` (INT, PRIMARY KEY, FOREIGN KEY referenciando a `app_users(id)`)
* `name` (VARCHAR(100))
* `phone` (VARCHAR(20))

## 4. Tabla: `appointment` (Cita)
Esta tabla es el corazón del sistema, relacionando de forma directa a un doctor con un paciente en una fecha específica.
* `id` (INT, PRIMARY KEY, AUTO_INCREMENT) - *Identificador único de la cita.*
* `doctor_id` (INT, FOREIGN KEY referenciando a `doctor(id)`) - *Clave foránea que enlaza la cita con el doctor que atenderá.*
* `patient_id` (INT, FOREIGN KEY referenciando a `patient(id)`) - *Clave foránea que enlaza la cita con el paciente que la reservó.*
* `appointment_time` (DATETIME) - *Fecha y hora exacta acordada para la cita médica.*
* `status` (VARCHAR(50)) - *Estado actual de la cita (ej., CONFIRMED, CANCELED).*

### Diagrama de Relaciones:
- `doctor(id)` y `patient(id)` ambos dependen originalmente de `app_users(id)` para fines de autenticación (super-tipo a sub-tipo).
- La tabla `appointment` es una tabla transaccional (muchos a muchos) que conecta a `doctor` y `patient`. Un `doctor` puede tener múltiples `appointment`s, y un `patient` puede tener múltiples `appointment`s, todas rastreadas en esta cuarta tabla principal.
