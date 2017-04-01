<jsp:include page="templates/header.jsp"></jsp:include>

<h2>Find a Movie</h2>

<form action="find-movie">
    <div class="form-group">
        <label for="title">Movie Title:</label>
        <input type="text" class="form-control" id="title" name="title">
    </div>

    <button type="submit" class="btn btn-default">Search</button>

</form>

<jsp:include page="templates/footer.jsp"></jsp:include>
