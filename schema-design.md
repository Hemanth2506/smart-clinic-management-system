# Smart Clinic Data Design
MySQL relational tables:
- admins(id BIGINT PK, username VARCHAR(100) UNIQUE, email VARCHAR(255) UNIQUE, password VARCHAR(255))
- doctors(id BIGINT PK, name VARCHAR(120), email VARCHAR(255) UNIQUE, password VARCHAR(255), specialty VARCHAR(100), available_times JSON)
- patients(id BIGINT PK, name VARCHAR(120), email VARCHAR(255) UNIQUE, phone VARCHAR(20), password VARCHAR(255))
- appointments(id BIGINT PK, doctor_id BIGINT FK -> doctors.id, patient_id BIGINT FK -> patients.id, appointment_time DATETIME, status VARCHAR(30))
- doctor_availability(id BIGINT PK, doctor_id BIGINT FK -> doctors.id, available_date DATE, start_time TIME, end_time TIME)
Foreign keys enforce referential integrity. Prescription documents are stored in MongoDB as {_id,doctorId,patientId,appointmentId,medications,notes,createdAt}. Avoid storing plain-text credentials.