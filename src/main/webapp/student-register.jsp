<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Registration</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="container">
    <h2 class="text-center mb-4">Student Registration</h2>

    <form action="Register" method="post" class="p-4 bg-white shadow rounded">
        <input type="hidden" name="role" value="Student">

        <div class="mb-3">
            <label class="form-label">Name:</label>
            <input type="text" name="name" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Email:</label>
            <input type="email" name="email" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Password:</label>
            <input type="password" name="password" class="form-control" required pattern=".{8,}" title="At least 8 characters with a special symbol">
        </div>

        <button type="submit" class="btn btn-success w-100">Register as Student</button>
    </form>
</div>

</body>
</html>
