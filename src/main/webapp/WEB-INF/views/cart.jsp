<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css"/>
    <title>Cart</title>
</head>
<body>
<jsp:include page="nav.jsp"/>
<div class="container">
    <h1>Cart</h1>
    <table class="table">
        <caption>Shopping Cart</caption>
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
    <form>
        <div class="form-group"><label>Redemption code:</label><input class="form-control" id="gc-redemption-code">
        </div>
        <div class="form-group"><label>Billing email:</label><input class="form-control" id="billing-email"></div>
        <div class="form-group"><label><input class="form-control" type="checkbox"
                                              id="confirm-email"> confirm email.</label></div>
        <div class="form-group"><label><input class="form-control" type="checkbox" id="ratings"> ratings</label></div>
        <div class="form-group">
            Mailing list:
            <label><input type="radio" name="customFieldDS.customfield_ROW0_value"
                          value="Weekly newsletter--New books, updates, news, and special offers" checked/>Weekly
                newsletter--New books, updates, news, and special offers.</label>
            <label><input type="radio" name="customFieldDS.customfield_ROW0_value"
                          value="Deal of the Day--These amazing special offers last just 24 hours"/>Deal of the
                Day--These amazing special offers last just 24 hours!</label>
            <label><input type="radio" name="customFieldDS.customfield_ROW0_value" value="Both"/>Both</label>
            <label><input type="radio" name="customFieldDS.customfield_ROW0_value"
                          value="No promotional mailers. I will still receive updates on my MEAPs and other books"/>No
                promotional mailers. I will still receive updates on my MEAPs and other books.</label>
            <label><input type="radio" name="customFieldDS.customfield_ROW0_value"
                          value="Keep me on the lists I&#39;m already on."/>Keep me on the lists I&#39;m already
                on.</label>
        </div>
        <div class="form-group"><label>Comments:</label><textarea class="form-control" id="comments"></textarea></div>
    </form>
</div>
</body>
