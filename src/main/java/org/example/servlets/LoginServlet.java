package org.example.servlets;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.Entities.User;
import org.example.services.CartService;
import org.example.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginServlet extends HttpServlet {
    UserService userService = new UserService();
    CartService cartService = new CartService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errorMessage","");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("pwd");
        Optional<User> user = userService.findEntityById(email);
        if (user.isPresent() &&
                BCrypt.verifyer().verify(password.toCharArray(), user.get().getPassword().toCharArray()).verified) {
            req.getSession().setAttribute("user", user.get());
            req.getSession().setAttribute("cart", cartService.findUserCart(user.get().getEmail()));
            resp.sendRedirect("/");
        } else {
            req.setAttribute("errorMessage", "Invalid email or password");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
