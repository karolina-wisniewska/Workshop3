package pl.coderslab.users;

import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/edit")
public class UserEdit extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        UserDAO userDao = new UserDAO();
        User read = userDao.read(Integer.parseInt(id));
//        request.setAttribute("user", read);

        HttpSession session = request.getSession();;
        session.setAttribute("user",read);

        getServletContext().getRequestDispatcher("/users/edit.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        User userToEdit = (User) session.getAttribute("user");

        userToEdit.setUserName(req.getParameter("name"));
        userToEdit.setEmail(req.getParameter("email"));
        userToEdit.setPassword(req.getParameter("password"));

        UserDAO userDao = new UserDAO();
        userDao.update(userToEdit);
        resp.sendRedirect("/workshop/user/list");
    }
}
