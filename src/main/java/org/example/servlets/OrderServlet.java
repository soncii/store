package org.example.servlets;

import org.example.Entities.Order;
import org.example.Entities.User;
import org.example.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends HttpServlet {
    OrderService service = new OrderService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user") ;
        List<Order> all = service.findAll(user);
        req.setAttribute("orders",all);
        req.getRequestDispatcher("/orders.jsp").forward(req,resp);
    }
}
