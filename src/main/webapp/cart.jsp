<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/header.jsp" />

<main>
    <aside>
    </aside>
    <div class="cart">
        <c:choose>
            <c:when test="${cart.emptyCart == true}">
                <h3>Your cart is empty!</h3>
            </c:when>
            <c:otherwise>
                <h2>Your cart</h2>
                <div class="cart-list">
                <table>
                    <tr>
                        <th>Quantity</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Total</th>
                        <th></th>
                    </tr>

                    <c:forEach var="item" items="${cart.items}">
                        <tr>
                            <td>
                                <form action="" method="post">
                                    <input type="hidden" name="productCode"
                                           value="<c:out value='${item.album.code}'/>">
                                    <input type="text" name="quantity"
                                           value="<c:out value='${item.quantity}'/>" id="quantity">
                                    <input type="submit" value="Update">
                                </form>
                            </td>
                            <td><c:out value='${item.album.artist}'/> - <c:out value='${item.album.title}'/></td>
                            <td>$<c:out value="${item.album.price}"/></td>
                            <td>$<c:out value="${item.total}"/></td>
                            <td>
                                <form action="" method="post">
                                    <input type="hidden" name="productCode"
                                           value="<c:out value='${item.album.code}'/>">
                                    <input type="hidden" name="quantity" value="0">
                                    <input type="submit" value="Remove Item">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                </div>
                <p><b>Total price:</b> $<c:out value="${cart.totalPrice}"/></p>
                <p><b>To change the quantity</b>, enter new quantity
                    and click on the Update button.</p>

                <form action="" method="post">
                    <input type="hidden" name="action" value="shop">
                    <input type="submit" value="Continue Shopping">
                </form><br>

                <form action="" method="post">
                    <input type="hidden" name="action" value="checkout">
                    <input type="submit" value="Checkout">
                </form>
            </c:otherwise>
        </c:choose>
    </div>
    <aside>
    </aside>
</main>

<jsp:include page="includes/footer.jsp" />