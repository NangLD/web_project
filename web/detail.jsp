<%-- 
    Document   : detail
    Created on : Mar 2, 2019, 9:01:16 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Student Detail</h2> <br/>
        ID: ${student.getId()}<br/> 
        Name: ${student.getFullName()}<br/>
        address: ${student.getAddress()}<br/>
        Phone: ${student.getPhoneNumber()}<br/>
        Teacher: ${student.getTeacher()}<br/>
        Class: ${student.getClassOfStudent()} <br/>
        Age: ${student.getAge()}<br/>
    </body>
    <br/>
    <a href="StudentServlet">Back</a>
</html>
