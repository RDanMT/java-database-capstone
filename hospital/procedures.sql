-- Ejecuta esto en tu cliente de Oracle
CREATE
OR REPLACE PROCEDURE GetDailyAppointmentReportByDoctor (
    p_doctor_id IN NUMBER,
    p_date IN DATE,
    p_report OUT SYS_REFCURSOR
) AS BEGIN OPEN p_report FOR
SELECT
    a.id as appointment_id,
    p.name AS patient_name,
    a.appointment_time,
    a.status
FROM
    appointment a
    JOIN patient p ON a.patient_id = p.id
WHERE
    a.doctor_id = p_doctor_id
    AND TRUNC (a.appointment_time) = TRUNC (p_date);

END;

CREATE OR REPLACE PROCEDURE GetDoctorWithMostPatientsByMonth(
    p_month IN NUMBER,
    p_year IN NUMBER,
    p_result OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_result FOR
    SELECT d.name as doctor_name, COUNT(DISTINCT a.patient_id) as total_patients
    FROM appointment a
    JOIN doctor d ON a.doctor_id = d.id
    WHERE EXTRACT(MONTH FROM a.appointment_time) = p_month
      AND EXTRACT(YEAR FROM a.appointment_time) = p_year
    GROUP BY d.id, d.name
    ORDER BY total_patients DESC
    FETCH FIRST 1 ROWS ONLY; -- Devuelve solo al ganador
END;
/

CREATE OR REPLACE PROCEDURE GetDoctorWithMostPatientsByYear(
    p_year IN NUMBER,
    p_result OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_result FOR
    SELECT d.name as doctor_name, COUNT(DISTINCT a.patient_id) as total_patients
    FROM appointment a
    JOIN doctor d ON a.doctor_id = d.id
    WHERE EXTRACT(YEAR FROM a.appointment_time) = p_year
    GROUP BY d.id, d.name
    ORDER BY total_patients DESC
    FETCH FIRST 1 ROWS ONLY;
END;
/

VARIABLE res REFCURSOR;
EXEC GetDailyAppointmentReportByDoctor(1, TO_DATE('2026-04-14', 'YYYY-MM-DD'), :res);
PRINT res;
