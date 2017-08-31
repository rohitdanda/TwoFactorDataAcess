package cloud.servlet;

import cloud.database.DataBaseFetchFile;
import cloud.database.DataBaseKeyValidator;
import cloud.model.DownloadableFile;
import cloud.model.PenDriveValidator;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String key = request.getParameter("key");
        //String uKey = (String)request.getSession().getAttribute("userKey");
        boolean validDeviceFound = PenDriveValidator.validate();
        if (request.getSession().getAttribute("userId") == null) {
            request.setAttribute("error", "User should be login first.");
            RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
            rd.forward(request, response);
        } else {
            if (!validDeviceFound) {
                request.setAttribute("error", "Valid pendrive device was not found. Please insert and try to download...");
                RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
                rd.forward(request, response);
            } else {
                boolean validKey = DataBaseKeyValidator.validate(id, key);
                //boolean validKey = key.equalsIgnoreCase(uKey);
                if (!validKey) {
                    request.setAttribute("error", "Entered file's key is invalid.");
                    RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
                    rd.forward(request, response);
                } else {
                    DownloadableFile f = DataBaseFetchFile.getFileBlob(id);
                    
                    response.setContentType("text/html");
                    
                    response.setContentType("APPLICATION/OCTET-STREAM");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + f.getName() + "\"");
                    OutputStream out = response.getOutputStream();
                    
                    out.write(f.getContent());
                    out.flush();
                    out.close();
                }
            }
        }
    }
}
