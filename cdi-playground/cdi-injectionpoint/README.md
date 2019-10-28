# CDI-injectionpoint
Using injection point to extract the HttpServletRequest. Please see the following code snippet to get the idea of extracting information from HttpServletRequest.  
```java
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpParam {
    @Nonbinding public String value(); 
}

// Extract the data from the http request
@Produces
@HttpParam("")
@Dependent
String getParamValue(InjectionPoint ip, HttpServletRequest req) {
  return req.getParameter(ip.getAnnotated().getAnnotation(HttpParam.class).value());
}

// Usage
@Inject
@HttpParam("productId")
String productId;
```