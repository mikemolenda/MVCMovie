<jsp:include page="/templates/_header.jsp"></jsp:include>

<h2>${pageTitle}</h2>

<ul>
    <li><strong>Title: </strong>${movie.title}</li>
    <li><strong>Year: </strong>${movie.year}</li>
    <li><strong>Genre: </strong>${movie.genre}</li>
</ul>

<jsp:include page="/templates/_footer.jsp"></jsp:include>