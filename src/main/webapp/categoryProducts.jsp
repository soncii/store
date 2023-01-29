<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style>
        /* Remove the navbar's default margin-bottom and rounded borders */ 
        .navbar {
        margin-bottom: 0;
        border-radius: 0;
        }
        
        /* Add a gray background color and some padding to the footer */
        footer {
        background-color: #f2f2f2;
        padding: 25px;
        }
        
    .carousel-inner img {
        width: 100%; /* Set width to 100% */
        margin: auto;
        min-height:200px;
    }

    /* Hide the carousel text when the screen is less than 600 pixels wide */
    @media (max-width: 600px) {
        .carousel-caption {
        display: none; 
        }
    }
    </style>
</head>
<body>
<jsp:include page="header.jsp">
<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav hidden-xs">
            <h2>Filters</h2>
            <ul class="nav nav-pills nav-stacked">
            <li class="active"><a href="#section1">CPU</a></li>
                <div class="checkbox">
                    <label><input type="checkbox" value="">i5</label>
                </div>
                <div class="checkbox">
                    <label><input type="checkbox" value="">i7</label>
                </div>
                <div class="checkbox disabled">
                    <label><input type="checkbox" value="">i9</label>
                </div>
            <li class="active"><a href="#section1">Price</a></li>
                <input type="range" id="myRange" class="form-range" min="1000" max="50000" step="1000">
                <p>Value: <span id="demo"></span></p>
                <script>
                    var slider = document.getElementById("myRange");
                    var output = document.getElementById("demo");
                    output.innerHTML = slider.value;
                    
                    slider.oninput = function() {
                      output.innerHTML = this.value;
                    }
                </script>
            <li><a href="#section2">Age</a></li>
            <li><a href="#section3">Gender</a></li>
            <li><a href="#section3">Geo</a></li>
            </ul><br>
        </div>
    


        <div class="col-sm-9">
            <h1>TAKE CATEGORY NAME WHICH USER CLICKED ON</h1><br>
            <div class="row">
                ЗДЕСЬ МОЖНО ЦИКЛ И ВЫТАЩИТЬ ПРОДУКТЫ ВМЕСТО КАРТИНКИ ИЛИ ИЗ БАЗЫ ИЛИ НАЗВАНИЕ ПОДСТАВЛЯТЬ
                <div class="col-sm-3">
                    <img src="pictures/lc/laptop/l1.webp" class="img-responsive" style="width:100%" alt="Image">
                    <p>PRODUCT NAME</p>
                </div>
                <div class="col-sm-3"> 
                    <img src="pictures/home/irons/i1.webp" class="img-responsive" style="width:100%" alt="Image">
                    <p>PRODUCT NAME</p>    
                </div>
                <div class="col-sm-3">
                    <img src="pictures/kitchen/dish/d1.webp" class="img-responsive" style="width:100%" alt="Image">
                    <p>PRODUCT NAME</p>
                </div>
                <div class="col-sm-3"> 
                    <img src="pictures/sg/smartphones/s1.webp" class="img-responsive" style="width:100%" alt="Image">
                    <p>PRODUCT NAME</p>    
                </div>
                <div class="col-sm-3"> 
                    <img src="pictures/sg/smartphones/s1.webp" class="img-responsive" style="width:100%" alt="Image">
                    <p>PRODUCT NAME</p>    
                </div>
            </div>
            <ul class="pagination">
                <li><a href="#">1</a></li>
                <li class="active"><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
            </ul>
        </div>
    </div>
</div>
<br>

<footer class="container-fluid text-center">
    <p>TechnoTop</p>
</footer>

</body>
</html>
