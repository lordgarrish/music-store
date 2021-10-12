<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/header.jsp" />

<main>
    <aside>
    </aside>
    <div class="checkout">
        <h3>Contact information and address</h3>
        <form action="<c:url value="/checkout"/>" method="post" class="checkout-form">
            <input type="hidden" name="action" value="add">
            <label><b>First Name:</b>
                <input type="text" name="firstName" pattern="[A-Za-z]\w+" required>
            </label><br>
            <label><b>Last Name:</b>
                <input type="text" name="lastName" pattern="[A-Za-z]\w+" required>
            </label><br>
            <label><b>Email:</b>
                <input type="email" name="email" required>
            </label><br>
            <label><b>Phone number: </b>
                <input type="tel" name="phoneNumber" maxlength="15" placeholder="79XXXXXXXXX" pattern="[0-9]{11,15}"
                       required>
            </label><br>
            <label><b>Address:</b>
                <input type="text" name="address" required>
            </label><br>
            <label><b>City:</b>
                <input type="text" name="city" required>
            </label><br>
            <label><b>State:</b>
                <input type="text" name="state" required>
            </label><br>
            <label><b>Zip code:</b>
                <input type="text" name="zip" required>
            </label><br>
            <label><b>Country:</b>
                <input type="text" name="country" required>
            </label><br>

            <br>
            <input type="submit" value="Submit">
        </form>
    </div>
    <aside>
    </aside>
</main>

<jsp:include page="includes/footer.jsp" />