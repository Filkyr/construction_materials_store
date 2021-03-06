<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: filkyr
  Date: 02.11.2018
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>

<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
    <title>Orders</title>

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
            <table class="table table-striped" style="table-layout: fixed">
                <thead>
                <tr>
                    <th>
                        Order ID
                    </th>
                    <th>
                        Customer ID
                    </th>
                    <th>
                        Date
                    </th>
                    <th>
                        Product
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orders}" var="order">
                    <c:choose>
                        <c:when test="${order.products.isEmpty()}">
                            <tr>
                                <td class="overflowHidden">
                                    <c:out value="${order.id}"/>
                                </td>
                                <td class="overflowHidden">
                                    <c:out value="${order.customerId}"/>
                                </td>
                                <td class="overflowHidden">
                                    <c:out value="${order.date}"/>
                                </td>
                                <td class="overflowHidden">

                                </td>
                                <td td class="overflowHidden">
                                    <a href="${pageContext.request.contextPath}/order-<c:out value="${order.id}"/>/delete/product-<c:forEach items="${order.products}" var="item">${item.getProductId()}</c:forEach>" onclick="submitAsPost(this.href); return false;">Delete</a>
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${order.products}" var="item">
                                <tr>
                                    <td class="overflowHidden">
                                        <c:out value="${order.id}"/>
                                    </td>
                                    <td class="overflowHidden">
                                        <c:out value="${order.customerId}"/>
                                    </td>
                                    <td class="overflowHidden">
                                        <c:out value="${order.date}"/>
                                    </td>
                                    <td class="overflowHidden">
                                            ${item.getProductId()}
                                    </td>
                                    <td class="overflowHidden">
                                        <a href="/order-<c:out value="${order.id}"/>/delete/product-${item.getProductId()}"
                                           onclick="submitAsPost(this.href); return false;">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
                </tbody>
            </table>
            <a href="/order/insert" class="btn btn-outline-primary" role="button">Add new order</a>
            <a href="/order/product/insert" class="btn btn-outline-primary" role="button">Add product to
                order</a>

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
