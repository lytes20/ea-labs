# Bank Application REST API

This is a Spring Boot REST API for a bank application that provides endpoints for managing bank accounts, deposits, withdrawals, and transfers.

## Features

- Create new bank accounts
- Get all accounts or a specific account
- Deposit money (USD and EUR)
- Withdraw money (USD and EUR)
- Transfer funds between accounts
- RESTful API with proper HTTP status codes
- Cross-origin support for web applications

## Getting Started

### Prerequisites

- Java 21
- Maven 3.6+

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the application:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Testing the API

You can test the API using the provided web interface at `http://localhost:8080` or use any REST client like Postman.

## API Endpoints

### Base URL

```
http://localhost:8080/api/bank
```

### 1. Create Account

**POST** `/accounts`

Creates a new bank account.

**Request Body:**

```json
{
  "accountNumber": 1263862,
  "customerName": "Frank Brown"
}
```

**Response:**

```json
{
  "accountnumber": 1263862,
  "balance": 0.0,
  "customer": {
    "name": "Frank Brown"
  },
  "entryList": []
}
```

### 2. Get All Accounts

**GET** `/accounts`

Returns all bank accounts.

**Response:**

```json
[
  {
    "accountnumber": 1263862,
    "balance": 100.0,
    "customer": {
      "name": "Frank Brown"
    },
    "entryList": [...]
  }
]
```

### 3. Get Account by Number

**GET** `/accounts/{accountNumber}`

Returns a specific account by account number.

**Response:**

```json
{
  "accountnumber": 1263862,
  "balance": 100.0,
  "customer": {
    "name": "Frank Brown"
  },
  "entryList": [...]
}
```

### 4. Deposit Money

**POST** `/accounts/{accountNumber}/deposit`

Deposits money in USD to the specified account.

**Request Body:**

```json
{
  "amount": 100.0
}
```

**Response:**

```
"Deposit successful"
```

### 5. Withdraw Money

**POST** `/accounts/{accountNumber}/withdraw`

Withdraws money in USD from the specified account.

**Request Body:**

```json
{
  "amount": 50.0
}
```

**Response:**

```
"Withdrawal successful"
```

### 6. Deposit Euros

**POST** `/accounts/{accountNumber}/deposit-euros`

Deposits money in EUR to the specified account (automatically converted to USD).

**Request Body:**

```json
{
  "amount": 100.0
}
```

**Response:**

```
"Euro deposit successful"
```

### 7. Withdraw Euros

**POST** `/accounts/{accountNumber}/withdraw-euros`

Withdraws money in EUR from the specified account (automatically converted from USD).

**Request Body:**

```json
{
  "amount": 50.0
}
```

**Response:**

```
"Euro withdrawal successful"
```

### 8. Transfer Funds

**POST** `/transfer`

Transfers money between two accounts.

**Request Body:**

```json
{
  "fromAccountNumber": 4253892,
  "toAccountNumber": 1263862,
  "amount": 25.0,
  "description": "Payment for services"
}
```

**Response:**

```
"Transfer successful"
```

## Error Handling

The API returns appropriate HTTP status codes:

- `200 OK` - Successful operation
- `201 Created` - Account created successfully
- `400 Bad Request` - Invalid request data
- `404 Not Found` - Account not found
- `500 Internal Server Error` - Server error

## Example Usage with curl

### Create an account:

```bash
curl -X POST http://localhost:8080/api/bank/accounts \
  -H "Content-Type: application/json" \
  -d '{"accountNumber": 1263862, "customerName": "Frank Brown"}'
```

### Get all accounts:

```bash
curl http://localhost:8080/api/bank/accounts
```

### Deposit money:

```bash
curl -X POST http://localhost:8080/api/bank/accounts/1263862/deposit \
  -H "Content-Type: application/json" \
  -d '{"amount": 100.0}'
```

### Transfer funds:

```bash
curl -X POST http://localhost:8080/api/bank/transfer \
  -H "Content-Type: application/json" \
  -d '{"fromAccountNumber": 4253892, "toAccountNumber": 1263862, "amount": 25.0, "description": "Test transfer"}'
```

## Web Interface

A web interface is available at `http://localhost:8080` for testing the API endpoints interactively.

## Architecture

The application follows a layered architecture:

- **Controller Layer**: REST endpoints (`BankController`)
- **Service Layer**: Business logic (`AccountService`)
- **DAO Layer**: Data access (`IAccountDAO`)
- **Domain Layer**: Business entities (`Account`, `Customer`, `AccountEntry`)

The application uses Spring Boot with:

- Spring Web for REST API
- Spring Data JPA for data persistence
- AspectJ for AOP logging
- MySQL as the database
