<%--
  Created by IntelliJ IDEA.
  User: filkyr
  Date: 30.10.2018
  Time: 20:59
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
    <title>Categories</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script type="text/javascript">
        function submitAsPost(url) {
            var postForm = document.createElement('form');
            postForm.action = url;
            postForm.method = 'post';
            var bodyTag = document.getElementsByTagName('body')[0];
            bodyTag.appendChild(postForm);
            postForm.submit();
        }
    </script>
</head>
<body>
<%@ include file="../../templates/header.html" %>
<div class="wrap">
    <section>
        <div class="container">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>
                        ID
                    </th>
                    <th>
                        Title
                    </th>
                    <th>
                        Description
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${categories}" var="category">
                    <tr>
                        <td>
                            <c:out value="${category.categoryId}"/>
                        </td>
                        <td>
                            <c:out value="${category.title}"/>
                        </td>
                        <td>
                            <c:out value="${category.description}"/>
                        </td>
                        <td>
                            <a href="/category/edit/<c:out value="${category.categoryId}"/>">Update</a>
                        </td>
                        <td>
                            <a href="/category/delete/<c:out value="${category.categoryId}"/>" onclick="submitAsPost(this.href); return false;">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a href="/category/insert" class="btn btn-outline-primary" role="button">Add new</a>

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