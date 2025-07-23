<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library Management System</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    
    <!-- Optional Custom CSS -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h1 class="text-center mb-4">Library Management System</h1>

    <div class="row justify-content-center">
        <!-- Register Column -->
        <div class="col-md-4">
            <div class="card p-4 text-center shadow-sm">
                <h3>Register</h3>
                <a href="admin-register.jsp" class="btn btn-primary mt-2">Admin Register</a>
                <a href="student-register.jsp" class="btn btn-secondary mt-2">Student Register</a>
            </div>
        </div>

        <!-- Login Column -->
        <div class="col-md-4">
            <div class="card p-4 text-center shadow-sm">
                <h3>Login</h3>
                <a href="login.jsp?role=Admin" class="btn btn-primary mt-2">Admin Login</a>
                <a href="login.jsp?role=Student" class="btn btn-secondary mt-2">Student Login</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
