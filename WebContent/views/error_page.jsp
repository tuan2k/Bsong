<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sorry ! something went wrong...</title>
    </head>
    <body>
    	<div>
            <img src="<%=request.getContextPath() %>/resources/public/images/error.png" class="img-fluid" >
            <h4 class="display-3">Sorry ! Something went wrong ...</h4>
            <a href="<%=request.getContextPath() %>/home"><h1 style="color:blue;">Home</h1></a>
        </div>
        
    </body>
</html>
