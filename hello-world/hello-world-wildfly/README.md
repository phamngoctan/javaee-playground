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

In the wildfly, there is something like this:

```xml
<security-domain name="other" cache-type="default">
    <authentication>
        <login-module code="Remoting" flag="optional">
            <module-option name="password-stacking" value="useFirstPass"/>
        </login-module>
        <login-module code="RealmDirect" flag="required">
            <module-option name="password-stacking" value="useFirstPass"/>
        </login-module>
    </authentication>
</security-domain>
```
That's why the block code below worked. Otherwise, it will throw exception "other security-domain not found... " something like that

```xml
<jboss-web>
    <security-domain>other</security-domain>
</jboss-web>
```

## Reference
 - https://www.radcortez.com/custom-principal-and-loginmodule-for-wildfly/
 - https://github.com/radcortez/wildfly-custom-login-module
 - http://www.mastertheboss.com/jboss-frameworks/resteasy/resteasy-basic-authentication-example
