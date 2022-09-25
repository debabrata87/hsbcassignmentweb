# HSBC Multi Currency Payment App
HSBC Multi currency Payment application with Web interface

# High Level Requirement :

  Web application to display Multi Currency Balance and Accept Payment and display Balance After processing Payment
  

# GIT URL 
  
  -https://github.com/debabrata87/hsbcassignmentweb.git

# Tools Used for development 

  1- STS 
  
  2- JDK 1.8 
  
  3- Springboot

# Application Type:
  
  Spring boot Web Appliction

# Build Tool:

  Maven 3.8.6

# Depenency :

   1- Spring boot Web application 
   
   2- H2
   
   3- JPA
   
   4- Thymeleaf 
   
   5- HTML
   

# Data Setup and Confiuration :

  1- data.sql file on resource folder contains currency and the initial balance along with exchange rate  [ as in 22/09/2022 corresponding to USD ] which will be displayed as initial balance
  
  2- Default port of Spring boot web application is 8080 but this can be overridden by activating and changing the value of [ server.port=8089 i.e. 8089 or any chosen value available ] property in application.properties

# Build:

  Go to Project directory where pom.xml is present and run below command 
  
  mvn install 

# Run Application :
  
  Go to <Project Home>\target Directory and run the application by command 
  
  java -jar hsbcassignmentweb-0.0.1-SNAPSHOT.jar
  
# Known Issues:

  White Label Error Page issue is occuring if the first transaction is invalid like Payment non numeric . It is handled with Thymeleaf handling of error. Page will be redirected to a error page and can be navigated back to Home page again 
