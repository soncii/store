<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Details</title>
</head>
<body>
<h2>Product Details</h2>
<table>
    <tr>
        <th>Product ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Image</th>
        <th>Price</th>
        <th>Category ID</th>
        <th>Category Name</th>
    </tr>
    <tr>
        <td>${product.pid}</td>
        <td>${product.name}</td>
        <td>${product.description}</td>
        <td>
            <img src="data:image/jpeg;base64,<c:out value='${base64Image}'/>" alt="Product Image" width="100" height="100"/>
        </td>
        <td>${product.price}</td>
        <td>${product.cid}</td>
        <td>${product.cname}</td>
    </tr>
</table>
</body>
</html>
Note: The above JSP code assumes that the image field of the ProductDTO object is a byte array, and it is being encoded as base64 to be displayed as an image using an img tag.