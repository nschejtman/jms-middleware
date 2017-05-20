glassfish_home="/Users/nicolas/devel/glassfish4.1.1" # Change with your glassfish installation
cd cli/instatweet-cli/
mvn clean package
asadmin start-domain domain1
$glassfish_home/glassfish/bin/appclient -client target/instatweet-cli-1.0-jar-with-dependencies.jar
asadmin stop-domain domain1
clear
