<html>

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


    <link rel="stylesheet" href="css/core-style.css">
    <link rel="stylesheet" href="style.css">

</head>

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
                    ' id ' +
                    '</th>' +
                    '<th>' +
                    ' name ' +
                    '</th>' +
                    '<th>' +
                    ' count ' +
                    '</th>' +
                    '</tr>';
            for (let i = 0; i < data.length; i++) {
                tableHtml += '<tr>' +
                        '<td>' + ' ' + data[i].id + '</td>' +
                        '<td>' + ' ' + data[i].name + '</td>' +
                        '<td>' + ' ' + data[i].count + '</td>' +
                        '<td>' + ' ' + '<button onclick="deleteProduct(' + data[i].id + ')" formaction="delete" id = ' + 1 + '>Delete</button>' + '</td>' +
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
                    ' id ' +
                    '</th>' +
                    '<th>' +
                    ' title ' +
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
                        <input type="text" id="q" onkeyup="search(document.getElementById('q').value)"
                               placeholder="Type your keyword...">
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
        <!-- Logo -->
        <div class="logo">
            <a href="/index"><img src="img/core-img/logo.jpg" alt=""></a>
        </div>
        <nav class="amado-nav">
            <ul>
                <li><a href="/index">Home</a></li>
                <li><a href="/shop">Shop</a></li>
                <li class="active"><a href="cart.html">Cart</a></li>
                <li><a href="/login">LogOut</a></li>
            </ul>
        </nav>
        <div class="cart-fav-search mb-100">
            <a href="/cart" class="cart-nav"><img src="img/core-img/cart.png" alt=""> Cart</a>
            <a href="#" class="search-nav"><img src="img/core-img/search.png" alt=""> Search</a>
        </div>
        <div class="social-info d-flex justify-content-between">
            <a href="https://www.instagram.com/euphoriaendlesss"><i class="fa fa-instagram" aria-hidden="true"></i></a>
            <a href="https://www.facebook.com/guzel.musina"><i class="fa fa-facebook" aria-hidden="true"></i></a>
            <a href="http://twitter.com/euphoriaendless"><i class="fa fa-twitter" aria-hidden="true"></i></a>
        </div>
    </header>
    <div class="cart-table-area section-padding-100">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12 col-lg-8">
                    <div class="cart-title mt-50">
                        <h2>Shopping Cart</h2>
                    </div>


                            <div id="goods_table"></div>

                </div>
                <div class="col-12 col-lg-4">
                    <div class="cart-summary">
                        <h5>Cart Total</h5>
                        <ul class="summary-table">
                            <li><span>subtotal:</span> <span>$140.00</span></li>
                            <li><span>delivery:</span> <span>Free</span></li>
                            <li><span>total:</span> <span>$140.00</span></li>
                        </ul>
                        <div class="cart-btn mt-100">
                            <a href="/cart" class="btn amado-btn w-100">Checkout</a>
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