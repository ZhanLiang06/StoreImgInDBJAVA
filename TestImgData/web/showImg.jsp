<%-- 
    Document   : showImg
    Created on : May 5, 2024, 12:25:25 AM
    Author     : User
--%>
<%@page import="java.util.Base64"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="imgData" class="JPAEntity.TestImg" scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <img src="data:image/jpeg;base64,${imgData.imgData}" alt="alt"/>
        <img src="${imgData.imgData}" alt="alt"/>
        <%byte[] imageData = (byte[])imgData.getImgData();  // Retrieve the byte[] image data
            String base64Image = Base64.getEncoder().encodeToString(imageData);
        %>
        <img src="data:image/jpeg;base64,<%= base64Image %>" alt="alt"/>
    </body>
</html>
