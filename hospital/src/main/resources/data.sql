-- Insertar Doctores
INSERT INTO doctors (id, name, specialty) VALUES (1, 'Dr. Alejandro Rodriguez', 'Cardiología');
INSERT INTO doctors (id, name, specialty) VALUES (2, 'Dra. Maria Garcia', 'Pediatría');
INSERT INTO doctors (id, name, specialty) VALUES (3, 'Dr. Carlos Ruiz', 'Dermatología');

-- Insertar Disponibilidad
INSERT INTO doctor_availability (doctor_id, available_time) VALUES (1, '2026-04-14 09:00:00');
INSERT INTO doctor_availability (doctor_id, available_time) VALUES (1, '2026-04-14 10:00:00');
INSERT INTO doctor_availability (doctor_id, available_time) VALUES (2, '2026-04-14 11:00:00');

-- Insertar Pacientes
INSERT INTO patients (id, name, email, phone_number) VALUES (1, 'Juan Perez', 'juan@email.com', '+54 11 1234 5678');
INSERT INTO patients (id, name, email, phone_number) VALUES (2, 'Ana Lopez', 'ana@email.com', '+54 11 8765 4321');
INSERT INTO patients (id, name, email, phone_number) VALUES (3, 'Saul Guzman', 'saul@email.com', '+54 11 5555 4444');
INSERT INTO patients (id, name, email, phone_number) VALUES (4, 'Laura Torres', 'laura@email.com', '+54 11 3333 2222');
INSERT INTO patients (id, name, email, phone_number) VALUES (5, 'Pedro Soria', 'pedro@email.com', '+54 11 9999 8888');
