<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="head.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
  <h2 class="text-center">Change Account Information</h2>
  <form action="/user" method="post">
    <input type="hidden" name="change" value="details">
    <div class="form-group">
      <label for="firstname">First Name:</label>
      <input type="text" class="form-control" id="firstname"
             placeholder="Enter first name" name="firstname"
             value="<c:out value='${user.firstname}' />">
    </div>
    <div class="form-group">
      <label for="lastname">Last Name:</label>
      <input type="text" class="form-control" id="lastname"
             placeholder="Enter last name" name="lastname"
             value="<c:out value='${user.lastname}' />">
    </div>
    <div class="form-group">
      <label for="mobile">Mobile:</label>
      <input type="text" class="form-control" id="mobile"
             placeholder="Enter mobile number" name="mobile"
             value="<c:out value='${user.mobile}' />">
    </div>
    <div class="form-group">
      <label for="address">Address:</label>
      <input type="text" class="form-control" id="address"
             placeholder="Enter address" name="address"
             value="<c:out value='${user.address}' />">
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
    <% } %>
    <button style="display: block;margin: 0 auto;" name="button1" type="submit" class="btn btn-primary">Submit</button>
  </form>
  <form action="/user" method="get">
    <input type="hidden" name="isPassword" value="password">
    <button type="submit" class="btn btn-link">Change My Password</button>
  </form>

  </div>

</body>