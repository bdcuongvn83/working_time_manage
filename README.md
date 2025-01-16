# JSFDemo
Author: DUC CUONG BUI  
Project: School manage aspnetcore  
GitHub Repository URL : (https://github.com/bdcuongvn83/working_time_manage.git))  
License: GPL License  

# Demo

Refer tutorial like below url:
https://howtodoinjava.com/maven/multi-module-project-eclipse/  

**Part 1:**

https://github.com/user-attachments/assets/4016318e-1cdd-4010-abcb-a62a6a25a818

**Part2**

https://github.com/user-attachments/assets/6e9f1e56-e232-4c34-a224-e7905ab5476c

# The features of this software include:  
### Admin Management Functions: 
► A. Manage employees (table: tb_employee)  
► B. Manage Project (table: tb_project)  
► C. Manage Task (table: tb_task)
### User functions: 
► D. Manage Working time(table: tb_workingtime)  
► E. Login/Logout/validate authority pages ( when access pages if not login, can not access)  

# Techniques used in this project:  
✔ JavaEE, JPA, JSF, richface/primefaces, MVC  
✔ Implementing Create, Read, Update, Delete (CRUD) operations using JPA framework  
✔ Form validation, binding property using EJB Bean  
✔ Manage transaction for update data, by JSF phase.  
✔ filtering/searching by Persistence Query  
✔ Face-config for Navigation, using session  
✔ CSS / image  
✔ Database Postgres SQL  
✔ Using Maven for build project  

# ERD structure:  

![image](https://github.com/user-attachments/assets/a5047990-7104-404b-b451-b40aab8bc184)


## Getting started  

To make it easy for you to get started with GitLab, here's a list of recommended next steps.  
Prepare environment:  

► Database  
➜ import file dump-postgres-202501161933.sql in database folder.  
![image](https://github.com/user-attachments/assets/3e074336-b411-4936-abc2-736cc8faa29e)  

► install JDK1.8  
https://www.oracle.com/au/java/technologies/javase/javase8-archive-downloads.html  

► install maven 3.3.9  
https://dlcdn.apache.org/maven/maven-3/3.9.9/source/apache-maven-3.9.9-src.zip  

► download Payara server 5  
https://repo1.maven.org/maven2/fish/payara/distributions/payara/5.2022.5/  

steps to build and deploy source:  
► 1. clone source code to local   
► 2. Open cmd (settup jdk, and maven)  
► 3. Go to folder working_time_manage, Run command line, to build source code  
mvn -Dmaven.test.skip=true clean package  
Build source : sucesss  
![image](https://github.com/user-attachments/assets/e1759f19-ed1f-4698-9ec6-32c27a5e42cc)  

► 4. check ear file was builded in ear target folder  
► 5. deploy ear file by Payara server 5 : 
 ![image](https://github.com/user-attachments/assets/d076b443-6a53-41d8-9910-3904ab24848c)  

deploy success:
![image](https://github.com/user-attachments/assets/4ba6cd18-969a-4924-9cad-7dc639577453)

► 6. Open browser, test with url:  
URL: localhost:8080/pe4j/login.jsf  
Or with any port that you did setup (8081,..)  
![image](https://github.com/user-attachments/assets/f445b412-3ace-486e-ad28-1b7b3f0bd54a)
login sucess:
![image](https://github.com/user-attachments/assets/a6fabb3f-0b46-4f53-93b8-b0546de623df)

userid:cuong / password:123  

**Thank you for reviewing the guide! If you have any questions or need clarification, feel free to ask. Your feedback is always welcome to help improve the material.**





