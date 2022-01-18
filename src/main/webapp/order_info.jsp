<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/header.jsp" />

<main>
    <aside>
    </aside>
    <div class="checkout">
        <c:choose>
            <c:when test="${order.emptyOrder == true}">
                <h3>Your order is empty!</h3>
            </c:when>
            <c:otherwise>
                <h3>Your order № <c:out value="${order.orderID}"/></h3>
                <div class="order-list">
                    <table>
                        <tr>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                        <c:forEach var="item" items="${order.items}">
                            <tr>
                                <td><c:out value='${item.album.artist}'/> - <c:out value='${item.album.title}'/></td>
                                <td>$<c:out value="${item.album.price}"/></td>
                                <td><c:out value='${item.quantity}'/></td>
                                <td>$<c:out value="${item.total}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <p><b>Total price:</b> $<c:out value="${cart.totalPrice}"/></p><br>
                <p><b>Shipping information:</b></p>
                <p><b>First Name: </b><c:out value="${customer.firstName}"/>
                <b> Last Name: </b><c:out value="${customer.lastName}"/></p>
                <p><b>Email: </b><c:out value="${customer.email}"/>
                <b> Phone Number: </b><c:out value="${customer.phoneNumber}"/></p>
                <p><b>Address: </b><c:out value="${customer.address.address}"/></p>
                <p><b>City: </b><c:out value="${customer.address.city}"/>
                <b> State: </b><c:out value="${customer.address.state}"/>
                <b> ZIP: </b><c:out value="${customer.address.zip}"/></p>
                <p><b>Country: </b><c:out value="${customer.address.country}"/></p>
            </c:otherwise>
        </c:choose>
    </div>
    <aside>
    </aside>
</main>

<jsp:include page="includes/footer.jsp" />