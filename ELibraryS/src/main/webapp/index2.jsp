<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>E-Book Library</title>
    <style>
        body { font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; background-color: #f4f4f9; }
        .container { background-color: #ffffff; padding: 40px; border-radius: 10px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); text-align: center; max-width: 500px; width: 100%; }
        h1 { color: #333; margin-bottom: 10px; }
        p { color: #666; margin-bottom: 30px; }
        .nav-links a { display: block; text-decoration: none; color: #fff; padding: 15px 20px; margin-bottom: 10px; border-radius: 8px; font-weight: 500; transition: background-color 0.3s ease, transform 0.2s ease; background-color: #007bff; }
        .nav-links a:hover { background-color: #0056b3; transform: translateY(-2px); }
        .nav-links .add-book { background-color: #28a745; }
        .nav-links .add-book:hover { background-color: #218838; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to the E-Book Library</h1>
        <p>Manage your digital book collection with ease.</p>
        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/admin?action=list">Manage Books</a>
            <a href="${pageContext.request.contextPath}/admin?action=showAddForm" class="add-book">Add a New Book</a>
        </div>
    </div>
</body>
</html>

