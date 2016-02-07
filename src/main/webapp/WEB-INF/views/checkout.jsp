<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="head.jsp"/>
    <title>BookStore</title>
</head>
<body>
<jsp:include page="nav.jsp"/>

<div class="container">
    <h1>Thank you for your order</h1>
    <p id="expirationDate">
        Expiration Date: <%=request.getParameter("ccPaymentDS.ccpayment_ROW0_expMonth")%>,    <%=request.getParameter("ccPaymentDS.ccpayment_ROW0_expYear")%>
    </p>
    <p>
        Order number #${orderNumber}.
    </p>

</div>
</body>
