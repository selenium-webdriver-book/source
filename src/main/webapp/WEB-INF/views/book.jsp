<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="head.jsp"/>
    <title>${book.title}</title>
</head>
<body>

<jsp:include page="nav.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <img src="/images/${book.id}.png" width="250" height="340"/>
        </div>
        <div class="col-md-7">
            <h1>${book.title}</h1>
            <p class="small">${book.authors}</p>
            <p>${book.description}</p>
        </div>
        <div class="col-md-2">
            <div class="panel panel-default">
                <div class="panel-heading">MEAP combo $${book.price} pBook + eBook</div>
                <div class="panel-body">

                    <form method="post" action="/bookstore/cart">
                        <input type="hidden" name="book" value="${book.id}"/>
                        <input value="add to cart" class="btn btn-primary" type="submit"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
