# Concurrency in java ee
Check this document for the direct recommendation from oracle  https://download.oracle.com/otndocs/jcp/concurrency-1_0-fr-eval-spec/index.html

## Important notes
The server containers provide runtime support for Java EE application components (such as servlets and Enterprise JavaBeans™ (EJB™)). They provide a layer between application component code and platform services and resources. All application component code is run on a thread managed by a container and each container typically expects all access to container-supplied objects to occur on the same thread.  

It is because of this behavior that <b>application components are typically unable to reliably use other Java EE platform services from a thread that is not managed by the container</b>.  


Java EE Product Providers (see chapter 2.11 of the Java EE 7 Specification) also <b>discourage the use of resources in a non-managed way<b/>, because it can potentially undermine the enterprise features that the platform is designed to provide such as availability, security, and reliability and scalability.