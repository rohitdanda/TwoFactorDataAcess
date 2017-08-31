package cloud.servlet;

import cloud.database.DataBaseAvailableFilesInCloud;
import cloud.database.DataBaseKeyStore;
import cloud.database.DataBaseInsertIntoCloud;
import cloud.model.RandomStringGenerator;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "upload", urlPatterns = {"/upload"})
@javax.servlet.annotation.MultipartConfig
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Part filePart = request.getPart("file1");
        String fileName = filePart.getSubmittedFileName();
        InputStream filecontent = filePart.getInputStream();
        byte[] content = new byte[filecontent.available()];
        filecontent.read(content);
        String title = request.getParameter("title1");
        String description = request.getParameter("description1");
        int userId = (Integer) request.getSession().getAttribute("userId");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String currentTime = sdf.format(cal.getTime());
        try {
            if (title == null || description == null || content == null || fileName == null) {
                request.setAttribute("message", "<font color='red'>Please provide all values and upload.</font>");
            } else {
                int rowNumber = DataBaseInsertIntoCloud.storeCloudStuff(title, description, content, userId, currentTime, fileName);
                if (rowNumber != 0) {
                    String randomString = RandomStringGenerator.generate();
                    DataBaseKeyStore.storeKey(rowNumber, randomString);
                    request.setAttribute("message", "File uploaded successfully. Key to download this file is : <b><h3>" + randomString + "</h3></b>Please note it down.With out this string, no one can download the file.");
                } else {
                    request.setAttribute("error", "Unable to upload the file.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "Unable to upload the file.");
        } finally {
            request.getSession().setAttribute("UPLOADED_FILES", DataBaseAvailableFilesInCloud.getAllFiles(userId));
            RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
            rd.forward(request, response);
        }
    }
}
