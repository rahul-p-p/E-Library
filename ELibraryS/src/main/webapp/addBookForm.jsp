<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Book</title>
    <style>
        body { font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif; margin: 0; padding: 20px; background-color: #f8f9fa; }
        .form-container { max-width: 600px; margin: 20px auto; background-color: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        h1 { margin-top: 0; color: #333; }
        .form-group { margin-bottom: 20px; }
        .form-group label { display: block; margin-bottom: 5px; font-weight: bold; color: #555; }
        .form-group input[type="text"], .form-group textarea { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; font-size: 16px; }
        .form-group textarea { resize: vertical; min-height: 100px; }
        .form-group input[type="file"] { padding: 5px; font-size: 14px; }
        .button-group { margin-top: 20px; }
        .button { padding: 12px 20px; border: none; border-radius: 5px; color: #fff; font-weight: bold; cursor: pointer; font-size: 16px; }
        .save-button { background-color: #28a745; margin-right: 10px; }
        .save-button:hover { background-color: #218838; }
        .cancel-link { text-decoration: none; color: #6c757d; font-size: 16px; vertical-align: middle; }
    </style>
    <%@include file="admin/style.jsp" %>
</head>
<body>
	<div>
		<%@include file="admin/navbar.jsp" %>
	</div>
    <div class="form-container">
        <h1>Add a New Book</h1>
        
        <%-- This form sends its data to the BookServlet, which is mapped to "/admin" --%>
        <%-- enctype="multipart/form-data" is CRUCIAL for file uploads to work --%>
        <form action="admin" method="POST" enctype="multipart/form-data">
        
            <%-- This hidden field tells the servlet which action to perform --%>
            <input type="hidden" name="action" value="add">
            
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" id="title" name="title" required>
            </div>
            
            <div class="form-group">
                <label for="author">Author:</label>
                <input type="text" id="author" name="author" required>
            </div>
            
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" required></textarea>
            </div>
            
            <div class="form-group">
                <label for="pdfFile">Book PDF:</label>
                <input type="file" id="pdfFile" name="pdfFile" accept=".pdf" required>
            </div>
            
            <div class="button-group">
                <button type="submit" class="button save-button">Save Book</button>
                <a href="${pageContext.request.contextPath}/admin" class="cancel-link">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>

