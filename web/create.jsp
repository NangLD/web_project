<%-- 
    Document   : create
    Created on : Mar 1, 2019, 10:44:56 PM
    Author     : Admin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="modle.Students"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Create A New Student</h2><br/>
        <form action="CreateNew" method="post">
            ID:   <br/>   
            <input type="text" name="id" value="${id}" /><br/>
            Name: <br/>
            <input type="text" name="full_name" value="${fullName}"/><br/>
            Address:<br/>
            <input type="text" name="address" value="${address}"/> <br/>
            Phone:<br/>
            <input type="text" name="phone_number" value="${phoneNumber}"/><br/>
            Teacher:<br/>
            <input type="text" name="teacher" value="${teacher}"/><br/>
            Class:<br/>
            <input type="text" name="class" value="${class}"/><br/>
            Age:<br/>
            <input type="text" name="age" value="${age}"/><br/><br/>
            <input type="submit" value="Add" />
        </form>
        <br/>
        <%
            List<Students> students = (ArrayList<Students>) request.getAttribute("students");
            String id= (String)request.getAttribute("id");
            boolean isEmpty= Boolean.valueOf(request.getAttribute("isEmptyField").toString());
            if(isEmpty==true){
                %>
                <p>Wrong Input!!!</p>
                <%
            }
            for(Students std: students){
                if(id.equals(""+std.getId())){
                    %>
                    <p> Duplicated ID!!!</p> <br/>
                    <%
                }
            }
            
        %>
        <%
                boolean isCreatedSuccessful= Boolean.valueOf(request.getAttribute("isCreatedSuccessful").toString());
                if(isCreatedSuccessful){
                    %>
                    <h4>created  ${fullName} successfully</h4>
                    <%
                }
            %>
        <br/>
        <a href="StudentServlet">Back</a>
    </body>
</html>
