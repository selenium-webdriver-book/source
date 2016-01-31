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
                    <label for="card-number">Credit Card Type:</label><br/>
                    <select name="ccPaymentDS.ccpayment_ROW0_ccType" id="card-type"
                            onchange="togCvn(this.options[this.selectedIndex].value);">
                        <option value="American Express" selected="selected">American Express</option>
                        <option value="JCB">JCB</option>
                        <option value="MasterCard">MasterCard</option>
                        <option value="Visa">Visa</option>
                        <option value="Discover">Discover</option>
                    </select>
                    <form:errors path="cardNumber" cssClass="help-inline" cssStyle="color: red"/>
                </div>

                <div class="form-group">
                    <label for="card-number">Credit Card:</label>
                    <input type="text" class="form-control" id="card-number" name="cardNumber">
                    <form:errors path="cardNumber" cssClass="help-inline" cssStyle="color: red"/>
                </div>
                <div class="form-group">
                    <label for="card-exp-month">Expiry Month:</label> <br>
                    <select name="ccPaymentDS.ccpayment_ROW0_expMonth" id="card-exp-month">
                        <option value="" selected="selected">----</option>
                        <option value="1">Jan (1)</option>
                        <option value="2">Feb (2)</option>
                        <option value="3">Mar (3)</option>
                        <option value="4">Apr (4)</option>
                        <option value="5">May (5)</option>
                        <option value="6">Jun (6)</option>
                        <option value="7">Jul (7)</option>
                        <option value="8">Aug (8)</option>
                        <option value="9">Sep (9)</option>
                        <option value="10">Oct (10)</option>
                        <option value="11">Nov (11)</option>
                        <option value="12">Dec (12)</option>
                    </select><br/>
                    <label for="card-exp-month">Expiry Year:</label> <br>
                    <select name="ccPaymentDS.ccpayment_ROW0_expYear" id="card-exp-year">
                        <option value="" selected="selected">----</option>
                        <option value="2015">2015</option>
                        <option value="2016">2016</option>
                        <option value="2017">2017</option>
                        <option value="2018">2018</option>
                        <option value="2019">2019</option>
                        <option value="2020">2020</option>
                        <option value="2021">2021</option>
                        <option value="2022">2022</option>
                        <option value="2023">2023</option>
                        <option value="2024">2024</option>
                        <option value="2025">2025</option>
                        <option value="2026">2026</option>
                        <option value="2027">2027</option>
                    </select>
                </div>

            </div>

        </div>
        <jsp:include page="cart/other-information.jsp"/>
        <input type="submit" class="btn btn-primary" value="Continue"/>
    </form:form>
</div>
</body>
