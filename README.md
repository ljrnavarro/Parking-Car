# 🅿️ Parking-Car API

![Java](https://img.shields.io/badge/Java-17-blue) ![Spring
Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-Database-orange)
![Docker](https://img.shields.io/badge/Docker-Container-blue)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

API REST para gerenciamento de um **sistema de estacionamento**,
construída com **Spring Boot**, **JPA**, **MySQL** e **Docker**.

------------------------------------------------------------------------

## 🚀 Objetivo do Projeto

Demonstrar boas práticas de desenvolvimento backend:

✔ Arquitetura em camadas\
✔ API RESTful\
✔ Persistência com JPA\
✔ Migrations com Flyway\
✔ Documentação OpenAPI\
✔ Ambiente isolado com Docker

------------------------------------------------------------------------

## 🧠 Regras de Negócio

  -----------------------------------------------------------------------
  Regra                     Descrição
  ------------------------- ---------------------------------------------
  🚫 Placa duplicada        Não permite cadastrar o mesmo veículo duas
                            vezes

  🚫 Estacionamento cheio   Impede entrada quando não há vagas

  📊 Controle de vagas      O sistema calcula ocupação automaticamente
  -----------------------------------------------------------------------

------------------------------------------------------------------------

## 🛠️ Stack Tecnológica

  Camada            Tecnologia
  ----------------- -----------------------------
  Linguagem         Java 17
  Framework         Spring Boot
  Persistência      Spring Data JPA / Hibernate
  Banco             MySQL
  Migração          Flyway
  Documentação      Swagger / OpenAPI
  Containerização   Docker

------------------------------------------------------------------------

## 🧱 Arquitetura

    Controller → Service → Repository → Database

Separação clara de responsabilidades para facilitar manutenção e testes.

------------------------------------------------------------------------

## ⚙️ Como Executar o Projeto

### 🔹 1. Clonar o repositório

``` bash
git clone https://github.com/ljrnavarro/Parking-Car.git
cd Parking-Car
```

------------------------------------------------------------------------

### 🔹 2. Subir banco com Docker

``` bash
docker-compose up -d
```

------------------------------------------------------------------------

### 🔹 3. Configurar aplicação

Arquivo:

    src/main/resources/application.properties

Exemplo:

``` properties
spring.datasource.url=jdbc:mysql://localhost:3306/parking
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
```

------------------------------------------------------------------------

### 🔹 4. Rodar a API

``` bash
mvn spring-boot:run
```

A aplicação iniciará em:

    http://localhost:8080

------------------------------------------------------------------------

## 📚 Documentação Swagger

Acesse a documentação interativa:

    http://localhost:8080/swagger-ui/index.html

------------------------------------------------------------------------

## 📌 Principais Endpoints

### 🚗 Veículos

**Criar veículo**

``` http
POST /vehicles
Content-Type: application/json
```

``` json
{
  "plate": "ABC-1234",
  "model": "Civic",
  "color": "Preto"
}
```

------------------------------------------------------------------------

### 🅿️ Estacionamentos

**Criar estacionamento**

``` http
POST /parkings
```

``` json
{
  "name": "Shopping Center",
  "capacity": 50
}
```

------------------------------------------------------------------------

### 🚘 Entradas

**Registrar entrada**

``` http
POST /entries
```

``` json
{
  "vehiclePlate": "ABC-1234",
  "parkingId": 1
}
```

------------------------------------------------------------------------

## ❌ Tratamento de Erros

  Código   Situação
  -------- --------------------------
  400      Dados inválidos
  404      Recurso não encontrado
  409      Regra de negócio violada

------------------------------------------------------------------------

## 🧪 Testes

``` bash
mvn test
```

------------------------------------------------------------------------

## 📈 Melhorias Futuras

-   🔐 Autenticação JWT\
-   📊 Dashboard de ocupação\
-   📅 Histórico de permanência\
-   🧾 Cálculo de cobrança

------------------------------------------------------------------------

## 👨‍💻 Autor

**Luiz Navarro**\
https://github.com/ljrnavarro

------------------------------------------------------------------------

## 📄 Licença

MIT
