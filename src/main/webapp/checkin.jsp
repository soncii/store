<%@ page import="org.example.services.CartService" %>
<%@ page import="org.example.Entities.User" %>
<%@ page import="org.example.Entities.Product" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
  <title>Check-In</title>
    <jsp:include page="head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
  <h1 class="text-center my-4">Check-In</h1>

  <form action="/checkin" method="post">
    <strong>Ordered items:</strong>
    <table class="table">
      <thead>
      </thead>
      <tbody>
      <tr>
        <td>Name</td>
        <td>Quantity</td>
        <td>Total Price</td>
      </tr>
      <c:forEach var="product" items="${products}">
        <tr>
          <td>${product.name}</td>
          <td>${product.quantity}</td>
          <td>${product.price*product.quantity}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <%
        User user = (User) request.getSession().getAttribute("user");
    %>
    <div class="form-group">
        <label for="shippingAddress"><strong>Shipping Address:</strong></label>
      <input type="text" class="form-control" id="shippingAddress" name="shippingAddress" value="<%= user.getAddress() %>" required/>
    </div>

    <div class="form-group">
        <label for="phoneNumber"><strong>Phone Number:</strong></label>
      <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="<%= user.getMobile() %>" required/>
    </div>
    <div  class="form-group">
      <label for="total"><strong>Total Price:</strong></label>
        <p class="h4" id="total">${Total}</p>
    </div>
      <%
          String errorMessage = (String) request.getAttribute("errorMessage");
          if (errorMessage != null) {
      %>
      <div class="form-group">
          <div class="col-sm-offset-2 col-sm-4">
              <p style="color: red;"><%= errorMessage %></p>
          </div>
      </div>
      <br>
      <% } %>

    <input type="submit" class="btn btn-primary" value="Confirm" />

  </form>

</div>