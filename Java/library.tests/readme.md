# BankID Merchant Library Tests

This application has a suite of junit tests to demonstrate how the SamlAttributesEncryptionKey key can be used to decrypt the Encrypted Data from the Raw Message Xml
The logic for the decryption of the element is presented in net.bankid.merchant.library.xml.XmlUtils.java class

You can run the tests either from your IDE (e.g. Eclipse, InteliJ IDEA Community Edition etc.) or by running the 'mvn clean test' command.

## Prerequisites
1. Merchant Library source code (version 1.2.6 or above) from the GitHub Repository: https://github.com/Currence-Online/iDIN-libraries-java/tree/master/Java
2. Maven
3. JDK 1.8
4. A suitable IDE for Java Development (e.g. Eclipse, InteliJ IDEA Community Edition etc.)


## Steps to run the tests
1. Install the library dependency into your Maven local repository
- navigate to Java/library folder:
```
$ cd Java/library
```
- install the latest library into your Maven local repository
```
$ mvn clean install
```

2. Run the tests
2.1 To run the tests using maven
- navigate to Java/library.tests folder:
```
$ cd Java/library.tests
```
- run the tests using maven
```
$ mvn clean test
```

2.2 To run the tests form your IDE
- open the library.tests project in your IDE 
- run the net.bankid.merchant.library.AttributeEncryptionKeysShouldTest test class

## Steps to run the tests using Docker

1. Install docker

2. Run the next commands from Java directory
```
$ docker volume create --name maven-repo  
$ docker run -it --rm --name my-maven-project -v maven-repo:/root/.m2 -v "$(pwd)/library":/usr/src/mymaven -w /usr/src/mymaven maven:3.3-jdk-8 mvn clean install  
$ docker run -it --rm --name my-maven-project -v maven-repo:/root/.m2 -v "$(pwd)/library.tests":/usr/src/mymaven -w /usr/src/mymaven maven:3.3-jdk-8 mvn clean test
$ docker volume rm maven-repo
```