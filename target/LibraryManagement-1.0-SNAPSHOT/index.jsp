<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library Management System</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        body {
            background: linear-gradient(to right, #e0f7fa, #ffffff);
        }
        .card {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        .card:hover {
            transform: translateY(-8px);
            box-shadow: 0 0 20px rgba(0,0,0,0.1);
        }
        .card i {
            font-size: 2.2rem;
            margin-bottom: 15px;
            color: #0d6efd;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center mb-5 text-primary">📚 Library Management System</h1>

    <div class="row justify-content-center g-4">
        <!-- Admin Registration -->
        <div class="col-md-4">
            <div class="card p-4 text-center shadow-sm">
                <i class="bi bi-person-plus-fill"></i>
                <h4 class="mb-3">Admin Register</h4>
                <a href="registerAdmin.jsp" class="btn btn-primary">Register as Admin</a>
            </div>
        </div>

        <!-- Student Registration -->
        <div class="col-md-4">
            <div class="card p-4 text-center shadow-sm">
                <i class="bi bi-person-bounding-box"></i>
                <h4 class="mb-3">Student Register</h4>
                <a href="registerStudent.jsp" class="btn btn-success">Register as Student</a>
            </div>
        </div>

        <!-- Login -->
        <div class="col-md-4">
            <div class="card p-4 text-center shadow-sm">
                <i class="bi bi-box-arrow-in-right"></i>
                <h4 class="mb-3">Login</h4>
                <a href="login.jsp?role=Admin" class="btn btn-dark">Admin/Student</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
