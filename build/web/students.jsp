<%-- 
    Document   : students
    Created on : Mar 1, 2019, 7:29:31 PM
    Author     : Admin
--%>

<%@page import="student.StudentManagement"%>
<%@page import="student.StudentDao"%>
<%@page import="comtext.DBContext"%>
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
        <h1>Student:</h1>
        <button type="button" onclick="create()"> Create new student  </button>
        <table border="1">
            <tr>
                
                <th>ID</th>
                <th>Fullname</th>
                <th>Activities</th>
                
            </tr>
            <tbody>

                <%
                    List<Students> students = (List<Students>) request.getAttribute("students");
                    for (Students std : students) {
                %>
                <tr>
                    <td><%=std.getId()%></td>
                    <td><%=std.getFullName()%></td>


                    <td>
                        <button type="button" onclick="show(<%=std.getId() %>)">Show</button>
                        <button type="button" onclick="edit(<%=std.getId() %>)">Edit</button>
                       
                        <button type="button" onclick="myFunction(<%=std.getId() %>)">Delete</button>
                        
                    </td>
                </tr>
            <script>
                function myFunction(id) {
                    var r = confirm("do you want to delete the student??");
                    if (r === true) {
                        window.location.href = "DeleteStudent?ID="+id;
                    }
                }
                function edit(id) {
                    window.location.href = "EditStudent?ID="+id;
                }
                function show(id) {
                    window.location.href = "ShowStudentDetail?ID="+id;
                }
                function create() {
                    window.location.href = "CreateNew";
                }
            </script>
            <%
                }
            %>



        </tbody>
    </table>
            <%
                boolean isCreatedSuccessful= Boolean.valueOf(request.getAttribute("isDeletedDone").toString());
                if(isCreatedSuccessful){
                    %>
                    <h4>Deleted id = ${ten} successfully</h4>
                    <%
                }
            %>
</body>
</html>
