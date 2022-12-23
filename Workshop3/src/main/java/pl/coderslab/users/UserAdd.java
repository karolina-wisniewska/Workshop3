package pl.coderslab.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/add")
public class UserAdd extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/users/add.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userToAdd = new User();
        userToAdd.setUserName(req.getParameter("name"));
        userToAdd.setUserName(req.getParameter("email"));
        userToAdd.setUserName(req.getParameter("password"));

        UserDAO userDao = new UserDAO();
        userDao.create(userToAdd);
    }
}
