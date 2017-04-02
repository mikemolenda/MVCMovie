<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/templates/_header.jsp"></jsp:include>

<h2>${pageTitle}</h2>

<table class="table table-striped">
    <tr>
        <th>Title</th>
        <th>Year</th>
        <th>Genre</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${movList}" var="movie">
    <tr>
        <td><a href="/find-movie?movieId=${movie.id}">${movie.title}</a></td>
        <td>${movie.year}</td>
        <td>${movie.genre}</td>
        <td>
            <form action="/edit-movie">
                <input type="hidden" name="movieId" value="${movie.id}">
                <input type="submit" class="btn btn-info" value="Edit">
            </form>
        </td>
        <td>
            <form action="/delete-movie">
                <input type="hidden" name="movieId" value="${movie.id}">
                <input type="submit" class="btn btn-danger" value="Delete">
            </form>
        </td>
    </tr>
    </c:forEach>
</table>

<hr>

<a href="/add-movie" class="btn btn-success">Add a new movie</a>

<jsp:include page="/templates/_footer.jsp"></jsp:include>
