<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <style>
.booklist{
	font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
		"Helvetica Neue", Arial, sans-serif;
	margin: 0;
	padding: 20px;
	background-color: #f8f9fa;
}

.container {
	max-width: 100%;
	margin: 20px auto;
	background-color: #fff;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

h1 {
	color: #333;
	border-bottom: 2px solid #eee;
	padding-bottom: 10px;
	margin-bottom: 20px;
}

.button {
	text-decoration: none;
	padding: 10px 20px;
	border-radius: 5px;
	color: #fff;
	font-weight: bold;
	display: inline-block;
	margin-bottom: 20px;
}

.add-button {
	background-color: #28a745;
}

.add-button:hover {
	background-color: #218838;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 12px;
	border: 1px solid #ddd;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

.action-links a, .action-links button {
	text-decoration: none;
	color: #fff;
	padding: 8px 12px;
	border-radius: 4px;
	margin-right: 5px;
	border: none;
	cursor: pointer;
}

.update-link {
	background-color: #007bff;
}

.delete-button {
	background-color: #dc3545;
}
    </style>
    <%@include file="admin/style.jsp" %>
</head>
<body>
    <div>
        <%@include file="admin/navbar.jsp" %>
    </div>
	<div class="booklist">
		<div class="container">
        <h1>Book List</h1>

        <!-- ✅ Add New Book button (fixed context path) -->
        <a href="${pageContext.request.contextPath}/admin?action=showAddForm" class="button add-button">
            Add New Book
        </a>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Description</th>
                    <th>Filename</th>
                    <th>Imagename</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${bookList}">
                    <tr>
                        <td><c:out value="${book.id}" /></td>
                        <td><c:out value="${book.title}" /></td>
                        <td><c:out value="${book.author}" /></td>
                        <td><c:out value="${book.description}" /></td>
                        <td><c:out value="${book.file_path}" /></td> <!-- PDF path -->
						<td><c:out value="${book.book_image}" /></td> <!-- Image path -->
						
						<!--
                        <td class="action-links">
                            
                            <a href="${pageContext.request.contextPath}/admin?action=showUpdateForm&id=${book.id}" 
                               class="update-link">Update</a>

                            
                            <form action="${pageContext.request.contextPath}/admin" 
                                  method="POST" style="display:inline;" 
                                  onsubmit="return confirm('Are you sure you want to delete this book?');">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="${book.id}">
                                <button type="submit" class="delete-button">Delete</button>
                            </form>
                        </td>
                        -->
                        <td class="action-links">
						    <!-- Update Button -->
						    <a href="${pageContext.request.contextPath}/admin?action=showUpdateForm&id=${book.id}" 
						       class="update-link">Update</a>
						
						    <!-- Delete Button -->
						    <form action="${pageContext.request.contextPath}/admin" method="POST" 
						          style="display:inline-block; margin:0;" 
						          onsubmit="return confirm('Are you sure you want to delete this book?');">
						        <input type="hidden" name="action" value="delete">
						        <input type="hidden" name="id" value="${book.id}">
						        <button type="submit" class="delete-button">Delete</button>
						    </form>
						</td>
                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
	</div>
    
</body>
</html>
