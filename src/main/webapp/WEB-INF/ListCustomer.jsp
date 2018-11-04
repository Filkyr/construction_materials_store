<%--
  Created by IntelliJ IDEA.
  User: filkyr
  Date: 01.11.2018
  Time: 15:55
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
    <title>Customers</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>
<body>
<%@ include file="../templates/header.html" %>

<div class="wrap">
    <section>
        <div class="container">
            <table class="table table-striped" style="table-layout: fixed">
                <thead>
                <tr>
                    <th>
                        ID
                    </th>
                    <th>
                        First name
                    </th>
                    <th>
                        Last name
                    </th>
                    <th>
                        Phone num
                    </th>
                    <th>
                        Address
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${customers}" var="customer">
                    <tr>
                        <td class="overflowHidden">
                            <c:out value="${customer.id}"/>
                        </td>
                        <td class="overflowHidden">
                            <c:out value="${customer.firstName}"/>
                        </td>
                        <td class="overflowHidden">
                            <c:out value="${customer.lastName}"/>
                        </td>
                        <td class="overflowHidden">
                            <c:out value="${customer.phoneNum}"/>
                        </td>
                        <td class="overflowHidden">
                            <c:out value="${customer.address}"/>
                        </td>
                        <td td class="overflowHidden">
                            <a href="CustomerController?action=edit&id=<c:out value="${customer.id}"/>">Update</a>
                        </td>
                        <td td class="overflowHidden">
                            <a href="CustomerController?action=delete&id=<c:out value="${customer.id}"/>">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a href="CustomerController?action=insert" class="btn btn-outline-primary" role="button">Add new</a>

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

