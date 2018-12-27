<html>
<head>
    <title>Shop</title>

    <link rel="stylesheet" href="css/core-style.css">
    <link rel="stylesheet" href="style.css">

    <script type="text/javascript" src="js/main.js"></script>
    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <script>
        function addProduct(id) {
            $.ajax({
                type: 'POST',
                url: '/shop',
                data: {
                    product_id: id,
                    action: 'buy'
                }
            }).done(
                getCart()
            )
        }
    </script>
    <script>
        function deleteProduct(id) {
            $.ajax({
                type: 'POST',
                url: '/shop',
                data: {
                    product_id: id,
                    action: 'delete'
                }
            }).done(
                getCart()
            )
        }

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

        <div class="nav-close">
            <i class="fa fa-close" aria-hidden="true"></i>
        </div>
        <div class="logo">
            <a href="/index"><img src="img/core-img/logo.jpg" alt=""></a>
        </div>
        <nav class="amado-nav">
            <ul>
                <li><a href="/index">Home</a></li>
                <li class="active"><a href="/shop">Shop</a></li>
                <li><a href="/cart">Cart</a></li>
                <li><a href="/login">LogOut</a></li>
            </ul>
        </nav>
        <div class="cart-fav-search mb-100">
            <a href="cart.html" class="cart-nav"><img src="img/core-img/cart.png" alt=""> Cart</a>
            <div id="goods_table"></div>
            <a href="#" class="search-nav"><img src="img/core-img/search.png" alt=""> Search</a>
        </div>
        <div class="social-info d-flex justify-content-between">
            <a href="https://www.instagram.com/euphoriaendlesss"><i class="fa fa-instagram" aria-hidden="true"></i></a>
            <a href="https://www.facebook.com/guzel.musina"><i class="fa fa-facebook" aria-hidden="true"></i></a>
            <a href="http://twitter.com/euphoriaendless"><i class="fa fa-twitter" aria-hidden="true"></i></a>
        </div>
    </header>

    <div class="amado_product_area section-padding-100">
        <div class="container-fluid">

            <div class="row">
                <div class="col-12">
                    <div class="product-topbar d-xl-flex align-items-end justify-content-between">
                        <div class="total-products">
                            <p>Hello Dear! I am glad to see you on my web site!</p>
                            <p>Let's found easy furniture</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                    <div class="single-product-wrapper">

                        <div class="product-img">
                            <img src="img/product-img/product2.jpg" alt="">
                            <img class="hover-img" src="img/product-img/product3.jpg" alt="">
                        </div>

                        <div class="product-description d-flex align-items-center justify-content-between">

                            <div class="product-meta-data" id="1" name="Modern Chair">
                                <div class="line"></div>
                                <p class="product-price" prize="prize1">$180</p>
                                <a href="product-details.html">
                                    <h6>Modern Chair</h6>
                                </a>
                            </div>
                            <div class="ratings-cart text-right">
                                <div class="ratings">
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                </div>
                                <div class="cart">
                                    <button onclick="addProduct(1)" data-toggle="tooltip" data-placement="left"
                                            title="Add to Cart"><img src="img/core-img/cart.png" alt=""></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                    <div class="single-product-wrapper">

                        <div class="product-img">
                            <img src="img/product-img/product3.jpg" alt="">
                            <img class="hover-img" src="img/product-img/product4.jpg" alt="">
                        </div>

                        <div class="product-description d-flex align-items-center justify-content-between">

                            <div class="product-meta-data" id="2" name="Thumb">
                                <div class="line"></div>
                                <p class="product-price" prize="prize2">$180</p>
                                <a href="product-details.html">
                                    <h6>Thumb</h6>
                                </a>
                            </div>
                            <div class="ratings-cart text-right">
                                <div class="ratings">
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                </div>
                                <div class="cart">
                                    <button onclick="addProduct(2)" data-toggle="tooltip" data-placement="left"
                                            title="Add to Cart"><img src="img/core-img/cart.png" alt=""></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                    <div class="single-product-wrapper">
                        <div class="product-img">
                            <img src="img/product-img/product4.jpg" alt="">

                            <img class="hover-img" src="img/product-img/product5.jpg" alt="">
                        </div>


                        <div class="product-description d-flex align-items-center justify-content-between">

                            <div class="product-meta-data" id="3" name="Flower">
                                <div class="line"></div>
                                <p class="product-price" prize="prize3">$60</p>
                                <a href="product-details.html">
                                    <h6>Flower</h6>
                                </a>
                            </div>

                            <div class="ratings-cart text-right">
                                <div class="ratings">
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                </div>
                                <div class="cart">
                                    <button onclick="addProduct(3)" data-toggle="tooltip" data-placement="left"
                                            title="Add to Cart"><img src="img/core-img/cart.png" alt=""></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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

</body>


</html>