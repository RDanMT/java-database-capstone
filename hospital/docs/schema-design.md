# Diseño de Base de Datos (MySQL)

- **doctors**: 
  - `id` (BIGINT, PK, AUTO_INCREMENT)
  - `name` (VARCHAR)
  - `specialty` (VARCHAR)
- **patients**: 
  - `id` (BIGINT, PK, AUTO_INCREMENT)
  - `name` (VARCHAR)
  - `email` (VARCHAR, UNIQUE)
  - `phone_number` (VARCHAR)
- **appointments**: 
  - `id` (BIGINT, PK, AUTO_INCREMENT)
  - `doctor_id` (BIGINT, FK -> doctors.id)
  - `patient_id` (BIGINT, FK -> patients.id)
  - `appointment_time` (DATETIME)
- **prescriptions**: 
  - `id` (BIGINT, PK, AUTO_INCREMENT)
  - `appointment_id` (BIGINT, FK -> appointments.id)
  - `description` (TEXT)