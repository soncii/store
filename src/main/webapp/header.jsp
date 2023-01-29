<%@ page import="org.example.Entities.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.services.CategoryService" %>
<%@ page import="org.example.Entities.User" %>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">TechnoTop</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="/">Home</a></li>
            <%
                User user = (User) session.getAttribute("user");
                if (user != null) {
            %>
            <li><a href="/orders"><span class="glyphicon glyphicon-list-alt"></span> Orders</a></li>
            <%
                }
            %>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Smartphones & Gadgets
                <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="categoryProducts.jsp">Smartphones</a></li>
                    <li><a href="categoryProducts.jsp">Smart watch</a></li>
                    <li><a href="categoryProducts.jsp">Home phones</a></li>
                </ul>
            </li>

        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/cart"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
            <%
                if(session.getAttribute("user") == null){
            %>
            <li><a href="signUp.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            <%
                } else {
            %>
            <li><a href="/user"><span class="glyphicon glyphicon-user"></span> Account</a></li>
            <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
            <%
                }
                if ( session.getAttribute("user") != null && user.getRole().equals("ADMIN")) {
            %>
            <li><a href="/add-product"><span class="glyphicon glyphicon-upload"></span> Add Product</a></li>
            <%
                }
            %>
        </ul>
    </div>
</nav>