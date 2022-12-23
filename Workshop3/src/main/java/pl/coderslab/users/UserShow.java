package pl.coderslab.users;

import pl.coderslab.users.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/edit")
public class UserShow extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            UserDAO userDao = new UserDAO();
            request.setAttribute("users", userDao.findAll());

        getServletContext().getRequestDispatcher("/users/add.jsp")
                .forward(request, response);
    }
}
