package org.example.servlets;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.Entities.User;
import org.example.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        String isPassword = req.getParameter("isPassword");
        req.setAttribute("user", user);
        if (isPassword==null) {
            req.getRequestDispatcher("/modifyUser.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/modifyPassword.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String change = req.getParameter("change");
        if (change.equals("password")) {
            String oldpassword = req.getParameter("oldpassword");
            String newpassword = req.getParameter("newpassword");
            if (!BCrypt.verifyer().verify(oldpassword.toCharArray(), user.getPassword().toCharArray()).verified) {
                req.setAttribute("errorMessage", "Password is incorrect");
                doGet(req, resp);
            } else {
                user.setPassword(BCrypt.withDefaults().hashToString(6, newpassword.toCharArray()));
                User updated = service.update(user);
                req.getSession().setAttribute("user",updated);
                resp.sendRedirect("/");
            }
        } else {
            String firstname = req.getParameter("firstname");
            String lastname = req.getParameter("lastname");
            String address = req.getParameter("address");
            String mobile = req.getParameter("mobile");
            String errorMessage = UserService.validateNumber(mobile);
            if (errorMessage.equals("")) {
                user.setFirstname(firstname);
                user.setLastname(lastname);
                user.setAddress(address);
                user.setMobile(mobile);
                User updated = service.update(user);
                req.getSession().setAttribute("user",updated);
                resp.sendRedirect("/");
            } else {
                req.setAttribute("errorMessage", errorMessage);
                doGet(req, resp);
            }
        }

    }
}
