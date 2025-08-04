# Bank Application JMS Integration

## Changes Made

### 1. ActiveMQ Configuration

- Added ActiveMQ configuration properties in `application.properties`:
  - Broker URL: `tcp://localhost:61616`
  - Username: `admin`
  - Password: `admin`
  - Trust all packages enabled

### 2. JMS Configuration Class

- Created `JmsConfig.java` in `src/main/java/bank/config/` to properly configure JMS messaging
- Enabled JMS with `@EnableJms` annotation
- Configured message converter for JSON/text messages

### 3. Updated JMSSender Implementation

- Modified `JMSSender.java` to actually send JMS messages to the "tax" topic
- Uses Spring's `JmsTemplate` to send messages
- Messages are sent to the "tax" topic as specified

### 4. Updated AccountService Logic

- Modified `depositEuros` method to check for Euro deposits of 10,000 Euros or greater (not the converted dollar amount)
- Fixed the condition from `amountDollars > 10000` to `amount >= 10000`
- Updated JMS message to clearly indicate it's a Euro deposit
- Fixed a bug in `transferFunds` method where account objects were being used instead of account numbers in the JMS message

### 5. Test Coverage

- Created comprehensive tests in `AccountServiceJmsTest.java` to verify:
  - JMS messages are sent for Euro deposits >= 10,000
  - JMS messages are NOT sent for Euro deposits < 10,000
  - JMS messages are sent for Euro deposits exactly equal to 10,000

## How It Works

1. When a user deposits 10,000 Euros or more using the `depositEuros` method
2. The system checks if the Euro amount is >= 10,000
3. If true, a JMS message is sent to the "tax" topic with details about the deposit
4. The TaxService application (running separately) can listen to the "tax" topic to receive these messages

## Prerequisites

- ActiveMQ broker must be running on `localhost:61616`
- TaxService application should be configured to listen on the "tax" topic

## Testing

Run the tests with:

```bash
mvn test -Dtest=AccountServiceJmsTest
```

## Message Format

JMS messages sent to the "tax" topic will have the format:

```
"Deposit of {amount} Euros to account with accountNumber= {accountNumber}"
```

Example: `"Deposit of 15000.0 Euros to account with accountNumber= 12345"`
