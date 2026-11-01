# Run these against your own live test system and capture screenshots
MySQL:
```sql
USE smartclinic;
SHOW TABLES;
SELECT * FROM patients LIMIT 5;
CALL GetDailyAppointmentReportByDoctor(CURDATE());
CALL GetDoctorWithMostPatientsByMonth(MONTH(CURDATE()),YEAR(CURDATE()));
CALL GetDoctorWithMostPatientsByYear(YEAR(CURDATE()));
```
cURL (replace TOKEN and ID with an authenticated test account):
```bash
curl -s http://localhost:8080/api/doctors
curl -s -H "Authorization: Bearer TOKEN" http://localhost:8080/api/appointments/patient/1
curl -s "http://localhost:8080/api/doctors/search?specialty=Cardiology"
```
Do not submit fabricated output.