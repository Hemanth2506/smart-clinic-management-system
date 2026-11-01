USE smartclinic;
DROP PROCEDURE IF EXISTS GetDailyAppointmentReportByDoctor;
DROP PROCEDURE IF EXISTS GetDoctorWithMostPatientsByMonth;
DROP PROCEDURE IF EXISTS GetDoctorWithMostPatientsByYear;
DELIMITER $$
CREATE PROCEDURE GetDailyAppointmentReportByDoctor(IN target_day DATE)
BEGIN
 SELECT d.id AS doctor_id,d.name AS doctor_name,a.id AS appointment_id,
        a.patient_id,a.appointment_time,a.status
 FROM appointments a JOIN doctors d ON d.id=a.doctor_id
 WHERE DATE(a.appointment_time)=target_day
 ORDER BY d.id,a.appointment_time;
END$$
CREATE PROCEDURE GetDoctorWithMostPatientsByMonth(IN input_month INT,IN input_year INT)
BEGIN
 SELECT d.id AS doctor_id,d.name AS doctor_name,COUNT(DISTINCT a.patient_id) AS patients_seen
 FROM doctors d JOIN appointments a ON a.doctor_id=d.id
 WHERE MONTH(a.appointment_time)=input_month AND YEAR(a.appointment_time)=input_year
 GROUP BY d.id,d.name ORDER BY patients_seen DESC, d.id LIMIT 1;
END$$
CREATE PROCEDURE GetDoctorWithMostPatientsByYear(IN input_year INT)
BEGIN
 SELECT d.id AS doctor_id,d.name AS doctor_name,COUNT(DISTINCT a.patient_id) AS patients_seen
 FROM doctors d JOIN appointments a ON a.doctor_id=d.id
 WHERE YEAR(a.appointment_time)=input_year
 GROUP BY d.id,d.name ORDER BY patients_seen DESC, d.id LIMIT 1;
END$$
DELIMITER ;
