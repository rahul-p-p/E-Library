<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="lib.DB.DBConnect"%>
<%@page import="lib.dao.BookViewDAO"%>
<%@page import="lib.dao.BookViewImp"%>
<%@page import="lib.model.Book"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book_Details</title>
<%@include file="components/style.jsp" %>
<style>
body{
	background-color: #ecf5cb;
}
.container{
	height:75vh;
	padding-top:5rem;
}
.readbook{
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	text-align: left;
	line-height: 1.6;
}
</style>
</head>
<body>
<%@include file="components/User_nav.jsp" %>
<%
	int book_id=Integer.parseInt(request.getParameter("book_id"));
	BookViewImp dao=new BookViewImp(DBConnect.getConnection());
	Book b=dao.getBookById(book_id);
%>

	<div class="container">
		<div class="row">
			<div class="col-md-6 text-center p-5 border bg-white">
				<img alt="book1" src="<%= b.getBook_image() %>" style="width: 9rem; height:12rem;" class="img-thumbnail">
					<h4>Book Name: <%=b.getTitle() %></h4>
					<h4>Author: <%=b.getAuthor() %></h4>
					<h4>Category: <%=b.getCategory() %></h4>
			
			</div>
			<div class="col-md-6 text-center p-5 border bg-white readbook">
				<h3><%=b.getTitle() %></h3>
				<p><%=b.getDescription() %></p>
				<div>
					<a href="" class="btn btn-outline-success btn-sm">Read Online</a>
					<a href="" class="btn btn-outline-primary btn-sm">BookMark</a>
				</div>
			</div>
		</div>
	</div>


	<%@include file="components/footer.jsp"%>
</body>
</html>