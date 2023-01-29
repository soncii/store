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
  <h2 class="text-center">Change Password</h2>
  <form action="/user" method="post">
    <input type="hidden" name="change" value="password">
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" class="form-control" id="email"
             placeholder="Enter email" name="email" readonly
             value="<c:out value='${user.email}' />">
    </div>
    <div class="form-group">
      <label for="oldpassword">Old Password:</label>
      <input type="password" class="form-control" id="oldpassword"
             name="oldpassword">
    </div>
    <div class="form-group">
      <label for="newpassword">New Password:</label>
      <input type="password" class="form-control" id="newpassword"
             name="newpassword">
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
    <button type="submit" class="btn btn-primary">Change</button>
  </form>
</div>
</body>