# URL Shortener Service
This is a Java/Spring based microservice to shorten urls. This has following functionalities : 

* `POST /api/shorten` - Takes a JSON request with url and returns a shortened url. 
* `GET /api/fetch/{id}` - Takes a shortened url and returns the original url.
* `GET /api/insights/{id}` - Takes a shortened url and returns insights like clicks, last visited time etc.


## Technical Requirements 
* Java11 
* Spring Boot 2.7 (managed via gradle)
* MongoDB

## How to Run 
``` shell
./gradlew bootRun 
```