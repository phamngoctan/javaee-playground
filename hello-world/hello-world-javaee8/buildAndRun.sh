#!/bin/sh
mvn clean package && docker build -t com.playground/hello-world-javaee8 .
docker rm -f hello-world-javaee8 || true && docker run -d -p 8080:8080 -p 4848:4848 --name hello-world-javaee8 com.playground/hello-world-javaee8 
