# CurrencyAccounts

The aim of project was to create an API for the registration of bank accounts with sub-accounts in PLN and USD.
The API allow users to set up an account and exchange currencies between sub-accounts.

## Assumptions [v 1.0]
- One person can only have one account
- The account is identified by PESEL (assumed that PESEL is unique)
- An adult is someone for whom: the current year minus the birth year is equal to or greater than 18
- Basic PESEL validation, based on: https://obywatel.gov.pl/pl/dokumenty-i-dane-osobowe/czym-jest-numer-pesel
- Basic exchange validation
- Data on current exchange rates are get from the NBP's public API (https://api.nbp.pl/)
- The application works with an in-memory database (H2)

## API request

- [X] New user registration:
```
POST http://localhost:8080/api/v1/registration
Content-Type: application/json

{
  "name": "Adam",
  "surname": "Kowalski",
  "PESEL": "99111278931",
  "account": {
  "PLN": 100
  }
}
```

- [X]  Get user data: http://localhost:8080/api/v1/user/{user-pesel}
```
GET http://localhost:8080/api/v1/user/99111278931
```

- [X]  Get all users data
```
GET http://localhost:8080/api/v1/user
```

- [X]  Currency exchange (works with PLN and USD both ways)
```
POST http://localhost:8080/api/v1/exchange
Content-Type: application/json

{
  "userPesel": 99111278931,
  "from": "PLN",
  "to": "USD",
  "amount": 100
}
```

## Technologies and tools 
- Java, Spring Boot
- H2 database
- Maven
