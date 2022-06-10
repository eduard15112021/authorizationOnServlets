package controler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/sign")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pr = resp.getWriter();
        String title = "just title";
        pr.print("<html>\n" +
                " <head>\n" +
                " </head>\n" +
                " <body>\n" +
                "\n" +
                "<form id =\"but\"  action=\"Login\" method = \"post\">\n" +
                "                login: <input type=\"text\" name=\"login\"/>\n" +
                "                password: <input type=\"password\"  name=\"pass\"/>\n" +
                "            <input type=\"submit\" value=\"Enter\" />\n" +
                "            </form>" +
                "\n" +
                " </body>\n" +
                "</html>");
    }
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/a1","root","123456");
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        super.init(config);
//    }
}
