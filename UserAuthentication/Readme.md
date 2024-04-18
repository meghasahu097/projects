# User SignUp API
### Request
```
curl --location 'localhost:8080/api/auth/sign_up' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=5875DD454B951CCBCA44121FFC1BD804' \
--data-raw '{
    "username":"Monica@gmail.com",
    "password":"abcde",
    "name":"Monica Geller",
    "role":"Employee"
}'

```
### Response
```
User Created Successfully
```
### Request
```
curl --location 'localhost:8080/api/auth/sign_up' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=5875DD454B951CCBCA44121FFC1BD804' \
--data-raw '{
    "username":"Monica@gmail.com",
    "password":"abcde",
    "name":"Monica Geller",
    "role":"Employee"
}'
```
### Response
```
User Already Exists
```
# User Login API
### Request
```
curl --location 'localhost:8080/api/auth/login' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=5875DD454B951CCBCA44121FFC1BD804' \
--data-raw '{
    "username":"meghasahu@gmail.com",
    "password":"12345"
}'
```
### Response
```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZWdoYXNhaHVAZ21haWwuY29tIiwiaWF0IjoxNzEzNDE4NzI2LCJleHAiOjE3MTM0MjIzMjZ9.-WBMk5MHr7EyfqdjgP41NnMUEFLvg0YBidB47ymVR54
```
### Request
```
curl --location 'localhost:8080/api/auth/login' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=5875DD454B951CCBCA44121FFC1BD804' \
--data-raw '{
    "username":"megha@gmail.com",
    "password":"12345"
}'
```
### Response
```
Invalid Username
```
### Request
```
curl --location 'localhost:8080/api/auth/login' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=5875DD454B951CCBCA44121FFC1BD804' \
--data-raw '{
    "username":"meghasahu@gmail.com",
    "password":"123"
}'
```
### Response
```
Authentication Failed (Invalid Password)
```
# LookUp User API
### Request
```
curl --location 'localhost:8080/api/auth/lookup_user' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZWdoYXNhaHVAZ21haWwuY29tIiwiaWF0IjoxNzEzNDE4MTYwLCJleHAiOjE3MTM0MjE3NjB9.jm5BArQqRzSRRj4ZRVuLjV5xFa21cBDd1g03tG8QZKk' \
--header 'Cookie: JSESSIONID=5875DD454B951CCBCA44121FFC1BD804'
```
### Response
```
{
    "role": "Employee",
    "name": "Megha Sahu",
    "id": 1,
    "username": "meghasahu@gmail.com"
}
```
### Request
```
curl --location 'localhost:8080/api/auth/lookup_user' \
--header 'Authorization: Bearer JhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZWdoYXNhaHVAZ21haWwuY29tIiwiaWF0IjoxNzEzNDE4MTYwLCJleHAiOjE3MTM0MjE3NjB9.jm5BArQqRzSRRj4ZRVuLjV5xFa21cBDd1g03tG8QZKk' \
--header 'Cookie: JSESSIONID=5875DD454B951CCBCA44121FFC1BD804'
```
### Response
```
Invalid Token
```