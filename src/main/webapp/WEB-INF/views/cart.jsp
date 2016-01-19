<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="head.jsp"/>
    <title>Cart</title>
</head>
<body>
<jsp:include page="nav.jsp"/>
<div class="container">
    <h1>Cart</h1>
    <form:form method="post" action="checkout" modelAttribute="checkoutRequest">
        <div class="panel panel-default">
            <div class="panel-heading">Shopping Cart</div>
            <table class="table">
                <thead>
                <tr>
                    <th>Item</th>
                    <th>Unit Price</th>
                    <th>Quantity</th>
                    <th>Cost</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="i" items="${cart.items}">
                    <tr>
                        <td>${i.title}</td>
                        <td>$${i.price}</td>
                        <td>${i.quantity}</td>
                        <td>$${i.total}</td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td></td>
                    <td></td>
                    <td><b>Subtotal:</b></td>
                    <td>$${cart.total}</td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td><b>Total:</b></td>
                    <td>$${cart.total}</td>
                </tr>
                </tfoot>
            </table>
        </div>
        <h2>Billing Information</h2>
        <div class="row">
            <div class="col-md-4">
                <div class="form-group">
                    <label for="billing-first-name">First Name:</label>
                    <input type="text" class="form-control" id="billing-first-name">
                </div>
                <div class="form-group">
                    <label for="billing-last-name">Last Name:</label>
                    <input type="text" class="form-control" id="billing-last-name">
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <label for="billing-address1">Street Address 1:</label>
                    <input type="text" class="form-control" id="billing-address1">
                </div>
                <div class="form-group">
                    <label for="billing-address2">Street Address 2:</label>
                    <input type="text" class="form-control" id="billing-address2">
                </div>
                <div class="form-group">
                    <label for="billing-city">City:</label>
                    <input type="text" class="form-control" id="billing-city">
                    <label for="billing-state">State:</label>
                    <input type="text" class="form-control" id="billing-state">
                </div>
                <div class="form-group">
                    <label for="billing-zip">Zip:</label>
                    <input type="text" class="form-control" id="billing-zip">
                    <label for="billing-country">Country:</label>
                    <input type="text" class="form-control" id="billing-country">
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <label for="card-number">Credit Card:</label>
                    <input type="text" class="form-control" id="card-number" name="cardNumber">
                    <form:errors path="cardNumber" cssClass="help-inline" cssStyle="color: red"/>
                </div>
                <div class="form-group">
                    <label for="card-exp-month">Expiry Month:</label>
                    <input type="text" class="form-control" id="card-exp-month">
                    <label for="card-exp-month">Expiry Year:</label>
                    <input type="text" class="form-control" id="card-exp-year">
                </div>
            </div>

        </div>
        <jsp:include page="cart/other-information.jsp"/>
        <button type="submit" class="btn btn-primary">Continue</button>
    </form:form>
</div>
</body>
