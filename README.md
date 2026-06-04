# Online Store Microservices

Online Store Microservices is a Spring Boot project composed of three separate applications that simulate a simple online store transaction flow.

The project demonstrates communication between multiple Spring Boot applications using OpenFeign, together with Spring Security, Spring Data JPA, MySQL, Thymeleaf and unit testing with JUnit/Mockito.

---

## Project Structure

```text
online-store-microservices
├── onlinestore
├── bankapp
├── tokenapp
└── sql
```

---

## Applications

### onlinestore

Main web application available on port `8080`.

Responsibilities:

* user registration
* user login with Spring Security
* displaying categories and products
* product search
* product details page
* user dashboard
* product purchase flow
* communication with `tokenapp` and `bankapp` using OpenFeign
* displaying error pages for failed operations

### bankapp

REST API available on port `8081`.

Responsibilities:

* storing bank accounts
* creating a bank account automatically during the first transaction
* validating transaction tokens through `tokenapp`
* subtracting product price from account balance
* returning proper HTTP statuses for invalid tokens or insufficient funds

### tokenapp

REST API available on port `8082`.

Responsibilities:

* generating UUID tokens
* storing tokens in the database
* updating an existing user token
* validating tokens sent by `bankapp`

---

## Transaction Flow

```text
1. User logs in to onlinestore.
2. User selects a product and clicks "Buy Product".
3. onlinestore sends a request to tokenapp to generate a UUID token.
4. tokenapp generates the token, saves it and returns it in the UUID-Token header.
5. onlinestore sends transaction data to bankapp with the UUID-Token header.
6. bankapp sends the token to tokenapp for validation.
7. tokenapp validates the token.
8. If the token is valid, bankapp creates a bank account if it does not exist yet.
9. bankapp subtracts the product price from the account balance.
10. onlinestore displays transaction confirmation.
```

---

## Architecture Overview

```text
+----------------+
|  onlinestore   |
|  port: 8080    |
+-------+--------+
        |
        | POST /token/generate
        v
+----------------+
|    tokenapp    |
|  port: 8082    |
+----------------+

+----------------+
|  onlinestore   |
|  port: 8080    |
+-------+--------+
        |
        | POST /account/transaction
        | Header: UUID-Token
        v
+----------------+
|    bankapp     |
|  port: 8081    |
+-------+--------+
        |
        | POST /token/validate
        | Header: UUID-Token
        v
+----------------+
|    tokenapp    |
|  port: 8082    |
+----------------+
```

---

## Technologies Used

* Java
* Spring Boot
* Spring MVC
* Spring Security
* Spring Data JPA
* Hibernate
* MySQL
* OpenFeign
* Thymeleaf
* Bootstrap
* Maven
* JUnit 5
* Mockito

---

## Ports

| Application | Port |
| ----------- | ---: |
| onlinestore | 8080 |
| bankapp     | 8081 |
| tokenapp    | 8082 |

---

## Database Configuration

The project uses MySQL.

Each application uses a separate database:

| Application | Database      |
| ----------- | ------------- |
| onlinestore | `onlinestore` |
| bankapp     | `bank`        |
| tokenapp    | `token`       |

Current database connection configuration:

```properties
spring.datasource.username=springstudent
spring.datasource.password=springstudent
```

SQL scripts are available in the `sql` directory:

```text
sql
├── onlinestore.sql
├── bank.sql
└── token.sql
```

The scripts create the required databases, tables and sample product/category data for `onlinestore`.

---

## Database Tables

### onlinestore

```text
users
categories
products
```

### bankapp

```text
accounts
```

### tokenapp

```text
tokens
```

---

## How to Run

Before running the applications:

1. Start MySQL.
2. Execute SQL scripts from the `sql` directory.
3. Run the applications in this order:

```text
1. tokenapp
2. bankapp
3. onlinestore
```

### Run tokenapp

```bash
cd tokenapp
mvn spring-boot:run
```

Application starts on:

```text
http://localhost:8082
```

### Run bankapp

```bash
cd bankapp
mvn spring-boot:run
```

Application starts on:

```text
http://localhost:8081
```

### Run onlinestore

```bash
cd onlinestore
mvn spring-boot:run
```

Application starts on:

```text
http://localhost:8080
```

Then open:

```text
http://localhost:8080
```

---

## Features

### User features

* registration
* login
* category browsing
* product browsing
* product search
* product details page
* product purchase
* dashboard with user and bank account information

### Backend features

* REST communication between applications
* token generation and validation
* transaction processing
* automatic bank account creation
* global exception handling
* unit tests for service layer

---

## Error Handling

The project contains global exception handling for application-specific errors.

Examples:

* product not found
* user already exists
* token generation failed
* token download failed
* transaction failed
* external service errors from OpenFeign

In `onlinestore`, errors are displayed using a Thymeleaf error page.

In `bankapp` and `tokenapp`, errors are returned as REST responses with proper HTTP statuses.

---

## Tests

The project contains unit tests written with JUnit 5 and Mockito.

Covered service tests:

### onlinestore

* `CategoryServiceTest`
* `ProductServiceTest`
* `UserServiceTest`
* `TokenServiceTest`
* `BankServiceTest`

### bankapp

* `AccountServiceTest`
* `TokenServiceTest`

### tokenapp

* `TokenServiceTest`

Run tests separately for each application:

```bash
cd onlinestore
mvn test
```

```bash
cd bankapp
mvn test
```

```bash
cd tokenapp
mvn test
```

---

## Example Transaction Scenario

```text
1. Register a new user.
2. Log in.
3. Open the dashboard.
4. If the user has no bank account yet, the empty dashboard is displayed.
5. Open product list.
6. Select a product.
7. Click "Buy Product".
8. onlinestore requests a token from tokenapp.
9. onlinestore sends transaction request to bankapp.
10. bankapp validates the token using tokenapp.
11. bankapp creates a new account with starting balance 20000 if it does not exist.
12. bankapp subtracts the product price.
13. onlinestore displays transaction confirmation.
14. Dashboard shows updated account balance.
```

---

## Current Status

Implemented:

* three Spring Boot applications
* user registration and login
* product and category views
* product search
* transaction flow
* token generation and validation
* bank account creation and balance update
* OpenFeign communication
* global exception handling
* unit tests for service layer
* SQL scripts for database setup and sample data

Possible future improvements:

* screenshots in README
* controller tests
* integration tests
