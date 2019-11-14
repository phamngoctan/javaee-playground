# hello-world with Thorntail application server
The idea of this project is to provide the minimal set-up to deliver a rest resource using Thorntail

To run it  
```
mvn thorntail:run
```  
To check the result
```
curl http://localhost:8080/endpoint?search=Tan%20Pham  
```
To package the jar file  
```
mvn clean package
```
## Reference
https://thorntail.io/generator/