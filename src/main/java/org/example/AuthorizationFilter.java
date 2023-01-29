package org.example;

import org.example.Entities.Order;
import org.example.Entities.User;
import org.example.services.OrderService;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpRequest;

public class AuthorizationFilter implements Filter {
    OrderService orderService = new OrderService();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            //user is not logged in
            ((HttpServletResponse) response).sendRedirect("login.jsp");
        } else {
            //user is logged in
            User user = (User) session.getAttribute("user");
            if (hasAccess(user, httpRequest)) {
                //user has access
                chain.doFilter(request, response);
            } else {
                //user does not have access
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }
    }

    private boolean hasAccess(User user, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        if (user.getRole().equals("USER")) {
            if (requestURI.equals("/test") || requestURI.equals("/cart") || requestURI.equals("/orders")) return true;
            if (requestURI.equals("/order")) {
                String oidString = request.getParameter("oid");
                if (oidString==null) return false;
                Long oid = Long.parseLong(oidString);
                Order orderById = orderService.findOrderById(oid);
                return orderById.getEmail().equals(user.getEmail());
            }

        }
        return user.getRole().equals("ADMIN");
    }



    @Override
    public void destroy() {
        //cleanup any resources
    }
}