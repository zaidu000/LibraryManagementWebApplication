<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Book</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2>Edit Book</h2>

    <form action="EditBook" method="post">
        <input type="hidden" name="id" value="${book.id}">
         <!--   <input type="hidden" name="id"> -->
        <div class="mb-3">
            <label>Book Name:</label>
            <input type="text" name="name" class="form-control" value="${book.name}" required>
        </div>
        <div class="mb-3">
            <label>Author:</label>
            <input type="text" name="author" class="form-control" value="${book.author}" required>
        </div>
        <div class="mb-3">
            <label>Edition:</label>
            <input type="text" name="edition" class="form-control" value="${book.edition}" required>
        </div>
        <div class="mb-3">
            <label>Quantity:</label>
            <input type="number" name="quantity" class="form-control" value="${book.quantity}" required>
        </div>
        <div class="mb-3">
            <label>Parking Slot:</label>
            <input type="text" name="parking_slot" class="form-control" value="${book.parking_slot}" required>
        </div>

        <button type="submit" class="btn btn-primary">Update Book</button>
        <a href="ViewBooks" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>