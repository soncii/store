<%@ page import="org.example.Entities.User" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <jsp:include page="head.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="col-sm-3 sidenav hidden-xs" style="float:left;">
    <div class="w3-sidebar w3-bar-block" style="background-color: rgb(45, 44, 44);color: #f2f2f2; height: 80%;"
         id="mySidebar">
        <input type="text" id="mySearch" style="background-color: rgb(45, 44, 44); border-color: azure;"
               onkeyup="myFunction()" placeholder="Search.." title="Type in a category">
        <ul id="myMenu">
            <c:forEach var="category" items="${categories}">
                <li>
                    <form action="/">
                        <input type="hidden" name="category" value="${category.cname}">
                        <button class="w3-bar-item w3-button">${category.cname}</button>
                    </form>
                </li>
            </c:forEach>
        </ul>
        <input type="range" id="myRange" class="form-range" min="5000" max="150000" step="5000">
        <p>Value: <span id="demo"></span></p>
    </div>
</div>

<div class="container text-center">
    <h1>Home Appliances and Electronics online-shop</h1><br>
    <div class="row">
        <%
            User user = (User) session.getAttribute("user");
            boolean isAdmin = user != null && user.getRole().equals("ADMIN");
        %>
        <c:forEach var="product" items="${products}">
            <div class="col-sm-3">
                <img src="data:image/jpeg;base64,<c:out value='${product.base64}'/>"
                     class="img-responsive" style="max-width: 200px; max-height: 200px; width: auto; height: auto;
                     margin:auto" alt="Image">
                <p>${product.name}</p>
                <%
                    if (isAdmin) {
                %>
                <button value="${product.pid}" name="deleteButton" class="btn btn-danger">Delete</button>
                <%
                } else {
                %>
                <button value="${product.pid}" name="addButton" class="btn btn-primary">Add to cart</button>
                <%
                    }
                %>
                &nbsp;
            </div>
        </c:forEach>
    </div>
</div>
<br>

<footer class="container-fluid text-center">
    <p>TechnoTop</p>
</footer>

</body>
<script>
    let addButtons = document.querySelectorAll('[name="addButton"]');
    addButtons.forEach(input => {
        input.addEventListener('click', event => {
            let pid = input.value;
            console.log("POST: " + pid);
            fetch('/cart?pid=' + pid, {
                method: 'POST',
                headers: {'Content-Type': 'application/json'}
            }).then((response) => {
                if (response.redirected) {
                    window.location.href = response.url;
                }
            });
        })
    });
</script>

</html>
