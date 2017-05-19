# jms-middleware

## Things to install
1. Maven
2. Glassfish server (v4)
3. Wildfly server (v10)


## Run 
### Glassfish
Option 1: Add IDE integration and run within IDE
Option 2: Package with maven and deploy to the server

Commands for Option 2
mvn clean package
asadmin start-domain domain1
asadmin delpoy "you path"/target/"name of the war".war

