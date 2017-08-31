
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
    </head>
    <body>

        <b>Please enter your details to login</b><br><br>
        <form action="login" method="post">
            <b>user name :&nbsp;</b><input type="text" name="username1"/><br/><br/>
            <b>password :&nbsp;</b><input type="password" name="password1"/><br/><br/>
            <input type="submit" value="login"/>
        </form>
        <font color="red">
        <%
            String e = (String) request.getAttribute("error");
            if (e != null) {
                out.println("<br/>");
                out.println(e);
            }
        %>
        </font>
        <!--
                <br/><br/><br/>
                <a href="register.jsp"><u>register</u></a>
        -->
    </body>
</html>
