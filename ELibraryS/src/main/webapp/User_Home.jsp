<%@page import="lib.DB.DBConnect"%>
<%@page import="lib.dao.BookViewDAO"%>
<%@page import="lib.dao.BookViewImp"%>
<%@page import="lib.model.Book"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Home page</title>
<%@include file="components/style.jsp" %>
<style>
body{
	background-color: #ecf5cb;
}
.card:hover{
  box-shadow: 2px 2px 15px #000000;
}
.bg-img{
	display:flex;
	flex-direction: column;
	background: url("img/librarybg.jpg");
	height: 60vh;
	width: 100%;
	background-size: cover;
	background-repeat: no-repeat;
	border-radius: 1rem;
	justify-content: center;
	align-items: center;
}
.bg-img h1{
	color: #fff;
}
</style>
</head>
<body>
	<%@include file="components/User_nav.jsp" %>
	<div style="padding: 1rem">
		<div class="container-fluid bg-img">
			<h1>Access to 100000+ books </h1>
			<div>
				<form action="register.jsp" method="get">
		            <button type="submit" class="btn btn-warning mt-2 fs-4 fw-bold">Get Start</button>
		    	</form>
			</div>
		</div>
	</div>
	
	
	
	<!-- Recent Book -->
	<div class="continer-fluid">
		<h1 class="text-center mt-4 mb-4">Recent Books</h1>
		<div class="row">
			<%
			BookViewImp dao=new BookViewImp(DBConnect.getConnection());
			List<Book> list=dao.getNewBook();
			for(Book b:list){
			%>
			<div class="col-md-2 mx-4">
				<div class="card p-2">
					<div class="card-body text-center">
						<img alt="book1" src="<%= b.getBook_image() %>" style="width: 10rem; height:10rem;" class="img-thumbnail">
						<p><%=b.getTitle() %></p>
						<p>by:<%=b.getAuthor() %></p>
						<div class="d-grid gap-2 d-md-block">
							<a href="" class="btn btn-outline-success btn-sm">View Book</a>
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
			
			
	
	<!-- Recent Book End-->
	
	<!-- Trending Book -->
	<div class="continer-fluid">
		<h1 class="text-center mt-4 mb-4">Trending Books</h1>
		<div class="row">
			<div class="col-md-2 mx-4">
				<div class="card p-2">
					<div class="card-body text-center">
						<img alt="book1" src="book/book1.jpg" style="width: 10rem;" class="img-thumbnail">
						<p>Foundations of Python Network Programming</p>
						<div class="d-grid gap-2 d-md-block">
							<a href="" class="btn btn-outline-success btn-sm">View Book</a>
							<a href="" class="btn btn-outline-primary btn-sm">BookMark</a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-2 mx-4">
				<div class="card p-2">
					<div class="card-body text-center">
						<img alt="book1" src="book/book1.jpg" style="width: 10rem;" class="img-thumbnail">
						<p>Foundations of Python Network Programming</p>
						<div class="d-grid gap-2 d-md-block">
							<a href="" class="btn btn-outline-success btn-sm">View Book</a>
							<a href="" class="btn btn-outline-primary btn-sm">BookMark</a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-2 mx-4">
				<div class="card p-2">
					<div class="card-body text-center">
						<img alt="book1" src="book/book1.jpg" style="width: 10rem;" class="img-thumbnail">
						<p>Foundations of Python Network Programming</p>
						<div class="d-grid gap-2 d-md-block">
							<a href="" class="btn btn-outline-success btn-sm">View Book</a>
							<a href="" class="btn btn-outline-primary btn-sm">BookMark</a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-2 mx-4">
				<div class="card p-2">
					<div class="card-body text-center">
						<img alt="book1" src="book/book1.jpg" style="width: 10rem;" class="img-thumbnail">
						<p>Foundations of Python Network Programming</p>
						<div class="d-grid gap-2 d-md-block">
							<a href="" class="btn btn-outline-success btn-sm">View Book</a>
							<a href="" class="btn btn-outline-primary btn-sm">BookMark</a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-2 mx-4">
				<div class="card p-2">
					<div class="card-body text-center">
						<img alt="book1" src="book/book1.jpg" style="width: 10rem;" class="img-thumbnail">
						<p>Foundations of Python Network Programming</p>
						<div class="d-grid gap-2 d-md-block">
							<a href="" class="btn btn-outline-success btn-sm">View Book</a>
							<a href="" class="btn btn-outline-primary btn-sm">BookMark</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<!-- Trending Book End-->
	<%@include file="components/footer.jsp"%>
</body>
</html>