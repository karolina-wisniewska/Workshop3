package pl.coderslab.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/edit")
public class UserEdit extends HttpServlet {
    private final UserDAO userDao = new UserDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User read = userDao.read(Integer.parseInt(id));
        request.setAttribute("user", read);

        getServletContext().getRequestDispatcher("/users/edit.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        User userToEdit = userDao.read(Integer.parseInt(id));
        userToEdit.setUserName(req.getParameter("name"));
        userToEdit.setEmail(req.getParameter("email"));
        userToEdit.setPassword(req.getParameter("password"));

        userDao.update(userToEdit);
        resp.sendRedirect(req.getContextPath() + "/user/list");
    }
}
