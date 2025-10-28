<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="lib.DB.DBConnect"%>
<%@ page import="lib.dao.BookViewImp"%>
<%@ page import="lib.model.Book"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Authors</title>
<%@include file="components/style.jsp" %>
<style>
body {
    background-color: #ecf5cb;
}
.container{
	min-height: 90vh;
}

.card:hover {
    box-shadow: 2px 2px 15px #000000;
}
a.author-link {
    text-decoration: none;
    color: #0d6efd;
    font-weight: bold;
}
a.author-link:hover {
    color: #0a58ca;
    text-decoration: underline;
}
</style>
</head>
<body>
<%@include file="components/navbar.jsp" %>

<div class="container mt-4">
    <h1 class="text-center mb-4">Authors</h1>
    <div class="row">
        <%
        BookViewImp dao = new BookViewImp(DBConnect.getConnection());
        String authorName = request.getParameter("author");
        
        if (authorName == null) {
            // Show list of all authors
            List<String> authors = dao.getAllAuthors();
        %>
            <div class="col-md-8 offset-md-2">
                <ul class="list-group">
                    <%
                    for (String a : authors) {
                    %>
                        <li class="list-group-item text-center">
                            <a class="author-link" href="Authors.jsp?author=<%= java.net.URLEncoder.encode(a, "UTF-8") %>"><%= a %></a>
                        </li>
                    <%
                    }
                    %>
                </ul>
            </div>
        <%
        } else {
            // Show all books by the selected author
            List<Book> books = dao.getBooksByAuthor(authorName);
        %>
            <h3 class="text-center mb-4">Books by <%= authorName %></h3>
            <div class="row justify-content-center">
                <%
                for (Book b : books) {
                %>
                    <div class="col-md-2 mx-3 mt-2">
                        <div class="card p-2" style="width: 14rem; height:19rem;">
                            <div class="card-body text-center">
                                <img alt="book" src="<%= b.getBook_image() %>" style="width: 8rem; height:10rem;" class="img-thumbnail">
                                <p><%= b.getTitle() %></p>
                                <p>by: <%= b.getAuthor() %></p>
                                <a href="<%= b.getFile_path() %>" target="_blank" class="btn btn-outline-success btn-sm">View Book</a>
                            </div>
                        </div>
                    </div>
                <%
                }
                %>
            </div>
            <div class="text-center mt-4">
                <a href="Authors.jsp" class="btn btn-primary">Back to Authors</a>
            </div>
        <%
        }
        %>
    </div>
</div>

<%@include file="components/footer.jsp"%>
</body>
</html>