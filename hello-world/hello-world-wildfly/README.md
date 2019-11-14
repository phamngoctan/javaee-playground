# hello-world-wildfly project

## build
mvn clean install

## test
Add some additional steps to make the username/password of authenticated user available

```bash
add-user.sh /add-user.cmd script which is available in the JBOSS_HOME/bin folder.

What type of user do you wish to add?

 a) Management User (mgmt-users.properties)

 b) Application User (application-users.properties)

(a): b

 

Enter the details of the new user to add.

Using realm 'ApplicationRealm' as discovered from the existing property files.

Username : jboss

Password :

Re-enter Password :

What groups do you want this user to belong to? (Please enter a comma separated list, or leave blank for none)[  ]: Manager

About to add user 'jboss' for realm 'ApplicationRealm'

Is this correct yes/no? yes
```

curl localhost:8080/hello-world-wildfly/api/hello-world

## Notes
At the very first version, the security-domain **"other"** is used to secure the REST API. At the very first test, it cached the login username/password after succesfully login in the first time.  
That's why you can see the loginModule is just called once.

## Reference
 - https://www.radcortez.com/custom-principal-and-loginmodule-for-wildfly/
 - https://github.com/radcortez/wildfly-custom-login-module
 - http://www.mastertheboss.com/jboss-frameworks/resteasy/resteasy-basic-authentication-example
