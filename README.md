# Smart Clinic Management System — Capstone
Coursework reference implementation with Spring Boot 3, Java 17, MySQL, MongoDB, role-based UI prototypes, JWT service, Docker Compose, and Maven CI.

## Course submission
- [Admin stories](../../issues/1), [Doctor stories](../../issues/2), [Patient stories](../../issues/3)
- [MySQL design](schema-design.md)
- [Source code](backend/src/main/java/com/smartclinic)
- [Dockerfile](Dockerfile), [CI workflow](.github/workflows/backend-build.yml)
- [SQL schema and stored procedures](database)
- [Capture instructions for required runtime evidence](evidence/COMMANDS.md)

## Development
Run `docker compose up --build` with Docker Compose installed, then test the API and database. Frontend HTML prototypes are in `frontend/` and are **not** currently bundled in Spring Boot static assets. Authentication, authorization, admin CRUD and end-to-end integration require further development. Do not use this coursework prototype with real patients or real medical records.

## Submission integrity
The repository contains project source and scripts, **not verified screenshots or database/API execution outputs**. Screenshots and outputs must be captured from a running application. The project has not been compiled or tested in this environment.

## Security
Use unique JWT signing secrets, production-safe authentication/RBAC, sanitized API responses, clinical privacy controls, encrypted databases, audit trails and secure deployment before real-world use.