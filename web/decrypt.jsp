
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Decrypting a key</title>
    </head>
    <body>
        <form action="DecryptServlet" method="post">
            <b>encrypted key value of the file :&nbsp;</b>
            <br><br>
            <textarea cols="50" rows="5" name="text" ></textarea>
            <br/><br/>
            <b>your private key :&nbsp;</b>
            <br><br>
            <textarea cols="50" rows="10" name="prKey"></textarea>
            <br><br>
            <input type="submit" value="Decrypt Now >>>"/>
        </form>
        <%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%>        
    </body>
</html>
