<%@page import="cloud.model.AsymmetricKeyManager"%>
<%@page import="cloud.model.RandomStringGenerator"%>
<%@page import="java.util.List"%>
<%@page import="cloud.model.CloudRow"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
    </head>
    <body>
        <table>
            <col width="400"/>
            <col width="400"/>
            <tr>
                <td>
                    <h3><font color="green">Hi, <%=session.getAttribute("userName")%>.Welcome ! </font></h3>
                </td>
                <td>
                    <a href="LogoutServlet">logout</a>
                </td>
            </tr>
        </table>
        <%
            String userName = (String) request.getSession().getAttribute("userName");
            if (userName.equalsIgnoreCase("admin")) {
        %>
        <h3>Add an User to the cloud system. </h3>
        <form action="register" method="post">
            <b>user name :&nbsp;</b><input type="text" name="username1"/><br/><br/>
            <b>password :&nbsp;</b><input type="password" name="password1"/><br/><br/>           
            <input type="submit" value="register"/>
        </form>
        <br><br>
        <font color="green">
        <%=request.getAttribute("userName") == null ? "" : "<b>" + request.getAttribute("userName") + " </b>is created with the password <b>" + request.getAttribute("pasword1") + "</b> and Privatekey is <br><br><b>" + request.getAttribute("privateKey") + "</b> <br><br>Values will be encrypted with public key and user need to decrypt with his/her private key.<br>Please pass this information to him/her.<br>"%>
        </font>
        <br><br>
        <%
            }
        %>

        <form action="upload" method="post" enctype = "multipart/form-data">
            <b>title :&nbsp;</b><input type="text" name="title1"/><br/><br/>
            <b>description :&nbsp;</b><input type="text" name="description1"/><br/><br/>
            <b>browse the file :&nbsp;</b><input type="file" name="file1"/><br/><br/>
            <input type="submit" value="upload"/>
        </form>
        <!--font color="green">
        
        <%
            /*
            String e1 = (String) request.getAttribute("message");
            if (e1 != null) {
                out.println("<br/>");
                out.println(e1);
            }
             */
        %>
        
        </font-->
        <font color="red">
        <%            String e2 = (String) request.getAttribute("error");
            if (e2 != null) {
                out.println("<br/><br/>");
                out.println(e2);
            }
        %>
        </font>
        <br>
        <a href="decrypt.jsp" target="_blank">decrypt a key with your private key</a>
        <br>
        <h2><u>All uploaded files in the cloud are : </u></h2>
        <h4>
            <form action="RefreshServlet" method="post">
                <input type="submit" value="refresh cloud content"/>
            </form>
        </h4>
        <%
            List<CloudRow> list = (List<CloudRow>) request.getSession().getAttribute("UPLOADED_FILES");

            for (CloudRow row : list) {
                String publicKey = row.getPublicKeyOfUser();
                String plainVal = row.getEncKey();
                String encVal = AsymmetricKeyManager.encryptTheDataWithPublicKey(plainVal, publicKey);
                out.println("<br><br>");
                out.println("<form action='DownloadServlet' method='post'>");
                out.println("Title : " + row.getTitle() + "<br>");
                out.println("Description : " + row.getDescription() + "<br>");
                out.println("Uploaded by : " + row.getUploadedBy() + " on " + row.getUploadedOn()+"<br>");
                out.println("Encrypted file key : <h4>" + row.getDisplayEncValue() + "</h4><br>Decrypt this with your private key and enter below to download.");
                out.println("<input type='hidden' name='id' value='" + row.getId() + "'/><br><br>");
                out.print("File key after decrypted with your private key : <input type='text' name='key'/>   <input type='submit' value='Download'/>");
                out.print("");
                out.println("</form>");

                if ((row.getUploadedBy().equalsIgnoreCase((String) request.getSession().getAttribute("userName"))) || ("admin".equalsIgnoreCase((String) request.getSession().getAttribute("userName")))) {
                    out.println("<br>");
                    out.println("<a href=DeleteServlet?rowId=" + row.getId() + "> delete </a>");
                    out.println("<br><br>");
                }
            }
        %>
    </body>
</html>
