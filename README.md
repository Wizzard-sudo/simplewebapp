### Practical task

Using the provided skeleton, implement the REST service.

Useful link: https://spring.io/guides/gs/rest-service/

In addition you could use Swagger to provide API documentation.

### Starting a project with Docker

Use Maven to create an executable jar file:

mvn clean package

The jar file will be created in the target folder. Now you need to move it to the src/main/docker folder:

cp target/simplewebapp-0.0.1-SNAPSHOT.jar src/main/docker

It remains to go to src/main/docker and run the container:

cd src/main/docker
docker-compose up