# Simple project to test the performance of calling @Inject a stateless bean in a loop vs a cdi bean
# Execute the following to start rocking
 - mvn clean install
 - docker-compose up --build
 - Check the performance of stateless loop call

```bash
curl -w "\
    time_namelookup:  %{time_namelookup}s\n \
       time_connect:  %{time_connect}s\n  \
    time_appconnect:  %{time_appconnect}s\n  \
   time_pretransfer:  %{time_pretransfer}s\n  \
      time_redirect:  %{time_redirect}s\n  \
 time_starttransfer:  %{time_starttransfer}s\n  \
                      ----------\n  \
         time_total:  %{time_total}s\n" -o /dev/null -s "http://localhost:8080/stateless_vs_cdi/api/action/stateless-loop-call"

```
 - Check the performance of cdi loop call

```bash
curl -w "\
    time_namelookup:  %{time_namelookup}s\n \
       time_connect:  %{time_connect}s\n  \
    time_appconnect:  %{time_appconnect}s\n  \
   time_pretransfer:  %{time_pretransfer}s\n  \
      time_redirect:  %{time_redirect}s\n  \
 time_starttransfer:  %{time_starttransfer}s\n  \
                      ----------\n  \
         time_total:  %{time_total}s\n" -o /dev/null -s "http://localhost:8080/stateless_vs_cdi/api/action/cdi-loop-call"
```

# Output of test
```bash
$ curl -w "\
    time_namelookup:  %{time_namelookup}s\n \
       time_connect:  %{time_connect}s\n  \
    time_appconnect:  %{time_appconnect}s\n  \
   time_pretransfer:  %{time_pretransfer}s\n  \
      time_redirect:  %{time_redirect}s\n  \
 time_starttransfer:  %{time_starttransfer}s\n  \
                      ----------\n  \
         time_total:  %{time_total}s\n" -o /dev/null -s "http://localhost:8080/stateless_vs_cdi/api/action/cdi-loop-call"
    time_namelookup:  0.005652s
        time_connect:  0.006126s
      time_appconnect:  0.000000s
     time_pretransfer:  0.006173s
        time_redirect:  0.000000s
   time_starttransfer:  0.011454s
                        ----------
           time_total:  0.011539s
```
Average time taken for cdi bean proceed 10^5 for do nothing method is around 0.012 second

```bash
$ curl -w "\
    time_namelookup:  %{time_namelookup}s\n \
       time_connect:  %{time_connect}s\n  \
    time_appconnect:  %{time_appconnect}s\n  \
   time_pretransfer:  %{time_pretransfer}s\n  \
      time_redirect:  %{time_redirect}s\n  \
 time_starttransfer:  %{time_starttransfer}s\n  \
                      ----------\n  \
         time_total:  %{time_total}s\n" -o /dev/null -s "http://localhost:8080/stateless_vs_cdi/api/action/stateless-loop-call"
    time_namelookup:  0.007091s
        time_connect:  0.007565s
      time_appconnect:  0.000000s
     time_pretransfer:  0.007611s
        time_redirect:  0.000000s
   time_starttransfer:  0.392576s
                        ----------
           time_total:  0.392649s
```
Average time taken for stateless bean proceed 10^5 for do nothing method is around 0.390 second. 
The more heavier the @Stateless bean, the more time-consuming for the stateless bean.

# Conclusion
 - Whenever you want to handle anything in a loop, please be aware that the Stateless bean takes quite a time
 - If you really want to use an @Inject a bean to handle the data (get benefit of injecting data instead of add the data as input params), please **use the CDI bean**.