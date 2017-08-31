package cloud.servlet;

import cloud.database.DataBaseAvailableFilesInCloud;
import cloud.database.DataBaseRegisterUser;
import cloud.database.DataBaseUser;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username1 = request.getParameter("username1");
        String password1 = request.getParameter("password1");
        
        try {
            String key = DataBaseRegisterUser.registerUser(username1, password1);
            // getting user details and stores in the session.
            int userId = DataBaseUser.getUserId(username1);
            request.setAttribute("pasword1", password1);
            request.setAttribute("userName", username1);
            request.setAttribute("privateKey", key);
            //request.getSession().setAttribute("UPLOADED_FILES", DataBaseAvailableFilesInCloud.getAllFiles());
            RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            request.setAttribute("error", "user already exists with the given name. Please try with another name.");
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
        }
    }

}
