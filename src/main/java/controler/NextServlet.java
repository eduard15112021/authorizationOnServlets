package controler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static controler.Validate.checkUser;

@WebServlet("/register")
public class NextServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        if (!checkUser(login)) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/a1", "root", "123456");
                PreparedStatement ps = conn.prepareStatement("INSERT INTO a1.register (login, pass, IsAdmin) VALUES ('"+login+"', '"+pass+"', DEFAULT);");
                ps.execute();
            }catch (SQLException e){
            }
            out.println("<html>\n" +
                    "<head>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<p0 class=\"message\">Registration successfully </p0>\n" +
                    "<p class=\"message\">to <a href=\"sign\">Sign In</a></p>" +
                    "</body>\n" +
                    "</html>");
            RequestDispatcher rs = request.getRequestDispatcher("index.html");
            rs.include(request, response);
        } else {
            out.println("<html>\n" +
                    "<head>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<p0 class=\"message\">You should chose different login </p0>\n" +
                    "<p class=\"message\">back to <a href=\"index.jsp\">registration</a></p>" +
                    "</body>\n" +
                    "</html>");
            RequestDispatcher rs = request.getRequestDispatcher("index.html");
            rs.include(request, response);
        }
    }
}
