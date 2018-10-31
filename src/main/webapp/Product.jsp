<%--
  Created by IntelliJ IDEA.
  User: filkyr
  Date: 31.10.2018
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add or Edit product</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>
<body>
<%@ include file="templates/header.html"%>

<div class="container">
    <form method="POST" action='ProductController' name="frmAddProduct" role="form">
        <div class="form-group">
            <label for="productId">
                ID: <input class="form-control" type="number" id="productId" name="productId" readonly value=<c:out value="${product.productId}" /> />
            </label>
        </div>
        <div class="form-group">
            <label for="title">
                Title:<input class="form-control" type="text" id="title" name="title" value="<c:out value="${product.title}" />" />
            </label>
        </div>
        <div class="form-group">
            <label for="categoryId">
                Category: <input class="form-control" type="number" id="categoryId" name="categoryId" value="<c:out value="${product.categoryId}" />" />
            </label>
        </div>
        <div class="form-group">
            <label for="producerId">
                Producer: <input class="form-control" type="number" id="producerId" name="producerId" value="<c:out value="${product.producerId}" />" />
            </label>
        </div>
        <div class="form-group">
            <label for="image">
                Image: <input class="form-control" type="text" id="image" name="image" value="<c:out value="${product.image}" />" />
            </label>
        </div>
        <div class="form-group">
            <label for="description">
                Description: <input class="form-control" type="text" id="description" name="description" value="<c:out value="${product.description}" />" />
            </label>
        </div>
        <input type="submit" value="Submit" class="btn btn-outline-success" />
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>
