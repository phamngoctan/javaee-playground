# Build
mvn clean package && docker build -t com.playground/hello-world-javaee8 .

# RUN

The below line doesn't work. The Glassfish server reject the rest call from client

```
docker rm -f hello-world-javaee8 || true && docker run -d -p 8080:8080 -p 4848:4848 --name hello-world-javaee8 com.playground/hello-world-javaee8 

```

Another way, copy/paste the war file to Wildfly deployment folder which supports microprofile (from Wildfly 19 - I think). It run successfully.

```
curl http://localhost:8080/hello-world-javaee8/resources/ping
```

