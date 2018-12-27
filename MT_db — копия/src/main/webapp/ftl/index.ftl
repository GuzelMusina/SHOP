<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title>Amado - Furniture Ecommerce Template | Home</title>

    <!-- Favicon  -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Style CSS -->
    <link rel="stylesheet" href="css/core-style.css">
    <link rel="stylesheet" href="style.css">

</head>

<body>
<script>
    function getCart() {
        $.ajax({
            type: 'GET',
            url: '/shop?list',
        }).done(function (data) {
            let tableHtml = "";
            tableHtml += '<table>';
            tableHtml +=
                    '<tr>' +
                    '<th>' +
                    'id' +
                    '</th>' +
                    '<th>' +
                    'name' +
                    '</th>' +
                    '<th>' +
                    'count' +
                    '</th>' +
                    '</tr>';
            for (let i = 0; i < data.length; i++) {
                tableHtml += '<tr>' +
                        '<td>' + data[i].id + '</td>' +
                        '<td>' + data[i].name + '</td>' +
                        '<td>' + data[i].count + '</td>' +
                        '<td>' + '<button onclick="deleteProduct(' + data[i].id + ')" formaction="delete" id = ' + 1 + '>Delete</button>' + '</td>' +
                        '</tr>';
            }
            tableHtml += '</table>';
            $("#goods_table").html(tableHtml);


        }).fail(function () {
            alert('ALL BAD')
        })
    }

    function search(query) {
        $.ajax({
            type: 'GET',
            url: '/shop',
            data: {
                q: query
            }
        }).done(function (data) {
            let tableHtml = "";
            tableHtml += '<table>';
            tableHtml +=
                    '<tr>' +
                    '<th>' +
                    'id' +
                    '</th>' +
                    '<th>' +
                    'title' +
                    '</th>' +
                    '</tr>';
            for (let i = 0; i < data.length; i++) {
                tableHtml += '<tr>' +
                        '<td>' + data[i].id + '</td>' +
                        '<td>' + data[i].name + '</td>' +
                        '</tr>';
            }
            tableHtml += '</table>';
            $("#products_table").html(tableHtml);
        })
    }
</script>
<body onload="getCart()">
<div class="search-wrapper section-padding-100">
    <div class="search-close">
        <i class="fa fa-close" aria-hidden="true"></i>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="search-content">
                    <form>
                        <input type="text" id="q" onkeyup="search(document.getElementById('q').value)" placeholder="Type your keyword...">
                        <div id="products_table">

                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="main-content-wrapper d-flex clearfix">

    <header class="header-area clearfix">
        <!-- Close Icon -->
        <div class="nav-close">
            <i class="fa fa-close" aria-hidden="true"></i>
        </div>
        <!-- Logo -->
        <div class="logo">
            <a href="/index"><img src="img/core-img/logo.jpg" alt=""></a>
        </div>
        <!-- Amado Nav -->
        <nav class="amado-nav">
            <ul>
                <li class="active"><a href="/index">Home</a></li>
                <li><a href="/shop">Shop</a></li>
                <li><a href="/cart">Cart</a></li>
                <li><a href="/signin">SignIn</a></li>
                <li><a href="/login">SignUp</a></li>
            </ul>
        </nav>
        <div class="cart-fav-search mb-100">
            <a href="/cart" class="cart-nav"><img src="img/core-img/cart.png" alt=""> Cart </a>
            <a href="#" class="search-nav"><img src="img/core-img/search.png" alt=""> Search</a>
        </div>
        <div class="social-info d-flex justify-content-between">
            <a href="https://www.instagram.com/euphoriaendlesss"><i class="fa fa-instagram" aria-hidden="true"></i></a>
            <a href="https://www.facebook.com/guzel.musina"><i class="fa fa-facebook" aria-hidden="true"></i></a>
            <a href="http://twitter.com/euphoriaendless"><i class="fa fa-twitter" aria-hidden="true"></i></a>
        </div>
    </header>

    <div class="products-catagories-area clearfix">
        <div class="amado-pro-catagory clearfix">

            <div class="single-products-catagory clearfix">
                <a href="/shop">
                    <img src="img/bg-img/1.jpg" alt="">
                    <div class="hover-content">
                        <div class="line"></div>
                        <p>From $180</p>
                        <h4>Modern Chair</h4>
                    </div>
                </a>
            </div>

            <div class="single-products-catagory clearfix">
                <a href="/shop">
                    <img src="img/bg-img/2.jpg" alt="">

                    <div class="hover-content">
                        <div class="line"></div>
                        <p>From $180</p>
                        <h4>Minimalistic Plant Pot</h4>
                    </div>
                </a>
            </div>

            <div class="single-products-catagory clearfix">
                <a href="/shop">
                    <img src="img/bg-img/3.jpg" alt="">

                    <div class="hover-content">
                        <div class="line"></div>
                        <p>From $180</p>
                        <h4>Modern Chair</h4>
                    </div>
                </a>
            </div>

            <div class="single-products-catagory clearfix">
                <a href="/shop">
                    <img src="img/bg-img/4.jpg" alt="">

                    <div class="hover-content">
                        <div class="line"></div>
                        <p>From $180</p>
                        <h4>Night Stand</h4>
                    </div>
                </a>
            </div>

            <div class="single-products-catagory clearfix">
                <a href="/shop">
                    <img src="img/bg-img/5.jpg" alt="">

                    <div class="hover-content">
                        <div class="line"></div>
                        <p>From $18</p>
                        <h4>Plant Pot</h4>
                    </div>
                </a>
            </div>

            <div class="single-products-catagory clearfix">
                <a href="/shop">
                    <img src="img/bg-img/6.jpg" alt="">
                    <div class="hover-content">
                        <div class="line"></div>
                        <p>From $320</p>
                        <h4>Small Table</h4>
                    </div>
                </a>
            </div>

            <div class="single-products-catagory clearfix">
                <a href="/shop">
                    <img src="img/bg-img/7.jpg" alt="">

                    <div class="hover-content">
                        <div class="line"></div>
                        <p>From $318</p>
                        <h4>Metallic Chair</h4>
                    </div>
                </a>
            </div>

            <div class="single-products-catagory clearfix">
                <a href="/shop">
                    <img src="img/bg-img/8.jpg" alt="">
                    <div class="hover-content">
                        <div class="line"></div>
                        <p>From $318</p>
                        <h4>Modern Rocking Chair</h4>
                    </div>
                </a>
            </div>

            <div class="single-products-catagory clearfix">
                <a href="/shop">
                    <img src="img/bg-img/9.jpg" alt="">
                    <div class="hover-content">
                        <div class="line"></div>
                        <p>From $318</p>
                        <h4>Home Deco</h4>
                    </div>
                </a>
            </div>
        </div>
    </div>
</div>
<section class="newsletter-area section-padding-100-0">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-12 col-lg-6 col-xl-7">
                <div class="newsletter-text mb-100">
                    <h2>Easy go to the future, easy furniture</h2>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="js/jquery/jquery-2.2.4.min.js"></script>
<!-- Popper js -->
<script src="js/popper.min.js"></script>
<!-- Bootstrap js -->
<script src="js/bootstrap.min.js"></script>
<!-- Plugins js -->
<script src="js/plugins.js"></script>
<!-- Active js -->
<script src="js/active.js"></script>

</body>

</html>