<%-- 
    Document   : edit
    Created on : Mar 3, 2019, 12:29:46 AM
    Author     : Admin
--%>

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
        <h2>Edit Student</h2><br/>
        
        <form action="EditStudent?ID=<%=request.getParameter("ID")%>" method="post">
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
            boolean isEmpty= Boolean.valueOf(request.getAttribute("isEmptyField").toString());
            if(isEmpty==true){
                %>
                <p>Wrong Input!!!</p>
                <%
            }
        %>
        <%
                boolean isCreatedSuccessful= Boolean.valueOf(request.getAttribute("isEditedDone").toString());
                if(isCreatedSuccessful){
                    %>
                    <h4>Edit successfully</h4>
                    <%
                }
            %>
        <br/>
        <a href="StudentServlet">Back</a>
    </body>
</html>
