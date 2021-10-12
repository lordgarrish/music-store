<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/header.jsp" />

<main>
    <aside>
    </aside>
    <div class="checkout">
        <h3>Please confirm your information</h3>
        <p><b>First Name: </b><c:out value="${customer.firstName}"/></p>
        <p><b>Last Name: </b><c:out value="${customer.lastName}"/></p>
        <p><b>Email: </b><c:out value="${customer.email}"/></p>
        <p><b>Phone Number: </b><c:out value="${customer.phoneNumber}"/></p>
        <p><b>Address: </b><c:out value="${customer.address}"/></p>
        <p><b>City: </b><c:out value="${customer.city}"/></p>
        <p><b>State: </b><c:out value="${customer.state}"/></p>
        <p><b>ZIP: </b><c:out value="${customer.zip}"/></p>
        <p><b>Country: </b><c:out value="${customer.country}"/></p>

        <form action="" method="post">
            <input type="hidden" name="action" value="edit">
            <input type="submit" value="Edit">
        </form><br>

        <form action="" method="post">
            <input type="hidden" name="action" value="confirm">
            <input type="submit" value="Confirm">
        </form>
    </div>
    <aside>
    </aside>
</main>

<jsp:include page="includes/footer.jsp" />