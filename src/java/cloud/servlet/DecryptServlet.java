package cloud.servlet;

import cloud.model.AsymmetricKeyManager;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DecryptServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String privateKey = request.getParameter("prKey");
        String text = request.getParameter("text");
        String decText = null;
        try {
            String val = AsymmetricKeyManager.decryptTheDataWithPrivateKey(text, privateKey);
            decText = "<br><br><font color='blue'>File's key was decrypted successfully.<br><h3>" + val + "</h3></font> ";
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            decText = "<font color='red'><h3> Invalid values. Pleasae enter your private key & encrypted key of the file correctly. </h3></font>";
        }
        request.setAttribute("msg", decText);
        RequestDispatcher rd = request.getRequestDispatcher("decrypt.jsp");
        rd.forward(request, response);
    }

}
