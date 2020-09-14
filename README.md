# BPDTS API Test

Task: Build an API which calls the API at https://bpdts-test-app-v3.herokuapp.com/ and returns people who are listed as either living in London, or whose current coordinates are within 60 miles of London.

I have developed a Spring Boot Microservice to fetch the required data from the API, process the results in a service layer and return it via an API endpoint.

Testing is implemented with JUnit5 and Mockito