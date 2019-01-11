# BankID Website

This application is developed with **Maven** and the package is represented as a WAR file. 
Can be created using **mvn clean package** command. Resulted file can be found in **target directory**.

It contains two profiles: **acc** and **qa**. In order to run this application you need to use using following command: **mvn clean install -P{profile-name}**. 
In this manner, the war file will be built and deployed on an embedded Glassfish container.

## Prerequisites
1. Eclipse IDE
2. JDK 1.8

##Migration notes from Netbeans to Eclipse

1. WAR file made by Netbeans is similar with Eclipse one. In order to achieve this, in **pom.xml** is specified resource files that needs to be added in specific target paths. 
	- Certificates in WEB-INF/classes
	- Profile specific xml config files in WEB-INF(e.g **bankid-config.xml** and **glassfish-web.xml**)
	- **net.bankid.merchant.library.jar** in WEB-INF/lib folder
2. Port and WAR name can be changed from pom.xml file

##Deploying steps

1. Connect to the machine where will be deployed. Can be used WinSCP.
2. Enter hostname or IP plus credentials. IP: 10.10.10.73. If it's not working, speak to the infra/support guy.
3. If it's a Linux machine, follow this path: /opt/tomcat/webapps.
4. Copy war file in the above path.
5. Logs can be checked in /opt/tomcat/logs/catalina.out.

## Endpoints

- Local: http://localhost:8080/net.bankid.merchant.website.{PROFILE_NAME} e.g.: http://localhost:8080/net.bankid.merchant.website.qa
- Remote: http://10.10.10.73:9876/net.bankid.merchant.website.{PROFILE_NAME}

 

