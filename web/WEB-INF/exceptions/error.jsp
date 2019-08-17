<%-- 
    Document   : error
    Created on : Feb 13, 2019, 9:47:27 AM
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
        <h1>ERROR:</h1>
        <h2>Error Code: ${error.errorCode} </h2>
        <h2>Error Message: ${error.getMessage()} </h2>
        <h2>${error.getStackTrace()}</h2>
    </body>
</html>
