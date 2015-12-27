<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="head.jsp"/>
    <title>Search</title>
</head>
<body>
<jsp:include page="nav.jsp"/>
<div class="container">
    <h1>Search results - ${query}</h1>
    <p>Total results: ${numResults}</p>
    <c:forEach var="i" items="${results}">
        <div>
            <a href="books/${i.id}">
                <h2><c:out value="${i.title}"/></h2>
                <p>${i.description}</p>
            </a>
        </div>
    </c:forEach>
</div>
</body>
