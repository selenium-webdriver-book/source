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
                    <input type="text" class="form-control" id="billing-first-name" name="billingFirstName" value="${request.billingFirstName}">
                    <form:errors path="billingFirstName" cssClass="help-inline" cssStyle="color: red"/>
                </div>
                <div class="form-group">
                    <label for="billing-last-name">Last Name:</label>
                    <input type="text" class="form-control" id="billing-last-name" name="billingLastName" value="${request.billingLastName}">
                    <form:errors path="billingLastName" cssClass="help-inline" cssStyle="color: red"/>
                </div>
                <div class="form-group">
                    <label for="billing-address1">Street Address 1:</label>
                    <input type="text" class="form-control" id="billing-address1" name="billingAddress1" value="${request.billingAddress1}">
                    <form:errors path="billingAddress1" cssClass="help-inline" cssStyle="color: red"/>
                </div>
                <div class="form-group">
                    <label for="billing-address2">Street Address 2:</label>
                    <input type="text" class="form-control" id="billing-address2" name="billingAddress2" value="${request.billingAddress2}">
                    <form:errors path="billingAddress2" cssClass="help-inline" cssStyle="color: red"/>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <label for="billing-city">City:</label>
                    <input type="text" class="form-control" id="billing-city" name="billingCity" value="${request.billingCity}">
                    <form:errors path="billingCity" cssClass="help-inline" cssStyle="color: red"/>
                </div>
                <div class="form-group">
                    <label for="billing-state">State:</label>
                    <input type="text" class="form-control" id="billing-state" name="billingState" value="${request.billingState}">
                    <form:errors path="billingState" cssClass="help-inline" cssStyle="color: red"/>
                </div>
                <div class="form-group">
                    <label for="billing-zip">Zip:</label>
                    <input type="text" class="form-control" id="billing-zip" name="billingZip" value="${request.billingZip}">
                    <form:errors path="billingZip" cssClass="help-inline" cssStyle="color: red"/>
                </div>
                <div class="form-group">
                    <label for="billing-country">Country:</label><br/>
                    <select name="shippingAddressDS.shipping_ROW0_country" id="billing-country" class="form-control">
                        <option value="AR">Argentina</option>
                        <option value="AW">Aruba</option>
                        <option value="AU">Australia</option>
                        <option value="AT">Austria</option>
                        <option value="BS">Bahamas</option>
                        <option value="BB">Barbados</option>
                        <option value="BY">Belarus</option>
                        <option value="BE">Belgium</option>
                        <option value="BM">Bermuda</option>
                        <option value="BA">Bosnia and Herzegowina</option>
                        <option value="BR">Brazil</option>
                        <option value="BG">Bulgaria</option>
                        <option value="CA">Canada</option>
                        <option value="KY">Cayman Islands</option>
                        <option value="CL">Chile</option>
                        <option value="CN">China</option>
                        <option value="CR">Costa Rica</option>
                        <option value="HR">Croatia (Hrvatska)</option>
                        <option value="CY">Cyprus</option>
                        <option value="CZ">Czech Republic</option>
                        <option value="DK">Denmark</option>
                        <option value="EC">Ecuador</option>
                        <option value="EG">Egypt</option>
                        <option value="SV">El Salvador</option>
                        <option value="EE">Estonia</option>
                        <option value="FJ">Fiji</option>
                        <option value="FI">Finland</option>
                        <option value="FR">France</option>
                        <option value="FX">France, Metropolitan</option>
                        <option value="DE">Germany</option>
                        <option value="GR">Greece</option>
                        <option value="GL">Greenland</option>
                        <option value="GU">Guam</option>
                        <option value="GT">Guatemala</option>
                        <option value="HK">Hong Kong</option>
                        <option value="HU">Hungary</option>
                        <option value="IS">Iceland</option>
                        <option value="IN">India</option>
                        <option value="ID">Indonesia</option>
                        <option value="IE">Ireland</option>
                        <option value="IL">Israel</option>
                        <option value="IT">Italy</option>
                        <option value="JM">Jamaica</option>
                        <option value="JP">Japan</option>
                        <option value="KZ">Kazakhstan</option>
                        <option value="KR">Korea, Republic of</option>
                        <option value="KW">Kuwait</option>
                        <option value="LV">Latvia</option>
                        <option value="LT">Lithuania</option>
                        <option value="LU">Luxembourg</option>
                        <option value="MO">Macau</option>
                        <option value="MK">Macedonia</option>
                        <option value="MW">Malawi</option>
                        <option value="MY">Malaysia</option>
                        <option value="MT">Malta</option>
                        <option value="MX">Mexico</option>
                        <option value="MC">Monaco</option>
                        <option value="NL">Netherlands</option>
                        <option value="NC">New Caledonia</option>
                        <option value="NZ">New Zealand</option>
                        <option value="NF">Norfolk Island</option>
                        <option value="NO">Norway</option>
                        <option value="PA">Panama</option>
                        <option value="PG">Papua New Guinea</option>
                        <option value="PE">Peru</option>
                        <option value="PH">Philippines</option>
                        <option value="PL">Poland</option>
                        <option value="PT">Portugal</option>
                        <option value="PR">Puerto Rico</option>
                        <option value="RO">Romania</option>
                        <option value="RU">Russian Federation</option>
                        <option value="SA">Saudi Arabia</option>
                        <option value="RS">Serbia</option>
                        <option value="SG">Singapore</option>
                        <option value="SK">Slovakia (Slovak Republic)</option>
                        <option value="SI">Slovenia</option>
                        <option value="ZA">South Africa</option>
                        <option value="ES">Spain</option>
                        <option value="LK">Sri Lanka</option>
                        <option value="SE">Sweden</option>
                        <option value="CH">Switzerland</option>
                        <option value="TW">Taiwan</option>
                        <option value="TZ">Tanzania, United Republic of</option>
                        <option value="TH">Thailand</option>
                        <option value="TT">Trinidad and Tobago</option>
                        <option value="TR">Turkey</option>
                        <option value="UM">US Minor Outlying Islands</option>
                        <option value="UA">Ukraine</option>
                        <option value="AE">United Arab Emirates</option>
                        <option value="UK">United Kingdom</option>
                        <option value="US" selected="selected">United States</option>
                        <option value="VE">Venezuela</option>
                        <option value="VN">Viet Nam</option>
                        <option value="VG">Virgin Islands (British)</option>
                        <option value="VI">Virgin Islands (U.S.)</option>
                    </select>
                    <form:errors path="billingCountry" cssClass="help-inline" cssStyle="color: red"/>
                </div>
            </div>
            <div class="col-md-4">

                <div class="form-group">
                    <label for="ccType">Credit Card Type:</label><br/>
                    <select name="ccType" id="card-type"
                            onchange="togCvn(this.options[this.selectedIndex].value);" class="form-control">
                        <option value="American Express" selected="selected">American Express</option>
                        <option value="JCB">JCB</option>
                        <option value="MasterCard">MasterCard</option>
                        <option value="Visa">Visa</option>
                        <option value="Discover">Discover</option>
                    </select>
                    <form:errors path="ccType" cssClass="help-inline" cssStyle="color: red"/>
                </div>

                <div class="form-group">
                    <label for="card-number">Credit Card Number:</label>
                    <input type="text" class="form-control" id="card-number" name="cardNumber" value="${request.cardNumber}">
                    <form:errors path="cardNumber" cssClass="help-inline" cssStyle="color: red"/>
                </div>
                <div class="form-group">
                    <label for="card-exp-month">Expiry Month:</label><br>
                    <select name="ccPaymentDS.ccpayment_ROW0_expMonth" id="card-exp-month" class="form-control">
                        <option value="">----</option>
                        <option value="1" ${request.expiryMonth == 1 ? "selected" : ""}>Jan (1)</option>
                        <option value="2" ${request.expiryMonth == 2 ? "selected" : ""}>Feb (2)</option>
                        <option value="3" ${request.expiryMonth == 3 ? "selected" : ""}>Mar (3)</option>
                        <option value="4" ${request.expiryMonth == 4 ? "selected" : ""}>Apr (4)</option>
                        <option value="5" ${request.expiryMonth == 5 ? "selected" : ""}>May (5)</option>
                        <option value="6" ${request.expiryMonth == 6 ? "selected" : ""}>Jun (6)</option>
                        <option value="7" ${request.expiryMonth == 7 ? "selected" : ""}>Jul (7)</option>
                        <option value="8" ${request.expiryMonth == 8 ? "selected" : ""}>Aug (8)</option>
                        <option value="9" ${request.expiryMonth == 9 ? "selected" : ""}>Sep (9)</option>
                        <option value="10" ${request.expiryMonth == 10 ? "selected" : ""}>Oct (10)</option>
                        <option value="11" ${request.expiryMonth == 11 ? "selected" : ""}>Nov (11)</option>
                        <option value="12" ${request.expiryMonth == 12 ? "selected" : ""}>Dec (12)</option>
                    </select>
                    <form:errors path="expiryMonth" cssClass="help-inline" cssStyle="color: red"/>
                </div>
                <div class="form-group">
                    <label for="card-exp-year">Expiry Year:</label><br>
                    <select name="ccPaymentDS.ccpayment_ROW0_expYear" id="card-exp-year" class="form-control">
                        <option value="">----</option>
                        <option value="2015" ${request.expiryYear == 2015 ? "selected" : ""}>2015</option>
                        <option value="2016" ${request.expiryYear == 2016 ? "selected" : ""}>2016</option>
                        <option value="2017" ${request.expiryYear == 2017 ? "selected" : ""}>2017</option>
                        <option value="2018" ${request.expiryYear == 2018 ? "selected" : ""}>2018</option>
                        <option value="2019" ${request.expiryYear == 2019 ? "selected" : ""}>2019</option>
                        <option value="2020" ${request.expiryYear == 2020 ? "selected" : ""}>2020</option>
                        <option value="2021" ${request.expiryYear == 2021 ? "selected" : ""}>2021</option>
                        <option value="2022" ${request.expiryYear == 2022 ? "selected" : ""}>2022</option>
                        <option value="2023" ${request.expiryYear == 2023 ? "selected" : ""}>2023</option>
                        <option value="2024" ${request.expiryYear == 2024 ? "selected" : ""}>2024</option>
                        <option value="2025" ${request.expiryYear == 2025 ? "selected" : ""}>2025</option>
                        <option value="2026" ${request.expiryYear == 2026 ? "selected" : ""}>2026</option>
                        <option value="2027" ${request.expiryYear == 2027 ? "selected" : ""}>2027</option>
                    </select>
                    <form:errors path="expiryYear" cssClass="help-inline" cssStyle="color: red"/>
                </div>
            </div>
        </div>

        <jsp:include page="cart/other-information.jsp"/>
        <input type="submit" class="btn btn-primary" value="Confirm"/>
    </form:form>
</div>
</body>
