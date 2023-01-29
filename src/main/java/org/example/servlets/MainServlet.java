package org.example.servlets;

import org.example.services.CategoryService;
import org.example.services.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("category");
        req.setAttribute("products", productService.findAll(category));
        req.setAttribute("categories",categoryService.findAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/mainPage.jsp");
        dispatcher.forward(req,resp);
    }

}