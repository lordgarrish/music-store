<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>MAH</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/main.css"/>">
</head>
<body>
<header>
    <div class="front centered">
        <img src="<c:url value="/images/front-title.png"/>" alt="MUSIC AGAINST HUMANITY">
    </div>
    <nav class="links centered">
        <div><a href="<c:url value="/"/>">Home</a></div>
        <div><a href="<c:url value="/catalog"/>">Catalog</a></div>
        <div><a href="<c:url value="/news"/>">News</a></div>
        <div><a href="<c:url value="/cart"/>">Cart</a></div>
    </nav>
</header>