# CDI-instancequalifier
Using @Any annotation to get all the relative implementation of a Bean. Then, using javax.enterprise.util.AnnotationLiteral to get a specific implementation.  
Also demo usage of org.hamcrest.Matchers.instanceOf

```java
@Inject @Any
private Instance<Greeting> instance;
	
// use Instance<T>#select()
Instance<Greeting> businessInstance = instance.select(new AnnotationLiteral<Business>() {});

assertThat(businessBean, instanceOf(FormalGreeting.class));
```

