package pl.coderslab.users;

import pl.coderslab.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/user/list")
public class UserList extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            UserDAO userDao = new UserDAO();
            request.setAttribute("users", userDao.findAll());

        getServletContext().getRequestDispatcher("/users/list.jsp")
                .forward(request, response);
    }
}
