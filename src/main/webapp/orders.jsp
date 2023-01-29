<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <jsp:include page="head.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
  <h1>Orders</h1>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>Order ID</th>
      <th>Email</th>
      <th>Address</th>
      <th>Phone Number</th>
      <th>Status</th>
      <th>Order Date</th>
      <th>Shipping Date</th>
      <th>Received Date</th>
      <th>View</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${orders}">
      <tr>
        <td>${order.oid}</td>
        <td>${order.email}</td>
        <td>${order.address}</td>
        <td>${order.phoneNumber}</td>
        <td>${order.status}</td>
        <td>${order.orderDate}</td>
        <td>${order.shippedDate==null ? '-':order.shippedDate}</td>
        <td>${order.receivedDate==null ? '-':order.receivedDate}</td>
        <td>
          <form action="/order" method="get">
            <input type="hidden" name="oid" value="${order.oid}"/>
            <input type="hidden" name="email" value="${order.email}"/>
            <button class="btn btn-primary">Details</button>
          </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

</body>
</html>
