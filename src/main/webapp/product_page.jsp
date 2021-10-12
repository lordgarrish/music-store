<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/header.jsp" />

<main>
    <aside>
    </aside>
    <div class="product-info">
        <div><h3>Cover</h3></div>
        <div><h3>Title</h3></div>
        <div><h3>Price</h3></div>
        <div></div>
        <div><img src="<c:url value="${album.imageURL}"/>" width="200" alt="Cover"></div>
        <div>
            <p><c:out value="${album.artist}"/> - <c:out value="${album.title}"/></p>
            <p>Genre: <c:out value="${album.genre}"/></p>
            <p>Year: <c:out value="${album.year}"/></p>
            <p>Description:</p>
            <p><c:out value="${album.description}"/></p>
        </div>
        <div>$<c:out value="${album.price}"/></div>
        <div>
            <form action="<c:url value="/cart"/>" method="post">
                <input type="hidden" name="productCode" value="${album.code}">
                <input type="submit" value="Add to cart">
            </form>
        </div>
    </div>
    <aside>
    </aside>
</main>

<jsp:include page="includes/footer.jsp" />