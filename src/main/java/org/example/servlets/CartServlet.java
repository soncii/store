package org.example.servlets;

import org.example.Entities.User;
import org.example.services.CartService;
import org.example.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class CartServlet extends HttpServlet {
    CartService service = new CartService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("products", service.findUserCart(user.getEmail()));
        req.getRequestDispatcher("/cart.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        String pid = req.getParameter("pid");
        if (pid==null) return;
        service.addToCart(user.getEmail(),Long.parseLong(pid));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid = req.getParameter("pid");
        String quantity = req.getParameter("quantity");
        if (pid==null || quantity==null) return;
        User user = (User)req.getSession().getAttribute("user");
        service.updateQuantity(user.getEmail(),Long.parseLong(pid), Long.parseLong(quantity));
        return ;
        //resp.sendRedirect("/cart");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid = req.getParameter("pid");
        if (pid==null) return;
        User user = (User)req.getSession().getAttribute("user");
        service.deleteProductFromCart(user.getEmail(),Long.parseLong(pid));
        //req.getRequestDispatcher("/cart").forward(req,resp);
    }
}
