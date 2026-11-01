USE smartclinic;
DELIMITER $$
CREATE PROCEDURE GetDailyAppointmentReportByDoctor(IN target_day DATE)
BEGIN SELECT d.id,d.name,COUNT(a.id) AS appointments FROM doctors d LEFT JOIN appointments a ON a.doctor_id=d.id AND DATE(a.appointment_time)=target_day GROUP BY d.id,d.name; END$$
CREATE PROCEDURE GetDoctorWithMostPatientsByMonth(IN input_month INT,IN input_year INT)
BEGIN SELECT d.id,d.name,COUNT(DISTINCT a.patient_id) AS total_patients FROM doctors d JOIN appointments a ON a.doctor_id=d.id WHERE MONTH(a.appointment_time)=input_month AND YEAR(a.appointment_time)=input_year GROUP BY d.id,d.name ORDER BY total_patients DESC LIMIT 1; END$$
CREATE PROCEDURE GetDoctorWithMostPatientsByYear(IN input_year INT)
BEGIN SELECT d.id,d.name,COUNT(DISTINCT a.patient_id) AS total_patients FROM doctors d JOIN appointments a ON a.doctor_id=d.id WHERE YEAR(a.appointment_time)=input_year GROUP BY d.id,d.name ORDER BY total_patients DESC LIMIT 1; END$$
DELIMITER ;