package controler;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
/*
 * дописать реализацию регистрации пользователей новая колонка исадмин
 * админ видит всех пользователей (обязательно, серега будет проверять!)
 */
import static controler.Validate.checkUser;

@WebServlet("/Login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        if (checkUser(login, pass)) {
            out.println("Welcome " + login);
            int IsAdmin = 0;
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/a1", "root", "123456");
                PreparedStatement ps = conn.prepareStatement("select IsAdmin from a1.register where login = '" + login + "' and pass = '" + pass + "';");
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    IsAdmin = resultSet.getInt("IsAdmin");
                }

                if (IsAdmin == 1) {
                    out.println("All registers:");
                    ps = conn.prepareStatement("select * from a1.register;");
                    resultSet = ps.executeQuery();
                    while (resultSet.next()) {
                        out.print("login: "+resultSet.getString("login")+"\t"+"password: ");
                        out.println(resultSet.getString("pass"));
                    }

                }
                else {out.print("hello:"+login);}
            }catch (SQLException e){

            }
            RequestDispatcher rs = request.getRequestDispatcher("index.html");
            rs.include(request, response);
        } else {
            out.println("Username or Password incorrect");
            RequestDispatcher rs = request.getRequestDispatcher("index.html");
            rs.include(request, response);
        }
    }


}