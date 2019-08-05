# EmployeeRestAPIs
REST APIs to make CURD operations on employee db

- Spring secutiry with Oauth2 is used to secure APIs.

- registration API can be used to create user.

Following are the APIs:

1. <baseUrl>/employee 
  method type : POST
  Description : used to create employee
  
2. <baseUrl>/employee/{empId}
  method type : GET
  Description : used to get only one employee
  
3. <baseUrl>/employee/{empId}
  method type : DELETE
  Description : used to delete employee
  
4. <baseUrl>/employee/{empId}
  method type : PUT
  Description : used to update employee
  
5. <baseUrl>/employee/getAll 
  method type : POST
  Description : used to get all employee
  
6. <baseUrl>/registration
  method type : POST
  Description : used to register user which can perform opertaion on employee data. This is for testing purpose only.
  
7. <baseUrl>/oauth/token
  method type : POST
  Description : used to authenticate user
  
