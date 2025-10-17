<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Book</title>
    <style>
        body { font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif; margin: 0; padding: 20px; background-color: #f8f9fa; }
        .form-container { max-width: 600px; margin: 20px auto; background-color: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        h1 { margin-top: 0; }
        .form-group { margin-bottom: 20px; }
        .form-group label { display: block; margin-bottom: 5px; font-weight: bold; color: #555; }
        .form-group input[type="text"], .form-group textarea { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        .form-group textarea { resize: vertical; min-height: 100px; }
        .form-group input[type="file"] { padding: 5px; }
        .current-file { font-style: italic; color: #888; margin-top: 10px; }
        .button-group { margin-top: 20px; }
        .button { padding: 12px 20px; border: none; border-radius: 5px; color: #fff; font-weight: bold; cursor: pointer; }
        .save-button { background-color: #007bff; margin-right: 10px; }
        .save-button:hover { background-color: #0056b3; }
        .cancel-link { text-decoration: none; color: #6c757d; }
    </style>
</head>
<body>
    <div class="form-container">
        <h1>Update Book Details</h1>
        
        <%-- This form also submits to the BookServlet --%>
        <form action="admin" method="POST" enctype="multipart/form-data">
        
            <%-- Hidden fields to tell the servlet what to do and which book to update --%>
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="<c:out value='${book.id}' />">
            <input type="hidden" name="existingFileName" value="<c:out value='${book.fileName}' />">
            
            <%-- The 'value' attribute is pre-filled with the book's current data from the servlet --%>
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" id="title" name="title" value="<c:out value='${book.title}' />" required>
            </div>
            
            <div class="form-group">
                <label for="author">Author:</label>
                <input type="text" id="author" name="author" value="<c:out value='${book.author}' />" required>
            </div>
            
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" required><c:out value='${book.description}' /></textarea>
            </div>
            
            <div class="form-group">
                <label for="pdfFile">Update Book PDF (Optional):</label>
                <input type="file" id="pdfFile" name="pdfFile" accept=".pdf">
                <p class="current-file">Current file: <c:out value='${book.fileName}' /></p>
            </div>
            
            <div class="button-group">
                <button type="submit" class="button save-button">Update Book</button>
                <a href="${pageContext.request.contextPath}/admin" class="cancel-link">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>

