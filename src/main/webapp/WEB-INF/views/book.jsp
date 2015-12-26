<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css"/>
    <title>${book.title}</title>
</head>
<body>

<jsp:include page="nav.jsp"/>
<div class="container">
    <h1>${book.title}</h1>
    <div class="row">
        <div class="col-md-3">
            <img src="/images/${book.id}.png"/>
        </div>
        <div class="col-md-9">
            <p>${book.description}</p>
            <label for="addToCart"><b>MEAP combo $${book.price} pBook + eBook</b></label>
            <form method="post" action="/bookstore/cart">
                <input type="hidden" name="book" value="${book.id}"/>
                <input id="addToCart" value="add to cart" class="btn btn-primary" type="submit"/>
            </form>
        </div>
    </div>
</div>
</body>
