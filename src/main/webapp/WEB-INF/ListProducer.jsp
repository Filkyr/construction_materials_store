<%--
  Created by IntelliJ IDEA.
  User: filkyr
  Date: 31.10.2018
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>

<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
    <title>Producers</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>
<body>
<%@ include file="../templates/header.html"%>
<div class="wrap">
    <section>
        <div class="container">
            <table class="table table-striped"  style="table-layout: fixed">
                <thead>
                <tr>
                    <th>
                        ID
                    </th>
                    <th>
                        Brand name
                    </th>
                    <th style="width: 45%">
                        Description
                    </th>
                    <th>
                        Logo
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${producers}" var="producer">
                    <tr>
                        <td class="overflowHidden">
                            <c:out value="${producer.producerId}"/>
                        </td>
                        <td class="overflowHidden">
                            <c:out value="${producer.brand_name}"/>
                        </td>
                        <td class="overflowHidden">
                            <c:out value="${producer.description}"/>
                        </td>
                        <td class="overflowHidden">
                            <%--<c:out value="${producer.logo}"/>--%>
                            <img src="${producer.logo}" class="img-thumbnail" alt="Brand logo">
                        </td>
                        <td td class="overflowHidden">
                            <a href="ProducerController?action=edit&producerId=<c:out value="${producer.producerId}"/>">Update</a>
                        </td>
                        <td td class="overflowHidden">
                            <a href="ProducerController?action=delete&producerId=<c:out value="${producer.producerId}"/>">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a href="ProducerController?action=insert" class="btn btn-outline-primary" role="button">Add new</a>

        </div>
    </section>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>

</body>
</html>
