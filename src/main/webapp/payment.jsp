<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/header.jsp" />

<main>
    <aside>
    </aside>
    <div class="checkout">
        <h3>Credit card information</h3>
        <form action="" method="post" class="checkout-form">
            <label><b>Credit Card Number:</b>
                <input type="text" name="creditCardNumber" pattern="[0-9]{16}" placeholder="1111222233334444" required>
            </label><br>
            <label><b>Credit Card Expiration Date:</b>
                <input type="text" name="creditCardExpirationDate" pattern="[0-9]{2}\/[0-9]{2}" placeholder="XX/XX"
                       maxlength="5" required>
            </label><br>
            <label><b>CVV:</b>
                <input type="password" name="cvv" pattern="[0-9]{3}" maxlength="3" placeholder="XXX" required>
            </label><br>

            <br>
            <input type="hidden" name="action" value="addCard">
            <input type="submit" value="Submit">
        </form>
    </div>
    <aside>
    </aside>
</main>

<jsp:include page="includes/footer.jsp" />