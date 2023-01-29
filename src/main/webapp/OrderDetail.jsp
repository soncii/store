<%@ page import="org.example.Entities.User" %>
<%@ page import="org.example.Entities.Order" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
  <h1>Order #${order.oid}</h1>
  <p>Email: ${order.email}</p>
  <p>Phone number: ${order.phoneNumber}</p>
  <p style="color:${order.status=="received" ? 'green':'red'}">Status: ${order.status}</p>
  <p>Order date: ${order.orderDate}</p>
  <p>Shipped date: ${order.shippedDate == null ? '-': order.shippedDate}</p>
  <p>Claimed date: ${order.receivedDate == null ? '-':order.receivedDate}</p>

  <table class="table table-striped">
    <thead>
    <tr>
      <th>Image</th>
      <th>Name</th>
      <th>Category</th>
      <th>Description</th>
      <th>Price</th>
      <th>Quantity</th>
      <th>Total</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${products}">
      <tr>
        <td>
          <img src="data:image/jpeg;base64,${product.base64}" alt="Product Image" class="img-thumbnail"
                   style="max-width: 200px; max-height: 200px; width: auto; height: auto; position: center">
        </td>
        <td>${product.name}</td>
        <td>${product.cname}</td>
        <td>${product.description}</td>
        <td>${product.price}</td>
        <td>${product.quantity}</td>
        <td>${product.price*product.quantity}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <p>Total sum is ${order.totalSum}</p>
  <%
    User user = (User) request.getSession().getAttribute("user");
    Order order = (Order) request.getAttribute("order");
    if (user.getRole().equals("ADMIN") && !order.getStatus().equals("received")) {
  %>
  <form action="/order" method="post">
    <input type="hidden" name="oid" value="${order.oid}">
    <button class="btn btn-primary">Process</button>
  </form>
  <%
    }
  %>
</div>

</body>
</html>
