<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <style>
        body { font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif; margin: 0; padding: 20px; background-color: #f8f9fa; }
        .container { max-width: 1000px; margin: 20px auto; background-color: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        h1 { color: #333; border-bottom: 2px solid #eee; padding-bottom: 10px; margin-bottom: 20px; }
        .button { text-decoration: none; padding: 10px 20px; border-radius: 5px; color: #fff; font-weight: bold; display: inline-block; margin-bottom: 20px; }
        .add-button { background-color: #28a745; }
        .add-button:hover { background-color: #218838; }
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #f2f2f2; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .action-links a, .action-links button { text-decoration: none; color: #fff; padding: 8px 12px; border-radius: 4px; margin-right: 5px; border: none; cursor: pointer; }
        .update-link { background-color: #007bff; }
        .delete-button { background-color: #dc3545; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Book Management</h1>
        <a href="${pageContext.request.contextPath}/admin?action=showAddForm" class="button add-button">Add New Book</a>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Description</th>
                    <th>Filename</th>
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
                        <td><c:out value="${book.fileName}" /></td>
                        <td class="action-links">
                            <a href="admin?action=showUpdateForm&id=<c:out value='${book.id}'/>" class="update-link">Update</a>
                            <form action="admin" method="POST" style="display:inline;" onsubmit="return confirm('Are you sure you want to delete this book?');">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="<c:out value='${book.id}'/>">
                                <button type="submit" class="delete-button">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>

