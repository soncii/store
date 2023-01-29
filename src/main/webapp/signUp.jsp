<!DOCTYPE html>
<html>
<head>
   <jsp:include page="head.jsp" />

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
<jsp:include page="header.jsp" />
<form class="form-horizontal" action="/register" method=post style="margin-top: 5%; margin-right: 100px; padding: auto;">
    <div class="form-group">
        <label class="control-label col-sm-2" for="email">Email:</label>
        <div class="col-sm-4">
            <input type="email" class="form-control" id="email" name="email" placeholder="Enter email" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="pwd">Password:</label>
        <div class="col-sm-4">
            <input type="password" class="form-control" id="pwd" name="pwd" placeholder="Enter password" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="pwd">First Name:</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="name" name="name" size="30" placeholder="First name" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="pwd">Last Name:</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="lastname" name="lastname" size="30" placeholder="Last name" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="pwd">Mobile number:</label>
        <div class="col-sm-4">
            <input type="tel" id="phone" name="phone" placeholder="+7 (777) 111 0000" pattern="+7 ([0-9]{3})[0-9]{3} [0-9]{4}" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="pwd">Address:</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="address" name="address" size="50" placeholder="Address" required>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-4">
            <button type="submit" class="btn btn-default">Sign Up</button>
        </div>
    </div>
</form>
</body>
</html>