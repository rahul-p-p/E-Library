<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="lib.DB.DBConnect"%>
<%@page import="lib.dao.BookViewDAO"%>
<%@page import="lib.dao.BookViewImp"%>
<%@page import="lib.model.Book"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Books</title>
<%@include file="components/style.jsp" %>
<style>
body{
	background-color: #ecf5cb;
}
.card:hover{
  box-shadow: 2px 2px 15px #000000;
}
</style>
</head>
<body>
	<%@include file="components/navbar.jsp" %>
	<div class="continer-fluid">
		<div class="row p-3">
			<%
			BookViewImp dao=new BookViewImp(DBConnect.getConnection());
			List<Book> list=dao.getAllBook();
			for(Book b:list){
			%>
			<div class="col-md-2 mx-4 mt-2">
				<div class="card p-2">
					<div class="card-body text-center">
						<img alt="book1" src="<%= b.getBook_image() %>" style="width: 8rem; height:10rem;" class="img-thumbnail">
						<p><%=b.getTitle() %></p>
						<p>by:<%=b.getAuthor() %></p>
						<div class="d-grid gap-2 d-md-block">
							<a href="Book_details.jsp" class="btn btn-outline-success btn-sm">View Book</a>
							<a href="" class="btn btn-outline-primary btn-sm">BookMark</a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>
	<%@include file="components/footer.jsp"%>
</body>
</html>