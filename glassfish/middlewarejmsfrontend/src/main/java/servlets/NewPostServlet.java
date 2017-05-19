package servlets;

import jms.JMSHandler;
import model.Instatweet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.joda.time.DateTime;
import utils.IdGenerator;
import utils.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/newpost")
public class NewPostServlet extends HttpServlet {
    public NewPostServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType().contains("multipart/form-data")) {
            final ServletContext context = getServletContext();

            // File upload configuration
            File file;
            final String filePath = context.getInitParameter("file-upload");
            final DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(5000 * 1024);

            factory.setRepository(new File(filePath + "/tmp"));
            final ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(5000 * 1024);

            // Request parsing
            try {
                List<FileItem> fileItems = upload.parseRequest(req);
                final Instatweet instatweet = new Instatweet();
                instatweet.setUser(UserDao.get((String) req.getSession().getAttribute("username")));

                // Iterate through request params
                for (FileItem fi : fileItems) {
                    final String fieldName = fi.getFieldName();

                    // Form field
                    if (fi.isFormField()) {
                        if (fieldName.equals("j_text")) {
                            instatweet.setText(fi.getString());

                        } else if (fieldName.equals("j_date_time")) {
                            instatweet.setDateTime(DateTime.parse(fi.getString()));
                        }

                        // File
                    } else {
                        final String fileName = fi.getName();

                        if (fileName.lastIndexOf("/") >= 0) {
                            file = new File(filePath + fileName.substring(fileName.lastIndexOf("/")));
                        } else {
                            final String newFileName = filePath + '/' + IdGenerator.next("images") + "." +
                                    FilenameUtils.getExtension(fileName);
                            file = new File(newFileName);
                        }

                        fi.write(file);

                    }
                }
                final JMSHandler handler = new JMSHandler();
                handler.tweet(instatweet);
                final RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
                requestDispatcher.forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

