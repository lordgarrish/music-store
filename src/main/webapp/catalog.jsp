<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/header.jsp" />

<main>
    <aside>
    </aside>
    <div class="catalog">
        <div><h3>Cover</h3></div>
        <div><h3>Title</h3></div>
        <div><h3>Price</h3></div>
        <c:forEach var="album" items="${albums}">
            <div><img src="<c:url value="${album.imageURL}"/>" width="200" alt="Cover"></div>
            <div>
                <p><a href="<c:url value="catalog/${album.code}"/>">
                        <c:out value="${album.artist}"/> - <c:out value="${album.title}"/>
                    </a></p>
                <p>Genre: <c:out value="${album.genre}"/></p>
                <p>Year: <c:out value="${album.year}"/></p>
            </div>
            <div>$<c:out value="${album.price}"/></div>
        </c:forEach>
    </div>
    <aside>
    </aside>
</main>

<jsp:include page="includes/footer.jsp" />