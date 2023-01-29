<%@ page import="org.example.Entities.Category" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>File Upload Servlet JSP JDBC MySQL Example</title>
  <link
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<div class="container col-lg-6">
  <h1 class="text-center">Add new product </h1>
  <div class="card">
    <div class="card-body">
      <form method="post" class="form-group" action="/add-product"
            enctype="multipart/form-data">
        <div class="form-group">
          <input type="text" class="form-control" name="name" size="50" />
        </div>
        <div class="form-group">
         <label>Description</label>
          <textarea class="form-control" name="description"></textarea>
        </div>
        <div class="form-group">
          <label>Price</label>
          <input class="form-control" name="price" size="50" type="number"/>
        </div>
        <div class="form-group" name="category">
          <%
            List<Category> listCategory = (List<Category>)request.getAttribute("listCategory");
          %>
          <select name="category">
            <% for (Category category : listCategory) { %>
            <option value="<%= category.getCid() %>"><%= category.getCname() %></option>
            <% } %>
          </select>
        </div>
        <div class="form-group">
          <label>Photo</label>
          <input type="file" name="file" size="50" />
        </div>
        <input type="submit" value="Save" class="btn btn-success">
      </form>
    </div>
  </div>
</div>
</body>
</html>