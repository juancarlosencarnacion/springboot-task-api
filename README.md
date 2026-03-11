# Task API

REST API para la gestión de tareas desarrollada con **Spring Boot**.
La aplicación implementa operaciones CRUD para administrar tareas y sigue una arquitectura en capas utilizando **Controller, Service, Repository, DTO y Entity**.

Este proyecto fue creado como práctica de desarrollo backend y despliegue en **Amazon EC2** utilizando **Application Load Balancer**.

---

# Features

* Crear tareas
* Obtener todas las tareas
* Obtener una tarea por ID
* Actualizar tareas
* Eliminar tareas
* Arquitectura en capas
* Uso de DTO para transferencia de datos

---

# Tech Stack

Backend:

* **Java**
* **Spring Boot**
* **Spring Data JPA**
* **Hibernate**

Herramientas:

* **Apache Maven**
* **Postman**

Infraestructura:

* **Amazon EC2**
* **Application Load Balancer**

---

# Project Structure

```text
src/main/java/com/example/taskapi

controller
service
repository
dto
entity
```

Descripción:

| Layer      | Responsibility                               |
| ---------- | -------------------------------------------- |
| Controller | Maneja las peticiones HTTP                   |
| Service    | Contiene la lógica de negocio                |
| Repository | Acceso a la base de datos                    |
| DTO        | Transferencia de datos                       |
| Entity     | Representa las entidades de la base de datos |

---

# Task Entity

Ejemplo de una tarea:

```json
{
  "taskId": 1,
  "title": "Study AWS",
  "description": "Review EC2 and Load Balancer",
  "status": "IN_PROGRESS",
  "createdAt": "2026-03-10T23:56:47.786441",
  "updatedAt": "2026-03-10T23:59:47.786441",
}
```

Campos principales:

* taskId
* title
* description
* status
* createdAt
* updatedAt

---

# API Endpoints

| Method | Endpoint    | Description              |
| ------ | ----------- | ------------------------ |
| GET    | /tasks      | Obtener todas las tareas |
| GET    | /tasks/{id} | Obtener una tarea por ID |
| POST   | /tasks      | Crear una nueva tarea    |
| PATCH  | /tasks/{id} | Actualización parcial    |
| DELETE | /tasks/{id} | Eliminar una tarea       |

---

# Run the Project

### Prerequisites

* **Java** 21+
* **Apache Maven**

---

### Clone the repository

```bash
git clone https://github.com/your-username/task-api.git
cd task-api
```

---

### Build the project

```bash
mvn clean package
```

---

### Run the application

```bash
java -jar target/task-api.jar
```

La API estará disponible en:

```
http://localhost:8080
```

---

# Testing

Puedes probar los endpoints usando:

* **Postman**
* **curl**

Ejemplo:

```bash
curl http://localhost:8080/tasks
```

---

# Deployment (AWS)

La aplicación puede desplegarse en instancias **Amazon EC2**.
Para manejar múltiples instancias se utiliza **Application Load Balancer**, que distribuye el tráfico entre servidores.

Arquitectura básica:

```
Client
   |
Load Balancer
   |
EC2 Instance(s)
   |
Spring Boot API
```

---

# Author

Juan Carlos Encarnación  
Backend Developer
