package cloud.servlet;

import cloud.database.DataBaseAvailableFilesInCloud;
import cloud.database.DataBaseDeleteRecord;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long rowId = Long.parseLong(req.getParameter("rowId"));
        DataBaseDeleteRecord.delete(rowId);
        int userId = (Integer) req.getSession().getAttribute("userId");
        req.getSession().setAttribute("UPLOADED_FILES", DataBaseAvailableFilesInCloud.getAllFiles(userId));
        RequestDispatcher rd = req.getRequestDispatcher("dashboard.jsp");
        rd.forward(req, resp);
    }
}
