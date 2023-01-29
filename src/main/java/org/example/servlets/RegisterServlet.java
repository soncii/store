package org.example.servlets;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.Entities.User;
import org.example.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setEmail( req.getParameter("email"));
        user.setPassword(BCrypt.withDefaults().hashToString(6, req.getParameter("pwd").toCharArray()));
        user.setFirstname(req.getParameter("name"));
        user.setLastname( req.getParameter( "lastname"));
        user.setAddress( req.getParameter("address"));
        user.setMobile(req.getParameter("phone"));
        String message = service.validate(user);
        if (!message.isEmpty()) {
            req.setAttribute("error", message);
            RequestDispatcher errorDispatcher = req.getRequestDispatcher("/signUp.jsp");
            errorDispatcher.forward(req,resp);
            return;
        }
        service.insertUser(user);
        resp.sendRedirect("/login.jsp");

    }
}
