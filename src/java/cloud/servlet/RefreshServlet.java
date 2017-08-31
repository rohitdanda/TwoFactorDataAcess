package cloud.servlet;

import cloud.database.DataBaseAvailableFilesInCloud;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RefreshServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         int userId = (Integer) request.getSession().getAttribute("userId");
        request.getSession().setAttribute("UPLOADED_FILES", DataBaseAvailableFilesInCloud.getAllFiles(userId));
        RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
        rd.forward(request, response);
    }
}
