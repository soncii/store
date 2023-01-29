package org.example.servlets;

import org.example.Entities.Product;
import org.example.Entities.User;
import org.example.services.CartService;
import org.example.services.OrderService;
import org.example.services.ProductService;
import org.example.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CheckinServlet extends HttpServlet{
    CartService service = new CartService();
    OrderService orderService = new OrderService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req = populateRequest(req);
        req.getRequestDispatcher("/checkin.jsp").forward(req,resp);
    }

    private HttpServletRequest populateRequest(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        List<Product> userCart = service.findUserCart(user.getEmail());
        req.setAttribute("products",userCart);
        req.setAttribute("Total", CartService.calculateTotal(userCart));
        return req;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String shippingAddress = req.getParameter("shippingAddress");
        String phoneNumber = req.getParameter("phoneNumber");
        User user = (User) req.getSession().getAttribute("user");
        String errorMessage = UserService.validateNumber(phoneNumber);
        if (errorMessage.equals("")) {
            resp.sendRedirect("/");
            boolean result = orderService.insertOrder(service.findUserCart(user.getEmail()), user.getEmail(), shippingAddress, phoneNumber);
            System.out.println(result);
        } else {
            req.setAttribute("errorMessage",errorMessage);
            req=populateRequest(req);
            req.getRequestDispatcher("/checkin.jsp").forward(req,resp);
        }
    }
}
