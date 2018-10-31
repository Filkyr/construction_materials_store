<%--
  Created by IntelliJ IDEA.
  User: filkyr
  Date: 31.10.2018
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add or Edit producer</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <form method="POST" action='ProducerController' name="frmAddProducer" role="form">
        <div class="form-group">
            <label for="producerId">
                ID: <input class="form-control" type="number" id="producerId" name="producerId" readonly value=<c:out value="${producer.producerId}" /> />
            </label>
        </div>
        <div class="form-group">
            <label for="brand_name">
                Brand name:<input class="form-control" type="text" id="brand_name" name="brand_name" value="<c:out value="${producer.brand_name}" />" />
            </label>
        </div>
        <div class="form-group">
            <label for="description">
                Description: <input class="form-control" type="text" id="description" name="description" value="<c:out value="${producer.description}" />" />
            </label>
        </div>
        <div class="form-group">
            <label for="description">
                Logo: <input class="form-control" type="text" id="logo" name="logo" value="<c:out value="${producer.logo}" />" />
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