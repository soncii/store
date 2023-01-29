<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="head.jsp"/>
    <%--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">--%>
    <%--    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>--%>
    <%--    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>--%>
    <%--    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>--%>
    <%--    <title>Cart</title>--%>
</head>
<body onload="updateTotalPrice()">
<jsp:include page="header.jsp"/>
<div class="container">
    <h1 class="text-center">Your Cart</h1>
    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>Image</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Quantity</th>
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
                <td>${product.description}</td>
                <td class="price">${product.price}</td>
                <td>
                <input data-product-id="${product.pid}" type="number" class="quantity" value="${product.quantity}">
                <br>
                <button value="${product.pid}" name="deleteButton" class="btn btn-danger">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p class="h4">Total price: <span id="total-price"></span></p>
    <br>
    <form action="/checkin" method="get">
        <button class="btn btn-success">Order</button>
    </form>
</div>
</body>
<script>
    function updateTotalPrice() {
        var totalPrice = 0;
        var quantityInputs = document.getElementsByClassName("quantity");
        var prices = document.getElementsByClassName("price");
        console.log(prices);
        console.log(quantityInputs)
        for (var i = 0; i < quantityInputs.length; i++) {
            var price = parseFloat(prices[i].innerText);
            var quantity = parseInt(quantityInputs[i].value);
            console.log("PRice: " + price + "Qua: " + quantity);
            totalPrice += price * quantity;
        }
        document.getElementById("total-price").textContent = totalPrice.toFixed(2);
    }

    var quantityInputs = document.getElementsByClassName("quantity");
    for (var i = 0; i < quantityInputs.length; i++) {
        quantityInputs[i].addEventListener("change", updateTotalPrice);
    }

    var quantity = document.querySelectorAll('.quantity');
    var deleteButtons = document.querySelectorAll('[name="deleteButton"]');
    console.log(deleteButtons)
    var timer;
    quantity.forEach(input => {
        input.addEventListener('change', event => {
            clearTimeout(timer);
            timer = setTimeout(() => {
                let pid = input.getAttribute('data-product-id');
                let quantity = input.value;
                console.log("PUT: " + pid + " " + quantity);
                fetch('/cart?pid=' + pid + '&quantity=' + quantity, {
                    method: 'PUT',
                    headers: {'Content-Type': 'application/json'}
                });
            }, 1500);
        });
    });
    deleteButtons.forEach(input => {
        input.addEventListener('click', event => {
            let pid = input.value;
            console.log("DELETE: " + pid);
            fetch('/cart?pid=' + pid, {
                method: 'DELETE',
                headers: {'Content-Type': 'application/json'}
            });
            window.location.reload();
        });
    });

</script>

