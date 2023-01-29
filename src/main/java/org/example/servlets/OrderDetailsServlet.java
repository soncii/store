package org.example.servlets;

import org.example.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderDetailsServlet extends HttpServlet {
    OrderService service = new OrderService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long oid = Long.parseLong(req.getParameter("oid"));
        req.setAttribute("products",service.findOrderProducts(oid));
        req.setAttribute("order",service.findOrderById(oid));
        req.getRequestDispatcher("/OrderDetail.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long oid =Long.parseLong(req.getParameter("oid"));
        service.process(oid);
        doGet(req,resp);
    }
}
