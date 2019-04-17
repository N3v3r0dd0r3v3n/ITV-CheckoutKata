# About the test

The test is a simple spring-boot-start-web project and runs in Java 1.8

The test can be built from the command line using

> ./mvnw clean install

which will run the tests.  (Integration test phase can be omitted using the parameter -Dskip.it=true)

The product can be run up as a stand-alone service using

> java -jar target/checkout-kata-0.1.0.jar

The service can then be located at http://localhost:8080
The checkout end point is found at /checkout and accepts http post method only with  content-type headers type application/json. 

It expects the body to consist of a JSON array of SKUs, e.g.  ["A",'B"]

## Improvements

As the exercise was is a Checkout Kata I didn't use any persistence, instead using a repository of hardcoded data.  One immediate improvement would be to extract this to an in-memory database.

None of the components have been implemented to an interface.  Should the product be further extended I would extract out interfaces where necessary.

Finally the controller simply returns the total of the cart.  An obvious improvement would be to return a detailed break-down of how the total was reached.

-Adrian M Bell
07762 103435
