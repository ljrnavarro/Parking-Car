# 🚗 ParkCar API

![Java](https://img.shields.io/badge/Java-17-blue) ![Spring
Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-Database-orange)
![Docker](https://img.shields.io/badge/Docker-Container-blue)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

API REST para **gestão de estacionamento**, desenvolvida em **Java com Spring Boot**, utilizando **MySQL em container Docker**, com versionamento de banco via Flyway e documentação automática com Swagger.

O sistema controla veículos, garagens e o registro de entrada, aplicando regras de negócio como lotação máxima e impedindo entradas duplicadas.

---

## 🧩 Funcionalidades

- Cadastro de veículos
- Cadastro de garagens
- Registro de entrada de veículos
- Controle de vagas por garagem
- Consulta de veículos estacionados
- Validações de negócio:
  - Veículo já estacionado
  - Garagem lotada
  - Veículo ou garagem inexistentes
- Tratamento global de exceções
- Versionamento de banco de dados com Flyway
- Documentação automática via Swagger (OpenAPI)

---

## 🏗️ Estrutura do Projeto

```text
src
└── main
    ├── java
    │   └── com.parkcar
    │       ├── config
    │       ├── controller
    │       ├── domain
    │       ├── dto
    │       ├── exception
    │       ├── repository
    │       └── service
    └── resources
        ├── db.migration
        ├── application.properties
```

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Web MVC
- Spring Security
- Flyway
- MySQL
- Docker / Docker Compose
- Lombok
- Swagger (SpringDoc OpenAPI)

---

## 🐳 Banco de Dados (MySQL via Docker)

O banco de dados MySQL roda em um **container Docker**, garantindo facilidade de setup e consistência entre ambientes.

### Exemplo de docker-compose.yml

```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    container_name: parkcar-mysql
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: parkcar
      MYSQL_USER: parkcar
      MYSQL_PASSWORD: parkcar
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql

volumes:
  mysql_data:
```

### Subir o banco

```bash
docker-compose up -d
```

---

## 🗄️ Versionamento de Banco (Flyway)

As migrations estão localizadas em:

```text
src/main/resources/db.migration
```

- V1__create_parking_tables.sql
- V2__insert_dados_iniciais.sql

As migrations são executadas automaticamente ao iniciar a aplicação.

---

## ▶️ Executando o Projeto

### Pré-requisitos

- Java 17
- Maven
- Docker e Docker Compose

### Passos

```bash
docker-compose up -d
mvn spring-boot:run
```

---

## 📘 Documentação da API (Swagger)

Após iniciar a aplicação:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## 🚦 Tratamento de Erros

A aplicação utiliza um **Global Exception Handler** (`@RestControllerAdvice`) para padronizar respostas de erro com códigos HTTP corretos e mensagens claras.

---

## 📄 Licença

Projeto desenvolvido para fins educacionais e demonstração de boas práticas com Spring Boot.
