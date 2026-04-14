# Diseño de la Base de Datos Oracle 21c (Clínica Inteligente)

## 1. Tabla: `app_users`
*(Nota: En Oracle se evita llamar a la tabla "users" porque es palabra reservada del sistema)*.
* `id` (NUMBER, PRIMARY KEY, GENERATED ALWAYS AS IDENTITY)
* `email` (VARCHAR2(255), UNIQUE)
* `password` (VARCHAR2(255))
* `role` (VARCHAR2(50), CHECK(role IN ('ADMIN', 'DOCTOR', 'PATIENT')))

## 2. Tabla: `doctor`
* `id` (NUMBER, PRIMARY KEY, FOREIGN KEY referenciando a `app_users(id)`)
* `name` (VARCHAR2(100))
* `specialty` (VARCHAR2(100))
* `available_times` (VARCHAR2(255)) - *(Almacena horarios disponibles)*

## 3. Tabla: `patient`
* `id` (NUMBER, PRIMARY KEY, FOREIGN KEY referenciando a `app_users(id)`)
* `name` (VARCHAR2(100))
* `phone` (VARCHAR2(20))

## 4. Tabla: `appointment` (Citas)
Posee las relaciones Foráneas (FOREIGN KEY) hacia paciente y doctor.
* `id` (NUMBER, PRIMARY KEY, GENERATED ALWAYS AS IDENTITY)
* `doctor_id` (NUMBER, FOREIGN KEY referenciando a `doctor(id)`)
* `patient_id` (NUMBER, FOREIGN KEY referenciando a `patient(id)`)
* `appointment_time` (TIMESTAMP)
* `status` (VARCHAR2(50))
